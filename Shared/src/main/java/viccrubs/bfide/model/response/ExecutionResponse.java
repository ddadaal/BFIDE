package viccrubs.bfide.model.response;

import viccrubs.bfide.model.ExecutionResult;

/**
 * Created by viccrubs on 2017/5/10.
 */
public class ExecutionResponse extends Response {
    public final ExecutionResult result;


    public ExecutionResponse(ExecutionResult result) {
        this.result = result;
    }
}
