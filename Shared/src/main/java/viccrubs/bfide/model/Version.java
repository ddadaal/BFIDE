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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Version)) return false;

        Version version1 = (Version) o;

        if (timeStamp != version1.timeStamp) return false;
        return version != null ? version.equals(version1.version) : version1.version == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (timeStamp ^ (timeStamp >>> 32));
        result = 31 * result + (version != null ? version.hashCode() : 0);
        return result;
    }
}
