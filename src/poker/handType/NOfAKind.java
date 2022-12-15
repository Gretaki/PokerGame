package poker.handType;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class NOfAKind implements HandType {
    final Rank rank;
    final List<Card> cards;
    final int number;

    public NOfAKind(int number, List<Card> cards) {
        this.number = number;
        this.cards = cards;
        this.rank = null;
    }

    public NOfAKind(int number, Rank rank, List<Card> cards) {
        this.number = number;
        this.rank = rank;
        this.cards = cards;
    }


    @Override
    public boolean exist() {
        return cards.stream()
            .collect(Collectors.groupingBy(Card::value))
            .values()
            .stream()
            .anyMatch(group -> group.size() == number);
    }

    @Override
    public int getHighestCardValue() {
        Optional<List<Card>> a = getCards(cards);
        return a.get().get(0).value();

    }

    @Override
    public List<Card> getHighestCards() {
        return getCards(cards).get();
    }

    @Override
    public Rank getRank() {
        return rank;
    }

    private Optional<List<Card>> getCards(List<Card> hand) {
        return hand.stream()
            .collect(Collectors.groupingBy(Card::value))
            .values()
            .stream()
            .filter(group -> group.size() == number).findFirst();
    }
}
