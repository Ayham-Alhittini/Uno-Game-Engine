package engine.interfaces.managers;

import engine.cards.Card;
import engine.entities.Player;

public interface PlayerTurnHandler {
    void handlePlayerTurn();
    boolean playerHasNoPlayableCards(Player player);
    void handleDrawPenaltyCard();
    void handlePlayerPlayingCard();
    void handlePlayerForgotToAnnounceUno(Player player);
    void handleFinishGame(Player player);
    void handleSkippedPlayers();
    void playCard(Card card);
}
