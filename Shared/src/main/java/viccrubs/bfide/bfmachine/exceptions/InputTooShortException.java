package viccrubs.bfide.bfmachine.exceptions;

import viccrubs.bfide.bfmachine.BFMachineStates;

import java.io.IOException;

/**
 * Created by viccrubs on 2017/5/30.
 */
public class InputTooShortException extends BFMachineException {
    public InputTooShortException(BFMachineStates states) {
        super(states);
    }
}
