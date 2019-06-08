package PongGame;

import javax.swing.*;
import java.awt.*;


public class BoardPong extends JPanel  {
    private Game game = new Game();

    public BoardPong() {
        setLayout(new BorderLayout());
        setBackground(Color.yellow);

        add(game,BorderLayout.CENTER);
        game.start();
        addKeyListener(game);
        setVisible ( true );
        setFocusable(true);
        grabFocus ();



    }

    public Game getGame(){
        return game;
    }
}
