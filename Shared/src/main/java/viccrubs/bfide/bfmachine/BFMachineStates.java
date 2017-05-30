package viccrubs.bfide.bfmachine;

import com.google.gson.annotations.Expose;

import java.util.Stack;

/**
 * Created by viccrubs on 2017/5/4.
 */
public class BFMachineStates {
    @Expose
    public int maxDataArrayLength = 256;
    @Expose
    public Instruction instruction;
    @Expose
    public int lineCounter;
    @Expose
    public int instructionCounter;
    @Expose
    public int programCounter =0;
    @Expose
    public byte[] array = new byte[maxDataArrayLength];
    @Expose
    public int arrayPointer =0;
    @Expose
    public Stack<Integer> cycleStack = new Stack<Integer>();
    public Program program;
    @Expose
    public String programInString;
    @Expose
    public String input;
    @Expose
    public StringBuilder output;
    @Expose
    public int inputReader;


    public void setProgram(Program program){
        this.program = program;
        this.programInString = program.toString();
    }

    public void reset(){
        instruction=null;
        arrayPointer = 0;
        for(int i=0;i<maxDataArrayLength;i++){
            array[i] = 0;
        }
        cycleStack.clear();
        programCounter = 0;
        input = "";
        output = new StringBuilder();
        inputReader = 0;
    }

}

