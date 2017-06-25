package viccrubs.bfide.model.response;

import viccrubs.bfide.model.ExecutionResult;

/**
 * Created by viccrubs on 2017/5/10.
 */
public class ExecuteProgramResponse extends Response {
    public final String output;
    public final String exception;
    public final String exceptionDescription;
    public final long executionTime;


    public ExecuteProgramResponse(ExecutionResult result) {
        this.output = result.output;
        this.exceptionDescription = result.exception !=null ?result.exception.description() :null;
        this.exception = result.exception != null ? result.exception.type : null;
        this.executionTime = result.executionTime;
    }
}
