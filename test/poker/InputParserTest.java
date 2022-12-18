package poker;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import poker.hand.Card;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class InputParserTest {
    List<String> input = List.of("5H 5C 6S 7S KD 2C 3S 8S 8D TD");
    List<String> inputWithTwoLines = List.of("5H 5C 6S 7S KD 2C 3S 8S 8D TD", "5H 5C 6S 7S KD 2C 3S 8S 8D TD");
    List<String> emptyInput = List.of();

    InputParser inputParser;
    List<Card> expectedCards = List.of(
        new Card(5, 'H'), new Card(5, 'C'), new Card(6, 'S'), new Card(7, 'S'), new Card(13, 'D'),
        new Card(2, 'C'), new Card(3, 'S'), new Card(8, 'S'), new Card(8, 'D'), new Card(10, 'D'));

    @Test
    @DisplayName("return list with one element of list of cards when given one input line as a match")
    void testFileParserWithOneInputLine() {
        inputParser = new InputParser(input);
        var parsedMatches = inputParser.parse();

        assertEquals(1, parsedMatches.size());
    }

    @Test
    @DisplayName("return list with two elements of list of cards when given one input lines as matches")
    void testFileParserWithTwoInputLines() {
        inputParser = new InputParser(inputWithTwoLines);
        var parsedMatches = inputParser.parse();

        assertEquals(2, parsedMatches.size());
    }

    @Test
    @DisplayName("return ten cards when given one input line as a match")
    void testFileParserWithTenCardsInOneLine() {
        inputParser = new InputParser(input);
        var parsedMatch = inputParser.parse().get(0);

        assertEquals(expectedCards, parsedMatch);
    }

    @Test
    @DisplayName("return list of two elements with ten cards in each when given two input lines as matches")
    void testFileParserWithTwoInputLinesWithTenCardsEach() {
        inputParser = new InputParser(inputWithTwoLines);
        var parsedFirstLineOfCards = inputParser.parse().get(0);
        var parsedSecondLineOfCards = inputParser.parse().get(1);

        assertEquals(expectedCards, parsedFirstLineOfCards);
        assertEquals(expectedCards, parsedSecondLineOfCards);
    }

    @Test
    @DisplayName("throw an exception when given empty input line")
    void testFileParserWithEmptyInputFile() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new InputParser(emptyInput));

        assertEquals("Incorrect number of cards", exception.getMessage());
    }
}