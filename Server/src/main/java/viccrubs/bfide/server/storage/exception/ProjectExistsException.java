package viccrubs.bfide.server.storage.exception;

/**
 * Created by viccrubs on 2017/6/22.
 */
public class ProjectExistsException extends Exception {
    @Override
    public String toString(){
        return "Project exists.";
    }
}
