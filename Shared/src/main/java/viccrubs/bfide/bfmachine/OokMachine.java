package viccrubs.bfide.bfmachine;

import viccrubs.bfide.bfmachine.exceptions.BFMachineException;
import viccrubs.bfide.models.ExecutionResult;
import viccrubs.bfide.utilities.DynamicInStream;
import viccrubs.bfide.utilities.DynamicOutStream;

/**
 * Created by viccrubs on 2017/5/10.
 */
public class OokMachine implements IMachine {

    private BFMachine machine;

    public OokMachine(){
        machine = new BFMachine();
    }

    public OokMachine(DynamicOutStream outStream, DynamicInStream inStream){
        machine = new BFMachine(outStream,inStream);
    }

    public DynamicOutStream getOutStream() {
        return machine.getOutStream();
    }

    public void setOutStream(DynamicOutStream outStream) {
        this.machine.setOutStream(outStream);
    }

    public DynamicInStream getInStream() {
        return machine.getInStream();
    }

    public void setInStream(DynamicInStream inStream) {
        this.machine.setInStream(inStream);
    }

    @Override
    public ExecutionResult executeProgram(String program) throws BFMachineException {
        return null;
    }

    public static char translateOokToBF(String instruction){
        switch(instruction){
            case "Ook. Ook?":
                return '>';
            case "Ook? Ook.":
                return '<';
            case "Ook. Ook.":
                return '+';
            case "Ook! Ook!":
                return '-';
            case "Ook. Ook!":
                return '.';
            case "Ook! Ook?":
                return '[';
            case "Ook? Ook!":
                return '[';
            default:
                return 0;
        }
    }

    public static String translateBFToOok(char bf){
        switch(bf){
            case '>':
                return "Ook. Ook?";
            case '<':
                return "Ook? Ook.";
            case '+':
                return "Ook. Ook.";
            case '-':
                return "Ook! Ook!";
            case '.':
                return "Ook! Ook.";
            case ',':
                return "Ook. Ook!";
            case '[':
                return "Ook! Ook?";
            case ']':
                return "Ook? Ook!";
            default:
                return "???";
        }
    }
}
