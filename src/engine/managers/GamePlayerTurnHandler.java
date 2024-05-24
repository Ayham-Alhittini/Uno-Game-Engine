package engine.managers;

import engine.Game;
import engine.cards.Card;
import engine.interfaces.GameFinishType;
import engine.interfaces.managers.CardManager;
import engine.interfaces.managers.PlayerManager;
import engine.interfaces.managers.PlayerTurnHandler;
import engine.entities.Player;
import engine.interfaces.managers.StateManager;
import engine.utilities.Input;
import engine.utilities.Painter;
import engine.utilities.Pair;

import java.util.List;

public class GamePlayerTurnHandler implements PlayerTurnHandler {
    private final Game game;
    private final PlayerManager playerManager;
    private final CardManager cardManager;
    private final StateManager stateManager;
    private final Input input;
    

    public GamePlayerTurnHandler(Game game) {
        this.game = game;
        playerManager = game.getPlayerManager();
        cardManager = game.getCardManager();
        stateManager = game.getStateManager();
        input = game.getInput();
    }

    @Override
    public void handlePlayerTurn() {
        Player currentPlayer = playerManager.getCurrentPlayer();
        if (playerHasNoPlayableCards(currentPlayer)) {
            handleDrawPenaltyCard();
        } else {
            handlePlayerPlayingCard();
        }
    }

    @Override
    public boolean playerHasNoPlayableCards(Player player) {
        List<Card> playerCards = player.getHandCards();

        for (Card card: playerCards)
            if (game.doesCardMatch(card))
                return false;
        return true;
    }


    @Override
    public void handleDrawPenaltyCard() {
        Player currentPlayer = playerManager.getCurrentPlayer();

        input.promptPlayerToDraw();
        cardManager.drawCards(currentPlayer, game.noLegalCardToPlayPenalty());
        if (playerHasNoPlayableCards(currentPlayer)) {
            playerManager.goNextPlayer();
        }
    }

    @Override
    public void handlePlayerPlayingCard() {
        Pair<Card,Boolean> cardWithUnoAnnounce = input.promptPlayerForCard();
        Card playedCard = cardWithUnoAnnounce.getFirst();
        stateManager.setForgetAnnounceUno(cardWithUnoAnnounce.getSecond());
        playCard(playedCard);//default target player on action itself.

        Player previousPlayer = playerManager.getPreviousPlayer();
        handlePlayerForgotToAnnounceUno(previousPlayer);
        handleFinishGame(previousPlayer);
        handleSkippedPlayers();
    }

    @Override
    public void handlePlayerForgotToAnnounceUno(Player player) {
        if (stateManager.getForgetAnnounceUno()) {
            cardManager.drawCards(player, 2);
            player.printPlayerCards();
            System.out.println("You forgot to announce uno.");
        }
        stateManager.setForgetAnnounceUno(false);
    }

    @Override
    public void handleFinishGame(Player player) {
        GameFinishType gameFinishType = stateManager.getGameFinishType();
        if (player.finishedGame()) {
            gameFinishType.addFinishedPlayer(player);
            playerManager.removePlayer(player.getName());
        }
        game.setGameFinish(gameFinishType.isGameFinished());
    }

    @Override
    public void handleSkippedPlayers() {
        Player player = playerManager.getCurrentPlayer();
        while (player.isSkipped()) {
            Painter.printAnnouncement(player + " has been skipped.");
            playerManager.goNextPlayer();
            player = playerManager.getCurrentPlayer();
        }
    }

    @Override
    public void playCard(Card card) {
        playerManager.getCurrentPlayer().playCard(card);
        cardManager.discardCard(card);
        card.performWild(stateManager);
        card.performAction(game, null);
        playerManager.goNextPlayer();
    }
}
