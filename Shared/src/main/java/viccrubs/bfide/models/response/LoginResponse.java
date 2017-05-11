package viccrubs.bfide.models.response;

import com.google.gson.annotations.Expose;

/**
 * Created by viccrubs on 2017/5/11.
 */
public class LoginResponse {
    @Expose
    public static final String type="LOGIN_RESPONSE";

    @Expose
    public final boolean success;



    public LoginResponse(boolean success) {
        this.success = success;

    }

}
