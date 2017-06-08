package viccrubs.bfide.utilities;

/**
 * Created by viccrubs on 2017/5/30.
 */
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateUtil {

    private static final String DATE_PATTERN = "yyyy-MM-dd, HH:mm:ss:SSS";

    private static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern(DATE_PATTERN).withZone(ZoneId.systemDefault());

    public static String format(Instant instant) {
        if (instant == null) {
            return null;
        }
        return DATE_FORMATTER.format(instant);
    }

    public static Instant parse(String dateString) {
        try {
            return DATE_FORMATTER.parse(dateString, Instant::from);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    public static long toTimestamp(Instant instant){
        if (instant!=null){
            return 0;
        }
        return instant.toEpochMilli();
    }

    public static Instant fromTimestamp(long timestamp){
        return Instant.ofEpochMilli(timestamp);
    }
}
