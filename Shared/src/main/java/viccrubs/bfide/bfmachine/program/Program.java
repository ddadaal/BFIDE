package viccrubs.bfide.bfmachine.program;

import viccrubs.bfide.bfmachine.exception.UnknownInstructionException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Created by viccrubs on 2017/5/30.
 */
public class Program {
    private final Instruction[] program;
    private final ProgramLanguage language;
    private Translator translator;

    public Program(Instruction[] program, Translator translator) {
        this.translator = translator;
        this.program = program;
        this.language = translator.getLanguage();
    }




    @Override
    public String toString(){
        return Arrays.stream(program).map(x->translator.translate(x.instructionChar)).reduce("",(x,y)->x+y);
    }

    public Instruction instructionAt(int index){
        if (index>=program.length){
            return null;
        }else{
            return program[index];
        }
    }

    public int length(){
        return program.length;
    }

}