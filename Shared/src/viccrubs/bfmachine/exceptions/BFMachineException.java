package viccrubs.bfmachine.exceptions;

import viccrubs.bfmachine.BFMachineStates;

/**
 * Created by viccrubs on 2017/5/3.
 */
public class BFMachineException extends Exception{
    protected BFMachineStates machine;

    protected final String exceptionName = this.getClass().getName();

    public BFMachineException(BFMachineStates machine){
        this.machine = machine;
    }

    protected String position(){
        return String.format("at line %d char %d", machine.lineCounter, machine.charCounter);
    }

    @Override
    public String toString(){
        return String.format("%s occurred when processing \"%c\" %s.", exceptionName, machine.instruction, position());
    }
}
