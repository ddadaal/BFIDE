package viccrubs.bfide.bfmachine.exception;

import viccrubs.bfide.bfmachine.BFMachineStates;

/**
 * Created by viccrubs on 2017/5/3.
 */
public class LoopStackEmptyException extends BFMachineException{
    public LoopStackEmptyException(BFMachineStates machine){
        super(machine);
    }
    @Override
    public String description(){
        return "A start of loop without a corresponding end of loop is detected.";
    }
}
