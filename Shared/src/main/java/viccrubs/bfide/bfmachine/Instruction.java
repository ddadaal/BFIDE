package viccrubs.bfide.bfmachine;

/**
 * Created by viccrubs on 2017/5/30.
 */
public class Instruction {
    public final char instructionChar;
    public final ProgramLanguage language;

    private Instruction(char insInBF, ProgramLanguage language){
        instructionChar = insInBF;
        this.language = language;
    }

    public static Instruction of(char bfChar){
        return new Instruction(bfChar, ProgramLanguage.BF);
    }

    public static Instruction of(String ookInstruction){
        char bfChar = Instruction.translateOokToBF(ookInstruction);
        return bfChar==0 ? null :new Instruction(bfChar, ProgramLanguage.Ook);
    }

    @Override
    public String toString(){
        return toString(language);
    }

    public String toString(ProgramLanguage language){
        if (language.equals(ProgramLanguage.BF)){
            return instructionChar+"";
        }else{
            return translateBFToOok(instructionChar);
        }
    }

    public static String translateBFToOok(char bf){
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

    public static char translateOokToBF(String instruction){
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
}
