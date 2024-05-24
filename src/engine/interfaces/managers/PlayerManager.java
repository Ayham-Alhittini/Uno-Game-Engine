package engine.interfaces.managers;
import engine.entities.Player;

import java.util.List;

public interface PlayerManager {
    List<Player> getPlayers();
    Player getCurrentPlayer();
    Player getNextPlayer();
    Player getPreviousPlayer();
    Player getPlayerByName(String playerName);
    void goNextPlayer();
    void addPlayer(Player player);
    void createPlayers(int numberOfPlayers);
    void removePlayer(String playerName);
    void reverseDirection();
    int getPlayersCount();
}
