package variations.junior;

import engine.Game;
import engine.cards.Card;
import engine.utilities.CardMatcher;
import shared.constants.CardColor;

import java.util.ArrayList;
import java.util.List;

public class UnoJuniorGame extends Game {
    //https://www.unorules.com/uno-junior-rules/


    @Override
    public List<Card> getGameCards() {
        return getUnoJuniorCards();
    }

    private List<Card> getUnoJuniorCards() {

        List<Card> cards = new ArrayList<>();

        for (AnimalType animalType: AnimalType.values()) {
            for (CardColor cardColor: CardColor.values()) {
                if (cardColor != CardColor.Wild) {
                    cards.add(new AnimalCard(cardColor, animalType));
                }
            }
        }
        return cards;
    }

    @Override
    public boolean doesCardMatch(Card card) {
        Card toMatchCard = getCardManager().getTopDiscard();

        return CardMatcher.matchByColor(getStateManager().getGameColor(), card)
                || CardMatcher.matchByLabel(toMatchCard, card);
    }

    @Override
    public int getNumberOfCardsToDistribute() {
        return 5;
    }

    @Override
    public int getMaxNumberOfPlayers() {
        return 4;
    }

    @Override
    public int noLegalCardToPlayPenalty() {
        return 1;
    }

    @Override
    public boolean doesAnnounceUnoMandatory() {
        return false;
    }
}
