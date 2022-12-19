package hand;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class NOfAKindTest {
    int threeOfAKind = 3;
    Rank rank = Rank.THREE_OF_A_KIND;
    List<Card> highCards = List.of(new Card(13, Suit.H), new Card(13, Suit.D), new Card(13, Suit.S));
    List<Card> cardsWithThreeOfAKind = Stream.concat(Stream.of(
        new Card(4, Suit.D), new Card(6, Suit.D)), highCards.stream()).toList();
    List<Card> cardsWithoutThreeOfAKind = List.of(
        new Card(4, Suit.D), new Card(6, Suit.S), new Card(9, Suit.H), new Card(11, Suit.H), new Card(12, Suit.C));

    @Test
    @DisplayName("return non empty optional when cards have three of a kind")
    void testBuildWithNOfAKindCards() {
        Optional<HandType> nOfAKind = NOfAKind.build(threeOfAKind, cardsWithThreeOfAKind, rank);
        assertTrue(nOfAKind.isPresent());
    }

    @Test
    @DisplayName("return empty optional when no three of a kind in cards")
    void testBuildWithoutNOfAKindCards() {
        Optional<HandType> nOfAKind = NOfAKind.build(threeOfAKind, cardsWithoutThreeOfAKind, rank);
        assertFalse(nOfAKind.isPresent());
    }

    @Test
    @DisplayName("return highest card value when cards have three of a kind")
    void testGetHighestCardValueWithNOfAKindCards() {
        Optional<Integer> nOfAKindHighestCardValue = NOfAKind.build(threeOfAKind, cardsWithThreeOfAKind, rank).map(HandType::getHighestCardValue);
        assertEquals(Optional.of(highCards.get(0).value()), nOfAKindHighestCardValue);
    }

    @Test
    @DisplayName("return highest cards when cards have three of a kind")
    void testGetHighestCardsWithNOfAKindCards() {
        Optional<List<Card>> nOfAKindHighestCards = NOfAKind.build(threeOfAKind, cardsWithThreeOfAKind, rank).map(HandType::getHighestCards);
        assertEquals(Optional.of(highCards), nOfAKindHighestCards);
    }

    @Test
    @DisplayName("return three of a kind rank when cards have three of a kind")
    void testGetRankWithNOfAKindCards() {
        Optional<Rank> nOfAKindRank = NOfAKind.build(threeOfAKind, cardsWithThreeOfAKind, rank).map(HandType::getRank);
        assertEquals(Optional.of(Rank.THREE_OF_A_KIND), nOfAKindRank);
    }
}
