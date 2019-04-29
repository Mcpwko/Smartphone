import javax.swing.*;
import java.io.IOException;


public class Window extends JFrame {
    private Lockscreen lockscreen = new Lockscreen();
    private HomeScreen homeScreen = new HomeScreen();


    public Window() throws IOException {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setSize(400, 600);
        this.setResizable(false); /** Lock the size of the window*/
        this.setLocationRelativeTo(null);
        this.add(lockscreen);
        //this.add(homeScreen);
    }
}
