package viccrubs.bfide.bfmachine.exceptions;

import viccrubs.bfide.bfmachine.BFMachineStates;

/**
 * Created by viccrubs on 2017/5/3.
 */

public class UnknownInstructionException extends BFMachineException {

    public UnknownInstructionException(BFMachineStates machine){
        super(machine);
    }
    @Override
    public String toString(){
        return String.format("Unknown instruction \"%s\" %s.",machine.instruction, position());
    }

}
