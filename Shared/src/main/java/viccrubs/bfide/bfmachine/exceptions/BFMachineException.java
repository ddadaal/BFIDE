package viccrubs.bfide.bfmachine.exceptions;

import com.google.gson.annotations.Expose;
import viccrubs.bfide.bfmachine.BFMachineStates;
/**
 * Created by viccrubs on 2017/5/3.
 */
public class BFMachineException extends Exception{
    protected BFMachineStates machine;

    @Expose
    public final String exceptionName = this.getClass().getSimpleName();

    public BFMachineException(BFMachineStates states){
        machine = states;
    }

    protected String position(){
        return String.format("at line %d char %d", machine.lineCounter, machine.instructionCounter);
    }

    @Override
    public String toString(){
        return String.format("%s occurred when processing \"%s\" %s.", exceptionName, machine.instruction.toString(), position());
    }
}
