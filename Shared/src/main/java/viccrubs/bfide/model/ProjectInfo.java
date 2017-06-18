package viccrubs.bfide.model;

import com.google.gson.annotations.Expose;
import viccrubs.bfide.bfmachine.ProgramLanguage;

/**
 * Created by viccrubs on 2017/6/1.
 */
public class ProjectInfo {
    @Expose
    public final String projectName;
    @Expose
    public final ProgramLanguage language;
    @Expose
    public final Version[] versions;
    @Expose
    public final Version latestVersion;

    public ProjectInfo(String projectName, ProgramLanguage language, Version[] versions, Version latestVersion) {
        this.projectName = projectName;
        this.language = language;
        this.versions = versions;
        this.latestVersion = latestVersion;
    }
}
