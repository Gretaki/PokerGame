package poker.handType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Flush implements HandType {
    @Override
    public Rank getRankType() {
        return Rank.FLUSH;
    }

    @Override
    public boolean exist(List<Card> hand) {
        return hand.stream()
            .map(Card::getSuit)
            .collect(Collectors.toSet())
            .size() == 1;
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
