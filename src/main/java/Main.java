import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws Exception {
        var winsOfPlayer1 = getWinsOfPlayer1(Constants.INPUT_FILE_PATH);
        System.out.printf("Wins of player 1: %d", winsOfPlayer1);
    }

    public static long getWinsOfPlayer1(String inputFilePath) throws FileNotFoundException {
        var inputLines = new FileReader(inputFilePath).read();
        var inputCards = new InputParser(inputLines).parse();
        var matches = new PlayPoker(inputCards);

        return matches.countPlayer1Wins();
    }
}
