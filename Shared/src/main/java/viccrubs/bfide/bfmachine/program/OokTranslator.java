package viccrubs.bfide.bfmachine.program;

/**
 * Created by viccrubs on 2017/6/24.
 */
public class OokTranslator extends Translator {

    public static final int instructionLength = 8;

    public OokTranslator(String ookProgram){
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
        return pointer<=program.length()-instructionLength;
    }

    @Override
    public String next() {
        String raw = program.substring(pointer, pointer + instructionLength);
        pointer+=instructionLength;
        return raw;
    }
}
