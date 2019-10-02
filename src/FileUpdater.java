import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileUpdater {

    // Path to data file
    private static Path p = Paths.get("src", "data.txt");

    // Read datafile return List
    public static List<String> readData() {
        List<String> lines = new ArrayList<>();

        try {
            lines = Files.readAllLines(p);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lines;
    }

}
