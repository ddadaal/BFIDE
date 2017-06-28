package viccrubs.bfide.bfmachine.program;

/**
 * Created by viccrubs on 2017/6/24.
 */
public class OokCompiler extends Compiler {

    public static final int INSTRUCTION_LENGTH = 8;

    public OokCompiler(){

    }
    public OokCompiler(String ookProgram){
        setProgram(ookProgram);
    }

    @Override
    public void setProgram(String ookProgram){
        this.program = ookProgram.replace(" ", "").replace("\n","");
    }

    @Override
    public char translate(String instruction) {
        switch(instruction){
            case "Ook.Ook?":
                return '>';
            case "Ook?Ook.":
                return '<';
            case "Ook.Ook.":
                return '+';
            case "Ook!Ook!":
                return '-';
            case "Ook!Ook.":
                return '.';
            case "Ook.Ook!":
                return ',';
            case "Ook!Ook?":
                return '[';
            case "Ook?Ook!":
                return ']';
            default:
                return 0;
        }
    }

    @Override
    public String translate(char bf){
        switch(bf){
            case '>':
                return "Ook.Ook?";
            case '<':
                return "Ook?Ook.";
            case '+':
                return "Ook.Ook.";
            case '-':
                return "Ook!Ook!";
            case '.':
                return "Ook!Ook.";
            case ',':
                return "Ook.Ook!";
            case '[':
                return "Ook!Ook?";
            case ']':
                return "Ook?Ook!";
            default:
                return "???";
        }
    }

    @Override
    public ProgramLanguage getLanguage() {
        return ProgramLanguage.Ook;
    }

    @Override
    public boolean hasNext() {
        return pointer<=program.length()-INSTRUCTION_LENGTH;
    }

    @Override
    public String next() {
        String raw = program.substring(pointer, pointer + INSTRUCTION_LENGTH);
        pointer+=INSTRUCTION_LENGTH;
        return raw;
    }
}
