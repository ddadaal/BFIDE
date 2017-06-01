package viccrubs.bfide.models.response;

import com.google.gson.annotations.Expose;

/**
 * Created by viccrubs on 2017/5/10.
 */
public class Response {
    @Expose
    public final String type=getClass().getSimpleName();
}
