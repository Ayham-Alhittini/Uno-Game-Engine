package engine.cards;

import shared.constants.CardColor;

public class ColoredCard extends Card {

    public ColoredCard(CardColor color) {
        super.setCardColor(color);
    }

    @Override
    public int getCardScore() {
        return 0;
    }
}
