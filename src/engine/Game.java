package engine;

import engine.cards.Card;
import engine.finishby.FirstPlayerFinish;
import engine.interfaces.managers.CardManager;
import engine.interfaces.managers.PlayerManager;
import engine.interfaces.managers.PlayerTurnHandler;
import engine.interfaces.managers.StateManager;
import engine.managers.GamePlayerManager;
import engine.managers.GameCardManager;
import engine.managers.GameStateManager;
import engine.managers.GamePlayerTurnHandler;
import engine.utilities.Input;

import java.util.List;


public abstract class Game{
    private final Input input;
    private boolean gameFinish = false;
    private PlayerManager playerManager;
    private CardManager cardManager;
    private PlayerTurnHandler playerTurnHandler;
    private StateManager stateManager;

    public Game() {
        input = new Input(this);
        cardManager = new GameCardManager(this);
        playerManager = new GamePlayerManager();
        playerTurnHandler = new GamePlayerTurnHandler(this);
        stateManager = new GameStateManager(this);
    }
    public void play() {
        defaultInitialization();
        while (!gameFinish) {
            stateManager.printGameState();
            playerTurnHandler.handlePlayerTurn();
        }
        stateManager.getGameFinishType().showWinners();
    }
    private void defaultInitialization() {
        int numberOfPlayers = input.promptNumberOfPlayers(getMaxNumberOfPlayers(), this);
        playerManager.createPlayers(numberOfPlayers);
        if (stateManager.getGameFinishType() == null)
            stateManager.setGameFinishType(new FirstPlayerFinish(playerManager));

        cardManager.distributeCards();
        cardManager.InitializeFirstCardCards();
        playerTurnHandler.handleSkippedPlayers();
    }
    public void setGameFinish(boolean gameFinish) {
        this.gameFinish = gameFinish;
    }
    public void setPlayerManager(PlayerManager playerManager) {
        this.playerManager = playerManager;
    }
    public void setCardManager(CardManager cardManager) {
        this.cardManager = cardManager;
    }
    public void setStateManager(StateManager stateManager) {
        this.stateManager = stateManager;
    }
    public void setPlayerTurnHandler(PlayerTurnHandler playerTurnHandler) {
        this.playerTurnHandler = playerTurnHandler;
    }
    public Input getInput() {
        return input;
    }
    public PlayerManager getPlayerManager() {
        return playerManager;
    }
    public CardManager getCardManager() {
        return cardManager;
    }
    public PlayerTurnHandler getPlayerTurnHandler() {
        return playerTurnHandler;
    }
    public StateManager getStateManager() {
        return stateManager;
    }


    public abstract List<Card> getGameCards();
    public abstract boolean doesCardMatch(Card card);
    public abstract int getNumberOfCardsToDistribute();
    public abstract int getMaxNumberOfPlayers();
    public abstract int noLegalCardToPlayPenalty();
    public abstract boolean doesAnnounceUnoMandatory();
}
