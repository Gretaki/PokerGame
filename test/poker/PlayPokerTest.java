package poker;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import poker.hand.Card;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlayPokerTest {
    List<Card> inputCardsWithPlayer1Win = List.of(
        new Card(2, 'C'), new Card(3, 'S'), new Card(8, 'S'), new Card(8, 'D'), new Card(10, 'D'), 
        new Card(5, 'H'), new Card(5, 'C'), new Card(6, 'S'), new Card(7, 'S'), new Card(13, 'D')
    );

    List<Card> inputCardsWithPlayer2Win = List.of(
        new Card(5, 'H'), new Card(5, 'C'), new Card(6, 'S'), new Card(7, 'S'), new Card(13, 'D'),
        new Card(2, 'C'), new Card(3, 'S'), new Card(8, 'S'), new Card(8, 'D'), new Card(10, 'D')
    );

    @Test
    @DisplayName("return number one as player1 win when player1 has higher cards")
    void testPlayPokerWithPlayer1Win() {
        PlayPoker playPoker = new PlayPoker(List.of(inputCardsWithPlayer1Win));
        var winsOfPlayer1 = playPoker.countPlayer1Wins();
        assertEquals(1, winsOfPlayer1);
    }

    @Test
    @DisplayName("return number two as player1 win when player1 has higher cards in two matches")
    void testPlayPokerWithPlayer1Wins() {
        PlayPoker playPoker = new PlayPoker(List.of(inputCardsWithPlayer1Win, inputCardsWithPlayer1Win));
        var winsOfPlayer1 = playPoker.countPlayer1Wins();
        assertEquals(2, winsOfPlayer1);
    }

    @Test
    @DisplayName("return number zero as player1 lose when player2 has higher cards")
    void testPlayPokerWithPlayer1Loss() {
        PlayPoker playPoker = new PlayPoker(List.of(inputCardsWithPlayer2Win));
        var winsOfPlayer1 = playPoker.countPlayer1Wins();
        assertEquals(0, winsOfPlayer1);
    }
}