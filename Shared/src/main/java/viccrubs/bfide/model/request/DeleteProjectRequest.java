package viccrubs.bfide.model.request;

import viccrubs.bfide.model.ProjectInfo;

/**
 * Created by viccrubs on 2017/6/18.
 */
public class DeleteProjectRequest extends Request {
    public final ProjectInfo projectInfo;

    public DeleteProjectRequest(ProjectInfo projectInfo) {
        this.projectInfo = projectInfo;
    }
}
