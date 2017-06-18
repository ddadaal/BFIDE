package viccrubs.bfide.model.request;

import com.google.gson.*;

import java.lang.reflect.Type;

/**
 * Created by viccrubs on 2017/5/9.
 */
public class RequestParser implements JsonDeserializer<Request> {
    @Override
    public Request deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        Gson gson = new GsonBuilder().create();

        Request baseRequest = gson.fromJson(jsonElement, Request.class);

        switch(baseRequest.type.replace("Request","")){
            case "Login":
                return gson.fromJson(jsonElement, LoginRequest.class);
            case "Register":
                return gson.fromJson(jsonElement, RegisterRequest.class);
            case "GetMachineStates":
                return gson.fromJson(jsonElement, GetMachineStatesRequest.class);
            case "TestConnection":
                return gson.fromJson(jsonElement, TestConnectionRequest.class);
            case "TerminateConnection":
                return gson.fromJson(jsonElement, TerminateConnectionRequest.class);
            case "RunProgram":
                return gson.fromJson(jsonElement, RunProgramRequest.class);
            case "GetProjectInfo":
                return gson.fromJson(jsonElement, GetProjectInfoRequest.class);
            case "SaveVersion":
                return gson.fromJson(jsonElement, SaveVersionRequest.class);
            case "CreateNewProject":
                return gson.fromJson(jsonElement, CreateNewProjectRequest.class);
            case "GetAllProjects":
                return gson.fromJson(jsonElement, GetAllProjectsRequest.class);
            case "GetASpecificVersion":
                return gson.fromJson(jsonElement, GetASpecificVersionRequest.class);
            case "DeleteProject":
                return gson.fromJson(jsonElement, DeleteProjectRequest.class);
            default:
                return baseRequest;

        }
    }
}