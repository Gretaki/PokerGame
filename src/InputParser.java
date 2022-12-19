import hand.Card;
import hand.Suit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class InputParser {
    private final List<String> inputLines;

    public InputParser(List<String> inputLines) {
        if (inputLines.size() > 0) {
            this.inputLines = inputLines;
        } else throw new IllegalArgumentException("Empty input list");
    }

    public List<List<Card>> parse() {
        List<List<Card>> result = new ArrayList<>();
        inputLines.forEach(inputLine -> result.add(parseCards(inputLine.trim())));

        return result;
    }

    private List<Card> parseCards(String inputLine) {
        var inputCards = inputLine.split(" ");
        var cards = new ArrayList<Card>();

        for (String card : inputCards) {
            cards.add(parseCard(card));
        }

        return cards;
    }

    private Card parseCard(String card) {
        int value = Constants.CARD_FACES_ASCENDING.indexOf(card.charAt(0));
        Suit suit = Suit.valueOf(String.valueOf(card.charAt(1)));
        return new Card(value, suit);
    }
}
