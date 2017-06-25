package viccrubs.bfide.bfmachine.exception;

/**
 * Created by viccrubs on 2017/5/11.
 */
public class UnknownInstructionException extends BFMachineException{

    public final String instruction;

    public UnknownInstructionException(String instruction) {
        super(null);
        this.instruction = instruction;
    }

    @Override
    public String description(){
        return "Unknown instruction "+instruction+".";
    }
}
