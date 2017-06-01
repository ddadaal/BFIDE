package viccrubs.bfide.server;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Scanner;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Created by viccrubs on 2017/5/11.
 */
public class Utils {

    public static String downloadContent(String filePath) throws IOException {
        URL url = BFIDEServer.class.getResource(filePath);

        Scanner scanner = new Scanner(url.openStream());
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
        return BFIDEServer.class.getResource(filePath).toURI();
    }

    public static <T> T[] concat(T[] first, T[] second) {
        T[] result = Arrays.copyOf(first, first.length + second.length);
        System.arraycopy(second, 0, result, first.length, second.length);
        return result;
    }
}
