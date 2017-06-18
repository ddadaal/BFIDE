package viccrubs.bfide.model.request;

import viccrubs.bfide.bfmachine.ProgramLanguage;

/**
 * Created by viccrubs on 2017/5/9.
 */
public class RunProgramRequest extends Request {

    public final String program;
    public final String input;
    public final ProgramLanguage language;

    public RunProgramRequest(String program, String input, ProgramLanguage language) {
        this.program = program;
        this.input = input;
        this.language = language;
    }
}
