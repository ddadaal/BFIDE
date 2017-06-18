package viccrubs.bfide.model.response;

import viccrubs.bfide.model.Version;

/**
 * Created by viccrubs on 2017/6/1.
 */
public class SaveVersionResponse extends Response {
    public final boolean success;

    public final Version latestVersion;

    public SaveVersionResponse(boolean success, Version latestVersion) {
        this.success = success;
        this.latestVersion = latestVersion;
    }
}
