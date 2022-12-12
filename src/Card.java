public class Card {
    private int value;
    private char suit;
    private final String VALUES = "23456789TJQKA";
    private final String SUITS = "CHSD";

    public Card(String card) {
        this.value = VALUES.indexOf(card.charAt(0))+2;
        this.suit = card.charAt(1);
        System.out.println(value + " " + suit);
    }

    public int getValue() {
        return value;
    }

    public char getSuit() {
        return suit;
    }
}
