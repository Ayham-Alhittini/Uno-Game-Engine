package engine.entities;

import engine.cards.Card;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private final String name;
    private boolean playerSkip = false;
    private List<Card> handCards;

    public Player(String name){
        this.name = name;
        handCards = new ArrayList<>();
    }

    public boolean isSkipped() {
        if (playerSkip) {
            playerSkip = false;
            return true;
        }
        return false;
    }

    public List<Card> getHandCards() {
        return handCards;
    }

    public void setHandCards(List<Card> handCards) {
        this.handCards = handCards;
    }

    public void skipPlayer() {
        playerSkip = true;
    }

    public void drawCard(Card card) {
        handCards.add(card);
    }

    public void playCard(Card card) {
        handCards.remove(card);
    }

    public String getName() {
        return name;
    }

    public void printPlayerCards() {
        System.out.println(handCards);
    }

    public boolean haveCardOnHand(Card card) {
        return handCards.contains(card);
    }

    public boolean finishedGame() {
        return handCards.isEmpty();
    }

    public int getNumberOfCards() {
        return handCards.size();
    }

    public int getPlayerScore() {
        int playerScore = 0;
        for (Card card: handCards)
            playerScore += card.getCardScore();
        return playerScore;
    }

    @Override
    public boolean equals(Object obj) {
        try {
            Player player = (Player) obj;
            return name.equals(player.getName());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public String toString() {
        return name;
    }
}
