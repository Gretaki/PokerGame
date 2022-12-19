import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MainTest {
    String inputFile = "test/inputTest.txt";

    @Test
    @DisplayName("return number one as player1 win when given input file with one match with player1 win")
    void testMain() throws Exception {
        assertEquals(3, Main.getWinsOfPlayer1(inputFile));
    }
}