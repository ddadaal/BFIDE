package viccrubs.bfide.bfmachine.exception;

import viccrubs.bfide.bfmachine.BFMachineStates;

import java.io.IOException;

/**
 * Created by viccrubs on 2017/5/4.
 */
public class MachineInternalException extends BFMachineException{
    private Exception e;
    public MachineInternalException(BFMachineStates states, Exception e) {
        super(states);
        this.e = e;
    }
    @Override
    public String description(){
        return "Internal Exception";
    }
}
