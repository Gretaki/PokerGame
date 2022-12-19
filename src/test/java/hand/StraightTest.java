package hand;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class StraightTest {
    Card highCard = new Card(6, Suit.D);
    List<Card> cardsWithStraight = List.of(
        new Card(2, Suit.S), new Card(3, Suit.D), new Card(4, Suit.H), new Card(5, Suit.D), highCard);
    List<Card> cardsWithoutStraight = List.of(
        new Card(4, Suit.D), new Card(6, Suit.S), new Card(9, Suit.H), new Card(12, Suit.H), new Card(12, Suit.C));

    @Test
    @DisplayName("return non empty optional when cards have straight")
    void testBuildWithStraightCards() {
        Optional<HandType> straight = Straight.build(cardsWithStraight);
        assertTrue(straight.isPresent());
    }

    @Test
    @DisplayName("return empty optional when no straight in cards")
    void testBuildWithoutStraightCards() {
        Optional<HandType> straight = Straight.build(cardsWithoutStraight);
        assertFalse(straight.isPresent());
    }

    @Test
    @DisplayName("return highest card value when cards have straight")
    void testGetHighestCardValueWithStraightCards() {
        Optional<Integer> straightHighestCardValue = Straight.build(cardsWithStraight).map(HandType::getHighestCardValue);
        assertEquals(Optional.of(highCard.value()), straightHighestCardValue);
    }

    @Test
    @DisplayName("return highest card when cards have straight")
    void testGetHighestCardsWithStraightCards() {
        Optional<List<Card>> straightHighestCards = Straight.build(cardsWithStraight).map(HandType::getHighestCards);
        assertEquals(Optional.of(List.of(highCard)), straightHighestCards);
    }

    @Test
    @DisplayName("return straight rank when cards have straight")
    void testGetRankWithStraightCards() {
        Optional<Rank> straightRank = Straight.build(cardsWithStraight).map(HandType::getRank);
        assertEquals(Optional.of(Rank.STRAIGHT), straightRank);
    }
}
