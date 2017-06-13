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
    public final ProjectInfo newProjectInfo;
    @Expose
    public final String description;

    public CreateNewProjectResponse(boolean success, ProjectInfo newProjectInfo, String description) {
        this.success = success;
        this.newProjectInfo = newProjectInfo;
        this.description = description;
    }
}
