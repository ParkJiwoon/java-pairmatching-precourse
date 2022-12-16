package pairmatching.util;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

public class FileHelper {

    public static List<String> readFileLines(String fileName) {
        try {
            return Files.readAllLines(Paths.get(fileName));
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }
}
