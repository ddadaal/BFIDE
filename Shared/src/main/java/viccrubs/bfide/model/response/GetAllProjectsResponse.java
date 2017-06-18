package viccrubs.bfide.model.response;

import viccrubs.bfide.model.ProjectInfo;

/**
 * Created by viccrubs on 2017/6/8.
 */
public class GetAllProjectsResponse extends Response {
    public final ProjectInfo[] projects;


    public GetAllProjectsResponse(ProjectInfo[] projects) {
        this.projects = projects;

    }
}
