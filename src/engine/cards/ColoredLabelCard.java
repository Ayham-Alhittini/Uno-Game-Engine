package engine.cards;

import shared.constants.CardColor;

public class ColoredLabelCard extends Card {

    public ColoredLabelCard(CardColor color, String label) {
        super.setCardColor(color);
        super.setCardLabel(label);
    }

    @Override
    public int getCardScore() {
        return 0;
    }
}
