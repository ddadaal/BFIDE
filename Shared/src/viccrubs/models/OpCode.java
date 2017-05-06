package viccrubs.models;

/**
 * Created by viccrubs on 2017/5/6.
 */
public enum OpCode {
    GetMachineStates("GET_MACHINE_STATES"),
    LoadProgram("LOAD_PROGRAM"),
    RunProgram("RUN_PROGRAM"),
    DebugStart("DEBUG_START"),
    Continue("CONTINUE"),
    Terminate("TERMINATE"),
    TestConnection("TEST_CONNECTION"),
    CloseConnection("CLOSE_CONNECTION");

    private String opCode;

    OpCode(String opCode){
        this.opCode = opCode;
    }

    public String getOpCode(){
        return this.opCode;
    }

}
