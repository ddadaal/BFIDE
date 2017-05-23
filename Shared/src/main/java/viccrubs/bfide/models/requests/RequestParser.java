package viccrubs.bfide.models.requests;

import com.google.gson.*;

import java.lang.reflect.Type;

import static viccrubs.bfide.models.requests.RequestType.*;

/**
 * Created by viccrubs on 2017/5/9.
 */
public class RequestParser implements JsonDeserializer<Request> {
    @Override
    public Request deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        Gson gson = new GsonBuilder().create();

        Request baseRequest = gson.fromJson(jsonElement, Request.class);

        switch(baseRequest.type){
            case Login:
                return gson.fromJson(jsonElement, LoginRequest.class);
            case RunProgram:
                return gson.fromJson(jsonElement, RunProgramRequest.class);
            case Input:
                return gson.fromJson(jsonElement, InputRequest.class);
            case Register:
                return gson.fromJson(jsonElement, RegisterRequest.class);
            case GetMachineStates:
                return gson.fromJson(jsonElement, GetMachineStatesRequest.class);
            case TestConnection:
                return gson.fromJson(jsonElement, TestConnectionRequest.class);
            case TerminateConnection:
                return gson.fromJson(jsonElement, TerminateConnectionRequest.class);
            default:
                return null;

        }
    }
}
