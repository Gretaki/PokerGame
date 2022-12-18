package poker.hand;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Flush implements HandType {
    final List<Card> cards;

    private Flush(List<Card> cards) {
        this.cards = cards;
    }

    public static Optional<HandType> build(List<Card> cards) {
        if (exist(cards)) {
            return Optional.of(new Flush(cards));
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
        return Collections.singletonList(cards.get(cards.size() - 1));
    }

    @Override
    public Rank getRank() {
        return Rank.FLUSH;
    }

    private static boolean exist(List<Card> cards) {
        return cards.stream()
            .map(Card::suit)
            .collect(Collectors.toSet())
            .size() == 1;
    }
}
