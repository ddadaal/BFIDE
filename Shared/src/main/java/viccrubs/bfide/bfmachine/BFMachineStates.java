package viccrubs.bfide.bfmachine;

import com.google.gson.annotations.Expose;

import java.util.Stack;

/**
 * Created by viccrubs on 2017/5/4.
 */
public class BFMachineStates {
    public int maxDataArrayLength = 256;
    public Instruction instruction;
    public int lineCounter;
    public int instructionCounter;
    public int programCounter =0;
    public byte[] array = new byte[maxDataArrayLength];
    public int arrayPointer =0;
    public Stack<Integer> cycleStack = new Stack<Integer>();
    public Program program;
    public String programInString;
    public String input;
    public StringBuilder output;
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

