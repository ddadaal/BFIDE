package viccrubs.bfide.bfmachine.exception;

import viccrubs.bfide.bfmachine.BFMachineStates;
/**
 * Created by viccrubs on 2017/5/3.
 */
public class BFMachineException extends Exception{
    public final BFMachineStates states;

    public final String type = getClass().getSimpleName();

    public BFMachineException(BFMachineStates states){
        this.states = states;
    }


    public String description(){
        return "Base Machine Exception";
    }

    @Override
    public String toString(){
        if (states!=null){
            return String.format("%s occurred when processing \"%s\".", type, states.instruction.toString());
        }
        return super.toString();
    }
}
