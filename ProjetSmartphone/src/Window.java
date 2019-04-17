import javax.swing.*;
import java.awt.*;


public class Window extends JFrame {
    private Lockscreen lockscreen = new Lockscreen();
    private HomeScreen homeScreen = new HomeScreen();


    public Window(){
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setSize(400, 600);
        this.setResizable(false); /** Lock the size of the window*/
        this.setLocationRelativeTo(null);
        this.setLayout(new CardLayout());
        //this.add(lockscreen);
        this.add(homeScreen);
    }
}
