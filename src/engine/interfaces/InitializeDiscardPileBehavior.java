package engine.interfaces;

import engine.cards.Card;

import java.util.List;

public interface InitializeDiscardPileBehavior {
    boolean ignoreAction();
    boolean ignoreWild();
    List<Card> preventedCards();
}
