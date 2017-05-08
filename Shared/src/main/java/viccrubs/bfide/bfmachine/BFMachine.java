package viccrubs.bfide.bfmachine;

import viccrubs.bfide.bfmachine.exceptions.*;
import viccrubs.bfide.models.ExecutionResult;
import viccrubs.bfide.utilities.DynamicInStream;
import viccrubs.bfide.utilities.DynamicOutStream;

import java.io.IOException;

/**
 * Created by viccrubs on 2017/5/3.
 */
public class BFMachine {

    private BFMachineStates states = new BFMachineStates();

    private DynamicOutStream outStream;
    private DynamicInStream inStream;
    private long startTime ;

    private BFMachineException occurredException = null;

    public DynamicOutStream getOutStream() {
        return outStream;
    }

    public void setOutStream(DynamicOutStream outStream) {
        this.outStream = outStream;
    }

    public DynamicInStream getInStream() {
        return inStream;
    }

    public void setInStream(DynamicInStream inStream) {
        this.inStream = inStream;
    }

    public void loadStates (BFMachineStates states){
        if (states!=null){
            this.states = states;
        }

    }

    public BFMachineStates getStates(){
        return states;
    }

    public void resetStates(){
        this.states.reset();
    }



    public void loadProgram(String program){
        this.states.program = program.replace("\r\n","\n");
    }

    public ExecutionResult executeProgram() throws IOException, BFMachineException {
        occurredException = null;
        while (stepOver()){
            if (occurredException!=null){
                break;
            }
        }
        return constructResult(occurredException);

    }

    public ExecutionResult constructResult(BFMachineException exception){
        ExecutionResult result = new ExecutionResult();
        result.exception = exception;
        result.states = this.states;
        return result;
    }


    public BFMachine(DynamicOutStream outStream, DynamicInStream inStream){
        this.outStream = outStream;
        this.inStream = inStream;
    }

    public boolean stepOver() throws  BFMachineException {
        this.executeInstruction(this.states.program.charAt(this.states.programCounter));
        this.states.programCounter++;
        return this.states.programCounter < this.states.program.length();
    }


    public void executeInstruction(char instruction) {
        switch (instruction) {
            case ' ':
                break;
            case '\n':
                this.states.lineCounter++;
                this.states.charCounter=-1;
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
                try{
                    outStream.write((char) this.states.array[this.states.arrayPointer]);
                }catch(IOException e){
                    this.occurredException = new MachineIOException(states, e);
                }

                break;
            case ',':
                try {
                    this.states.array[this.states.arrayPointer] = (byte) inStream.read();
                }catch (IOException e) {
                    this.occurredException = new MachineIOException(states, e);
                }
                break;
            case '[':
                if (this.states.array[this.states.arrayPointer] == 0) {
                    int cycleCounter = 0;
                    for (this.states.programCounter++; this.states.programCounter < this.states.program.length(); this.states.programCounter++) {
                        if (this.states.program.charAt(this.states.programCounter) == '[') {
                            cycleCounter++;
                        } else if (this.states.program.charAt(this.states.programCounter) == ']') {
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
                    this.occurredException = new CycleStackEmptyException(states);
                }
                if (this.states.array[this.states.arrayPointer] != 0) {
                    this.states.programCounter = this.states.cycleStack.pop();
                }

                break;
            default:
                this.occurredException =  new UnknownInstructionException(states);
        }
    }



}
