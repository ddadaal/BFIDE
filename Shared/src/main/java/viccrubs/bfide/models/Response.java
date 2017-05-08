package viccrubs.bfide.models;

/**
 * Created by viccrubs on 2017/5/8.
 */
public enum Response {
    Success("SUCCESS"),
    TestConnection("TEST_CONNECTION"),
    UnknownCommand("UNKNOWN_COMMAND"),
    NotImplemented("NOT_IMPLEMENTED"),
    TerminateConnection("TERMINATE_CONNECTION");

    private String message;
    Response(String msg){
        this.message = msg;
    }

    @Override
    public String toString(){
        return this.message;
    }

}
