package viccrubs.bfide.models.requests;

import com.google.gson.*;

import java.lang.reflect.Type;

/**
 * Created by viccrubs on 2017/5/9.
 */
public class RequestParser implements JsonDeserializer<Request> {
    @Override
    public Request deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

        JsonElement jsonType = jsonObject.get("type");
        String typeField = jsonType.getAsString();

        Request typeModel = null;

        switch(typeField){
            case LoginRequest.type:
                typeModel = gson.fromJson(jsonObject, LoginRequest.class);
                break;
            case RunProgramRequest.type:
                typeModel = gson.fromJson(jsonObject, RunProgramRequest.class);
                break;
            case InputRequest.type:
                typeModel = gson.fromJson(jsonObject, InputRequest.class);
                break;
            default:
                typeModel = null;

        }

        return typeModel;
    }
}
