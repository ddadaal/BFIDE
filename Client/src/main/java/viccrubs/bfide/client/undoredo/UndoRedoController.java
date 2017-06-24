package viccrubs.bfide.client.undoredo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Stack;

/**
 * Created by viccrubs on 2017/6/19.
 */
public class UndoRedoController {

    private Stack<String> history = new Stack<>();
    private Stack<String> redone = new Stack<>();
    private boolean ignoreNextAddition = false;
    private final int MAX_HISTORY = 50;

    public void add(String newContent){
        if (history.size()>MAX_HISTORY){
            history.remove(0);
        }
        if (redone.size()>MAX_HISTORY){
            redone.remove(0);
        }

        if (ignoreNextAddition){
            ignoreNextAddition = false;
            return;
        }
        history.push(newContent);
    }

    public void initialize(String firstVersion){
        history.clear();
        redone.clear();
        history.add(firstVersion);
        ignoreNextAddition=true;
    }

    public Optional<String> undo(){
        if (canUndo()){
            ignoreNextAddition = true;
            redone.push(history.pop());
            return Optional.of(history.peek());
        }
        return Optional.empty();
    }

    public Optional<String> redo(){
        if (canRedo()){
            ignoreNextAddition = true;
            history.push(redone.pop());
            return Optional.of(history.peek());
        }
        return Optional.empty();
    }

    public boolean canUndo(){
        return !history.empty();
    }
    public boolean canRedo(){
        return !redone.empty();
    }


}
