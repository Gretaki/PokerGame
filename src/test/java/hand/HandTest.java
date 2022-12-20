package hand;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HandTest {
    Card secondToHighCard = new Card(12, Suit.D);
    Card highCard = new Card(13, Suit.D);
    List<Card> cardsWithFlush = Stream
        .concat(Stream.of(new Card(4, Suit.D), new Card(6, Suit.D), new Card(9, Suit.D)), Stream.of(secondToHighCard, highCard))
        .toList();

    @Test
    @DisplayName("return second highest card when cards have flush")
    void testGetHandWithoutHighestCardsInFlushRank() {
        Hand flush = new Hand(cardsWithFlush);
        assertEquals(secondToHighCard.value(), flush.getHandWithoutHighestCardsInRank().getHighestCardValue());
    }

    @Test
    @DisplayName("return second highest card when cards have three of a kind")
    void testGetHandWithoutHighestCardsInThreeOfAKindRank() {
        List<Card> highCards = List.of(new Card(13, Suit.H), new Card(13, Suit.D), new Card(13, Suit.S));
        Card secondToHighCard = new Card(6, Suit.D);
        List<Card> cardsWithThreeOfAKind = Stream.concat(Stream.of(
            new Card(4, Suit.D), secondToHighCard), highCards.stream()).toList();

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
    @DisplayName("return Flush in rank when cards' highest hand type is flush")
    void testGetRank() {
        Hand flush = new Hand(cardsWithFlush);
        assertEquals(Rank.FLUSH, flush.getRank());
    }
}
