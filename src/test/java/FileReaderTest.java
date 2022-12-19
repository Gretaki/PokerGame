import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FileReaderTest {
    String inputFile = "src/test/inputTest.txt";
    String nonExistingInputFile = "src/test/inputNonExistingTest.txt";


    @Test
    @DisplayName("return list of strings from correct existing input file")
    void testFileReader() throws FileNotFoundException {
        FileReader fileReader = new FileReader(inputFile);

        List<String> expectedResult = Arrays.asList("5H 5C 6S 7S KD 2C 3S 8S 8D TD",
            "5D 8C 9S JS AC 2C 5C 7D 8S QH",
            "2D 9C AS AH AC 3D 6D 7D TD QD",
            "4D 6S 9H QH QC 3D 6D 7H QD QS",
            "2H 2D 4C 4D 4S 3C 3D 3S 9S 9D");

        assertEquals(expectedResult, fileReader.read());
    }

    @Test
    @DisplayName("throw file not found exception when given file path not exists")
    void testFileReaderWithNonExistingPath() {
        FileReader fileReader = new FileReader(nonExistingInputFile);

        assertThrows(FileNotFoundException.class, fileReader::read);
    }
}
