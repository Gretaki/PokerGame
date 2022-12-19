package hand;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class HighCardTest {
    Card highCard = new Card(13, Suit.D);
    List<Card> cardsWithHighCard = List.of(
        new Card(4, Suit.H), new Card(6, Suit.H), new Card(9, Suit.D), new Card(12, Suit.D), highCard);
    List<Card> cardsWithoutHighCard = List.of();

    @Test
    @DisplayName("return non empty optional when cards have high card")
    void testBuildWithHighCards() {
        Optional<HandType> highCard = HighCard.build(cardsWithHighCard);
        assertTrue(highCard.isPresent());
    }

    @Test
    @DisplayName("return empty optional when no high card in cards")
    void testBuildWithoutHighCards() {
        Optional<HandType> highCard = HighCard.build(cardsWithoutHighCard);
        assertFalse(highCard.isPresent());
    }

    @Test
    @DisplayName("return highest card value when cards have high card")
    void testGetHighestCardValue() {
        Optional<Integer> highCardHighestCardValue = HighCard.build(cardsWithHighCard).map(HandType::getHighestCardValue);
        assertEquals(Optional.of(highCard.value()), highCardHighestCardValue);
    }

    @Test
    @DisplayName("return highest card when cards have high card")
    void testGetHighestCards() {
        Optional<List<Card>> highCardHighestCards = HighCard.build(cardsWithHighCard).map(HandType::getHighestCards);
        assertEquals(Optional.of(List.of(highCard)), highCardHighestCards);
    }

    @Test
    @DisplayName("return high card rank when cards have high card")
    void testGetRank() {
        Optional<Rank> highCardRank = HighCard.build(cardsWithHighCard).map(HandType::getRank);
        assertEquals(Optional.of(Rank.HIGH_CARD), highCardRank);
    }
}
