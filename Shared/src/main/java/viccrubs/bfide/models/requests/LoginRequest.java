package viccrubs.bfide.models.requests;

import com.google.gson.annotations.Expose;

/**
 * Created by viccrubs on 2017/5/9.
 */
public class LoginRequest extends Request {

    public final String username;

    public final String password;


    public LoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
        this.type = RequestType.Login;
    }
}
