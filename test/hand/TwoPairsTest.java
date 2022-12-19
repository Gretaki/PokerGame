package hand;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class TwoPairsTest {
    List<Card> highCards = List.of(new Card(13, Suit.H), new Card(13, Suit.D));
    List<Card> cardsWithTwoPairs = Stream.concat(Stream.of(
        new Card(4, Suit.D), new Card(6, Suit.D), new Card(6, Suit.H)), highCards.stream()).toList();
    List<Card> cardsWithoutTwoPairs = List.of(
        new Card(4, Suit.D), new Card(6, Suit.S), new Card(9, Suit.H), new Card(12, Suit.H), new Card(12, Suit.C));

    @Test
    @DisplayName("return non empty optional when cards have two pairs")
    void testBuildWithTwoPairsCards() {
        Optional<HandType> twoPairs = TwoPairs.build(cardsWithTwoPairs);
        assertTrue(twoPairs.isPresent());
    }

    @Test
    @DisplayName("return empty optional when no two pairs in cards")
    void testBuildWithoutTwoPairsCards() {
        Optional<HandType> twoPairs = TwoPairs.build(cardsWithoutTwoPairs);
        assertFalse(twoPairs.isPresent());
    }

    @Test
    @DisplayName("return highest card value when cards have two pairs")
    void testGetHighestCardValueWithTwoPairsCards() {
        Optional<Integer> twoPairsHighestCardValue = TwoPairs.build(cardsWithTwoPairs).map(HandType::getHighestCardValue);
        assertEquals(Optional.of(highCards.get(0).value()), twoPairsHighestCardValue);
    }

    @Test
    @DisplayName("return highest card when cards have two pairs")
    void testGetHighestCardsWithTwoPairsCards() {
        Optional<List<Card>> twoPairsHighestCards = TwoPairs.build(cardsWithTwoPairs).map(HandType::getHighestCards);
        assertEquals(Optional.of(highCards), twoPairsHighestCards);
    }

    @Test
    @DisplayName("return two pairs rank when cards have two pairs")
    void testGetRankWithTwoPairsCards() {
        Optional<Rank> twoPairsRank = TwoPairs.build(cardsWithTwoPairs).map(HandType::getRank);
        assertEquals(Optional.of(Rank.TWO_PAIRS), twoPairsRank);
    }
}
