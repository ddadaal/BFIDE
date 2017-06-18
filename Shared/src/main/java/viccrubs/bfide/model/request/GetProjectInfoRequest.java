package viccrubs.bfide.model.request;

/**
 * Created by viccrubs on 2017/6/1.
 */
public class GetProjectInfoRequest extends Request {
    public final String projectName;

    public GetProjectInfoRequest(String projectName) {
        this.projectName = projectName;


    }
}
