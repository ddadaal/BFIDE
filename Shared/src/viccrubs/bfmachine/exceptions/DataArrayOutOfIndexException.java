package viccrubs.bfmachine.exceptions;

import viccrubs.bfmachine.BFMachineStates;

/**
 * Created by viccrubs on 2017/5/3.
 */
public class DataArrayOutOfIndexException extends BFMachineException {
    public DataArrayOutOfIndexException(BFMachineStates machine){
        super(machine);
    }
}
