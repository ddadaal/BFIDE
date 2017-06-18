package viccrubs.bfide.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import viccrubs.bfide.bfmachine.exception.BFExceptionParser;
import viccrubs.bfide.bfmachine.exception.BFMachineException;
import viccrubs.bfide.model.request.Request;
import viccrubs.bfide.model.request.RequestParser;
import viccrubs.bfide.model.response.Response;
import viccrubs.bfide.model.response.ResponseParser;

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
