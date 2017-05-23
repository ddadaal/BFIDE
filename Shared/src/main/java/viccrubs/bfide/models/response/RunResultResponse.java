package viccrubs.bfide.models.response;

import com.google.gson.annotations.Expose;
import viccrubs.bfide.models.ExecutionResult;

/**
 * Created by viccrubs on 2017/5/10.
 */
public class RunResultResponse extends Response {
    public final ExecutionResult result;


    public RunResultResponse(ExecutionResult result) {
        this.type = ResponseType.RunResult;
        this.result = result;
    }
}
