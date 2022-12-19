package hand;

import hand.Card;
import hand.HandType;
import hand.HighCard;
import hand.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class HighCardTest {
    Card highCard = new Card(13, 'D');
    List<Card> cardsWithHighCard = List.of(
        new Card(4, 'H'), new Card(6, 'H'), new Card(9, 'D'), new Card(12, 'D'), highCard);
    List<Card> cardsWithoutHighCard = List.of();

    @Test
    @DisplayName("return true in high card optional when cards have high card")
    void testBuildWithHighCards() {
        Optional<HandType> highCard = HighCard.build(cardsWithHighCard);
        assertTrue(highCard.isPresent());
    }

    @Test
    @DisplayName("return false in high card optional when no high card in cards")
    void testBuildWithoutHighCards() {
        Optional<HandType> highCard = HighCard.build(cardsWithoutHighCard);
        assertFalse(highCard.isPresent());
    }

    @Test
    @DisplayName("return highest card value when cards have high card")
    void getHighestCardValueWithHighCards() {
        Optional<Integer> highCardHighestCardValue = HighCard.build(cardsWithHighCard).map(HandType::getHighestCardValue);
        assertEquals(Optional.of(highCard.value()), highCardHighestCardValue);
    }

    @Test
    @DisplayName("return optional empty in highest card value when no high card in cards")
    void getHighestCardValueWithoutHighCards() {
        Optional<Integer> highCardHighestCardValue = HighCard.build(cardsWithoutHighCard).map(HandType::getHighestCardValue);
        assertEquals(Optional.empty(), highCardHighestCardValue);
    }

    @Test
    @DisplayName("return highest card when cards have high card")
    void getHighestCardsWithHighCards() {
        Optional<List<Card>> highCardHighestCards = HighCard.build(cardsWithHighCard).map(HandType::getHighestCards);
        assertEquals(Optional.of(List.of(highCard)), highCardHighestCards);
    }

    @Test
    @DisplayName("return optional empty in highest card when no high card in cards")
    void getHighestCardsWithoutHighCards() {
        Optional<List<Card>> highCardHighestCards = HighCard.build(cardsWithoutHighCard).map(HandType::getHighestCards);
        assertEquals(Optional.empty(), highCardHighestCards);
    }

    @Test
    @DisplayName("return high card rank when cards have high card")
    void testGetRankWithHighCards() {
        Optional<Rank> highCardRank = HighCard.build(cardsWithHighCard).map(HandType::getRank);
        assertEquals(Optional.of(Rank.HIGH_CARD), highCardRank);
    }

    @Test
    @DisplayName("return optional empty in rank when no high card in cards")
    void testGetRankWithoutHighCards() {
        Optional<Rank> highCardRank = HighCard.build(cardsWithoutHighCard).map(HandType::getRank);
        assertEquals(Optional.empty(), highCardRank);
    }
}