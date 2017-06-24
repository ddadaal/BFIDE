package viccrubs.bfide.bfmachine.program;

/**
 * Created by viccrubs on 2017/5/30.
 */
public class Instruction {
    public final char instructionChar;

    private Instruction(char insInBF){
        instructionChar = insInBF;
    }

    public static Instruction of(char bfChar){
        if (bfChar==0){
            return null;
        }
        return new Instruction(bfChar);
    }

    @Override
    public String toString(){
        return instructionChar+"";
    }


}
