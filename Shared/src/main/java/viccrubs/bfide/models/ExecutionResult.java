package viccrubs.bfide.models;

import viccrubs.bfide.bfmachine.BFMachineStates;
import viccrubs.bfide.bfmachine.exceptions.BFMachineException;

/**
 * Created by viccrubs on 2017/5/8.
 */
public class ExecutionResult {
    public BFMachineException exception;
    public BFMachineStates states;
}
