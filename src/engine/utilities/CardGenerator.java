package engine.utilities;

import engine.cards.*;
import shared.constants.ActionType;
import shared.constants.CardColor;
import variations.official.NumberCard;

import java.util.ArrayList;
import java.util.List;

public class CardGenerator {
    public static ArrayList<Card> createNumberCards(int start, int end, CardColor color) {
        ArrayList<Card> numberCards = new ArrayList<>();
        for (int i = start; i <= end; i++) {
            numberCards.add(new NumberCard(color, i));
        }
        return numberCards;
    }
    public static ArrayList<Card> getActionCards(CardColor color) {
        ArrayList<Card> actionCards = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            actionCards.add(new ColoredActionCard(color, ActionType.Skip));
            actionCards.add(new ColoredActionCard(color, ActionType.Reverse));
            actionCards.add(new ColoredActionCard(color, ActionType.Draw_2));
        }

        return actionCards;
    }
    public static ArrayList<Card> getNumberCards(CardColor color) {
        ArrayList<Card> numberCards = new ArrayList<>();
        numberCards.addAll(CardGenerator.createNumberCards(0, 9, color));
        numberCards.addAll(CardGenerator.createNumberCards(1, 9, color));
        return numberCards;
    }
    public static ArrayList<Card> getWildCards() {
        ArrayList<Card> wildCards = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            wildCards.add(new WildCard());
            wildCards.add(new WildActionCard(ActionType.Draw_4));
        }

        return wildCards;
    }

    public static ArrayList<Card> getAllCardsColorForAction(ActionType actionType) {
        ArrayList<Card> cards = new ArrayList<>();

        for (CardColor cardColor: CardColor.values()) {
            if (cardColor != CardColor.Wild) {
                cards.add(new ColoredActionCard(cardColor, actionType));
            }
        }

        return cards;
    }

    public static List<Card> getAllCardsColorForLabel(String label) {
        List<Card> cards = new ArrayList<>();
        for(CardColor cardColor: CardColor.values()) {
            if (cardColor != CardColor.Wild) {
                cards.add(new ColoredLabelCard(cardColor, label));
            }
        }
        return cards;
    }

    public static ArrayList<Card> getUnoBasicCards() {

        ArrayList<Card> cards = new ArrayList<>();

        cards.addAll(CardGenerator.getNumberCards(CardColor.Green));
        cards.addAll(CardGenerator.getNumberCards(CardColor.Red));
        cards.addAll(CardGenerator.getNumberCards(CardColor.Blue));
        cards.addAll(CardGenerator.getNumberCards(CardColor.Yellow));

        cards.addAll(CardGenerator.getActionCards(CardColor.Green));
        cards.addAll(CardGenerator.getActionCards(CardColor.Red));
        cards.addAll(CardGenerator.getActionCards(CardColor.Blue));
        cards.addAll(CardGenerator.getActionCards(CardColor.Yellow));

        cards.addAll(CardGenerator.getWildCards());

        return cards;
    }
}
