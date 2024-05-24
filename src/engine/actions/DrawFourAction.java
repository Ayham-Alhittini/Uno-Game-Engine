package engine.actions;

import engine.Game;
import shared.constants.ActionType;
import engine.interfaces.ActionBehavior;
import engine.entities.Player;

public class DrawFourAction implements ActionBehavior {

    @Override
    public ActionType getActionType() {
        return ActionType.Draw_4;
    }

    @Override
    public void execute(Game game, Player targetPlayer) {
        if (targetPlayer == null)
            targetPlayer = game.getPlayerManager().getNextPlayer();
        game.getCardManager().drawCards(targetPlayer, 4);
        new SkipAction().execute(game, targetPlayer);
    }
}
