package hand;

import hand.Card;
import hand.HandType;
import hand.Rank;
import hand.Straight;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class StraightTest {
    Card highCard = new Card(6, 'D');
    List<Card> cardsWithStraight = List.of(
        new Card(2, 'S'), new Card(3, 'D'), new Card(4, 'H'), new Card(5, 'D'), highCard);
    List<Card> cardsWithoutStraight = List.of(
        new Card(4, 'D'), new Card(6, 'S'), new Card(9, 'H'), new Card(12, 'H'), new Card(12, 'C'));

    @Test
    @DisplayName("return true in straight optional when cards have straight")
    void testBuildWithStraightCards() {
        Optional<HandType> straight = Straight.build(cardsWithStraight);
        assertTrue(straight.isPresent());
    }

    @Test
    @DisplayName("return false in straight optional when no straight in cards")
    void testBuildWithoutStraightCards() {
        Optional<HandType> straight = Straight.build(cardsWithoutStraight);
        assertFalse(straight.isPresent());
    }

    @Test
    @DisplayName("return highest card value when cards have straight")
    void getHighestCardValueWithStraightCards() {
        Optional<Integer> straightHighestCardValue = Straight.build(cardsWithStraight).map(HandType::getHighestCardValue);
        assertEquals(Optional.of(highCard.value()), straightHighestCardValue);
    }

    @Test
    @DisplayName("return optional empty in highest card value when no straight in cards")
    void getHighestCardValueWithoutStraightCards() {
        Optional<Integer> straightHighestCardValue = Straight.build(cardsWithoutStraight).map(HandType::getHighestCardValue);
        assertEquals(Optional.empty(), straightHighestCardValue);
    }

    @Test
    @DisplayName("return highest card when cards have straight")
    void getHighestCardsWithStraightCards() {
        Optional<List<Card>> straightHighestCards = Straight.build(cardsWithStraight).map(HandType::getHighestCards);
        assertEquals(Optional.of(List.of(highCard)), straightHighestCards);
    }

    @Test
    @DisplayName("return optional empty in highest card when no straight in cards")
    void getHighestCardsWithoutStraightCards() {
        Optional<List<Card>> straightHighestCards = Straight.build(cardsWithoutStraight).map(HandType::getHighestCards);
        assertEquals(Optional.empty(), straightHighestCards);
    }

    @Test
    @DisplayName("return straight rank when cards have straight")
    void testGetRankWithStraightCards() {
        Optional<Rank> straightRank = Straight.build(cardsWithStraight).map(HandType::getRank);
        assertEquals(Optional.of(Rank.STRAIGHT), straightRank);
    }

    @Test
    @DisplayName("return optional empty in rank when no straight in cards")
    void testGetRankWithoutStraightCards() {
        Optional<Rank> straightRank = Straight.build(cardsWithoutStraight).map(HandType::getRank);
        assertEquals(Optional.empty(), straightRank);
    }
}