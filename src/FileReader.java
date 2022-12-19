import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class FileReader {
    private final String inputFile;

    public FileReader(String inputFile) {
        this.inputFile = inputFile;
    }

    public List<String> read() throws FileNotFoundException {
        File file = new File(inputFile);
        Scanner sc = new Scanner(file);
        var input = new ArrayList<String>();

        while (sc.hasNextLine()) {
            String inputLine = sc.nextLine().trim();
            input.add(inputLine);
        }
        return input;
    }
}
