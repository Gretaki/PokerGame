package poker.handType;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class NOfAKind implements HandType {
    int number;
    Rank rank;

    public NOfAKind(int number) {
        this.number = number;
    }

    public NOfAKind(int number, Rank rank) {
        this.number = number;
        this.rank = rank;
    }

    @Override
    public Rank getRankType() {
        return rank;
    }

    @Override
    public boolean exist(List<Card> hand) {
        return hand.stream()
            .collect(Collectors.groupingBy(Card::getValue))
            .values()
            .stream()
            .anyMatch(group -> group.size() == number);
    }

    @Override
    public int getHighestCardValue(List<Card> hand) {
        Optional<List<Card>> a = getCards(hand);
        return a.get().get(0).getValue();

    }

    @Override
    public List<Card> getHighestCards(List<Card> hand) {
        return getCards(hand).get();
    }

    private Optional<List<Card>> getCards(List<Card> hand) {
        return hand.stream()
            .collect(Collectors.groupingBy(Card::getValue))
            .values()
            .stream()
            .filter(group -> group.size() == number).findFirst();
    }
}
