import javax.swing.*;
import java.awt.*;
import java.io.IOException;


public class Window extends JFrame {
    private Lockscreen lockscreen = new Lockscreen();
    private HomeScreen homeScreen = new HomeScreen();
    private NorthPanel northpanel = new NorthPanel();
    private JPanel jp = new JPanel();

    public Window() throws IOException {

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setSize(480, 800);
        this.setResizable(false); /** Lock the size of the window*/
        this.setLocationRelativeTo(null);
        this.add(lockscreen);
        //this.add(homeScreen);
        //this.add(northpanel);
        /*add(jp);
        jp.setBackground(Color.YELLOW);
        jp.setLayout(null);
        northpanel.setBounds(0,0,480,30);
        jp.add(northpanel);*/
    }
}
