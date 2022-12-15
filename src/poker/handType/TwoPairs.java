package poker.handType;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TwoPairs implements HandType {

    @Override
    public Rank getRankType() {
        return Rank.TWO_PAIRS;
    }

    @Override
    public boolean exist(List<Card> hand) {
        return getTwoPairs(hand).count() == 2;
    }

    @Override
    public int getHighestCardValue(List<Card> hand) {
        return getTwoPairs(hand)
            .max(Comparator.comparingInt(one -> one.get(0).getValue()))
            .get()
            .get(0)
            .getValue();
    }

    @Override
    public List<Card> getHighestCards(List<Card> hand) {
        return getTwoPairs(hand)
            .max(Comparator.comparingInt(one -> one.get(0).getValue())).get();
    }

    private static Stream<List<Card>> getTwoPairs(List<Card> hand) {
        return hand.stream()
            .collect(Collectors.groupingBy(Card::getValue))
            .values()
            .stream()
            .filter(group -> group.size() == 2);
    }
}
