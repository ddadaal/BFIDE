package viccrubs.bfide.bfmachine.exception;

import viccrubs.bfide.bfmachine.BFMachineStates;

/**
 * Created by viccrubs on 2017/5/3.
 */
public class LoopStackEmptyException extends BFMachineException{
    public LoopStackEmptyException(BFMachineStates machine){
        super(machine);
    }
}
