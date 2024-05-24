package engine.finishby;

import engine.interfaces.GameFinishType;
import engine.interfaces.managers.PlayerManager;

public class FirstPlayerFinish extends GameFinishType {

    public FirstPlayerFinish(PlayerManager playerManager) {
        super(playerManager);
    }

    @Override
    public void showWinners() {
        if (isGameFinished()) {
            System.out.println(getFinishedPlayers().getFirst() + " won.");
        } else {
            throw new IllegalArgumentException("Game not finished yet!!");
        }
    }

    @Override
    public boolean isGameFinished() {
        return isThereAnyPlayer();
    }

    private boolean isThereAnyPlayer() {
        return !getFinishedPlayers().isEmpty();
    }

}
