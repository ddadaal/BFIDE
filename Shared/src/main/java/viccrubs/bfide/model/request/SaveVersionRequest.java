package viccrubs.bfide.model.request;

import viccrubs.bfide.model.ProjectInfo;

/**
 * Created by viccrubs on 2017/6/1.
 */
public class SaveVersionRequest extends Request {
    public final String content;
    public final long timestamp;
    public final ProjectInfo project;


    public SaveVersionRequest(String content, long timestamp, ProjectInfo project) {
        this.content = content;
        this.timestamp = timestamp;
        this.project = project;
    }
}
