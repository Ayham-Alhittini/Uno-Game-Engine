package engine.colorshitf;

import engine.interfaces.ColorShiftBehavior;
import engine.interfaces.managers.StateManager;
import shared.constants.CardColor;

public class Colored implements ColorShiftBehavior {

    @Override
    public void shiftColor(StateManager stateManager, CardColor cardColor) {
        if (cardColor == CardColor.Wild)
            throw new IllegalArgumentException("Can not set wild color directly, use wild behavior instead.");
        stateManager.setGameColor(cardColor);
    }
}
