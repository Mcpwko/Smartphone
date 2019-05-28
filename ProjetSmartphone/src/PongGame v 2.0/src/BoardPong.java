import javax.swing.*;
import java.awt.*;


public class BoardPong extends JPanel  {


    public BoardPong() {
        setLayout(new BorderLayout());
        setBackground(Color.yellow);

        Game game = new Game();
        add(game,BorderLayout.CENTER);
        game.start();
        addKeyListener(game);
        setFocusable(true);



    }
}
