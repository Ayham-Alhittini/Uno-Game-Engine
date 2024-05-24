package engine.cards;

import engine.colorshitf.Wild;

public class WildCard extends Card {

    public WildCard() {
        super.setColorShiftBehavior(new Wild());
    }

    @Override
    public int getCardScore() {
        return 50;
    }
}
