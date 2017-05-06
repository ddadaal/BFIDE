package viccrubs.bfmachine.exceptions;

import viccrubs.bfmachine.BFMachineStates;

/**
 * Created by viccrubs on 2017/5/3.
 */

public class UnknownInstructionException extends BFMachineException {

    public UnknownInstructionException(BFMachineStates machine){
        super(machine);
    }
    @Override
    public String toString(){
        return String.format("Unknown instruction \"%c\" %s.",machine.instruction, position());
    }

}
