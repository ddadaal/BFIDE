package viccrubs.bfide.model.request;

import viccrubs.bfide.bfmachine.program.ProgramLanguage;

/**
 * Created by viccrubs on 2017/5/9.
 */
public class ExecuteProgramRequest extends Request {

    public final String program;
    public final String input;
    public final ProgramLanguage language;

    public ExecuteProgramRequest(String program, String input, ProgramLanguage language) {
        this.program = program;
        this.input = input;
        this.language = language;
    }
}
