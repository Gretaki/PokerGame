import org.junit.jupiter.api.Test;
import poker.FileReader;
import poker.Main;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static poker.Constant.INPUT_FILE;

class MainTest {

    @Test
    void main() throws Exception {

        assertEquals(376, Main.countPlayer1Wins(new FileReader(INPUT_FILE).read()));
    }
}