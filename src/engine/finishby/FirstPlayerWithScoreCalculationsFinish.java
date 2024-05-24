package engine.finishby;

import engine.entities.Player;
import engine.interfaces.GameFinishType;
import engine.interfaces.managers.PlayerManager;

import java.util.Comparator;
import java.util.List;

public class FirstPlayerWithScoreCalculationsFinish extends GameFinishType {


    public FirstPlayerWithScoreCalculationsFinish(PlayerManager playerManager) {
        super(playerManager);
    }

    private static class SortByScore implements Comparator<Player> {
        @Override
        public int compare(Player p1, Player p2) {
            return Integer.compare(p1.getPlayerScore(), p2.getPlayerScore());
        }
    }

    @Override
    public void showWinners() {

        List<Player> players = getUnfinishedPlayers();

        players.addAll(getFinishedPlayers());

        players.sort(new SortByScore());

        System.out.println("Game finish with the following result:");
        for (Player player: players) {
            System.out.println("*" + player + " " + player.getPlayerScore() + " points");
        }
    }

    @Override
    public boolean isGameFinished() {
        return isThereAnyPlayer();
    }

    private List<Player> getUnfinishedPlayers() {
        return getPlayerManager().getPlayers();
    }

    private boolean isThereAnyPlayer() {
        return !getFinishedPlayers().isEmpty();
    }
}
