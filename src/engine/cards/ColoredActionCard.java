package engine.cards;

import shared.ActionFactory;
import shared.constants.ActionType;
import shared.constants.CardColor;

public class ColoredActionCard extends Card {
    private final ActionType actionType;

    public ColoredActionCard(CardColor color, ActionType actionType) {
        this.actionType = actionType;
        super.setCardColor(color);
        super.setActionBehavior(ActionFactory.getAction(actionType));
    }

    public ActionType getActionType() {
        return this.actionType;
    }

    @Override
    public int getCardScore() {
        return 20;
    }
}
