package viccrubs.bfide.models.response;

import com.google.gson.annotations.Expose;
import viccrubs.bfide.models.User;

/**
 * Created by viccrubs on 2017/5/11.
 */
public class LoginResponse extends Response {

    public final boolean success;
    public final User user;

    public LoginResponse(boolean success, User user) {
        this.success = success;
        this.user = user;
    }

}
