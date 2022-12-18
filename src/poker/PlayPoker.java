package poker;

import poker.hand.Card;
import poker.hand.Hand;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static poker.Constant.HAND_SIZE;

public class PlayPoker {
    private final List<Match> matches;

    public PlayPoker(List<List<Card>> inputCards) {
        var result = new ArrayList<Match>();
        inputCards.forEach(cardsLine -> {
            Hand player1Hand = parseHand(cardsLine.subList(0, HAND_SIZE));
            Hand player2Hand = parseHand(cardsLine.subList(HAND_SIZE, cardsLine.size()));

            result.add(new Match(player1Hand, player2Hand));
        });

        this.matches = result;
    }

    private Hand parseHand(List<Card> cards) {
        var hand = new ArrayList<>(cards);
        hand.sort(Comparator.comparing(Card::value));
        return new Hand(hand);
    }

    public long countPlayer1Wins() {
        return matches
            .stream()
            .filter(Match::player1Wins)
            .count();
    }
}