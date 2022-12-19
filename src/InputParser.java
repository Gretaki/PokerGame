import hand.Card;

import java.util.ArrayList;
import java.util.List;

public class InputParser {
    private final List<String> inputLines;

    public InputParser(List<String> inputLines) {
        if (inputLines.size() > 0) {
            this.inputLines = inputLines;
        } else throw new IllegalArgumentException("Incorrect number of cards");
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
        int value = Constant.CARD_FACES_ASCENDING.indexOf(card.charAt(0)) + 2;
        char suit = card.charAt(1);
        return new Card(value, suit);
    }
}
