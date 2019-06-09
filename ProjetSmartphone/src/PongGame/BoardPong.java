package PongGame;

import javax.swing.*;
import java.awt.*;


/**
 * @author Mickaël
 * @version 1.0
 */
public class BoardPong extends JPanel  {
    /**
     * création du jeu
     */
    private Game game = new Game();

    /**
     * Constructeur panneau Pong
     * <p>panneau qui contient le jeu Pong</p>
     */
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

    /**
     * <p>retourne l'objet Game </p>
     * @return objet Game
     */
    public Game getGame(){
        return game;
    }
}
