package poker.handType;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TwoPairs implements HandType {
    static final Rank rank = Rank.TWO_PAIRS;
    final List<Card> cards;

    public TwoPairs(List<Card> cards) {
        this.cards = cards;
    }

    @Override
    public boolean exist() {
        return getTwoPairs(cards).count() == 2;
    }

    @Override
    public int getHighestCardValue() {
        return getTwoPairs(cards)
            .max(Comparator.comparingInt(one -> one.get(0).value()))
            .get()
            .get(0)
            .value();
    }

    @Override
    public List<Card> getHighestCards() {
        return getTwoPairs(cards)
            .max(Comparator.comparingInt(one -> one.get(0).value())).get();
    }

    @Override
    public Rank getRank() {
        return rank;
    }

    private static Stream<List<Card>> getTwoPairs(List<Card> hand) {
        return hand.stream()
            .collect(Collectors.groupingBy(Card::value))
            .values()
            .stream()
            .filter(group -> group.size() == 2);
    }
}
