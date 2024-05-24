package variations.mygame;

import engine.Game;
import engine.interfaces.ActionBehavior;
import engine.entities.Player;
import shared.constants.ActionType;

import java.util.List;

public class SkipAllAction implements ActionBehavior {

    @Override
    public ActionType getActionType() {
        return ActionType.Skip_All;
    }

    @Override
    public void execute(Game game, Player targetPlayer) {

        if (targetPlayer == null)
            targetPlayer = game.getPlayerManager().getCurrentPlayer();

        List<Player> allPlayers = game.getPlayerManager().getPlayers();

        for (Player player : allPlayers) {
            if (!player.equals(targetPlayer)) {
                player.skipPlayer();
            }
        }
    }
}
