package poker;

import poker.handType.Card;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class FileReader {
    String inputFile;

    public FileReader(String inputFile) {
        this.inputFile = inputFile;
    }

    public List<List<Hand>> read() throws FileNotFoundException {
        File file = new File(inputFile);
        Scanner sc = new Scanner(file);

        List<List<Hand>> input = new ArrayList<>();

        while (sc.hasNextLine()) {
            String twoHandsInput = sc.nextLine();

            List<Hand> inputLine = new ArrayList<>();
            String player1Input = twoHandsInput.substring(0, twoHandsInput.length() / 2).trim();
            String player2Input = twoHandsInput.substring(twoHandsInput.length() / 2).trim();

            Hand player1Hand = parseHand(player1Input);
            Hand player2Hand = parseHand(player2Input);

            inputLine.add(player1Hand);
            inputLine.add(player2Hand);

            input.add(inputLine);
        }
        return input;
    }

    private Hand parseHand(String handInput) {
        String[] cards = handInput.split(" ");
        List<Card> hand = new ArrayList<>();

        for (String card : cards) {
            hand.add(parseCard(card));
        }

        hand.sort(Comparator.comparing(Card::getValue));

        return new Hand(hand);
    }

    private Card parseCard(String card) {
        int value = Constant.CARD_VALUES.indexOf(card.charAt(0)) + 2;
        char suit = card.charAt(1);
        return new Card(value, suit);
    }
}
