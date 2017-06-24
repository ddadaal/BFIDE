package viccrubs.bfide.model.response;

import viccrubs.bfide.model.Version;

/**
 * Created by viccrubs on 2017/6/1.
 */
public class SaveVersionResponse extends Response {
    public final boolean success;
    public final String description;

    public final Version latestVersion;

    public SaveVersionResponse(boolean success, String description, Version latestVersion) {
        this.success = success;
        this.description = description;
        this.latestVersion = latestVersion;
    }
}
