package viccrubs.bfide.model.response;

/**
 * Created by viccrubs on 2017/5/10.
 */
public class Response {
    public final ResponseType type=ResponseType.valueOf(getClass().getSimpleName().replace("Response",""));
}
