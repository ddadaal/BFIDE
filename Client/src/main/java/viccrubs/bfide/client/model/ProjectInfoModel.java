package viccrubs.bfide.client.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import viccrubs.bfide.bfmachine.program.ProgramLanguage;
import viccrubs.bfide.model.ProjectInfo;
import viccrubs.bfide.util.DateUtil;

import java.time.Instant;

/**
 * Created by viccrubs on 2017/6/8.
 */
public class ProjectInfoModel {

    public ProjectInfoModel(ProjectInfo info){
        projectName = new SimpleStringProperty(info.projectName);
        latestVersion = new SimpleObjectProperty<>(DateUtil.fromTimestamp(info.versions[0].timeStamp));
        versionCount = new SimpleStringProperty(info.versions.length+"");
        language = new SimpleObjectProperty<>(info.language);
        this.originalInfo = info;
    }

    private final StringProperty projectName;
    private final ObjectProperty<Instant> latestVersion;
    private final StringProperty versionCount;
    private final ObjectProperty<ProgramLanguage> language;
    private final ProjectInfo originalInfo;

    public String getProjectName() {
        return projectName.get();
    }

    public StringProperty projectNameProperty() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName.set(projectName);
    }

    public Instant getLatestVersion() {
        return latestVersion.get();
    }

    public ObjectProperty<Instant> latestVersionProperty() {
        return latestVersion;
    }

    public void setLatestVersion(Instant latestVersion) {
        this.latestVersion.set(latestVersion);
    }

    public String getVersionCount() {
        return versionCount.get();
    }

    public StringProperty versionCountProperty() {
        return versionCount;
    }

    public void setVersionCount(String versionCount) {
        this.versionCount.set(versionCount);
    }

    public ProgramLanguage getLanguage() {
        return language.get();
    }

    public ObjectProperty<ProgramLanguage> languageProperty() {
        return language;
    }

    public void setLanguage(ProgramLanguage language) {
        this.language.set(language);
    }

    public ProjectInfo toProjectInfo(){
        return originalInfo;
    }

}
