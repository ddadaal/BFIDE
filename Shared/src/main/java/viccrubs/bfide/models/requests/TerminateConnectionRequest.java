package viccrubs.bfide.models.requests;

/**
 * Created by viccrubs on 2017/5/23.
 */
public class TerminateConnectionRequest extends Request {
    public TerminateConnectionRequest(){
        this.type = RequestType.TerminateConnection;
    }
}
