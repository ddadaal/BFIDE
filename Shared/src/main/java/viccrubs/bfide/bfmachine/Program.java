package viccrubs.bfide.bfmachine;

import com.sun.org.apache.bcel.internal.generic.InstructionComparator;
import viccrubs.bfide.bfmachine.exceptions.OokUnknownInstructionException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

/**
 * Created by viccrubs on 2017/5/30.
 */
public class Program implements Iterable<Instruction>, Iterator<Instruction> {
    private final Instruction[] program;
    private final ProgramLanguage language;
    private int counter=0;

    public Program(Instruction[] program, ProgramLanguage language) {
        this.program = program;
        this.language = language;
    }

    public static Program translateBFProgram(String bfProgram){
        return new Program(bfProgram.chars().mapToObj(x->Instruction.of((char)x)).toArray(Instruction[]::new), ProgramLanguage.BF);
    }

    public static Program translateOokProgram(String ookProgram) {
        int ookInstructionLength = 8;
        ArrayList<Instruction> program = new ArrayList<>();
        ookProgram = ookProgram.replace(" ", "").replace("\n","");
        if (ookProgram.length()%ookInstructionLength!=0){
            return null;
        }
        for (int pointer = 0; pointer < ookProgram.length(); pointer += ookInstructionLength) {
            Instruction bf = Instruction.of(ookProgram.substring(pointer, pointer + ookInstructionLength));
            if (bf == null) {
                return null;
            }
            program.add(bf);
        }
        Instruction[] result = new Instruction[program.size()];
        program.toArray(result);
        return new Program(result, ProgramLanguage.Ook);
    }

    public static Program translateProgram(String program, ProgramLanguage language){
        if (language.equals(ProgramLanguage.BF)){
            return translateBFProgram(program);
        }else{
            return translateOokProgram(program);
        }
    }

    @Override
    public String toString(){
        return Arrays.stream(program).map(x->x.toString(language)).reduce("",(x,y)->x+y);
    }

    public String toString(ProgramLanguage language){
        return Arrays.stream(program).map(x->x.toString(language)).reduce("",(x,y)->x+y);
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

    @Override
    public Iterator<Instruction> iterator() {
        return this;
    }

    @Override
    public boolean hasNext() {
        return instructionAt(counter)!=null;
    }

    @Override
    public Instruction next() {
        return instructionAt(counter++);
    }
}