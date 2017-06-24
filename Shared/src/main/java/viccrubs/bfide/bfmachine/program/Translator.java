package viccrubs.bfide.bfmachine.program;

import viccrubs.bfide.bfmachine.exception.UnknownInstructionException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by viccrubs on 2017/6/24.
 */



public abstract class Translator implements Iterator<String>, Iterable<String> {

    protected String program;
    protected int pointer=0;

    public void setProgram(String program){
        if (program!=null){
            this.program= program;
        }
    }

    public Program compile() throws UnknownInstructionException {
        List<Instruction> result = new ArrayList<>();
        for(String s: this){
            Instruction ins = Instruction.of(translate(s));
            if (ins==null){
                throw new UnknownInstructionException(s);
            }
            result.add(ins);
        }

        if (result.size()==0){
            throw new UnknownInstructionException(program);
        }
        return new Program(result.toArray(new Instruction[result.size()]),this);
    }

    @Override
    public Iterator<String> iterator(){
        return this;
    }

    /**
     * Translate one single instruction to Brainfuck char.
     * Non supported char should be noted as 0.
     * @param instruction instruction in this language
     * @return a Brainfuck char
     */
    abstract char translate(String instruction);

    /**
     * Translate Brainfuck char into this language
     * Non supported bf char should be noted as ???
     * @param bf instruction in bf
     * @return instruction your language
     */
    abstract String translate(char bf);

    abstract ProgramLanguage getLanguage();
}
