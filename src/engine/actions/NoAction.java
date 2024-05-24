package engine.actions;

import shared.constants.ActionType;
import engine.Game;
import engine.entities.Player;
import engine.interfaces.ActionBehavior;

public class NoAction implements ActionBehavior {

    @Override
    public ActionType getActionType() {
        return ActionType.NoAction;
    }

    @Override
    public void execute(Game game, Player targetPlayer) {
        //do nothing
    }
}
