import hand.Card;
import hand.Hand;
import hand.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MatchTest {
    Hand play1Hand = new Hand(List.of(
        new Card(4, Suit.D), new Card(6, Suit.S), new Card(9, Suit.H), new Card(12, Suit.H), new Card(12, Suit.C)));
    Hand player2Hand = new Hand(List.of(
        new Card(3, Suit.D), new Card(6, Suit.D), new Card(7, Suit.H), new Card(12, Suit.D), new Card(12, Suit.S)));

    @Test
    @DisplayName("return true when both players have pair of Queens and player1 has higher card")
    void testPlayer1Wins() {
        Match match = new Match(play1Hand, player2Hand);
        assertTrue(match.player1Wins());
    }

    @Test
    @DisplayName("return false when both players have pair of Queens and player2 has higher card")
    void testPlayer2Wins() {
        Match match = new Match(player2Hand, play1Hand);
        assertFalse(match.player1Wins());
    }
}
