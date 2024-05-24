package variations.mygame;

import engine.Game;
import engine.cards.Card;
import engine.cards.WildActionCard;
import engine.cards.WildLabelCard;
import engine.finishby.LastPlayerFinish;
import engine.utilities.CardGenerator;
import engine.utilities.CardMatcher;
import shared.constants.ActionType;

import java.util.List;

public class MyGame extends Game {

    public MyGame() {
        getStateManager().setGameFinishType(new LastPlayerFinish(getPlayerManager()));
        getCardManager().setFirstCardBehavior(new MyGameFirstCardBehavior());
    }


    @Override
    public List<Card> getGameCards() {
        List<Card> cards = CardGenerator.getUnoBasicCards();

        cards.removeIf(card -> card.equals(new BlueZeroCard()));

        //My cards
        for (int i = 0; i < 20; i++) {
            cards.add(new BlueZeroCard());//could match with skip cards by action
            cards.add(new WildLabelCard("I-Love-Coding"));
        }
        for (int i = 0; i < 3; i++) {
            cards.addAll(CardGenerator.getAllCardsColorForLabel("I-Love-Coding"));
            cards.addAll(CardGenerator.getAllCardsColorForAction(ActionType.Skip_All));
            cards.addAll(CardGenerator.getAllCardsColorForAction(ActionType.Swap_Hands));
            cards.addAll(CardGenerator.getAllCardsColorForAction(ActionType.Targeted_Draw_2));
            cards.add(new WildActionCard(ActionType.Skip_All));
            cards.add(new WildActionCard(ActionType.Swap_Hands));
            cards.add(new WildActionCard(ActionType.Targeted_Draw_2));
        }

        return cards;
    }

    @Override
    public boolean doesCardMatch(Card card) {
        Card toMatchCard = getCardManager().getTopDiscard();

        if (card.isWildCard())//you could play wild card any time
            return true;

        return CardMatcher.matchByColor(getStateManager().getGameColor(), card)
                || CardMatcher.matchByAction(toMatchCard, card)
                || CardMatcher.matchByLabel(toMatchCard, card)
                || matchByParity(card);
    }

    private boolean matchByParity(Card card) {

        try {
            Card topDiscardCard = getCardManager().getTopDiscard();

            int topDiscardCardNumber = Integer.parseInt(topDiscardCard.getCardLabel());
            int cardNumber = Integer.parseInt(card.getCardLabel());

            return topDiscardCardNumber % 2 == cardNumber % 2;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public int getNumberOfCardsToDistribute() {
        return 4;
    }

    @Override
    public int getMaxNumberOfPlayers() {
        return 10;
    }

    @Override
    public int noLegalCardToPlayPenalty() {
        return 1;
    }

    @Override
    public boolean doesAnnounceUnoMandatory() {
        return true;
    }
}
