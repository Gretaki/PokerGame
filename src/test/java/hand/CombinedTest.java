package hand;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class CombinedTest {
    List<Card> highCards = List.of(new Card(13, Suit.S), new Card(13, Suit.H), new Card(13, Suit.D));

    @Test
    @DisplayName("return non empty optional when both hand types are non empty")
    void testBuildWithBothPresent() {
        Optional<HandType> fullHouse = Combined
            .build(
                Optional.of(new DummyHandType(13, highCards, Rank.THREE_OF_A_KIND)),
                Optional.of(new DummyHandType(4, highCards, Rank.ONE_PAIR)),
                Rank.FULL_HOUSE);
        assertTrue(fullHouse.isPresent());
    }

    @Test
    @DisplayName("return empty optional when one of hand types is empty")
    void testBuildWithOneEmpty() {
        Optional<HandType> fullHouse = Combined
            .build(Optional.empty(),
                Optional.of(new DummyHandType(4, highCards, Rank.ONE_PAIR)),
                Rank.FULL_HOUSE);
        assertFalse(fullHouse.isPresent());
    }

    @Test
    @DisplayName("return empty optional when both hand types are empty")
    void testBuildWithoutFullHouseInBothCards() {
        Optional<HandType> fullHouse = Combined
            .build(Optional.empty(), Optional.empty(), Rank.FULL_HOUSE);
        assertFalse(fullHouse.isPresent());
    }

    @Test
    @DisplayName("return highest card value when both hand types are present")
    void testGetHighestCardValue() {
        Optional<Integer> fullHouseHighestCardValue = Combined
            .build(
                Optional.of(new DummyHandType(13, highCards, Rank.THREE_OF_A_KIND)),
                Optional.of(new DummyHandType(4, highCards, Rank.ONE_PAIR)),
                Rank.FULL_HOUSE)
            .map(HandType::getHighestCardValue);
        assertEquals(Optional.of(highCards.get(0).value()), fullHouseHighestCardValue);
    }

    @Test
    @DisplayName("return highest card when both hand types are present")
    void testGetHighestCards() {
        Optional<List<Card>> fullHouseHighestCards = Combined
            .build(
                Optional.of(new DummyHandType(13, highCards, Rank.THREE_OF_A_KIND)),
                Optional.of(new DummyHandType(4, highCards, Rank.ONE_PAIR)),
                Rank.FULL_HOUSE)
            .map(HandType::getHighestCards);
        assertEquals(Optional.of(highCards), fullHouseHighestCards);
    }

    @Test
    @DisplayName("return full house rank when cards have full house")
    void testGetRank() {
        Optional<Rank> fullHouseRank = Combined
            .build(
                Optional.of(new DummyHandType(13, highCards, Rank.THREE_OF_A_KIND)),
                Optional.of(new DummyHandType(4, highCards, Rank.ONE_PAIR)),
                Rank.FULL_HOUSE)
            .map(HandType::getRank);
        assertEquals(Optional.of(Rank.FULL_HOUSE), fullHouseRank);
    }
}

class DummyHandType implements HandType {
    int highestCardValue;
    List<Card> highestCards;
    Rank rank;

    public DummyHandType(int highestCardValue, List<Card> highestCards, Rank rank) {
        this.highestCardValue = highestCardValue;
        this.highestCards = highestCards;
        this.rank = rank;
    }

    @Override
    public int getHighestCardValue() {
        return highestCardValue;
    }

    @Override
    public List<Card> getHighestCards() {
        return highestCards;
    }

    @Override
    public Rank getRank() {
        return rank;
    }
}
