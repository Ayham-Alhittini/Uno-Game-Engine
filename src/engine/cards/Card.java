package engine.cards;
import engine.actions.NoAction;
import engine.interfaces.managers.StateManager;
import engine.utilities.Painter;
import shared.constants.ActionType;
import shared.constants.CardColor;
import engine.colorshitf.Colored;
import engine.Game;
import engine.entities.Player;
import engine.interfaces.ActionBehavior;
import engine.interfaces.ColorShiftBehavior;


public abstract class Card {

    private String cardLabel;
    private CardColor cardColor;
    private ActionBehavior actionBehavior;
    private ColorShiftBehavior colorShiftBehavior;

    protected Card() {
        defaultCard();
    }

    protected void setCardLabel(String cardLabel) {
        this.cardLabel = cardLabel;
    }

    protected void setCardColor(CardColor cardColor) {
        this.cardColor = cardColor;
    }

    protected void setActionBehavior(ActionBehavior actionBehavior) {
        this.actionBehavior = actionBehavior;
    }

    protected void setColorShiftBehavior(ColorShiftBehavior colorShiftBehavior) {
        this.colorShiftBehavior = colorShiftBehavior;
    }



    public String getCardLabel() {
        return cardLabel;
    }

    public CardColor getCardColor() {
        return cardColor;
    }

    public ActionType getActionType() {
        return actionBehavior.getActionType();
    }

    public boolean isWildCard() {
        return this instanceof WildCard || this instanceof WildActionCard || this instanceof WildLabelCard;
    }

    public void performAction(Game game, Player targetPlayer) {
        actionBehavior.execute(game, targetPlayer);
    }

    public void performWild(StateManager stateManager) {
        colorShiftBehavior.shiftColor(stateManager, getCardColor());
    }

    private void defaultCard() {
        cardLabel = null;
        cardColor = CardColor.Wild;
        actionBehavior = new NoAction();
        colorShiftBehavior = new Colored();
    }

    @Override
    public boolean equals(Object obj) {
        try {
            Card card = (Card) obj;
            return getIdentifier().equals(card.getIdentifier());
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public String toString() {
        return cardFormat();
    }

    public String cardFormat() {
        return Painter.cardPainter("(" + getIdentifier() + ")", getCardColor());
    }
    public String getIdentifier() {
        CardColor cardColor = getCardColor();
        if (cardLabel == null && actionBehavior instanceof NoAction)//for example (wild)
            return cardColor.toString();
        else if (cardLabel != null)
            return cardColor + "-" + cardLabel;
        else
            return cardColor + "-" + getActionType();
    }

    public abstract int getCardScore();
}
