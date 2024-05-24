package engine.cards;

import engine.colorshitf.Wild;

public class WildLabelCard extends Card {

    public WildLabelCard(String label) {
        super.setColorShiftBehavior(new Wild());
        super.setCardLabel(label);
    }

    @Override
    public int getCardScore() {
        return 0;
    }
}
