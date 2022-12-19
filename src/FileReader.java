import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileReader {
    private final String inputFilePath;

    public FileReader(String inputFilePath) {
        this.inputFilePath = inputFilePath;
    }

    public List<String> read() throws FileNotFoundException {
        File file = new File(inputFilePath);
        Scanner sc = new Scanner(file);
        var input = new ArrayList<String>();

        while (sc.hasNextLine()) {
            String inputLine = sc.nextLine().trim();
            input.add(inputLine);
        }
        
        sc.close();
        return input;
    }
}
