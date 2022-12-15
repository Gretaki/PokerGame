package poker;

import java.util.*;

import static poker.Constant.INPUT_FILE;

public class Main {
    public static void main(String[] args) throws Exception {
        List<List<Hand>> fileInput = new FileReader(INPUT_FILE).read();
        countPlayer1Wins(fileInput);
    }

    public static int countPlayer1Wins(List<List<Hand>> hands) {
        int winsOfPlayer1 = 0;

        for (List<Hand> match : hands) {
            Hand player1Hand = match.get(0);
            Hand player2Hand = match.get(1);

            winsOfPlayer1 = compare(player1Hand, player2Hand, winsOfPlayer1);
        }
        return winsOfPlayer1;
    }

    private static int compare(Hand player1Hand, Hand player2Hand, int winsOfPlayer1) {
        int player1RankNumber = player1Hand.getRank().number;
        int player2RankNumber = player2Hand.getRank().number;

        if (player1RankNumber < player2RankNumber) winsOfPlayer1++;
        else if (player1RankNumber == player2RankNumber) {

            int player1HighestCardValue = player1Hand.getHighestCardValue();
            int player2HighestCardValue = player2Hand.getHighestCardValue();

            if (player1HighestCardValue > player2HighestCardValue) winsOfPlayer1++;
            else if (player1HighestCardValue == player2HighestCardValue) {

                Hand player1LeftoverCards = player1Hand.getHandWithoutHighestCardsInRank();
                Hand player2LeftoverCards = player2Hand.getHandWithoutHighestCardsInRank();
                return compare(player1LeftoverCards, player2LeftoverCards, winsOfPlayer1);
            }
        }

        return winsOfPlayer1;
    }
}
