package engine.interfaces.managers;

import engine.cards.Card;
import engine.interfaces.InitializeDiscardPileBehavior;
import engine.entities.Player;

public interface CardManager {
    void setFirstCardBehavior(InitializeDiscardPileBehavior firstCardBehavior);
    void InitializeFirstCardCards();
    void drawCards(Player player, int numberOfCards);
    void distributeCards();
    void shuffleCards();
    void refillDrawPileIfIsEmpty();
    void discardCard(Card card);
    Card getTopDiscard();
    int getCardsSize();
}
