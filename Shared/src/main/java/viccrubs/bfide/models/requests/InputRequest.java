package viccrubs.bfide.models.requests;

/**
 * Created by viccrubs on 2017/5/10.
 */
public class InputRequest extends Request {

    public final String input;

    public InputRequest(String input) {
        this.input = input;
        this.type = RequestType.Input;

    }
}
