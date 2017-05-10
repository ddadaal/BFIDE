package viccrubs.bfide.bfmachine;

import viccrubs.bfide.bfmachine.exceptions.BFMachineException;
import viccrubs.bfide.models.ExecutionResult;

/**
 * Created by viccrubs on 2017/5/10.
 */
public interface IMachine {

    ExecutionResult executeProgram(String program) throws BFMachineException;

}
