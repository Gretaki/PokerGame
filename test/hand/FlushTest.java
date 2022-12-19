package hand;

import hand.Card;
import hand.Flush;
import hand.HandType;
import hand.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class FlushTest {
    Card highCard = new Card(13, 'D');
    List<Card> cardsWithFlush = List.of(
        new Card(4, 'D'), new Card(6, 'D'), new Card(9, 'D'), new Card(12, 'D'), highCard);
    List<Card> cardsWithoutFlush = List.of(
        new Card(4, 'D'), new Card(6, 'S'), new Card(9, 'H'), new Card(12, 'H'), new Card(12, 'C'));

    @Test
    @DisplayName("return true in flush optional when cards have flush")
    void testBuildWithFlushCards() {
        Optional<HandType> flush = Flush.build(cardsWithFlush);
        assertTrue(flush.isPresent());
    }

    @Test
    @DisplayName("return false in flush optional when no flush in cards")
    void testBuildWithoutFlushCards() {
        Optional<HandType> flush = Flush.build(cardsWithoutFlush);
        assertFalse(flush.isPresent());
    }

    @Test
    @DisplayName("return highest card value when cards have flush")
    void getHighestCardValueWithFlushCards() {
        Optional<Integer> flushHighestCardValue = Flush.build(cardsWithFlush).map(HandType::getHighestCardValue);
        assertEquals(Optional.of(highCard.value()), flushHighestCardValue);
    }

    @Test
    @DisplayName("return optional empty in highest card value when no flush in cards")
    void getHighestCardValueWithoutFlushCards() {
        Optional<Integer> flushHighestCardValue = Flush.build(cardsWithoutFlush).map(HandType::getHighestCardValue);
        assertEquals(Optional.empty(), flushHighestCardValue);
    }

    @Test
    @DisplayName("return highest card when cards have flush")
    void getHighestCardsWithFlushCards() {
        Optional<List<Card>> flushHighestCards = Flush.build(cardsWithFlush).map(HandType::getHighestCards);
        assertEquals(Optional.of(List.of(highCard)), flushHighestCards);
    }

    @Test
    @DisplayName("return optional empty in highest card when no flush in cards")
    void getHighestCardsWithoutFlushCards() {
        Optional<List<Card>> flushHighestCards = Flush.build(cardsWithoutFlush).map(HandType::getHighestCards);
        assertEquals(Optional.empty(), flushHighestCards);
    }

    @Test
    @DisplayName("return flush rank when cards have flush")
    void testGetRankWithFlushCards() {
        Optional<Rank> flushRank = Flush.build(cardsWithFlush).map(HandType::getRank);
        assertEquals(Optional.of(Rank.FLUSH), flushRank);
    }

    @Test
    @DisplayName("return optional empty in rank when no flush in cards")
    void testGetRankWithoutFlushCards() {
        Optional<Rank> flushRank = Flush.build(cardsWithoutFlush).map(HandType::getRank);
        assertEquals(Optional.empty(), flushRank);
    }
}