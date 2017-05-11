package viccrubs.bfide.bfmachine.exceptions;

import viccrubs.bfide.bfmachine.BFMachineStates;

/**
 * Created by viccrubs on 2017/5/11.
 */
public class OokUnknownInstructionException extends BFMachineException {

    public final String instruction;

    public OokUnknownInstructionException(BFMachineStates states, String instruction) {
        super(states);
        this.instruction = instruction;
    }
}
