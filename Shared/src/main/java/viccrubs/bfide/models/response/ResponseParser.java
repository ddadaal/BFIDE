package viccrubs.bfide.models.response;

import com.google.gson.*;
import viccrubs.bfide.models.ExecutionResult;
import viccrubs.bfide.models.requests.LoginRequest;
import viccrubs.bfide.models.requests.RegisterRequest;
import viccrubs.bfide.models.requests.Request;
import viccrubs.bfide.models.requests.RunProgramRequest;

import java.lang.reflect.Type;

/**
 * Created by viccrubs on 2017/5/10.
 */
public class ResponseParser implements JsonDeserializer<Response> {
    @Override
    public Response deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        Gson gson = new Gson();

        Response baseResponse = gson.fromJson(jsonElement, Response.class);

        switch(baseResponse.type){
            case LoginResponse:
                return gson.fromJson(jsonElement, LoginResponse.class);
            case RequestInput:
                return gson.fromJson(jsonElement, RequestInputResponse.class);
            case RegisterResponse:
                return gson.fromJson(jsonElement, RegisterResponse.class);
            case RunResult:
                return gson.fromJson(jsonElement, RunResultResponse.class);
            case TestConnection:
                return gson.fromJson(jsonElement, TestConnectionResponse.class);
            default:
                return null;

        }
    }

}
