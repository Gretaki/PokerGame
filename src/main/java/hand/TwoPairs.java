package hand;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TwoPairs implements HandType {
    final List<Card> cards;

    private TwoPairs(List<Card> cards) {
        this.cards = cards;
    }

    public static Optional<HandType> build(List<Card> cards) {
        if (getTwoPairs(cards).count() == 2) {
            return Optional.of(new TwoPairs(cards));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public int getHighestCardValue() {
        return getTwoPairs(cards)
            .max(Comparator.comparingInt(one -> one.get(0).value()))
            .map(firstList -> firstList.get(0).value())
            .orElseThrow(() -> new IllegalStateException("No HandType exists within given cards"));
    }

    @Override
    public List<Card> getHighestCards() {
        return getTwoPairs(cards)
            .max(Comparator.comparingInt(one -> one.get(0).value()))
            .orElseThrow(() -> new IllegalStateException("No HandType exists within given cards"));
    }

    @Override
    public Rank getRank() {
        return Rank.TWO_PAIRS;
    }

    private static Stream<List<Card>> getTwoPairs(List<Card> hand) {
        return hand.stream()
            .collect(Collectors.groupingBy(Card::value))
            .values()
            .stream()
            .filter(group -> group.size() == 2);
    }
}
