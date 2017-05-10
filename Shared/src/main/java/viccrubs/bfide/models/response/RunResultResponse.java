package viccrubs.bfide.models.response;

import com.google.gson.annotations.Expose;
import viccrubs.bfide.models.ExecutionResult;

/**
 * Created by viccrubs on 2017/5/10.
 */
public class RunResultResponse extends Response {
    @Expose
    public final ExecutionResult result;

    @Expose
    public final static String type="RUN_RESULT";

    public RunResultResponse(ExecutionResult result) {
        this.result = result;
    }
}
