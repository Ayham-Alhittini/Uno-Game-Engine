package engine.managers;

import engine.Game;
import engine.interfaces.GameFinishType;
import engine.interfaces.managers.StateManager;
import engine.entities.Player;
import engine.utilities.Painter;
import shared.constants.CardColor;

public class GameStateManager implements StateManager {
    private final Game game;
    private CardColor gameColor;
    private boolean forgetAnnounceUno = false;
    private GameFinishType gameFinishType;


    public GameStateManager(Game game) {
        this.game = game;
    }

    @Override
    public void setGameColor(CardColor gameColor) {
        this.gameColor = gameColor;
    }

    @Override
    public CardColor getGameColor() {
        return gameColor;
    }

    @Override
    public void setGameFinishType(GameFinishType gameFinishType) {
        this.gameFinishType = gameFinishType;
    }

    @Override
    public GameFinishType getGameFinishType() {
        return gameFinishType;
    }

    @Override
    public void printGameState() {
        for (int i = 0; i < 5; i++) System.out.println();
        Player currentPlayer = game.getPlayerManager().getCurrentPlayer();

        System.out.println(currentPlayer + " turn");
        System.out.println("To play color : " + Painter.cardPainter(gameColor.toString(), gameColor));
        System.out.println("Last played card : " + game.getCardManager().getTopDiscard());
        currentPlayer.printPlayerCards();
    }

    @Override
    public void setForgetAnnounceUno(boolean playerAnnouncedUno) {
        if (!game.doesAnnounceUnoMandatory())
            return;

        Player currentPlayer = game.getPlayerManager().getCurrentPlayer();
        forgetAnnounceUno = currentPlayer.getNumberOfCards() == 2 && !playerAnnouncedUno;
    }

    @Override
    public boolean getForgetAnnounceUno() {
        return forgetAnnounceUno;
    }
}
