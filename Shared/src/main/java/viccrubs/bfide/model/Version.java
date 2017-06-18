package viccrubs.bfide.model;

import com.google.gson.annotations.Expose;
import viccrubs.bfide.util.DateUtil;

import java.time.Instant;

/**
 * Created by viccrubs on 2017/6/1.
 */
public class Version {
    @Expose
    public final long timeStamp;
    @Expose
    public final Instant version;

    public Version(long timeStamp){
        this.timeStamp = timeStamp;
        this.version = DateUtil.fromTimestamp(timeStamp);
    }

    public Version(Instant instant){
        this.version = instant;
        this.timeStamp = DateUtil.toTimestamp(instant);
    }

}
