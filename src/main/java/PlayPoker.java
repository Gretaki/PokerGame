import hand.Card;
import hand.Hand;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class PlayPoker {
    private final List<Match> matches;

    public PlayPoker(List<List<Card>> inputCards) {
        matches = inputCards.stream().map(cardsLine -> {
            var player1Hand = parseHand(cardsLine.subList(0, Constants.HAND_SIZE));
            var player2Hand = parseHand(cardsLine.subList(Constants.HAND_SIZE, cardsLine.size()));
            return new Match(player1Hand, player2Hand);
        }).collect(Collectors.toList());
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
