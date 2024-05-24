package shared.constants;
public enum ActionType {
    NoAction, Skip, Reverse, Draw_2, Draw_4, Skip_All, Targeted_Draw_2, Swap_Hands;

    @Override
    public String toString() {
        return name().replace('_', '-');
    }
}

