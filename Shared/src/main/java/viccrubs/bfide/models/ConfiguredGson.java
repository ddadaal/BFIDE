package viccrubs.bfide.models;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import viccrubs.bfide.bfmachine.exceptions.BFExceptionParser;
import viccrubs.bfide.bfmachine.exceptions.BFMachineException;
import viccrubs.bfide.models.requests.Request;
import viccrubs.bfide.models.requests.RequestParser;
import viccrubs.bfide.models.response.Response;
import viccrubs.bfide.models.response.ResponseParser;

/**
 * Created by viccrubs on 2017/5/22.
 */
public class ConfiguredGson {
    public static Gson get(){
        return new GsonBuilder()
                .registerTypeAdapter(Request.class, new RequestParser())
                .registerTypeAdapter(Response.class, new ResponseParser())
                .registerTypeAdapter(BFMachineException.class, new BFExceptionParser())
                .create();
    }
}
