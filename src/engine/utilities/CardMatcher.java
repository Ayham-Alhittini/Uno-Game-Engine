package engine.utilities;

import engine.cards.Card;
import shared.constants.ActionType;
import shared.constants.CardColor;

import java.util.Objects;

public class CardMatcher {

    public static boolean matchByColor(CardColor gameColor, Card card) {
        return gameColor != CardColor.Wild && gameColor == card.getCardColor();
    }

    public static boolean matchByLabel(Card topDiscardCard, Card card) {
        return card.getCardLabel() != null && Objects.equals(topDiscardCard.getCardLabel(), card.getCardLabel());
    }

    public static boolean matchByAction(Card topDiscardCard, Card card) {
        return card.getActionType() != ActionType.NoAction && topDiscardCard.getActionType() == card.getActionType();
    }
}
