package viccrubs.bfide.models.requests;

import viccrubs.bfide.models.ProjectInfo;
import viccrubs.bfide.models.Version;

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
