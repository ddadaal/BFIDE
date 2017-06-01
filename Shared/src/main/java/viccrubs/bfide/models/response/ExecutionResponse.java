package viccrubs.bfide.models.response;

import com.google.gson.annotations.Expose;
import viccrubs.bfide.models.ExecutionResult;

/**
 * Created by viccrubs on 2017/5/10.
 */
public class ExecutionResponse extends Response {
    @Expose
    public final ExecutionResult result;


    public ExecutionResponse(ExecutionResult result) {
        this.result = result;
    }
}
