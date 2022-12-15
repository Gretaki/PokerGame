package poker.handType;

import java.util.List;

public interface HandType {
    Rank getRankType();
    boolean exist(List<Card> hand);
    
    int getHighestCardValue(List<Card> hand);
    
    List<Card> getHighestCards(List<Card> hand);
}
