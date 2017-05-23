package viccrubs.bfide.models.response;

import viccrubs.bfide.models.requests.TestConnectionRequest;

/**
 * Created by viccrubs on 2017/5/22.
 */
public class TestConnectionResponse extends Response {
    public TestConnectionResponse(){
        this.type = ResponseType.TestConnection;
    }
}
