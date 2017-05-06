package viccrubs.bfmachine.exceptions;

import viccrubs.bfmachine.BFMachineStates;

/**
 * Created by viccrubs on 2017/5/3.
 */
public class CycleStackEmptyException extends BFMachineException{
    public CycleStackEmptyException(BFMachineStates machine){
        super(machine);
    }
}
