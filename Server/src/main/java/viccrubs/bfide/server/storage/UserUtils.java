package viccrubs.bfide.server.storage;

import viccrubs.bfide.models.User;
import viccrubs.bfide.server.Utils;

import java.net.URL;

/**
 * Created by viccrubs on 2017/5/10.
 */
public class UserUtils {
    public static String getUserDirectory(User user){
        return Utils.pathCombine(Configurations.rootUserDirectory, user.uuid);
    }

}
