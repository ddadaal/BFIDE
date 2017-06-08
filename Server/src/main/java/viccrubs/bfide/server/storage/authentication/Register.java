package viccrubs.bfide.server.storage.authentication;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import viccrubs.bfide.models.ConfiguredGson;
import viccrubs.bfide.models.User;
import viccrubs.bfide.server.BFIDEServer;
import viccrubs.bfide.server.Utils;
import viccrubs.bfide.server.storage.Configurations;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

/**
 * Created by viccrubs on 2017/5/11.
 */
public class Register {
    public static Optional<User> register(String username, String password) throws IOException {
        Authentication auth = new Authentication();
        if (auth.usernameExists(username)){
            return Optional.empty();
        }

        User newUser = new User();
        newUser.username = username;
        newUser.password = password;

        User[] newUsers = Stream.concat(Arrays.stream(auth.getUsers()), Stream.of(newUser)).toArray(User[]::new);

        Gson gson = ConfiguredGson.get();
        String newContent = gson.toJson(newUsers);

        try {
            File file = new File(Utils.getFileUri(Utils.pathCombine(Configurations.rootAuthenticationDirectory, Configurations.credentialFileName)));
            FileWriter writer = new FileWriter(file);
            writer.write(newContent);
            writer.close();
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return Optional.empty();
        }

        return Optional.of(newUser);


    }
}
