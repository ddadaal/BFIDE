package viccrubs.bfide.bfmachine.exceptions;

import viccrubs.bfide.bfmachine.BFMachineStates;

/**
 * Created by viccrubs on 2017/5/3.
 */
public class CycleStackEmptyException extends BFMachineException{
    public CycleStackEmptyException(BFMachineStates machine){
        super(machine);
    }
}
