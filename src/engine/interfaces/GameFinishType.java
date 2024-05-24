package engine.interfaces;

import engine.entities.Player;
import engine.interfaces.managers.PlayerManager;

import java.util.ArrayDeque;
import java.util.Deque;

public abstract class GameFinishType {

    private final PlayerManager playerManager;
    protected GameFinishType(PlayerManager playerManager) {
        this.playerManager = playerManager;
    }

    protected PlayerManager getPlayerManager() {
        return playerManager;
    }

    private final Deque<Player> players = new ArrayDeque<>();

    protected Deque<Player> getFinishedPlayers() {
        return players;
    }

    public void addFinishedPlayer(Player player) {
        players.add(player);
    }

    public abstract void showWinners();
    public abstract boolean isGameFinished();
}
