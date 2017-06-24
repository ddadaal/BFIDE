package viccrubs.bfide.model.request;

import com.google.gson.*;

import java.lang.reflect.Type;

/**
 * Created by viccrubs on 2017/5/9.
 */
public class RequestParser implements JsonDeserializer<Request> {
    @Override
    public Request deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        Gson gson = new Gson();
        return gson.fromJson(jsonElement, (Type) RequestType.valueOf(jsonElement.getAsJsonObject().get("type").getAsString()).correspondingClass);


    }
}
