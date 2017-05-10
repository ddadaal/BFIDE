package viccrubs.bfide.models.response;

import com.google.gson.*;
import viccrubs.bfide.models.ExecutionResult;
import viccrubs.bfide.models.requests.LoginRequest;
import viccrubs.bfide.models.requests.Request;
import viccrubs.bfide.models.requests.RunProgramRequest;

import java.lang.reflect.Type;

/**
 * Created by viccrubs on 2017/5/10.
 */
public class ResponseParser implements JsonDeserializer<Response> {
    @Override
    public Response deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject jsonObject = jsonElement.getAsJsonObject();

        JsonElement jsonType = jsonObject.get("type");
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        String typeField = jsonType.getAsString();

        Response typeModel = null;

        switch(typeField){
            case RunResultResponse.type:
                typeModel = new RunResultResponse(gson.fromJson(jsonObject.get("result"), ExecutionResult.class));
                break;
            case RequestInputResponse.type:
                typeModel = new RequestInputResponse();
                break;
            default:
                typeModel = null;

        }

        return typeModel;
    }

}
