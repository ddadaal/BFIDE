package viccrubs.bfide.bfmachine.program;

import viccrubs.bfide.bfmachine.exception.UnknownInstructionException;

import java.util.Arrays;

/**
 * Created by viccrubs on 2017/6/24.
 */
public class BFTranslator extends Translator {

    public String allowedChars = "><+-,.[]";

    public BFTranslator(String program){
        setProgram(program);
    }

    @Override
    public char translate(String instruction) {

        char c= instruction.charAt(0);
        if (!allowedChars.contains(c+"")){
            return 0;
        }
        return c;
    }

    @Override
    public String translate(char bf) {
        return bf+"";
    }

    @Override
    public ProgramLanguage getLanguage() {
        return ProgramLanguage.BF;
    }

    @Override
    public boolean hasNext() {
        return pointer<=program.length()-1;
    }

    @Override
    public String next() {
        return program.charAt(pointer++)+"";
    }
}
