package variations.mygame;

import engine.cards.Card;
import engine.cards.WildActionCard;
import shared.constants.ActionType;
import engine.interfaces.InitializeDiscardPileBehavior;
import engine.utilities.CardGenerator;

import java.util.ArrayList;

public class MyGameFirstCardBehavior implements InitializeDiscardPileBehavior {
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

        ArrayList<Card> cards = new ArrayList<>();

        cards.add(new WildActionCard(ActionType.Draw_4));
        cards.addAll(CardGenerator.getAllCardsColorForAction(ActionType.Skip_All));//wild color not included.
        cards.add(new WildActionCard(ActionType.Skip_All));

        return cards;
    }
}
