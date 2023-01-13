package gameOfLife;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Game game = new Game(args[0]);
        game.gameStart();
    }
}
