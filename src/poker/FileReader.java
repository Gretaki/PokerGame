package poker;

import poker.handType.Card;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class FileReader {
    final String inputFile;

    public FileReader(String inputFile) {
        this.inputFile = inputFile;
    }

    public List<Match> read() throws FileNotFoundException {
        File file = new File(inputFile);
        Scanner sc = new Scanner(file);

        var input = new ArrayList<Match>();

        while (sc.hasNextLine()) {
            String twoHandsInput = sc.nextLine();

            String player1Input = twoHandsInput.substring(0, twoHandsInput.length() / 2).trim();
            String player2Input = twoHandsInput.substring(twoHandsInput.length() / 2).trim();

            Hand player1Hand = parseHand(player1Input);
            Hand player2Hand = parseHand(player2Input);

            input.add(new Match(player1Hand, player2Hand));
        }
        return input;
    }

    private Hand parseHand(String handInput) {
        var cards = handInput.split(" ");
        var hand = new ArrayList<Card>();

        for (String card : cards) {
            hand.add(parseCard(card));
        }

        hand.sort(Comparator.comparing(Card::value));

        return new Hand(hand);
    }

    private Card parseCard(String card) {
        int value = Constant.CARD_FACES_ASCENDING.indexOf(card.charAt(0)) + 2;
        char suit = card.charAt(1);
        return new Card(value, suit);
    }
}
