package poker.hand;

public enum Rank {
    STRAIGHT_FLUSH(8),
    FOUR_OF_A_KIND(7),
    FULL_HOUSE(6),
    FLUSH(5),
    STRAIGHT(4),
    THREE_OF_A_KIND(3),
    TWO_PAIRS(2),
    ONE_PAIR(1),
    HIGH_CARD(0);

    public final int number;

    Rank(int number) {
        this.number = number;
    }
}
