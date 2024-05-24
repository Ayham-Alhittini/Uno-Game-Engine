package engine.utilities;

import engine.Game;
import engine.cards.Card;
import engine.entities.Player;

import java.util.Scanner;

public class Input {
    private final Game game;
    private final Scanner scanner;
    private final CardIdentifier cardIdentifier;

    public Input(Game game) {
        this.game = game;
        scanner = new Scanner(System.in);
        cardIdentifier = new CardIdentifier(game.getGameCards());
    }

    public int promptNumberOfPlayers(int maxNumberOfPlayers, Game game) {
        validateDeveloperInput(maxNumberOfPlayers, game);

        int numberOfPlayers = -1;
        while (numberOfPlayers == -1) {
            Painter.printInput("Enter number of players: ");
            numberOfPlayers = scanner.nextInt();

            if (numberOfPlayers < 2) {
                Painter.printError("Min number of players is 2.");
                numberOfPlayers = -1;
            }

            if (numberOfPlayers > maxNumberOfPlayers) {
                Painter.printError("Max number of players is " + maxNumberOfPlayers + ".");
                numberOfPlayers = -1;
            }
        }
        return  numberOfPlayers;
    }

    private  void validateDeveloperInput(int wantedMaxNumberOfPlayers, Game game) {

        if (game.doesAnnounceUnoMandatory()) {
            //must be at least 2 so player could announce uno
            if (game.getNumberOfCardsToDistribute() < 2)
                throw new IllegalArgumentException("Min number of card is 2");
        } else {
            if (game.getNumberOfCardsToDistribute() < 1)
                throw new IllegalArgumentException("Min number of card is 2");
        }

        int maxPossibleNumberOfPlayers = game.getCardManager().getCardsSize() / game.getNumberOfCardsToDistribute();

        if (maxPossibleNumberOfPlayers < wantedMaxNumberOfPlayers)
            throw new IllegalArgumentException("max number of players can't exceed " + maxPossibleNumberOfPlayers + ".");
    }

    public void promptPlayerToDraw() {
        String response = "";
        while (!response.equalsIgnoreCase("draw")) {
            Painter.printInput("You have no card to play, draw from deck using (draw): ");
            response = scanner.next();
        }
    }

    public Player promptTargetPlayer() {
        Player player = null;
        while (player == null) {
            Painter.printInput("Enter player name: ");
            String playerName = scanner.next();

            player = game.getPlayerManager().getPlayerByName(playerName);
            if (player == null) Painter.printError("Player not found!!");
            else if (player == getCurrentPlayer()) {
                Painter.printError("You can not select yourself!!");
                player = null;
            }
        }
        return player;
    }

    public  Pair<Card, Boolean> promptPlayerForCard() {
        Card playedCard = null;
        boolean playerAnnouncedUno = false;
        while (playedCard == null) {
            Painter.printInput("Enter your card: ");
            String playerInput = scanner.next();

            if (getCurrentPlayer().getNumberOfCards() == 2) {
                String unoInput = scanner.nextLine().trim();
                playerAnnouncedUno = !unoInput.isBlank() && unoInput.split(" ").length == 1
                        && unoInput.equalsIgnoreCase("uno");
            } else {
                playerAnnouncedUno = false;
            }

            playedCard = cardIdentifier.identitfyCard(playerInput);

            if (playedCard == null) {
                Painter.printError("Card can not be identified!!");
            }
            playedCard = resetCardIfInvalid(playedCard);
        }
        return new Pair<>(playedCard, playerAnnouncedUno);
    }

    private Card resetCardIfInvalid(Card playedCard) {
        if (playedCard == null) return null;

        Player currentPlayer = getCurrentPlayer();
        if (!currentPlayer.haveCardOnHand(playedCard)) {
            Painter.printError("You don't have this card to play!!");
            return null;
        } else if (!game.doesCardMatch(playedCard)) {
            Painter.printError("Card does not match!!");
            return null;
        }
        return playedCard;
    }

    private  Player getCurrentPlayer() {
        return game.getPlayerManager().getCurrentPlayer();
    }
}
