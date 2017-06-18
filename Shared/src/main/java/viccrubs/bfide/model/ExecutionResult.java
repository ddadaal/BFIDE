package viccrubs.bfide.model;

import com.google.gson.annotations.Expose;
import viccrubs.bfide.bfmachine.exception.BFMachineException;

/**
 * Created by viccrubs on 2017/5/8.
 */
public class ExecutionResult {
    public final BFMachineException exception;
    public final String output;
    public final long executionTime; // in milliseconds

    public ExecutionResult(String output, BFMachineException exception,  long executionTime) {
        this.exception = exception;
        this.output = output;
        this.executionTime = executionTime;
    }
}
