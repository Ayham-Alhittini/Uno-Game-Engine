package shared;

import variations.mygame.SkipAllAction;
import variations.mygame.SwapHandsAction;
import variations.mygame.TargetedDrawTwoAction;
import shared.constants.ActionType;
import engine.actions.*;
import engine.interfaces.ActionBehavior;

public class ActionFactory {
    public static ActionBehavior getAction(ActionType actionType) {
        if (actionType == ActionType.NoAction)
            return new NoAction();
        else if (actionType == ActionType.Skip)
            return new SkipAction();
        else if (actionType == ActionType.Reverse)
            return new ReverseAction();
        else if (actionType == ActionType.Draw_2)
            return new DrawTwoAction();
        else if (actionType == ActionType.Draw_4)
            return new DrawFourAction();

        //MyGame actions
        else if (actionType == ActionType.Skip_All)
            return new SkipAllAction();
        else if (actionType == ActionType.Targeted_Draw_2)
            return new TargetedDrawTwoAction();
        else if (actionType == ActionType.Swap_Hands)
            return new SwapHandsAction();

        return null;
    }
}
