package viccrubs.bfmachine;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Stack;
import java.util.UUID;

/**
 * Created by viccrubs on 2017/5/4.
 */
public class BFMachineStates {
    private String machineGUID = UUID.randomUUID().toString();
    public int maxDataArrayLength = 256;
    public char instruction=0;
    public int programCounter =0;
    public int charCounter=0;
    public int lineCounter=0;
    public byte[] array = new byte[maxDataArrayLength];
    public int arrayPointer =0;
    public Stack<Integer> cycleStack = new Stack<Integer>();
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

