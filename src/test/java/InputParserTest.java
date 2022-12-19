import hand.Card;
import hand.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class InputParserTest {
    List<String> input = List.of("5H 5C 6S 7S KD 2C 3S 8S 8D TD");
    List<String> inputWithTwoLines = List.of("5H 5C 6S 7S KD 2C 3S 8S 8D TD", "5H 5C 6S 7S KD 2C 3S 8S 8D TD");
    List<String> emptyInput = List.of();

    List<Card> expectedCards = List.of(
        new Card(3, Suit.H), new Card(3, Suit.C), new Card(4, Suit.S), new Card(5, Suit.S), new Card(11, Suit.D),
        new Card(0, Suit.C), new Card(1, Suit.S), new Card(6, Suit.S), new Card(6, Suit.D), new Card(8, Suit.D));

    @Test
    @DisplayName("return list with one element when given one input line")
    void testFileParserWithOneInputLine() {
        InputParser inputParser = new InputParser(input);
        var parsedMatches = inputParser.parse();

        assertEquals(1, parsedMatches.size());
    }

    @Test
    @DisplayName("return list with two elements when given two input lines")
    void testFileParserWithTwoInputLines() {
        InputParser inputParser = new InputParser(inputWithTwoLines);
        var parsedMatches = inputParser.parse();

        assertEquals(2, parsedMatches.size());
    }

    @Test
    @DisplayName("return expected list of cards when given one input line")
    void testFileParserWithTenCardsInOneLine() {
        InputParser inputParser = new InputParser(input);
        var parsedMatch = inputParser.parse().get(0);

        assertEquals(expectedCards, parsedMatch);
    }

    @Test
    @DisplayName("return two lists of expected elements when given two input lines")
    void testFileParserWithTwoInputLinesWithTenCardsEach() {
        var parsed = new InputParser(inputWithTwoLines).parse();

        assertEquals(expectedCards, parsed.get(0));
        assertEquals(expectedCards, parsed.get(1));
    }

    @Test
    @DisplayName("throw an exception when given empty input line")
    void testFileParserWithEmptyInputFile() {
        assertThrows(IllegalArgumentException.class, () -> new InputParser(emptyInput));
    }
}
