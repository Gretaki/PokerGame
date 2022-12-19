import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MainTest {
    String inputFile = "src/test/inputTest.txt";

    @Test
    @DisplayName("return player1 win count")
    void testMain() throws Exception {
        assertEquals(3, Main.getWinsOfPlayer1(inputFile));
    }
}
