package engine.managers;

import engine.interfaces.managers.PlayerManager;
import engine.entities.Player;

import java.util.ArrayList;
import java.util.List;

public class GamePlayerManager implements PlayerManager {

    private static class PlayerTurn {
        Player player;
        PlayerTurn onRightPlayer, onLeftPlayer;
        PlayerTurn(Player player, PlayerTurn onRightPlayer, PlayerTurn onLeftPlayer) {
            this.player = player;
            this.onLeftPlayer = onLeftPlayer;
            this.onRightPlayer = onRightPlayer;
        }
        @Override
        public String toString() {
            return player.getName();
        }
    }
    private PlayerTurn firstPlayer, lastPlayer, currentPlayer;
    private int playersCount = 0;
    private boolean clockWiseDirection = true;

    public GamePlayerManager(int numberOfPlayers) {
        if (numberOfPlayers < 1)
            throw new IllegalArgumentException("Enter a valid number of players.");
        createPlayers(numberOfPlayers);
    }

    public GamePlayerManager() {}

    public void createPlayers(int numberOfPlayers) {
        for (int i = 1; i <= numberOfPlayers; i++) {
            addPlayer(new Player("Player" + i));
        }
        currentPlayer = firstPlayer;
    }

    @Override
    public void addPlayer(Player player) {
        PlayerTurn newPlayer = new PlayerTurn(player, null, null);
        if (playersCount == 0) {
            firstPlayer = lastPlayer = newPlayer;
        } else {
            newPlayer.onLeftPlayer = lastPlayer;
            newPlayer.onRightPlayer = firstPlayer;

            lastPlayer.onRightPlayer = newPlayer;
            lastPlayer = newPlayer;
            firstPlayer.onLeftPlayer = newPlayer;
        }
        playersCount++;
    }

    private PlayerTurn getPlayerTurnByPlayerName(String playerName) {
        PlayerTurn playerTurn = currentPlayer;

        for (int i = 0; i < playersCount; i++) {
            if (playerTurn.player.getName().equalsIgnoreCase(playerName))
                return playerTurn;
            playerTurn = playerTurn.onRightPlayer;
        }
        return null;
    }

    @Override
    public List<Player> getPlayers() {
        List<Player> players = new ArrayList<>();

        PlayerTurn temp = currentPlayer;
        for (int i = 0; i < playersCount; i++) {
            players.add(temp.player);
            temp = temp.onRightPlayer;
        }

        return players;
    }

    @Override
    public Player getCurrentPlayer() {
        return currentPlayer.player;
    }

    @Override
    public Player getNextPlayer() {
        if (playersCount == 1)
            return currentPlayer.player;
        if (clockWiseDirection) {
            return currentPlayer.onRightPlayer.player;
        }
        return currentPlayer.onLeftPlayer.player;
    }

    @Override
    public Player getPreviousPlayer() {
        if (playersCount == 1)
            return currentPlayer.player;
        if (clockWiseDirection) {
            return currentPlayer.onLeftPlayer.player;
        }
        return currentPlayer.onRightPlayer.player;
    }

    @Override
    public Player getPlayerByName(String playerName) {
        PlayerTurn playerTurn = getPlayerTurnByPlayerName(playerName);
        return playerTurn == null ? null : playerTurn.player;
    }

    @Override
    public void goNextPlayer() {
        if (clockWiseDirection) {
            currentPlayer = currentPlayer.onRightPlayer;
        } else {
            currentPlayer = currentPlayer.onLeftPlayer;
        }
    }

    @Override
    public void removePlayer(String playerName) {
        PlayerTurn toRemovePlayer = getPlayerTurnByPlayerName(playerName);

        if (toRemovePlayer == null)
            throw new IllegalArgumentException("Player not exists.");//developer error

        if (playersCount < 1) {
            System.out.println("No player to remove.");
            return;
        }

        if (playersCount == 1) {
            firstPlayer = lastPlayer = currentPlayer = null;
        } else {
            PlayerTurn onLeftPlayer, onRightPlayer;

            onRightPlayer = toRemovePlayer.onRightPlayer;
            onLeftPlayer = toRemovePlayer.onLeftPlayer;

            onRightPlayer.onLeftPlayer = onLeftPlayer;
            onLeftPlayer.onRightPlayer = onRightPlayer;

            if (toRemovePlayer == currentPlayer)
                goNextPlayer();
        }
        playersCount--;
    }

    @Override
    public void reverseDirection() {
        clockWiseDirection = !clockWiseDirection;
    }

    @Override
    public int getPlayersCount() {
        return playersCount;
    }
}
