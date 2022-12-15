package poker.handType;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Flush implements HandType {
    static final Rank rank = Rank.FLUSH;
    final List<Card> cards;

    public Flush(List<Card> cards) {
        this.cards = cards;
    }

    @Override
    public boolean exist() {
        return cards.stream()
            .map(Card::suit)
            .collect(Collectors.toSet())
            .size() == 1;
    }

    @Override
    public int getHighestCardValue() {
        return cards.get(cards.size() - 1).value();
    }

    @Override
    public List<Card> getHighestCards() {
        return Collections.singletonList(cards.get(cards.size() - 1));
    }

    @Override
    public Rank getRank() {
        return rank;
    }
}
