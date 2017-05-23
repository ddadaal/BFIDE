package viccrubs.bfide.models.response;

/**
 * Created by viccrubs on 2017/5/11.
 */
public class RegisterResponse extends Response {

    public final boolean success;

    public RegisterResponse(boolean success){
        this.success= success;
        this.type = ResponseType.RegisterResponse;
    }
}
