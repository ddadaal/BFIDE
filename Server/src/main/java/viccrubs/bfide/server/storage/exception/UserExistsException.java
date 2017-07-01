package viccrubs.bfide.server.storage.exception;

/**
 * Created by viccrubs on 2017/6/22.
 */
public class UserExistsException extends Exception {
    @Override
    public String toString(){
        return "User exists.";
    }
}
