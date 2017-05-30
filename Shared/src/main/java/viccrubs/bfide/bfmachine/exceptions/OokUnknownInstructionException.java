package viccrubs.bfide.bfmachine.exceptions;

import viccrubs.bfide.bfmachine.BFMachineStates;

/**
 * Created by viccrubs on 2017/5/11.
 */
public class OokUnknownInstructionException extends Exception{

    public final String instruction;

    public OokUnknownInstructionException(String instruction) {
        this.instruction = instruction;
    }
}
