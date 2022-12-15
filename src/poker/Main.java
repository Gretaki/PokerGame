package poker;

import java.util.*;

import static poker.Constant.INPUT_FILE;

public class Main {
    public static void main(String[] args) throws Exception {
        var input = new FileReader(INPUT_FILE).read();
        long winsOfPlayer1 = countPlayer1Wins(input);
        System.out.printf("Wins of player 1: %d", winsOfPlayer1);
    }

    public static long countPlayer1Wins(List<Match> hands) {
        return hands
            .stream()
            .filter(Match::player1Wins)
            .count();
    }
}
