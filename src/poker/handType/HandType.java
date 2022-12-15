package poker.handType;

import java.util.List;

public interface HandType {
    boolean exist();
    
    int getHighestCardValue();
    
    List<Card> getHighestCards();

    Rank getRank();
}
