package viccrubs.bfide.client.models;

import javafx.beans.property.*;

import java.time.LocalDateTime;

/**
 * Created by viccrubs on 2017/5/22.
 */
public class Log {
    private final StringProperty description;
    private final ObjectProperty<LocalDateTime> time;

    public Log(String description, LocalDateTime time) {
        this.description =new SimpleStringProperty( description);
        this.time = new SimpleObjectProperty<>(time);
    }

    public Log(String description){
        this.description = new SimpleStringProperty(description);
        this.time = new SimpleObjectProperty<>(LocalDateTime.now());
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

    public LocalDateTime getTime() {
        return time.get();
    }

    public ObjectProperty<LocalDateTime> timeProperty() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time.set(time);
    }
}
