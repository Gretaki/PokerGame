import java.util.List;

public interface HandType {
    Rank getRankType();
    boolean exist(Card[] hand);
    
    int getHighestCardValue(Card[] hand);
    
    List<Card> getHighestCards(Card[] hand);
}
