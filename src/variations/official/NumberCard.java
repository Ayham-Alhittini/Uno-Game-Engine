package variations.official;

import engine.cards.ColoredLabelCard;
import shared.constants.CardColor;

public class NumberCard extends ColoredLabelCard {
    private final int cardNumber;

    public NumberCard(CardColor color, int cardNumber) {
        super(color, cardNumber +"");
        this.cardNumber = cardNumber;
    }

    @Override
    public int getCardScore() {
        return cardNumber;
    }
}
