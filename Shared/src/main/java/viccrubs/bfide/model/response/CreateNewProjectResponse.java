package viccrubs.bfide.model.response;

import viccrubs.bfide.model.ProjectInfo;

/**
 * Created by viccrubs on 2017/6/1.
 */
public class CreateNewProjectResponse extends Response {
    public final boolean success;
    public final ProjectInfo newProjectInfo;
    public final String description;

    public CreateNewProjectResponse(boolean success, ProjectInfo newProjectInfo, String description) {
        this.success = success;
        this.newProjectInfo = newProjectInfo;
        this.description = description;
    }
}
