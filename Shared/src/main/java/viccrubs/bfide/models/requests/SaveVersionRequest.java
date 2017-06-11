package viccrubs.bfide.models.requests;

import com.google.gson.annotations.Expose;
import viccrubs.bfide.models.ProjectInfo;

/**
 * Created by viccrubs on 2017/6/1.
 */
public class SaveVersionRequest extends Request {
    @Expose
    public final String content;
    @Expose
    public final long timestamp;
    @Expose
    public final ProjectInfo project;


    public SaveVersionRequest(String content, long timestamp, ProjectInfo project) {
        this.content = content;
        this.timestamp = timestamp;
        this.project = project;
    }
}
