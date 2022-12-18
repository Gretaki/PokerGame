package poker.hand;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class CombinedTest {
    List<Card> highCards = List.of(new Card(13, 'S'), new Card(13, 'H'), new Card(13, 'D'));
    List<Card> cardsWithFullHouse =
        Stream.concat(Stream.of(
            new Card(4, 'H'), new Card(4, 'D')), highCards.stream()).toList();
    List<Card> cardsWithoutFullHouse = List.of(
        new Card(4, 'D'), new Card(6, 'S'), new Card(9, 'H'), new Card(12, 'H'), new Card(12, 'C'));

    @Test
    @DisplayName("return true in full house optional when cards have full house")
    void testBuildWithFullHouseCards() {
        Optional<HandType> fullHouse = Combined
            .build(NOfAKind.build(3, cardsWithFullHouse), NOfAKind.build(2, cardsWithFullHouse), Rank.FULL_HOUSE);
        assertTrue(fullHouse.isPresent());
    }

    @Test
    @DisplayName("return false in full house optional when no full house in cards")
    void testBuildWithoutFullHouseCards() {
        Optional<HandType> fullHouse = Combined
            .build(NOfAKind.build(3, cardsWithoutFullHouse), NOfAKind.build(2, cardsWithoutFullHouse), Rank.FULL_HOUSE);
        assertFalse(fullHouse.isPresent());
    }

    @Test
    @DisplayName("return highest card value when cards have full house")
    void getHighestCardValueWithFullHouseCards() {
        Optional<Integer> fullHouseHighestCardValue = Combined
            .build(NOfAKind.build(3, cardsWithFullHouse), NOfAKind.build(2, cardsWithFullHouse), Rank.FULL_HOUSE)
            .map(HandType::getHighestCardValue);
        assertEquals(Optional.of(highCards.get(0).value()), fullHouseHighestCardValue);
    }

    @Test
    @DisplayName("return optional empty in highest card value when no full house in cards")
    void getHighestCardValueWithoutFullHouseCards() {
        Optional<Integer> fullHouseHighestCardValue = Combined
            .build(NOfAKind.build(3, cardsWithoutFullHouse), NOfAKind.build(2, cardsWithoutFullHouse), Rank.FULL_HOUSE)
            .map(HandType::getHighestCardValue);
        assertEquals(Optional.empty(), fullHouseHighestCardValue);
    }

    @Test
    @DisplayName("return highest card when cards have full house")
    void getHighestCardsWithFullHouseCards() {
        Optional<List<Card>> fullHouseHighestCards = Combined
            .build(
                NOfAKind.build(3, cardsWithFullHouse), 
                NOfAKind.build(2, cardsWithFullHouse), 
                Rank.FULL_HOUSE)
            .map(HandType::getHighestCards);
        assertEquals(Optional.of(highCards), fullHouseHighestCards);
    }

    @Test
    @DisplayName("return optional empty in highest card when no full house in cards")
    void getHighestCardsWithoutFullHouseCards() {
        Optional<List<Card>> fullHouseHighestCards = Combined
            .build(NOfAKind.build(3, cardsWithoutFullHouse), NOfAKind.build(2, cardsWithoutFullHouse), Rank.FULL_HOUSE)
            .map(HandType::getHighestCards);
        assertEquals(Optional.empty(), fullHouseHighestCards);
    }

    @Test
    @DisplayName("return full house rank when cards have full house")
    void testGetRankWithFullHouseCards() {
        Optional<Rank> fullHouseRank = Combined
            .build(NOfAKind.build(3, cardsWithFullHouse), NOfAKind.build(2, cardsWithFullHouse), Rank.FULL_HOUSE)
            .map(HandType::getRank);
        assertEquals(Optional.of(Rank.FULL_HOUSE), fullHouseRank);
    }

    @Test
    @DisplayName("return optional empty in rank when no full house in cards")
    void testGetRankWithoutFullHouseCards() {
        Optional<Rank> fullHouseRank = Combined
            .build(NOfAKind.build(3, cardsWithoutFullHouse), NOfAKind.build(2, cardsWithoutFullHouse), Rank.FULL_HOUSE)
            .map(HandType::getRank);
        assertEquals(Optional.empty(), fullHouseRank);
    }
}