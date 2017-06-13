package viccrubs.bfide.models.response;

import com.google.gson.annotations.Expose;
import viccrubs.bfide.models.ProjectInfo;

/**
 * Created by viccrubs on 2017/6/8.
 */
public class GetAllProjectsResponse extends Response {
    public final ProjectInfo[] projects;


    public GetAllProjectsResponse(ProjectInfo[] projects) {
        this.projects = projects;

    }
}
