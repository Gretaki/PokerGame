package poker;

import poker.handType.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Hand {
    private final List<Card> cards;

    public Hand(List<Card> cards) {
        this.cards = cards;
    }

    public Hand getHandWithoutHighestCardsInRank() {
        var cardsToBeDeleted = getHighestHandType().getHighestCards();
        var cardsToKeep = cards.stream().filter(card -> !cardsToBeDeleted.contains(card)).collect(Collectors.toList());
        return new Hand(cardsToKeep);
    }

    public int getHighestCardValue() {
        return getHighestHandType().getHighestCardValue();
    }

    public Rank getRank() {
        return getHighestHandType().getRank();
    }

    private HandType getHighestHandType() {
        return getAllHandTypes()
            .stream()
            .filter(HandType::exist)
            .findFirst()
            .orElseThrow(() -> new IllegalStateException("No HandType exists within given cards"));
    }

    private List<HandType> getAllHandTypes() {
        var allHandTypes = new ArrayList<HandType>();

        allHandTypes.add(new Combined(new Straight(cards), new Flush(cards), Rank.STRAIGHT_FLUSH));
        allHandTypes.add(new NOfAKind(4, Rank.FOUR_OF_A_KIND, cards));
        allHandTypes.add(new Combined(
            new NOfAKind(3, cards),
            new NOfAKind(2, cards),
            Rank.FULL_HOUSE));
        allHandTypes.add(new Flush(cards));
        allHandTypes.add(new Straight(cards));
        allHandTypes.add(new NOfAKind(3, Rank.THREE_OF_A_KIND, cards));
        allHandTypes.add(new TwoPairs(cards));
        allHandTypes.add(new NOfAKind(2, Rank.ONE_PAIR, cards));
        allHandTypes.add(new HighCard(cards));

        return allHandTypes;
    }
}
