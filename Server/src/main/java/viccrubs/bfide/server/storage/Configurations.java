package viccrubs.bfide.server.storage;

import viccrubs.bfide.server.BFIDEServer;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Created by viccrubs on 2017/5/10.
 */
public class Configurations {
    public final static String rootUserDirectory = "/users";
    public final static String rootAuthenticationDirectory = "/authentication";
    public final static String credentialFileName = "credentials.json";
    public static String rootUserDirectoryAbsolutePath(){
        return BFIDEServer.class.getResource(rootUserDirectory).getPath().substring(1);
    }
}


/*file structure
authentication
    credentials.json   //save all the usernames and passwords
users
    {user's uuid} //123
        {filename.ext}  //1.bf
            {time of version} //20170601152004


*/