package poker.hand;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
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
        return getAllValidHandTypes()
            .stream()
            .max(Comparator.comparingInt(type -> type.getRank().number))
            .orElseThrow(() -> new IllegalStateException("No HandType exists within given cards"));
    }

    private List<HandType> getAllValidHandTypes() {
        var allHandTypes = new ArrayList<Optional<HandType>>();

        allHandTypes.add(Combined.build(Straight.build(cards), Flush.build(cards), Rank.STRAIGHT_FLUSH));
        allHandTypes.add(NOfAKind.build(4, cards, Optional.of(Rank.FOUR_OF_A_KIND)));
        allHandTypes.add(Combined.build(
            NOfAKind.build(3, cards, Optional.of(Rank.THREE_OF_A_KIND)),
            NOfAKind.build(2, cards, Optional.of(Rank.ONE_PAIR)),
            Rank.FULL_HOUSE));
        allHandTypes.add(Flush.build(cards));
        allHandTypes.add(Straight.build(cards));
        allHandTypes.add(NOfAKind.build(3, cards, Optional.of(Rank.THREE_OF_A_KIND)));
        allHandTypes.add(TwoPairs.build(cards));
        allHandTypes.add(NOfAKind.build(2, cards, Optional.of(Rank.ONE_PAIR)));
        allHandTypes.add(HighCard.build(cards));

        return allHandTypes.stream().flatMap(Optional::stream).collect(Collectors.toList());
    }
}
