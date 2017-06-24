package viccrubs.bfide.model.request;

/**
 * Created by viccrubs on 2017/5/9.
 */
public class Request {
    public final RequestType type=RequestType.valueOf(getClass().getSimpleName().replace("Request",""));

}
