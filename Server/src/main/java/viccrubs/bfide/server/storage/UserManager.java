package viccrubs.bfide.server.storage;

import viccrubs.bfide.models.User;
import viccrubs.bfide.server.Utils;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Created by viccrubs on 2017/5/10.
 */
public class UserManager {

    private File userDirectory;

    public UserManager(User user){
        try {
            userDirectory = new File(Utils.getFileUri(getUserDirectory(user)));
            if (!userDirectory.exists()){
                userDirectory.mkdir();
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }


    public static String getUserDirectory(User user){
        return  Utils.pathCombine(Configurations.rootUserDirectory, user.uuid);
    }

    public String[] getAllProjectNames(){
        return Arrays.stream(userDirectory.listFiles()).filter(File::isDirectory).map(File::getName).toArray(String[]::new);
    }

    public String[] getAllVersionsOfAProject(String projectName){
        File projectFolder = new File(Utils.pathCombine(userDirectory.getAbsolutePath(), projectName));
        if (!projectFolder.exists()){
            return null;
        }
        return Arrays.stream(projectFolder.listFiles()).map(File::getName).toArray(String[]::new);
    }




}
