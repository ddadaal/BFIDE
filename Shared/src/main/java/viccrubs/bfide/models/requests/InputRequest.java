package viccrubs.bfide.models.requests;

import com.google.gson.annotations.Expose;

/**
 * Created by viccrubs on 2017/5/10.
 */
public class InputRequest extends Request {
    @Expose
    public final static String type="INPUT";

    @Expose
    public final String input;

    public InputRequest(String input) {
        this.input = input;

    }
}
