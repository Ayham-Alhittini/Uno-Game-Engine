package engine.interfaces;

import engine.interfaces.managers.StateManager;
import shared.constants.CardColor;

public interface ColorShiftBehavior {
    void shiftColor(StateManager stateManager, CardColor cardColor);
}
