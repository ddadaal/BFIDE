package viccrubs.bfide.server;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Scanner;

import java.net.URL;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Created by viccrubs on 2017/5/11.
 */
public class Utils {

    public static String downloadContentViaAbsolutePath(String absoluteFilePath) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(absoluteFilePath));
        StringBuilder result = new StringBuilder();
        while(scanner.hasNextLine()){
            result.append(scanner.nextLine()).append(System.lineSeparator());
        }
        return result.toString();
    }


    public static String pathCombine(String... paths)
    {
        return Arrays.stream(paths).collect(Collectors.joining("/"));
    }

    public static URI getFileUri(String filePath) throws URISyntaxException {
        URL a= BFIDEServer.class.getResource(filePath);
        if (a==null){
            return null;
        }else{
            return a.toURI();
        }
    }
    public static <T> T[] concat(T[] first, T[] second) {
        T[] result = Arrays.copyOf(first, first.length + second.length);
        System.arraycopy(second, 0, result, first.length, second.length);
        return result;
    }


}
