package poker.handType;

import java.util.List;

public class Combined implements HandType {
    HandType firstClass;
    HandType secondClass;

    Rank rank;

    public Combined(HandType firstClass, HandType secondClass, Rank rank) {
        this.firstClass = firstClass;
        this.secondClass = secondClass;
        this.rank = rank;
    }

    @Override
    public Rank getRankType() {
        return rank;
    }

    @Override
    public boolean exist(List<Card> hand) {
        return this.firstClass.exist(hand) && this.secondClass.exist(hand);
    }

    @Override
    public int getHighestCardValue(List<Card> hand) {
        if (rank == Rank.STRAIGHT_FLUSH || rank == Rank.FULL_HOUSE) {
            return firstClass.getHighestCardValue(hand);
        }

        throw new IllegalStateException("nesamone");
    }

    @Override
    public List<Card> getHighestCards(List<Card> hand) {
        if (rank == Rank.STRAIGHT_FLUSH || rank == Rank.FULL_HOUSE) {
            return firstClass.getHighestCards(hand);
        }

        throw new IllegalStateException("nesamone");
    }
}
