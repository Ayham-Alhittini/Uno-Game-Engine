package engine.colorshitf;

import engine.interfaces.managers.StateManager;
import shared.constants.CardColor;
import engine.utilities.Painter;
import engine.interfaces.ColorShiftBehavior;

import java.util.Scanner;

public class Wild implements ColorShiftBehavior {


    @Override
    public void shiftColor(StateManager stateManager, CardColor cardColor) {
        Scanner scanner = new Scanner(System.in);

        boolean validColor = false;

        while (!validColor) {
            Painter.printInput("Select a color: ");

            String selectedColor = scanner.next();
            cardColor = getColor(selectedColor);
            if (cardColor != null) {
                stateManager.setGameColor(cardColor);
                validColor = true;
            } else {
                Painter.printError("Invalid color");
            }
        }
    }

    private CardColor getColor(String color) {
        for (CardColor cardColor : CardColor.values()) {
            if (cardColor.toString().equalsIgnoreCase(color) && cardColor != CardColor.Wild)
                return cardColor;
        }
        return null;
    }

}
