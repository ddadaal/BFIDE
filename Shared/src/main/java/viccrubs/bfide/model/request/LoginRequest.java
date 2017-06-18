package viccrubs.bfide.model.request;

/**
 * Created by viccrubs on 2017/5/9.
 */
public class LoginRequest extends Request {

    public final String username;

    public final String password;


    public LoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
