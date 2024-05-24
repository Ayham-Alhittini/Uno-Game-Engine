package engine.interfaces.managers;

import engine.interfaces.GameFinishType;
import shared.constants.CardColor;

public interface StateManager {
    void setGameColor(CardColor gameColor);
    CardColor getGameColor();

    void setGameFinishType(GameFinishType gameFinishType);
    GameFinishType getGameFinishType();

    void printGameState();

    void setForgetAnnounceUno(boolean playerAnnouncedUno);
    boolean getForgetAnnounceUno();

}
