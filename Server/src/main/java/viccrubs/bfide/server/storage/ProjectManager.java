package viccrubs.bfide.server.storage;

import viccrubs.bfide.bfmachine.program.ProgramLanguage;
import viccrubs.bfide.model.ProjectInfo;
import viccrubs.bfide.model.User;
import viccrubs.bfide.model.Version;
import viccrubs.bfide.server.Utils;
import viccrubs.bfide.server.storage.exception.ProjectExistsException;
import viccrubs.bfide.server.storage.exception.ProjectNotExistException;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by viccrubs on 2017/5/10.
 */
public class ProjectManager {

    private String userDirectoryAbsolutePath;
    private File userDirectory;

    public ProjectManager(User user){
        this.userDirectoryAbsolutePath = getUserDirectory(user);
        userDirectory = new File(userDirectoryAbsolutePath);
        userDirectory.mkdirs();
    }

    public ProjectInfo[] getAllProjects(){
        return Arrays.stream(userDirectory.listFiles()).filter(File::isDirectory).map(File::getName)
                .map(x->x.split("\\.")[0])
                .map(x -> {
                    try {
                        return getProjectInfo(x);
                    } catch (ProjectNotExistException e) {
                        e.printStackTrace();
                        return null;
                    }
                })
                .toArray(ProjectInfo[]::new);
    }


    public static String getUserDirectory(User user){
        return  Utils.pathCombine(Configurations.rootUserDirectoryAbsolutePath(), user.username);
    }


    public Version[] getAllVersionsOfAProject(String projectName) throws ProjectNotExistException {
        File projectFolder = new File(getProjectPath(projectName));
        return Arrays.stream(projectFolder.listFiles()).map(x->new Version(Long.parseLong(x.getName()))).sorted(Comparator.comparingLong(x->-x.timeStamp)).toArray(Version[]::new);
    }

    public String getContentOfAVersion(ProjectInfo info, Version version) throws ProjectNotExistException {
        try {
            return Utils.downloadContentViaAbsolutePath(
                    Utils.pathCombine(getProjectPath(info.projectName), version.timeStamp+""));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ProjectInfo createNewProject(String projectName, ProgramLanguage language) throws ProjectExistsException {
        if (projectExists(projectName)) {
            throw new ProjectExistsException();
        }
        File newProject = new File(Utils.pathCombine(userDirectory.getAbsolutePath(), projectName + "." + language.toString()));

        Version firstVersion = new Version(System.currentTimeMillis());
        ProjectInfo newInfo = new ProjectInfo(projectName,language,new Version[]{firstVersion});

        if (newProject.mkdirs()) {
            try {
                createNewVersion(newInfo, "", firstVersion);
                return newInfo;
            } catch (ProjectNotExistException | IOException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            return null;
        }
    }

    public boolean deleteProject(ProjectInfo projectInfo) throws ProjectNotExistException{
        File path = new File(getProjectPath(projectInfo.projectName));
        return Arrays.stream(path.listFiles()).map(File::delete).reduce(true, (x, y) -> x && y) && path.delete();
    }

    public ProjectInfo getProjectInfo(String projectName) throws ProjectNotExistException {
        String[] splitFileName = getProjectFullName(projectName).split("\\.");
        return new ProjectInfo(projectName,
                ProgramLanguage.valueOf(splitFileName[splitFileName.length-1]),
                getAllVersionsOfAProject(projectName));
    }

    public Version createNewVersion(ProjectInfo info, String content, Version version) throws ProjectNotExistException, IOException {
        if (info == null || !projectExists(info.projectName)){
            throw new ProjectNotExistException();
        }

        File file = new File(Utils.pathCombine(userDirectory.getAbsolutePath(), getProjectFullName(info.projectName), String.valueOf(version.timeStamp)));
        if (file.exists()){
            return null;
        }
        if (!file.createNewFile()){
            throw new IOException();
        }
        FileWriter writer = new FileWriter(file);
        writer.write(content);
        writer.close();
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

    public String getProjectPath(String projectName) throws ProjectNotExistException{
        if (!projectExists(projectName)){
            throw new ProjectNotExistException();
        }
        return Utils.pathCombine(userDirectoryAbsolutePath, getProjectFullName(projectName));
    }

    public boolean projectExists(String projectName){
        File projectFolder1 = new File(Utils.pathCombine(userDirectory.getAbsolutePath(), projectName+".BF"));
        File projectFolder2 = new File(Utils.pathCombine(userDirectory.getAbsolutePath(), projectName+".Ook"));
        return projectFolder1.exists() || projectFolder2.exists();
    }

}
