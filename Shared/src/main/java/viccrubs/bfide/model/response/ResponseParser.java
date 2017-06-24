package viccrubs.bfide.model.response;

import com.google.gson.*;

import java.lang.reflect.Type;

/**
 * Created by viccrubs on 2017/5/10.
 */
public class ResponseParser implements JsonDeserializer<Response> {
    @Override
    public Response deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        Gson gson = new Gson();

        return gson.fromJson(jsonElement, (Type) ResponseType.valueOf(jsonElement.getAsJsonObject().get("type").getAsString()).correspondingClass);
    }

}
