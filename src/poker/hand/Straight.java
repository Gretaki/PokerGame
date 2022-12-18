package poker.hand;

import java.util.*;
import java.util.stream.Collectors;

public class Straight implements HandType {
    final List<Card> cards;

    private Straight(List<Card> cards) {
        this.cards = cards;
    }

    public static Optional<HandType> build(List<Card> cards) {
        if (exist(cards)) {
            return Optional.of(new Straight(cards));
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
        return Rank.STRAIGHT;
    }

    private static boolean exist(List<Card> cards) {
        int min = cards.get(0).value();
        int max = cards.get(cards.size() - 1).value();
        Set<Integer> values = cards.stream().map(Card::value).collect(Collectors.toSet());
        var valuesWithAceAsOne = new HashSet<>(Arrays.asList(2, 3, 4, 5, 14));

        return values.size() == 5
            && (min == max - 4) || (values.equals(valuesWithAceAsOne));
    }
}
