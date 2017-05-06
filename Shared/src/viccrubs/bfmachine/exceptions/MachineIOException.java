package viccrubs.bfmachine.exceptions;

import viccrubs.bfmachine.BFMachineStates;

import java.io.IOException;

/**
 * Created by viccrubs on 2017/5/4.
 */
public class MachineIOException extends BFMachineException{
    private IOException e;
    public MachineIOException(BFMachineStates states, IOException e) {
        super(states);
        this.e = e;
    }
}
