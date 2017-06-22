package viccrubs.bfide.client.undo;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Stack;

/**
 * Created by viccrubs on 2017/6/19.
 */
public class UndoController {

    private List<String> history = new ArrayList<>();
    private int pointer = 0;
    private boolean ignoreNextAddition = false;
    private final int MAX_HISTORY = 50;

    public void add(String newContent){
        if (history.size()>MAX_HISTORY){
            history.remove(0);
            pointer--;
        }

        if (ignoreNextAddition){
            ignoreNextAddition = false;
            return;
        }

        if (history.size()==0){
            history.add("");
        }

        if (!history.get(pointer).equals(newContent)){
            if (history.size()==pointer+1){
                history.add(newContent);
            }else{
                history.set(pointer+1, newContent);
            }
            pointer++;
        }
    }

    public void initialize(String firstVersion){
        history.clear();
        history.add(firstVersion);
        pointer=0;
    }

    public Optional<String> undo(){
        if (canUndo()){
            ignoreNextAddition = true;
            return Optional.of(history.get(--pointer));
        }
        return Optional.empty();
    }

    public Optional<String> redo(){
        if (canRedo()){
            ignoreNextAddition = true;
            return Optional.of(history.get(++pointer));
        }
        return Optional.empty();
    }

    public boolean canUndo(){
        return pointer>0;
    }
    public boolean canRedo(){
        return pointer<history.size()-1;
    }


}
