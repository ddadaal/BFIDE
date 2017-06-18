package viccrubs.bfide.model.response;

import viccrubs.bfide.model.Version;

/**
 * Created by viccrubs on 2017/6/11.
 */
public class GetASpecificVersionResponse extends Response {
    public final String content;
    public final Version version;

    public GetASpecificVersionResponse(String content, Version version) {
        this.content = content;
        this.version = version;
    }
}
