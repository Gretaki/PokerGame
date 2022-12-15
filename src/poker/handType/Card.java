package poker.handType;

public class Card {
    private final int value;
    private final char suit;

    public Card(int value, char suit) {
        this.value = value;
        this.suit = suit;
    }

    public int getValue() {
        return value;
    }

    public char getSuit() {
        return suit;
    }
}
