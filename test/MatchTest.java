import hand.Card;
import hand.Hand;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class MatchTest {
    Hand play1Hand = new Hand(List.of(
        new Card(4, 'D'), new Card(6, 'S'), new Card(9, 'H'), new Card(12, 'H'), new Card(12, 'C')));
    Hand player2Hand = new Hand(List.of(
        new Card(3, 'D'), new Card(6, 'D'), new Card(7, 'H'), new Card(12, 'D'), new Card(12, 'S')));


    @Test
    @DisplayName("return true as player1 win when both players have pair of Queens and player1 has higher card")
    void player1Wins() {
        Match match = new Match(play1Hand, player2Hand);
        assertTrue(match.player1Wins());
    }
}
