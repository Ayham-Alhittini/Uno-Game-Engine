package variations.mygame;

import engine.Game;
import engine.cards.Card;
import engine.interfaces.ActionBehavior;
import engine.entities.Player;
import engine.utilities.Painter;
import shared.constants.ActionType;

import java.util.List;

public class SwapHandsAction implements ActionBehavior {
    @Override
    public ActionType getActionType() {
        return ActionType.Swap_Hands;
    }

    @Override
    public void execute(Game game, Player targetPlayer) {
        targetPlayer = game.getInput().promptTargetPlayer();
        Player currentPlayer = game.getPlayerManager().getCurrentPlayer();

        List<Card> temp = targetPlayer.getHandCards();

        targetPlayer.setHandCards(currentPlayer.getHandCards());
        currentPlayer.setHandCards(temp);

        Painter.printAnnouncement(currentPlayer + " & " + targetPlayer + " swap there cards.");
    }
}
