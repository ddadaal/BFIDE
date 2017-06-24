package viccrubs.bfide.model;

import com.google.gson.annotations.Expose;
import viccrubs.bfide.bfmachine.program.ProgramLanguage;

import java.util.Arrays;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProjectInfo)) return false;

        ProjectInfo that = (ProjectInfo) o;

        if (projectName != null ? !projectName.equals(that.projectName) : that.projectName != null) return false;
        if (language != that.language) return false;

        return Arrays.equals(versions, that.versions) && (latestVersion != null ? latestVersion.equals(that.latestVersion) : that.latestVersion == null);
    }

    @Override
    public int hashCode() {
        int result = projectName != null ? projectName.hashCode() : 0;
        result = 31 * result + (language != null ? language.hashCode() : 0);
        result = 31 * result + Arrays.hashCode(versions);
        result = 31 * result + (latestVersion != null ? latestVersion.hashCode() : 0);
        return result;
    }
}
