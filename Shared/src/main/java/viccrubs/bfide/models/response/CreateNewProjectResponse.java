package viccrubs.bfide.models.response;

import com.google.gson.annotations.Expose;
import viccrubs.bfide.models.ProjectInfo;

/**
 * Created by viccrubs on 2017/6/1.
 */
public class CreateNewProjectResponse extends Response {
    @Expose
    public final boolean success;
    @Expose
    public final ProjectInfo info;

    public CreateNewProjectResponse(boolean success, ProjectInfo info) {
        this.success = success;
        this.info = info;
    }
}
