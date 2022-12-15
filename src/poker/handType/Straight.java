package poker.handType;

import java.util.*;
import java.util.stream.Collectors;

public class Straight implements HandType {

    @Override
    public Rank getRankType() {
        return Rank.STRAIGHT;
    }

    @Override
    public boolean exist(List<Card> hand) {
        int min = hand.get(0).getValue();
        int max = hand.get(hand.size() - 1).getValue();
        Set<Integer> values = hand.stream().map(Card::getValue).collect(Collectors.toSet());
        Set<Integer> valuesWithAceAsOne = new HashSet<>(Arrays.asList(2, 3, 4, 5, 14));

        return values.size() == 5
            && (min == max - 4) || (values.equals(valuesWithAceAsOne));
    }

    @Override
    public int getHighestCardValue(List<Card> hand) {
        return hand.get(hand.size() - 1).getValue();
    }

    @Override
    public List<Card> getHighestCards(List<Card> hand) {
        return new ArrayList<>(Collections.singletonList(hand.get(hand.size() - 1)));
    }
}
