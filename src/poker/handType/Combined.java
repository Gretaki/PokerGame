package poker.handType;

import java.util.List;

public class Combined implements HandType {
    final Rank rank;
    final HandType firstClass;
    final HandType secondClass;

    public Combined(HandType firstClass, HandType secondClass, Rank rank) {
        this.firstClass = firstClass;
        this.secondClass = secondClass;
        this.rank = rank;
    }

    @Override
    public boolean exist() {
        return this.firstClass.exist() && this.secondClass.exist();
    }

    @Override
    public int getHighestCardValue() {
        if (rank == Rank.STRAIGHT_FLUSH || rank == Rank.FULL_HOUSE) {
            return firstClass.getHighestCardValue();
        }

        throw new IllegalStateException("nesamone");
    }

    @Override
    public List<Card> getHighestCards() {
        if (rank == Rank.STRAIGHT_FLUSH || rank == Rank.FULL_HOUSE) {
            return firstClass.getHighestCards();
        }

        throw new IllegalStateException("nesamone");
    }

    @Override
    public Rank getRank() {
        return rank;
    }
}
