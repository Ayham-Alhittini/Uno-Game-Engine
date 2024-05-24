package engine.actions;

import shared.constants.ActionType;
import engine.Game;
import engine.interfaces.ActionBehavior;
import engine.entities.Player;

public class DrawTwoAction implements ActionBehavior {


    @Override
    public ActionType getActionType() {
        return ActionType.Draw_2;
    }

    @Override
    public void execute(Game game, Player targetPlayer) {

        if (targetPlayer == null)
            targetPlayer = game.getPlayerManager().getNextPlayer();

        game.getCardManager().drawCards(targetPlayer, 2);
        new SkipAction().execute(game, targetPlayer);
    }
}
