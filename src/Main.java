import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws Exception {
        long winsOfPlayer1 = getWinsOfPlayer1(Constant.INPUT_FILE);
        System.out.printf("Wins of player 1: %d", winsOfPlayer1);
    }

    public static long getWinsOfPlayer1(String inputFile) throws FileNotFoundException {
        var inputLines = new FileReader(inputFile).read();
        var inputCards = new InputParser(inputLines).parse();
        var matches = new PlayPoker(inputCards);

        return matches.countPlayer1Wins();
    }
}
