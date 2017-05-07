package viccrubs.bfmachine;

import viccrubs.bfmachine.exceptions.BFMachineException;
import viccrubs.bfmachine.exceptions.CycleStackEmptyException;
import viccrubs.bfmachine.exceptions.MachineIOException;
import viccrubs.bfmachine.exceptions.UnknownInstructionException;
import viccrubs.models.ExecutionResult;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.time.Clock;
import java.util.Stack;
import java.util.UUID;

/**
 * Created by viccrubs on 2017/5/3.
 */
public class BFMachine {

    private BFMachineStates states = new BFMachineStates();

    private PrintStream outStream;
    private InputStream inStream;
    private long startTime ;

    private BFMachineException occurredException = null;

    public PrintStream getOutStream() {
        return outStream;
    }

    public void setOutStream(PrintStream outStream) {
        this.outStream = outStream;
    }

    public InputStream getInStream() {
        return inStream;
    }

    public void setInStream(InputStream inStream) {
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
        startTime = Clock.systemUTC().millis();
        while (stepOver()){
            if (occurredException!=null){
                break;
            }
        }
        long endTime = Clock.systemUTC().millis();
        return constructResult(startTime, endTime, occurredException);

    }

    public ExecutionResult constructResult(long startTime, long endTime,BFMachineException exception){
        ExecutionResult result = new ExecutionResult();
        result.startTime = startTime;
        result.endTime = endTime;
        result.exception = exception;
        result.machineStates = this.states;
        return result;
    }


    public BFMachine(PrintStream outStream, InputStream inStream){
        this.outStream = outStream;
        this.inStream = inStream;
    }

    public boolean stepOver() throws IOException, BFMachineException {
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
                outStream.print((char) this.states.array[this.states.arrayPointer]);
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
