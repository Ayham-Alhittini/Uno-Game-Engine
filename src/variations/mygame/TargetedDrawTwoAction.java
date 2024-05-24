package variations.mygame;

import engine.Game;
import shared.constants.ActionType;
import engine.interfaces.ActionBehavior;
import engine.entities.Player;

public class TargetedDrawTwoAction implements ActionBehavior {

    @Override
    public ActionType getActionType() {
        return ActionType.Targeted_Draw_2;
    }

    @Override
    public void execute(Game game, Player targetPlayer) {
        targetPlayer = game.getInput().promptTargetPlayer();
        game.getCardManager().drawCards(targetPlayer, 2);
    }


}
