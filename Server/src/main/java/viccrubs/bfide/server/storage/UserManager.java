package viccrubs.bfide.server.storage;

import viccrubs.bfide.bfmachine.Program;
import viccrubs.bfide.bfmachine.ProgramLanguage;
import viccrubs.bfide.models.ProjectInfo;
import viccrubs.bfide.models.User;
import viccrubs.bfide.models.Version;
import viccrubs.bfide.server.Utils;
import viccrubs.bfide.utilities.DateUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * Created by viccrubs on 2017/5/10.
 */
public class UserManager {

    private String userDirectoryAbsolutePath;
    private File userDirectory;

    public UserManager(User user){
        this.userDirectoryAbsolutePath = getUserDirectory(user);
        userDirectory = new File(userDirectoryAbsolutePath);
        userDirectory.mkdirs();
    }


    public static String getUserDirectory(User user){
        return  Utils.pathCombine(Configurations.rootUserDirectoryAbsolutePath(), user.username);
    }

    public ProjectInfo[] getAllProjects(){
        return Arrays.stream(userDirectory.listFiles()).filter(File::isDirectory).map(File::getName).map(x->new ProjectInfo(
                x.split("\\.")[0],
                ProgramLanguage.valueOf(x.split("\\.")[1]),
                getAllVersionsOfAProject(x.split("\\.")[0]),
                getLatestVersionOfAProject(x.split("\\.")[0])
                )).toArray(ProjectInfo[]::new);
    }

    public Version[] getAllVersionsOfAProject(String projectName){
        File projectFolder = new File(getProjectPath(projectName));
        if (!projectFolder.exists()){
            return null;
        }
        return Arrays.stream(projectFolder.listFiles()).map(x->new Version(Long.parseLong(x.getName()))).toArray(Version[]::new);
    }

    public Version getLatestVersionOfAProject(String projectName) {
        return Arrays.stream(getAllVersionsOfAProject(projectName)).sorted(Comparator.comparingLong(x->-x.timeStamp)).findFirst().orElse(null);
    }

    public String getContentOfAVersion(ProjectInfo info, Version version){
        try {
            return Utils.downloadContentViaAbsolutePath(
                    Utils.pathCombine(getProjectPath(info.projectName), version.timeStamp+""));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ProjectInfo createNewProject(String projectName, ProgramLanguage language){
        if (projectExists(projectName)){
            return null;
        }
        File newProject = new File(Utils.pathCombine(userDirectory.getAbsolutePath(),projectName+"."+language.toString()));
        if (newProject.mkdirs()){
            createNewVersion(getProjectInfo(projectName),"",new Version(System.currentTimeMillis()));
            return getProjectInfo(projectName);
        }else{
            return null;
        }

    }

    public ProjectInfo getProjectInfo(String projectName){
        return Arrays.stream(getAllProjects()).filter(x->x.projectName.equals(projectName)).findFirst().orElse(null);
    }

    public Version createNewVersion(ProjectInfo info, String content, Version version){
        File newVersion = new File(Utils.pathCombine(userDirectory.getAbsolutePath(), getProjectFullName(info.projectName), String.valueOf(version.timeStamp)));
        try(FileWriter writer = new FileWriter(newVersion,false)){
            writer.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return version;
    }

    public String getProjectFullName(String projectName){
        File projectFolder1 = new File(Utils.pathCombine(userDirectory.getAbsolutePath(), projectName+".BF"));
        if (projectFolder1.exists()){
            return projectName+".BF";
        }
        File projectFolder2 = new File(Utils.pathCombine(userDirectory.getAbsolutePath(), projectName+".Ook"));
        if (projectFolder2.exists()){
            return projectName+".Ook";
        }
        return null;
    }

    public String getProjectPath(String projectName){
        if (!projectExists(projectName)){
            return null;
        }
        return Utils.pathCombine(userDirectoryAbsolutePath, getProjectFullName(projectName));
    }

    public boolean projectExists(String projectName){
        File projectFolder1 = new File(Utils.pathCombine(userDirectory.getAbsolutePath(), projectName+".BF"));
        File projectFolder2 = new File(Utils.pathCombine(userDirectory.getAbsolutePath(), projectName+".Ook"));
        return projectFolder1.exists() || projectFolder2.exists();
    }

}
