package variations.mygame;

import variations.official.NumberCard;
import engine.actions.SkipAction;
import shared.constants.CardColor;

public class BlueZeroCard extends NumberCard {
    public BlueZeroCard() {
        super(CardColor.Blue, 0);
        setActionBehavior(new SkipAction());
    }
}
