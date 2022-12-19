package hand;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class NOfAKindTest {
    int threeOfAKind = 3;
    Rank rank  = Rank.THREE_OF_A_KIND;
    List<Card> highCards = List.of(new Card(13, 'H'), new Card(13, 'D'), new Card(13, 'S'));
    List<Card> cardsWithThreeOfAKind = Stream.concat(Stream.of(
        new Card(4, 'D'), new Card(6, 'D')), highCards.stream()).toList();
    List<Card> cardsWithoutThreeOfAKind = List.of(
        new Card(4, 'D'), new Card(6, 'S'), new Card(9, 'H'), new Card(11, 'H'), new Card(12, 'C'));

    @Test
    @DisplayName("return true in n of a kind optional when cards have three of a kind")
    void testBuildWithNOfAKindCards() {
        Optional<HandType> nOfAKind = NOfAKind.build(threeOfAKind, cardsWithThreeOfAKind, rank);
        assertTrue(nOfAKind.isPresent());
    }

    @Test
    @DisplayName("return false in n of a kind optional when no three of a kind in cards")
    void testBuildWithoutNOfAKindCards() {
        Optional<HandType> nOfAKind = NOfAKind.build(threeOfAKind, cardsWithoutThreeOfAKind, rank);
        assertFalse(nOfAKind.isPresent());
    }

    @Test
    @DisplayName("return highest card value when cards have three of a kind")
    void getHighestCardValueWithNOfAKindCards() {
        Optional<Integer> nOfAKindHighestCardValue = NOfAKind.build(threeOfAKind, cardsWithThreeOfAKind, rank).map(HandType::getHighestCardValue);
        assertEquals(Optional.of(highCards.get(0).value()), nOfAKindHighestCardValue);
    }

    @Test
    @DisplayName("return optional empty in highest card value when no three of a kind in cards")
    void getHighestCardValueWithoutNOfAKindCards() {
        Optional<Integer> nOfAKindHighestCardValue = NOfAKind.build(threeOfAKind, cardsWithoutThreeOfAKind, rank).map(HandType::getHighestCardValue);
        assertEquals(Optional.empty(), nOfAKindHighestCardValue);
    }

    @Test
    @DisplayName("return highest cards when cards have three of a kind")
    void getHighestCardsWithNOfAKindCards() {
        Optional<List<Card>> nOfAKindHighestCards = NOfAKind.build(threeOfAKind, cardsWithThreeOfAKind, rank).map(HandType::getHighestCards);
        assertEquals(Optional.of(highCards), nOfAKindHighestCards);
    }

    @Test
    @DisplayName("return optional empty in highest card when no three of a kind in cards")
    void getHighestCardsWithoutNOfAKindCards() {
        Optional<List<Card>> nOfAKindHighestCards = NOfAKind.build(threeOfAKind, cardsWithoutThreeOfAKind, rank).map(HandType::getHighestCards);
        assertEquals(Optional.empty(), nOfAKindHighestCards);
    }

    @Test
    @DisplayName("return three of a kind rank when cards have three of a kind")
    void testGetRankWithNOfAKindCards() {
        Optional<Rank> nOfAKindRank = NOfAKind.build(threeOfAKind, cardsWithThreeOfAKind, rank).map(HandType::getRank);
        assertEquals(Optional.of(Rank.THREE_OF_A_KIND), nOfAKindRank);
    }

    @Test
    @DisplayName("return optional empty in rank when no three of a kind in cards")
    void testGetRankWithoutNOfAKindCards() {
        Optional<Rank> nOfAKindRank = NOfAKind.build(threeOfAKind, cardsWithoutThreeOfAKind, rank).map(HandType::getRank);
        assertEquals(Optional.empty(), nOfAKindRank);
    }
}
