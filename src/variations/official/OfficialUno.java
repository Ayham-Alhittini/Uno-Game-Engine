package variations.official;

import engine.Game;
import engine.cards.Card;
import engine.cards.WildActionCard;
import engine.entities.Player;
import engine.finishby.FirstPlayerWithScoreCalculationsFinish;
import engine.utilities.CardGenerator;
import engine.utilities.CardMatcher;
import shared.constants.ActionType;

import java.util.ArrayList;
import java.util.List;

public class OfficialUno extends Game {

    public OfficialUno() {
        getStateManager()
                .setGameFinishType(new FirstPlayerWithScoreCalculationsFinish(getPlayerManager()));
        getCardManager().setFirstCardBehavior(new OfficialUnoFirstCardBehavior());
    }

    @Override
    public ArrayList<Card> getGameCards() {
        return CardGenerator.getUnoBasicCards();
    }
    @Override
    public boolean doesCardMatch(Card card) {
        Card toMatchCard = getCardManager().getTopDiscard();
        Card specialCard = new WildActionCard(ActionType.Draw_4);

        if (card.isWildCard()) {
            if (card.equals(specialCard)) {
                //wild-draw-4 card can't be thrown if there is any alternative
                return haveAlternativeCard(getPlayerManager().getCurrentPlayer(), specialCard);
            }
            return true;
        }

        return CardMatcher.matchByColor(getStateManager().getGameColor(), card)
                || CardMatcher.matchByLabel(toMatchCard, card)
                || CardMatcher.matchByAction(toMatchCard, card);
    }

    @Override
    public int getNumberOfCardsToDistribute() {
        return 3;
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

    private boolean haveAlternativeCard(Player player, Card alternativeCard) {
        List<Card> playerCards = player.getHandCards();

        for (Card card: playerCards)
            if (!card.equals(alternativeCard) && doesCardMatch(card))
                return false;

        return true;
    }
}
