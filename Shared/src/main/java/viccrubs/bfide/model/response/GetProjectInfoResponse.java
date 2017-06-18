package viccrubs.bfide.model.response;

import viccrubs.bfide.model.ProjectInfo;

/**
 * Created by viccrubs on 2017/6/1.
 */
public class GetProjectInfoResponse extends Response {
    public final ProjectInfo info;

    public GetProjectInfoResponse(ProjectInfo info) {
        this.info = info;
    }
}
