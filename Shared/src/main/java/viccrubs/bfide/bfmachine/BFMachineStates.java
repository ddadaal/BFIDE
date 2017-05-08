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
    public char instruction=0;
    @Expose
    public int programCounter =0;

    @Expose
    public int charCounter=0;
    @Expose
    public int lineCounter=0;
    @Expose
    public byte[] array = new byte[maxDataArrayLength];
    @Expose
    public int arrayPointer =0;
    @Expose
    public Stack<Integer> cycleStack = new Stack<Integer>();
    @Expose
    public String program;


    public void reset(){
        instruction=0;
        arrayPointer = 0;
        for(int i=0;i<maxDataArrayLength;i++){
            array[i] = 0;
        }
        cycleStack.clear();
        programCounter = 0;
    }

}

