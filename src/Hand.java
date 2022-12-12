import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Hand {
    private Card[] hand;
    private Rank rank;

    private int highestCard;

    public Hand(String handString) {
        String[] cards = handString.split(" ");
        hand = new Card[cards.length];
        for (int i = 0; i < cards.length; i++) {
            hand[i] = new Card(cards[i]);
        }
        Arrays.sort(hand, Comparator.comparing(Card::getValue));
    }

    public Hand(List<Card> listOfCards) {
        hand = listOfCards.toArray(new Card[0]);
    }

    public Hand getHandWithoutHighestCardsInRank() {
        List<HandType> allHandTypes = getAllHandTypes();

        List<Card> cardsToBeDeleted = null;
        for (HandType handType : allHandTypes) {
            if (handType.exist(hand)) {
                cardsToBeDeleted = handType.getHighestCards(hand);
                break;
            }
        }

        if (cardsToBeDeleted == null) {
            throw new IllegalStateException("No such state");
        }

        List<Card> finalCardsToBeDeleted = cardsToBeDeleted;
        List<Card> toKeep = Arrays.stream(hand).filter(a -> !finalCardsToBeDeleted.contains(a)).collect(Collectors.toList());
        return new Hand(toKeep);
    }

    public int getHighestCardValue() {
        List<HandType> allHandTypes = getAllHandTypes();

        for (HandType handType : allHandTypes) {
            if (handType.exist(hand)) {
                return handType.getHighestCardValue(hand);
            }
        }
        throw new IllegalStateException("No such state");
    }

    public Rank getRank() {
        List<HandType> allHandTypes = getAllHandTypes();

        for (HandType handType : allHandTypes) {
            if (handType.exist(hand)) {
                return handType.getRankType();
            }
        }
        throw new IllegalStateException("No such state");
    }

    private List<HandType> getAllHandTypes() {
        List<HandType> allHandTypes = new ArrayList<>();

        allHandTypes.add(new Combined(new Straight(), new Flush(), Rank.STRAIGHT_FLUSH));
        allHandTypes.add(new NOfAKind(4, Rank.FOUR_OF_A_KIND));
        allHandTypes.add(new Combined(
            new NOfAKind(3),
            new NOfAKind(2),
            Rank.FULL_HOUSE));
        allHandTypes.add(new Flush());
        allHandTypes.add(new Straight());
        allHandTypes.add(new NOfAKind(3, Rank.THREE_OF_A_KIND));
        allHandTypes.add(new TwoPairs());
        allHandTypes.add(new NOfAKind(2, Rank.ONE_PAIR));
        allHandTypes.add(new HighCard());

        return allHandTypes;
    }
}
