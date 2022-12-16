package poker;

public record Match(Hand player1, Hand player2) {

    public boolean player1Wins() {
        int player1RankNumber = player1.getRank().number;
        int player2RankNumber = player2.getRank().number;

        if (player1RankNumber > player2RankNumber) return true;
        else if (player1RankNumber == player2RankNumber) {

            int player1HighestCardValue = player1.getHighestCardValue();
            int player2HighestCardValue = player2.getHighestCardValue();

            if (player1HighestCardValue > player2HighestCardValue) return true;
            else if (player1HighestCardValue == player2HighestCardValue) {

                Hand player1LeftoverCards = player1.getHandWithoutHighestCardsInRank();
                Hand player2LeftoverCards = player2.getHandWithoutHighestCardsInRank();
                return new Match(player1LeftoverCards, player2LeftoverCards).player1Wins();
            }
        }

        return false;
    }
}
