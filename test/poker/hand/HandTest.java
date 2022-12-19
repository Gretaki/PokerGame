package poker.hand;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HandTest {
    Card secondToHighCard = new Card(12, 'D');
    Card highCard = new Card(13, 'D');
    List<Card> cardsWithFlush = Stream
        .concat(Stream.of(new Card(4, 'D'), new Card(6, 'D'), new Card(9, 'D')), Stream.of(secondToHighCard, highCard))
        .toList();

    @Test
    @DisplayName("return second highest card when cards have flush")
    void testGetHighestCardValueFromGetHandWithoutHighestCardsInFlushRank() {
        Hand flush = new Hand(cardsWithFlush);
        assertEquals(secondToHighCard.value(), flush.getHandWithoutHighestCardsInRank().getHighestCardValue());
    }

    @Test
    @DisplayName("return second highest card when cards have three of a kind")
    void testGetHighestCardValueFromGetHandWithoutHighestCardsInThreeOfAKindRank() {
        List<Card> highCards = List.of(new Card(13, 'H'), new Card(13, 'D'), new Card(13, 'S'));
        Card secondToHighCard = new Card(6, 'D');
        List<Card> cardsWithThreeOfAKind = Stream.concat(Stream.of(
            new Card(4, 'D'), secondToHighCard), highCards.stream()).toList();

        Hand threeOfAKind = new Hand(cardsWithThreeOfAKind);
        assertEquals(secondToHighCard.value(), threeOfAKind.getHandWithoutHighestCardsInRank().getHighestCardValue());
    }

    @Test
    @DisplayName("return highest card value in flush when cards have flush")
    void testGetHighestCardValue() {
        Hand flush = new Hand(cardsWithFlush);
        assertEquals(highCard.value(), flush.getHighestCardValue());
    }

    @Test
    @DisplayName("return Flush in rank when cards' higher hand type is flush")
    void testGetRank() {
        Hand flush = new Hand(cardsWithFlush);
        assertEquals(Rank.FLUSH, flush.getRank());
    }
}