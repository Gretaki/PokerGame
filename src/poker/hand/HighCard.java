package poker.hand;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class HighCard implements HandType {
    final List<Card> cards;

    private HighCard(List<Card> cards) {
        this.cards = cards;
    }

    public static Optional<HandType> build(List<Card> cards) {
        if (cards.size() > 0) {
            return Optional.of(new HighCard(cards));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public int getHighestCardValue() {
        return cards.get(cards.size() - 1).value();
    }

    @Override
    public List<Card> getHighestCards() {
        var highestCards = new ArrayList<Card>();
        highestCards.add(cards.get(cards.size() - 1));
        return highestCards;
    }

    @Override
    public Rank getRank() {
        return Rank.HIGH_CARD;
    }
}
