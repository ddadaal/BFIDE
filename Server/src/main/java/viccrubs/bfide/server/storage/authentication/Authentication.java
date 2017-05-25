package viccrubs.bfide.server.storage.authentication;

import com.google.gson.Gson;
import viccrubs.bfide.models.User;
import viccrubs.bfide.server.Utils;
import viccrubs.bfide.server.storage.Configurations;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Optional;
import java.io.IOException;
import java.net.URL;

/**
 * Created by viccrubs on 2017/5/10.
 */
public class Authentication {
    private User[] users;

    public Authentication(){
        updateUsers();


    }

    public User[] getUsers(){
        return users;
    }

    public void updateUsers(){
        URL credentialFile = getClass().getResource(Utils.pathCombine(Configurations.rootAuthenticationDirectory, Configurations.credentialFileName));
        try {

            BufferedReader reader = new BufferedReader(new InputStreamReader(credentialFile.openStream()));

            Gson gson = new Gson();

            users = gson.fromJson(reader, User[].class);
            reader.close();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Optional<User> authenticate(String username, String password){
        for(User user: users){
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

    public boolean usernameExists(String username){
        return Arrays.stream(users).anyMatch(x->x.username.equals(username));
    }

}
