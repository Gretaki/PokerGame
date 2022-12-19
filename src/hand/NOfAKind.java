package hand;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class NOfAKind implements HandType {
    final int number;
    final List<Card> cards;
    final Rank rank;

    private NOfAKind(int number, List<Card> cards, Rank rank) {
        this.number = number;
        this.cards = cards;
        this.rank = rank;
    }

    public static Optional<HandType> build(int number, List<Card> cards, Rank rank) {
        if (exist(number, cards)) {
            return Optional.of(new NOfAKind(number, cards, rank));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public int getHighestCardValue() {
        return getHandTypeCards(cards).get(0).value();
    }

    @Override
    public List<Card> getHighestCards() {
        return getHandTypeCards(cards);
    }

    @Override
    public Rank getRank() {
        return rank;
    }

    private static boolean exist(int number, List<Card> cards) {
        return cards.stream()
            .collect(Collectors.groupingBy(Card::value))
            .values()
            .stream()
            .anyMatch(group -> group.size() == number);
    }

    private List<Card> getHandTypeCards(List<Card> hand) {
        return hand.stream()
            .collect(Collectors.groupingBy(Card::value))
            .values()
            .stream()
            .filter(group -> group.size() == number)
            .findFirst()
            .orElseThrow(() -> new IllegalStateException("No HandType exists within given cards"));
    }
}
