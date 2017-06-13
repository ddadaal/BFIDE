package viccrubs.bfide.models.requests;

import com.google.gson.annotations.Expose;

/**
 * Created by viccrubs on 2017/6/1.
 */
public class GetProjectInfoRequest extends Request {
    public final String projectName;

    public GetProjectInfoRequest(String projectName) {
        this.projectName = projectName;


    }
}
