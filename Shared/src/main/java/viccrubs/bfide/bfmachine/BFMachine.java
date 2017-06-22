package viccrubs.bfide.bfmachine;

import viccrubs.bfide.bfmachine.exception.*;
import viccrubs.bfide.model.ExecutionResult;

/**
 * Created by viccrubs on 2017/5/3.
 */
public class BFMachine {

    private BFMachineStates states = new BFMachineStates();
    private long startTime;

    private BFMachineException occurredException = null;

    public BFMachineStates getStates(){
        return states;
    }

    public void resetStates(){
        this.states.reset();
        occurredException = null;
    }

    public ExecutionResult execute(Program program, String input){
        resetStates();
        states.setProgram(program);
        states.input = input==null ?"" :input;
        long startTime = System.currentTimeMillis();
        while(stepOver()){
            if (occurredException!=null){
                long endTime = System.currentTimeMillis();
                return new ExecutionResult(states.output.toString(), occurredException, endTime - startTime );
            }
        }
        long endTime = System.currentTimeMillis();
        return new ExecutionResult(states.output.toString(), occurredException, endTime - startTime );
    }


    public boolean stepOver() {
        this.executeInstruction(this.states.program.instructionAt(this.states.programCounter));
        this.states.programCounter++;
        return this.states.programCounter < this.states.program.length();
    }

    public void executeInstruction(Instruction instruction) {
        switch (instruction.instructionChar) {
            case ' ':
                break;
            case '\n':
                this.states.lineCounter++;
                this.states.instructionCounter=-1;
                break;
            case '>':
                this.states.arrayPointer++;
                if (this.states.arrayPointer==this.states.maxDataArrayLength){
                    this.states.arrayPointer =0;
                }
                break;
            case '<':
                if(this.states.arrayPointer==0){
                    this.states.arrayPointer = this.states.maxDataArrayLength;
                }
                this.states.arrayPointer--;
                break;
            case '+':
                this.states.array[this.states.arrayPointer]++;
                break;
            case '-':
                this.states.array[this.states.arrayPointer]--;
                break;
            case '.':
                this.states.output.append((char) this.states.array[this.states.arrayPointer]);
                break;
            case ',':
                if (this.states.inputReader >= this.states.input.length()){
                    this.occurredException = new InputTooShortException(this.states);
                }else{
                    this.states.array[this.states.arrayPointer] = (byte) this.states.input.charAt(this.states.inputReader++);
                }
                break;
            case '[':
                if (this.states.array[this.states.arrayPointer] == 0) {
                    int cycleCounter = 0;
                    for (this.states.programCounter++; this.states.programCounter < this.states.program.length(); this.states.programCounter++) {
                        if (this.states.program.instructionAt(this.states.programCounter).instructionChar == '[') {
                            cycleCounter++;
                        } else if (this.states.program.instructionAt(this.states.programCounter).instructionChar == ']') {
                            if (cycleCounter==0){
                                break;
                            }
                            cycleCounter--;
                        }
                    }
                } else {
                    this.states.cycleStack.push(this.states.programCounter - 1);
                }
                break;
            case ']':
                if (this.states.cycleStack.empty()){
                    this.occurredException = new LoopStackEmptyException(states);
                }
                if (this.states.array[this.states.arrayPointer] != 0) {
                    this.states.programCounter = this.states.cycleStack.pop();
                }

                break;
            default:

        }
    }



}
