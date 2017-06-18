package viccrubs.bfide.model.response;

import viccrubs.bfide.model.User;

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
