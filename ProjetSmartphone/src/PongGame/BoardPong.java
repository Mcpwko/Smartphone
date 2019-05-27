package PongGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;

public class BoardPong extends JPanel {
    Game game = new Game();


    public BoardPong() {
        Game game = new Game();
        setLayout(new BorderLayout());
        add(game,BorderLayout.CENTER);
        addListener(game);

        game.start();



    }



    public void addGameInstance(Game game) {
        add(game);
    }
    public void addListener(KeyListener listener) {
        this.addKeyListener(listener);
    }
}
