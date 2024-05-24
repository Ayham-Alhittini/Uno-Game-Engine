import engine.Game;
import variations.mygame.MyGame;

public class GameDriver {
    public static void main(String[] args) {
        Game game = new MyGame();
        game.play();
    }
}
