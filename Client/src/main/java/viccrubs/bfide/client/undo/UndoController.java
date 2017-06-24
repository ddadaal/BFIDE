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
    private int maxPointer=0;
    private boolean ignoreNextAddition = false;
    private final int MAX_HISTORY = 50;

    public void add(String newContent){
        if (history.size()>MAX_HISTORY){
            history.remove(0);
            pointer--;
            maxPointer--;
        }

        if (ignoreNextAddition){
            ignoreNextAddition = false;
            return;
        }
        if (!history.get(pointer).equals(newContent)){
            if (pointer==maxPointer){
                history.add(newContent);
                pointer++;
                maxPointer++;
            }else{
                history.set(++pointer, newContent);
                maxPointer=pointer;
            }
        }
    }

    public void initialize(String firstVersion){
        history.clear();
        history.add(firstVersion);
        ignoreNextAddition=true;
        pointer=0;
        maxPointer=0;
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
        return pointer<maxPointer;
    }


}
