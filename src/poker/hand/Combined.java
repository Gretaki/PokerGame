package poker.hand;

import java.util.List;
import java.util.Optional;

public class Combined implements HandType {
    final Rank rank;
    final HandType firstClass;
    final HandType secondClass;

    private Combined(HandType firstClass, HandType secondClass, Rank rank) {
        this.firstClass = firstClass;
        this.secondClass = secondClass;
        this.rank = rank;
    }

    public static Optional<HandType> build(Optional<HandType> maybeFirstClass, Optional<HandType> maybeSecondClass, Rank rank) {
        return maybeFirstClass.flatMap(firstClass -> maybeSecondClass.map(secondClass -> new Combined(firstClass, secondClass, rank)));
    }
    
    @Override
    public int getHighestCardValue() {
        if (firstClass.getRank().number > secondClass.getRank().number) {
            return firstClass.getHighestCardValue();
        }
        return secondClass.getHighestCardValue();
    }

    @Override
    public List<Card> getHighestCards() {
        if (firstClass.getRank().number > secondClass.getRank().number) {
            return firstClass.getHighestCards();
        }
        return secondClass.getHighestCards();
    }

    @Override
    public Rank getRank() {
        return rank;
    }
}
