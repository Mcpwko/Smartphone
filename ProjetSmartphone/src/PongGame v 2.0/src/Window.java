import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyListener;


public class Window extends JFrame {
    private BoardPong boardPong = new BoardPong();

    public Window(String name) {
        setTitle("PONG-GAME");
        setSize ( 480,800 );
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setContentPane(boardPong);



        //setFocusable(true);
        /*addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                requestFocus();
            }
        });*/
        }








    public static void main(String[] args) {
        Window window = new Window ("Pong");
        //BoardPong boardPong = new BoardPong();
        //Game game = new Game();

        //window.setContentPane(game);
        //window.addKeyListener(game);
        //game.start();

    }

}
