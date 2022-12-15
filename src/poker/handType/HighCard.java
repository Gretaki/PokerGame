package poker.handType;

import java.util.ArrayList;
import java.util.List;

public class HighCard implements HandType {
    static final Rank rank = Rank.HIGH_CARD;
    final List<Card> cards;

    public HighCard(List<Card> cards) {
        this.cards = cards;
    }

    @Override
    public boolean exist() {
        return true;
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
        return rank;
    }
}
