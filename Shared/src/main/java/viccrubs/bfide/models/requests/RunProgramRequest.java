package viccrubs.bfide.models.requests;

import com.google.gson.annotations.Expose;

/**
 * Created by viccrubs on 2017/5/9.
 */
public class RunProgramRequest extends Request {
    @Expose
    public final static String type = "RUN_PROGRAM";

    @Expose
    public final String program;

    public RunProgramRequest(String program) {
        this.program = program;

    }
}
