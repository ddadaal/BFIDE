package viccrubs.bfide.client.models;

import javafx.beans.property.*;

import java.time.Instant;
import java.time.LocalDateTime;

/**
 * Created by viccrubs on 2017/5/22.
 */
public class Log {
    private final ObjectProperty<LogType> type;


    private final StringProperty description;
    private final ObjectProperty<Instant> time;

    public Log(String description, Instant time, LogType type) {
        this.description =new SimpleStringProperty( description);
        this.time = new SimpleObjectProperty<>(time);
        this.type = new SimpleObjectProperty<>(type);
    }

    public Log(String description){
        this(description, Instant.now(), LogType.Notification);
    }


    public Log(String description, LogType type){
        this(description, Instant.now(), type);
    }


    public String getDescription() {
        return description.get();
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public Instant getTime() {
        return time.get();
    }

    public ObjectProperty<Instant> timeProperty() {
        return time;
    }

    public void setTime(Instant time) {
        this.time.set(time);
    }

    public LogType getType() {
        return type.get();
    }

    public ObjectProperty<LogType> typeProperty() {
        return type;
    }

    public void setType(LogType type) {
        this.type.set(type);
    }

}
