package engine.cards;

import engine.colorshitf.Wild;
import shared.ActionFactory;
import shared.constants.ActionType;

public class WildActionCard extends Card {

    public WildActionCard(ActionType actionType) {
        super.setColorShiftBehavior(new Wild());
        super.setActionBehavior(ActionFactory.getAction(actionType));
    }

    @Override
    public int getCardScore() {
        return 50;
    }
}