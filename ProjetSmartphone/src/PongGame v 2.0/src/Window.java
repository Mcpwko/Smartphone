import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;


public class Window extends JFrame {
    private static final long serialVersionUID = 1L;

    public Window(String name) {
        setTitle("PONG-GAME");
        setSize ( 480,800 );
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        }

    public static void main(String[] args) {
        Window window = new Window ("Pong");
        Game game = new Game();

        window.addGameInstance(game);
        window.addListener(game);
        game.start();
    }
    public void addGameInstance(Game game) {
        add(game);
    }
    public void addListener(KeyListener listener) {
        this.addKeyListener(listener);
    }
}
