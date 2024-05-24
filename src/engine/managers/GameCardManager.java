package engine.managers;

import engine.Game;
import engine.cards.Card;
import engine.entities.Player;
import engine.interfaces.InitializeDiscardPileBehavior;
import engine.interfaces.managers.CardManager;
import engine.utilities.Painter;
import variations.official.OfficialUnoFirstCardBehavior;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameCardManager implements CardManager {

    private final Game game;
    private List<Card> drawPile;
    private List<Card> discardPile;
    private InitializeDiscardPileBehavior firstCardBehavior;


    public GameCardManager(Game game) {
        this.game = game;
        this.firstCardBehavior = new OfficialUnoFirstCardBehavior();
        drawPile = game.getGameCards();
        shuffleCards();
    }


    @Override
    public void setFirstCardBehavior(InitializeDiscardPileBehavior firstCardBehavior) {
        this.firstCardBehavior = firstCardBehavior;
    }
    @Override
    public void InitializeFirstCardCards() {
        shuffleCards();
        Card firstCard = drawPile.removeLast();

        if (firstCardBehavior.preventedCards().contains(firstCard)) {
            Painter.printAnnouncement(firstCard + " prevented to be first card.");
            drawPile.add(firstCard);//put card back
            InitializeFirstCardCards();
            return;
        }
        discardPile = new ArrayList<>();
        discardPile.add(firstCard);

        if (!firstCardBehavior.ignoreAction() || !firstCardBehavior.ignoreWild())
            Painter.printAnnouncement("First card " + firstCard);

        if (!firstCardBehavior.ignoreWild())
            firstCard.performWild(game.getStateManager());

        if (!firstCardBehavior.ignoreAction()) {
            firstCard.performAction(game, game.getPlayerManager().getCurrentPlayer());
        }
    }

    @Override
    public void drawCards(Player player, int numberOfCards) {
        for (int i = 0; i < numberOfCards; i++) {
            refillDrawPileIfIsEmpty();
            Card topCard = drawPile.removeLast();
            player.drawCard(topCard);
        }
        Painter.printAnnouncement(player + " drawing " + numberOfCards + " card" + (numberOfCards > 1 ? "s" : "") + ".");
    }

    @Override
    public void distributeCards() {
        List<Player> players = game.getPlayerManager().getPlayers();

        for (Player player : players) {
            drawCards(player, game.getNumberOfCardsToDistribute());
        }

        System.out.println();
    }

    @Override
    public void shuffleCards() {
        Collections.shuffle(drawPile, new SecureRandom());
    }

    @Override
    public void refillDrawPileIfIsEmpty() {
        if (!drawPile.isEmpty()) return;

        Card topCard = discardPile.removeLast();

        drawPile = discardPile;
        shuffleCards();

        discardPile.clear();
        discardPile.add(topCard);
    }


    @Override
    public void discardCard(Card card) {
        discardPile.add(card);
    }


    @Override
    public Card getTopDiscard() {
        return discardPile.isEmpty() ? null : discardPile.getLast();
    }

    @Override
    public int getCardsSize() {
        return drawPile.size();
    }
}
