package engine.actions;

import shared.constants.ActionType;
import engine.Game;
import engine.entities.Player;
import engine.interfaces.ActionBehavior;

public class SkipAction implements ActionBehavior {
    @Override
    public ActionType getActionType() {
        return ActionType.Skip;
    }
    @Override
    public void execute(Game game, Player targetPlayer) {

        if (targetPlayer == null)
            targetPlayer = game.getPlayerManager().getNextPlayer();
        targetPlayer.skipPlayer();
    }
}
