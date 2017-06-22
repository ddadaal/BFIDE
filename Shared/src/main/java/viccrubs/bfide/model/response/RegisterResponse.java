package viccrubs.bfide.model.response;

/**
 * Created by viccrubs on 2017/5/11.
 */
public class RegisterResponse extends Response {

    public final boolean success;
    public final String description;

    public RegisterResponse(boolean success, String description){
        this.success= success;
        this.description = description;
    }
}
