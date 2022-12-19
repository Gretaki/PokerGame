package hand;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class TwoPairsTest {
    List<Card> highCards = List.of(new Card(13, 'H'), new Card(13, 'D'));
    List<Card> cardsWithTwoPairs = Stream.concat(Stream.of(
        new Card(4, 'D'), new Card(6, 'D'), new Card(6, 'H')), highCards.stream()).toList();
    List<Card> cardsWithoutTwoPairs = List.of(
        new Card(4, 'D'), new Card(6, 'S'), new Card(9, 'H'), new Card(12, 'H'), new Card(12, 'C'));

    @Test
    @DisplayName("return true in two pairs optional when cards have two pairs")
    void testBuildWithTwoPairsCards() {
        Optional<HandType> twoPairs = TwoPairs.build(cardsWithTwoPairs);
        assertTrue(twoPairs.isPresent());
    }

    @Test
    @DisplayName("return false in two pairs optional when no two pairs in cards")
    void testBuildWithoutTwoPairsCards() {
        Optional<HandType> twoPairs = TwoPairs.build(cardsWithoutTwoPairs);
        assertFalse(twoPairs.isPresent());
    }

    @Test
    @DisplayName("return highest card value when cards have two pairs")
    void getHighestCardValueWithTwoPairsCards() {
        Optional<Integer> twoPairsHighestCardValue = TwoPairs.build(cardsWithTwoPairs).map(HandType::getHighestCardValue);
        assertEquals(Optional.of(highCards.get(0).value()), twoPairsHighestCardValue);
    }

    @Test
    @DisplayName("return optional empty in highest card value when no two pairs in cards")
    void getHighestCardValueWithoutTwoPairsCards() {
        Optional<Integer> twoPairsHighestCardValue = TwoPairs.build(cardsWithoutTwoPairs).map(HandType::getHighestCardValue);
        assertEquals(Optional.empty(), twoPairsHighestCardValue);
    }

    @Test
    @DisplayName("return highest card when cards have two pairs")
    void getHighestCardsWithTwoPairsCards() {
        Optional<List<Card>> twoPairsHighestCards = TwoPairs.build(cardsWithTwoPairs).map(HandType::getHighestCards);
        assertEquals(Optional.of(highCards), twoPairsHighestCards);
    }

    @Test
    @DisplayName("return optional empty in highest card when no two pairs in cards")
    void getHighestCardsWithoutTwoPairsCards() {
        Optional<List<Card>> twoPairsHighestCards = TwoPairs.build(cardsWithoutTwoPairs).map(HandType::getHighestCards);
        assertEquals(Optional.empty(), twoPairsHighestCards);
    }

    @Test
    @DisplayName("return two pairs rank when cards have two pairs")
    void testGetRankWithTwoPairsCards() {
        Optional<Rank> twoPairsRank = TwoPairs.build(cardsWithTwoPairs).map(HandType::getRank);
        assertEquals(Optional.of(Rank.TWO_PAIRS), twoPairsRank);
    }

    @Test
    @DisplayName("return optional empty in rank when no two pairs in cards")
    void testGetRankWithoutTwoPairsCards() {
        Optional<Rank> twoPairsRank = TwoPairs.build(cardsWithoutTwoPairs).map(HandType::getRank);
        assertEquals(Optional.empty(), twoPairsRank);
    }
}
