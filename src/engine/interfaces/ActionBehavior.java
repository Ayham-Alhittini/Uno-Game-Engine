package engine.interfaces;

import shared.constants.ActionType;
import engine.Game;
import engine.entities.Player;

public interface ActionBehavior {

    ActionType getActionType();
    void execute(Game game, Player targetPlayer);
}
