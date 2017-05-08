package viccrubs.bfide.models;

/**
 * Created by viccrubs on 2017/5/8.
 */
public enum Operation {
    Unknown("UNKNOWN"),
    LoadProgram("LOAD_PROGRAM"),
    TerminateConnection("TERMINATE_CONNECTION"),
    TerminateProgram("TERMINATE_PROGRAM"),
    TestConnection("TEST_CONNECTION"),
    GetMachineStates("GET_MACHINE_STATES"),
    RunProgram("RUN_PROGRAM");


    private String message;
    Operation(String msg){
        this.message = msg;
    }
    @Override
    public String toString(){
        return this.message;
    }
    public static Operation fromString(String input){
        for(Operation op : Operation.values()){
            if (op.toString().equalsIgnoreCase(input)){
                return op;
            }
        }
        return Operation.Unknown;
    }

}
