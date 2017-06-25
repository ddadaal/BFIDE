package viccrubs.bfide.bfmachine.exception;

import viccrubs.bfide.bfmachine.BFMachineStates;

/**
 * Created by viccrubs on 2017/5/3.
 */
public class DataArrayOutOfIndexException extends BFMachineException {
    public DataArrayOutOfIndexException(BFMachineStates machine){
        super(machine);
    }

    @Override
    public String description(){
        return "Data Array out of index. Currently only 256 bytes are supported.";
    }
}
