import hand.Card;
import hand.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PlayPokerTest {
    List<Card> inputCardsWithPlayer1Win = List.of(
        new Card(2, Suit.C), new Card(3, Suit.S), new Card(8, Suit.S), new Card(8, Suit.D), new Card(10, Suit.D), 
        new Card(5, Suit.H), new Card(5, Suit.C), new Card(6, Suit.S), new Card(7, Suit.S), new Card(13, Suit.D)
    );

    List<Card> inputCardsWithPlayer2Win = List.of(
        new Card(5, Suit.H), new Card(5, Suit.C), new Card(6, Suit.S), new Card(7, Suit.S), new Card(13, Suit.D),
        new Card(2, Suit.C), new Card(3, Suit.S), new Card(8, Suit.S), new Card(8, Suit.D), new Card(10, Suit.D)
    );

    @Test
    @DisplayName("return one win when player1 has higher cards")
    void testPlayPokerWithPlayer1Win() {
        PlayPoker playPoker = new PlayPoker(List.of(inputCardsWithPlayer1Win));
        var winsOfPlayer1 = playPoker.countPlayer1Wins();
        assertEquals(1, winsOfPlayer1);
    }

    @Test
    @DisplayName("return two wins when player1 has higher cards in two matches")
    void testPlayPokerWithPlayer1Wins() {
        PlayPoker playPoker = new PlayPoker(List.of(inputCardsWithPlayer1Win, inputCardsWithPlayer1Win));
        var winsOfPlayer1 = playPoker.countPlayer1Wins();
        assertEquals(2, winsOfPlayer1);
    }

    @Test
    @DisplayName("return zero wins when player2 has higher cards")
    void testPlayPokerWithPlayer1Loss() {
        PlayPoker playPoker = new PlayPoker(List.of(inputCardsWithPlayer2Win));
        var winsOfPlayer1 = playPoker.countPlayer1Wins();
        assertEquals(0, winsOfPlayer1);
    }
}
