package poker.handType;

import java.util.ArrayList;
import java.util.List;

public class HighCard implements HandType {

    @Override
    public Rank getRankType() {
        return Rank.HIGH_CARD;
    }

    @Override
    public boolean exist(List<Card> hand) {
        return true;
    }

    @Override
    public int getHighestCardValue(List<Card> hand) {
        return hand.get(hand.size() - 1).getValue();
    }

    @Override
    public List<Card> getHighestCards(List<Card> hand) {
        List<Card> highestCards = new ArrayList<>();
        highestCards.add(hand.get(hand.size() - 1));
        return highestCards;
    }
}
