package hand;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class FlushTest {
    Card highCard = new Card(13, Suit.D);
    List<Card> cardsWithFlush = List.of(
        new Card(4, Suit.D), new Card(6, Suit.D), new Card(9, Suit.D), new Card(12, Suit.D), highCard);
    List<Card> cardsWithoutFlush = List.of(
        new Card(4, Suit.D), new Card(6, Suit.S), new Card(9, Suit.H), new Card(12, Suit.H), new Card(12, Suit.C));

    @Test
    @DisplayName("return non empty optional when cards have flush")
    void testBuildWithFlushCards() {
        Optional<HandType> flush = Flush.build(cardsWithFlush);
        assertTrue(flush.isPresent());
    }

    @Test
    @DisplayName("return empty optional when no flush in cards")
    void testBuildWithoutFlushCards() {
        Optional<HandType> flush = Flush.build(cardsWithoutFlush);
        assertFalse(flush.isPresent());
    }

    @Test
    @DisplayName("return highest card value when cards have flush")
    void testGetHighestCardValueWithFlushCards() {
        Optional<Integer> flushHighestCardValue = Flush.build(cardsWithFlush).map(HandType::getHighestCardValue);
        assertEquals(Optional.of(highCard.value()), flushHighestCardValue);
    }

    @Test
    @DisplayName("return highest card when cards have flush")
    void testGetHighestCardsWithFlushCards() {
        Optional<List<Card>> flushHighestCards = Flush.build(cardsWithFlush).map(HandType::getHighestCards);
        assertEquals(Optional.of(List.of(highCard)), flushHighestCards);
    }

    @Test
    @DisplayName("return flush rank when cards have flush")
    void testGetRankWithFlushCards() {
        Optional<Rank> flushRank = Flush.build(cardsWithFlush).map(HandType::getRank);
        assertEquals(Optional.of(Rank.FLUSH), flushRank);
    }
}
