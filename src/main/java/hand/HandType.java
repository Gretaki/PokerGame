package hand;

import java.util.List;

public interface HandType {
    int getHighestCardValue();
    
    List<Card> getHighestCards();

    Rank getRank();
}
