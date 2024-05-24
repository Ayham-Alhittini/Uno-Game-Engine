package engine.finishby;

import engine.entities.Player;
import engine.interfaces.GameFinishType;
import engine.interfaces.managers.PlayerManager;

import java.util.Deque;

public class LastPlayerFinish extends GameFinishType {

    public LastPlayerFinish(PlayerManager playerManager) {
        super(playerManager);
    }

    @Override
    public void showWinners() {
        if (isGameFinished()) {
            Deque<Player> players = getFinishedPlayers();
            players.add(getLastPlayerRemaining());
            System.out.println("Game finished with the following results:");
            for (int i = 1; !players.isEmpty(); i++) {
                System.out.println("*" + getPlayerRank(i) + " place (" + players.removeFirst() + ")");
            }
        } else {
            throw new IllegalArgumentException("Game not finished yet!!");
        }
    }
    @Override
    public boolean isGameFinished() {
        return isOnlyLastPlayerRemaining();
    }

    private boolean isOnlyLastPlayerRemaining() {
        return getPlayerManager().getPlayersCount() == 1;
    }

    private Player getLastPlayerRemaining() {
        return getPlayerManager().getCurrentPlayer();
    }

    private String getPlayerRank(int rankNumber) {

        String[] ranks = {
                "", "First", "Second", "Third", "Fourth", "Fifth", "Sixth", "Seventh", "Eighth", "Ninth", "Tenth",
                "Eleventh", "Twelfth", "Thirteenth", "Fourteenth", "Fifteenth", "Sixteenth", "Seventeenth",
                "Eighteenth", "Nineteenth", "Twentieth"
        };
        return rankNumber < ranks.length ? ranks[rankNumber] : rankNumber + "";
    }

}
