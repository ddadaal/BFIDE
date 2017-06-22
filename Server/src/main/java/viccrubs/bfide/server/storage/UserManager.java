package viccrubs.bfide.server.storage;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import viccrubs.bfide.model.User;
import viccrubs.bfide.server.Utils;
import viccrubs.bfide.server.storage.Configurations;
import viccrubs.bfide.server.storage.exception.UserExistsException;
import viccrubs.bfide.util.ConfiguredGson;

import java.io.*;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.net.URL;
import java.util.stream.Stream;

/**
 * Created by viccrubs on 2017/5/10.
 */
public class UserManager {


    public User[] getUsers(){
        URL credentialFile = getClass().getResource(Utils.pathCombine(Configurations.rootAuthenticationDirectory, Configurations.credentialFileName));
        try {

            BufferedReader reader = new BufferedReader(new InputStreamReader(credentialFile.openStream()));

            Gson gson = new Gson();

            User[] users = gson.fromJson(reader, User[].class);
            reader.close();
            return users;


        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Optional<User> authenticate(String username, String password){
        for(User user: getUsers()){
            if (username.equals(user.username)){
                if (password.equals(user.password)){
                    return Optional.of(user);
                }else{
                    return Optional.empty();
                }
            }
        }
        return Optional.empty();
    }

    public User register(String username, String password) throws UserExistsException, IOException {
        if (usernameExists(username)){
            throw new UserExistsException();
        }
        User newUser = new User();
        newUser.username = username;
        newUser.password = password;
        Gson gson = ConfiguredGson.get();

        User[] newUsers = Utils.concat(getUsers(),new User[]{newUser});

        try {
            File file = new File(Utils.getFileUri(Utils.pathCombine(Configurations.rootAuthenticationDirectory, Configurations.credentialFileName)));
            FileWriter writer = new FileWriter(file);
            writer.write(gson.toJson(newUsers));
            writer.write(System.lineSeparator());
            writer.close();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        return newUser;

    }

    public boolean usernameExists(String username){

        return Arrays.stream(getUsers()).anyMatch(x->x.username.equals(username));
    }

}
