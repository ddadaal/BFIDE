package viccrubs.bfide.model.response;

/**
 * Created by viccrubs on 2017/6/18.
 */
public class DeleteProjectResponse extends Response {
    public final boolean success;
    public final String description;

    public DeleteProjectResponse(boolean success, String description) {
        this.success = success;

        this.description = description;


    }
}
