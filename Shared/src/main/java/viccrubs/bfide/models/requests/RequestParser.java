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

        JsonElement jsonType = jsonObject.get("type");
        String typeField = jsonType.getAsString();

        Request typeModel = null;

        switch(typeField){
            case LoginRequest.type:
                typeModel = new LoginRequest(jsonObject.get("username").getAsString(), jsonObject.get("password").getAsString());
                break;
            case RunProgramRequest.type:
                typeModel = new RunProgramRequest(jsonObject.get("program").getAsString());
                break;
            default:
                typeModel = null;

        }

        return typeModel;
    }
}
