package engine.utilities;

import engine.cards.Card;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CardIdentifier {
    private HashMap<String, Card> uniqueCards;
    private HashMap<String, List<Card>> cardShortcuts;
    public CardIdentifier(List<Card> gameCards) {
        setUniqueCards(gameCards);
        setCardShortcuts();
    }

    public Card identitfyCard(String cardStr) {
        cardStr = cardStr.toLowerCase();

        Card card = uniqueCards.getOrDefault(cardStr, null);

        if (card != null)
            return card;

        return identifyShortcut(cardStr);
    }

    private Card identifyShortcut(String shortcut) {
        if (cardShortcuts.containsKey(shortcut)) {
            List<Card> shortcuts = cardShortcuts.get(shortcut);

            if (shortcuts.size() == 1)
                return shortcuts.getFirst();

            System.out.println("shortcut have " + shortcuts + " use full identifier instead.");
            return null;
        }
        return null;
    }

    private void setUniqueCards(List<Card> gameCards) {
        uniqueCards = new HashMap<>();

        for (Card card: gameCards) {
            String identifier = card.getIdentifier().toLowerCase();
            if (!uniqueCards.containsKey(identifier)) {
                uniqueCards.put(identifier, card);
            }
        }
    }

    private void setCardShortcuts() {
        cardShortcuts = new HashMap<>();

        for (Card card: uniqueCards.values()) {
            String shortcut = getCardShortcut(card);
            if (cardShortcuts.containsKey(shortcut)) {
                List<Card> list = cardShortcuts.get(shortcut);
                list.add(card);
                cardShortcuts.put(shortcut, list);
            } else {
                List<Card> newList = new ArrayList<>();
                newList.add(card);
                cardShortcuts.put(shortcut, newList);
            }
        }

    }
    private String getCardShortcut(Card card) {

        String []identifierParts = card.getIdentifier().split("-");
        StringBuilder cardShortcut = new StringBuilder();

        for (int i = 0; i < identifierParts.length; i++) {

            cardShortcut.append(identifierParts[i].charAt(0));

            if (i + 1 < identifierParts.length) {
                cardShortcut.append("-");
            }
        }

        return cardShortcut.toString().toLowerCase();
    }
}
