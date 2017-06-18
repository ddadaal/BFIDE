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

        Response baseResponse = gson.fromJson(jsonElement, Response.class);

        switch(baseResponse.type.replace("Response","")){
            case "Login":
                return gson.fromJson(jsonElement, LoginResponse.class);
            case "RequireLogin":
                return gson.fromJson(jsonElement, RequireLoginResponse.class);
            case "Register":
                return gson.fromJson(jsonElement, RegisterResponse.class);
            case "Execution":
                return gson.fromJson(jsonElement, ExecutionResponse.class);
            case "TestConnection":
                return gson.fromJson(jsonElement, TestConnectionResponse.class);
            case "TerminateConnection":
                return gson.fromJson(jsonElement, TerminateConnectionResponse.class);
            case "GetProjectInfo":
                return gson.fromJson(jsonElement, GetProjectInfoResponse.class);
            case "CreateNewProject":
                return gson.fromJson(jsonElement, CreateNewProjectResponse.class);
            case "SaveVersion":
                return gson.fromJson(jsonElement, SaveVersionResponse.class);
            case "GetAllProjects":
                return gson.fromJson(jsonElement, GetAllProjectsResponse.class);
            case "GetASpecificVersion":
                return gson.fromJson(jsonElement, GetASpecificVersionResponse.class);
            case "DeleteProject":
                return gson.fromJson(jsonElement, DeleteProjectResponse.class);
            default:
                return baseResponse;

        }
    }

}
