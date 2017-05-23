package viccrubs.bfide.models.requests;

/**
 * Created by viccrubs on 2017/5/11.
 */
public class RegisterRequest extends Request {
    public final String username;

    public final String password;

    public RegisterRequest(String username, String password) {
        this.username = username;
        this.password = password;
        this.type = RequestType.Register;
    }
}
