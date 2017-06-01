package viccrubs.bfide.models.response;

import com.google.gson.annotations.Expose;
import viccrubs.bfide.models.ProjectInfo;

/**
 * Created by viccrubs on 2017/6/1.
 */
public class GetProjectInfoResponse extends Response {
    @Expose
    public final ProjectInfo info;

    public GetProjectInfoResponse(ProjectInfo info) {
        this.info = info;
    }
}
