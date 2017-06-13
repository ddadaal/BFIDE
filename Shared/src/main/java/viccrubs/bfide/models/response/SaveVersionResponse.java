package viccrubs.bfide.models.response;

import com.google.gson.annotations.Expose;
import viccrubs.bfide.models.Version;

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
