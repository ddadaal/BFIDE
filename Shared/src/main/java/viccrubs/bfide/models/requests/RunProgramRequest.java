package viccrubs.bfide.models.requests;

import com.google.gson.annotations.Expose;
import viccrubs.bfide.models.ProgramLanguage;

/**
 * Created by viccrubs on 2017/5/9.
 */
public class RunProgramRequest extends Request {
    @Expose
    public final static String type = "RUN_PROGRAM";

    @Expose
    public final String program;

    @Expose
    public final ProgramLanguage language;

    public RunProgramRequest(String program, ProgramLanguage language) {
        this.program = program;

        this.language = language;
    }
}
