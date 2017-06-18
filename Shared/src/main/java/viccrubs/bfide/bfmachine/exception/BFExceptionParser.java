package viccrubs.bfide.bfmachine.exception;

import com.google.gson.*;
import java.lang.reflect.Type;

public class BFExceptionParser implements JsonDeserializer<BFMachineException> {
    @Override
    public BFMachineException deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        Gson gson = new Gson();

        BFMachineException baseException = gson.fromJson(jsonElement, BFMachineException.class);

        switch(baseException.type){
            case "LoopStackEmptyException":
                return gson.fromJson(jsonElement, LoopStackEmptyException.class);
            case "DataArrayOutOfIndexException":
                return gson.fromJson(jsonElement, DataArrayOutOfIndexException.class);
            case "InputTooShortException":
                return gson.fromJson(jsonElement, InputTooShortException.class);
            case "MachineIOException":
                return gson.fromJson(jsonElement, MachineIOException.class);
            case "UnknownInstructionException":
                return gson.fromJson(jsonElement, UnknownInstructionException.class);
            default:
                return baseException;

        }
    }
}