package viccrubs.bfide.utilities;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by viccrubs on 2017/5/8.
 */
public class DynamicInStream extends InputStream {

    private int pointer =0;
    private StringBuilder internalStream = new StringBuilder();

    public void appendContent(String content){
        internalStream.append(content);
    }

    public void resetPointer(){
        pointer = 0;
    }

    @Override
    public int read() throws IOException {
        return internalStream.charAt(pointer++);
    }
}
