package variations.official;

import engine.cards.Card;
import engine.cards.WildActionCard;
import shared.constants.ActionType;
import engine.interfaces.InitializeDiscardPileBehavior;

import java.util.ArrayList;

public class OfficialUnoFirstCardBehavior implements InitializeDiscardPileBehavior {
    @Override
    public boolean ignoreAction() {
        return false;
    }

    @Override
    public boolean ignoreWild() {
        return false;
    }

    @Override
    public ArrayList<Card> preventedCards() {
        ArrayList<Card> toPreventCards = new ArrayList<>();
        toPreventCards.add(new WildActionCard(ActionType.Draw_4));
        return toPreventCards;
    }
}
