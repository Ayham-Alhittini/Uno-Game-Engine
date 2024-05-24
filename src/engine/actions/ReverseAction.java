package engine.actions;

import shared.constants.ActionType;
import engine.Game;
import engine.utilities.Painter;
import engine.entities.Player;
import engine.interfaces.ActionBehavior;

public class ReverseAction implements ActionBehavior {

    @Override
    public ActionType getActionType() {
        return ActionType.Reverse;
    }

    @Override
    public void execute(Game game, Player targetPlayer) {
        game.getPlayerManager().reverseDirection();
        Painter.printAnnouncement("Direction has been flipped.");
    }
}
