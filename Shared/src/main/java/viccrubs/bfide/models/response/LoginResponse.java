package viccrubs.bfide.models.response;

import com.google.gson.annotations.Expose;

/**
 * Created by viccrubs on 2017/5/11.
 */
public class LoginResponse extends Response {

    public final boolean success;

    public LoginResponse(boolean success) {
        this.success = success;
        this.type = ResponseType.LoginResponse;
    }

}
