import java.io.File;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) throws Exception {

        File file = new File("src/input.txt");

        Scanner sc = new Scanner(file);
        int winsOfPlayer1 = 0;

        while (sc.hasNextLine()) {
            String twoHandsInput = sc.nextLine();
            String player1HandInput = twoHandsInput.substring(0, twoHandsInput.length() / 2).trim();
            String player2HandInput = twoHandsInput.substring(twoHandsInput.length() / 2).trim();

            System.out.println(twoHandsInput);
            System.out.println("player1Hand " + player1HandInput);
            System.out.println("player2Hand " + player2HandInput);

            Hand player1Hand = new Hand(player1HandInput);
            Hand player2Hand = new Hand(player2HandInput);

            winsOfPlayer1 = compare(player1Hand, player2Hand, winsOfPlayer1);
            System.out.println("winsOfPlayer1 " + winsOfPlayer1);

        }
        System.out.println("winsOfPlayer1 final " + winsOfPlayer1);

    }

    private static int compare(Hand player1Hand, Hand player2Hand, int winsOfPlayer1) {
        int pl1Number = player1Hand.getRank().number;
        int pl2Number = player2Hand.getRank().number;
        System.out.println("pl1 " + player1Hand.getRank() + " Rank number: " + pl1Number);
        System.out.println("pl2 " + player2Hand.getRank() + " Rank number: " + pl2Number);

        if (pl1Number < pl2Number) {
            return ++winsOfPlayer1;
        } else if (pl1Number == pl2Number) {
            if (player1Hand.getHighestCardValue() > player2Hand.getHighestCardValue()) {
                return ++winsOfPlayer1;
            } else if (player1Hand.getHighestCardValue() == player2Hand.getHighestCardValue()) {
                System.out.println("cut elements");

                Hand player1LeftoverCards = player1Hand.getHandWithoutHighestCardsInRank();
                Hand player2LeftoverCards = player2Hand.getHandWithoutHighestCardsInRank();
                return compare(player1LeftoverCards, player2LeftoverCards, winsOfPlayer1);
            }
        }
        System.out.println("winsOfPlayer1 " + winsOfPlayer1);
        return winsOfPlayer1;
    }
}

class Combined implements HandType {
    HandType firstClass;
    HandType secondClass;

    Rank rank;

    public Combined(HandType firstClass, HandType secondClass, Rank rank) {
        this.firstClass = firstClass;
        this.secondClass = secondClass;
        this.rank = rank;
    }

    @Override
    public Rank getRankType() {
        return rank;
    }

    @Override
    public boolean exist(Card[] hand) {
        return this.firstClass.exist(hand) && this.secondClass.exist(hand);
    }

    @Override
    public int getHighestCardValue(Card[] hand) {
        if (rank == Rank.STRAIGHT_FLUSH) {
            return hand[hand.length - 1].getValue();
        } else if (rank == Rank.FULL_HOUSE) {
            return firstClass.getHighestCardValue(hand);
        } else {
            throw new IllegalStateException("nesamone");
        }
    }

    @Override
    public List<Card> getHighestCards(Card[] hand) {
        if (rank == Rank.STRAIGHT_FLUSH) {
            return firstClass.getHighestCards(hand);
        } else if (rank == Rank.FULL_HOUSE) {
            return firstClass.getHighestCards(hand);
        } else {
            throw new IllegalStateException("nesamone");
        }
    }
}

class NOfAKind implements HandType {
    int number;
    Rank rank;

    public NOfAKind(int number) {
        this.number = number;
    }

    public NOfAKind(int number, Rank rank) {
        this.number = number;
        this.rank = rank;
    }

    @Override
    public Rank getRankType() {
        return rank;
    }

    @Override
    public boolean exist(Card[] hand) {
        return Arrays.stream(hand)
            .collect(Collectors.groupingBy(Card::getValue))
            .values()
            .stream()
            .anyMatch(group -> group.size() == number);
    }

    @Override
    public int getHighestCardValue(Card[] hand) {
        Optional<List<Card>> a = getCards(hand);
        return a.get().get(0).getValue();

    }

    @Override
    public List<Card> getHighestCards(Card[] hand) {
        return getCards(hand).get();
    }

    private Optional<List<Card>> getCards(Card[] hand) {
        return Arrays.stream(hand)
            .collect(Collectors.groupingBy(Card::getValue))
            .values()
            .stream()
            .filter(group -> group.size() == number).findFirst();
    }
}

class Flush implements HandType {
    @Override
    public Rank getRankType() {
        return Rank.FLUSH;
    }

    @Override
    public boolean exist(Card[] hand) {
        return Arrays
            .stream(hand)
            .map(Card::getSuit)
            .collect(Collectors.toSet())
            .size() == 1;
    }

    @Override
    public int getHighestCardValue(Card[] hand) {
        return hand[hand.length - 1].getValue();
    }

    @Override
    public List<Card> getHighestCards(Card[] hand) {
        List<Card> toReturn = new ArrayList<>();
        toReturn.add(hand[hand.length - 1]);
        return toReturn;
    }
}

class Straight implements HandType {

    @Override
    public Rank getRankType() {
        return Rank.STRAIGHT;
    }

    @Override
    public boolean exist(Card[] hand) {
        int min = hand[0].getValue();
        int max = hand[hand.length - 1].getValue();
        Set<Integer> values = Arrays.stream(hand).map(Card::getValue).collect(Collectors.toSet());
        Set<Integer> valuesWithAceAsOne = new HashSet<>(Arrays.asList(2, 3, 4, 5, 14));

        return values.size() == 5
            && (min == max - 4) || (values.equals(valuesWithAceAsOne));
    }

    @Override
    public int getHighestCardValue(Card[] hand) {
        return hand[hand.length - 1].getValue();
    }

    @Override
    public List<Card> getHighestCards(Card[] hand) {
        List<Card> toReturn = new ArrayList<>();
        toReturn.add(hand[hand.length - 1]);
        return toReturn;
    }
}

class TwoPairs implements HandType {

    @Override
    public Rank getRankType() {
        return Rank.TWO_PAIRS;
    }

    @Override
    public boolean exist(Card[] hand) {
        return extracted(hand).count() == 2;
    }

    @Override
    public int getHighestCardValue(Card[] hand) {
        return extracted(hand)
            .max(Comparator.comparingInt(one -> one.get(0).getValue()))
            .get()
            .get(0)
            .getValue();
    }

    @Override
    public List<Card> getHighestCards(Card[] hand) {
        return extracted(hand)
            .max(Comparator.comparingInt(one -> one.get(0).getValue())).get();
    }

    private static Stream<List<Card>> extracted(Card[] hand) {
        return Arrays.stream(hand)
            .collect(Collectors.groupingBy(Card::getValue))
            .values()
            .stream()
            .filter(group -> group.size() == 2);
    }
}

class HighCard implements HandType {

    @Override
    public Rank getRankType() {
        return Rank.HIGH_CARD;
    }

    @Override
    public boolean exist(Card[] hand) {
        return true;
    }

    @Override
    public int getHighestCardValue(Card[] hand) {
        return hand[hand.length - 1].getValue();
    }

    @Override
    public List<Card> getHighestCards(Card[] hand) {
        List<Card> a = new ArrayList<>();
        a.add(hand[hand.length - 1]);
        return a;
    }
}
