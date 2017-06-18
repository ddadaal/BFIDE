package viccrubs.bfide.model.request;

import viccrubs.bfide.model.ProjectInfo;
import viccrubs.bfide.model.Version;

/**
 * Created by viccrubs on 2017/6/11.
 */
public class GetASpecificVersionRequest extends Request {
    public final ProjectInfo projectInfo;
    public final Version version;

    public GetASpecificVersionRequest(ProjectInfo projectInfo, Version version) {
        this.projectInfo = projectInfo;
        this.version = version;
    }
}
