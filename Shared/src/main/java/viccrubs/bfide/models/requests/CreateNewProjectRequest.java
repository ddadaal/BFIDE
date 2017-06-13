package viccrubs.bfide.models.requests;

import com.google.gson.annotations.Expose;
import viccrubs.bfide.bfmachine.ProgramLanguage;

/**
 * Created by viccrubs on 2017/6/1.
 */
public class CreateNewProjectRequest extends Request {
    @Expose
    public final String projectName;
    @Expose
    public final ProgramLanguage language;

    public CreateNewProjectRequest(String projectName, ProgramLanguage language) {
        this.projectName = projectName;
        this.language = language;
    }
}
