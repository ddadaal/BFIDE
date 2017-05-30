package viccrubs.bfide.models.response;

/**
 * Created by viccrubs on 2017/5/30.
 */
public class RequireLoginResponse extends Response {
    public RequireLoginResponse(){
        this.type = ResponseType.RequireLogin;
    }
}
