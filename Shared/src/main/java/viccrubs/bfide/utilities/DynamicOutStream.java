package viccrubs.bfide.utilities;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by viccrubs on 2017/5/8.
 */
public class DynamicOutStream extends OutputStream {

    private StringBuilder result = new StringBuilder();


    public String getContent (){
        return result.toString();
    }

    public void clear(){
        result = new StringBuilder();
    }

    @Override
    public void write(int b) throws IOException {
        result.append((char)b);
    }
}
