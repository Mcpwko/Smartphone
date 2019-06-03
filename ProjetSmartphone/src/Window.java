import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;


public class Window extends JFrame {
    private Lockscreen lockscreen = new Lockscreen();

    public Window() throws IOException {

        this.setUndecorated ( true );
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setSize(480, 800);
        this.setResizable(false); /** Lock the size of the window*/
        this.setLocationRelativeTo(null);
        this.add(lockscreen);

    }
}


class FrameExtern extends JFrame {
    private JLabel label = new JLabel(new ImageIcon("images\\smartphone.png"));
    private JPanel panelintern = new JPanel ( );
    private Lockscreen lockscreen = new Lockscreen();



    public FrameExtern() throws IOException {
        setUndecorated ( true );
        setBackground ( new Color ( 0, 0, 0, 0) );
        setSize ( 700,1000 );
        setLayout ( null );
        add(label);
        label.setBounds ( 0,0,685,1000 );

        add(panelintern);
        panelintern.setBounds ( 102,100,480,800 );
        setVisible ( true );

        panelintern.setBackground ( Color.WHITE );
        panelintern.setLayout ( new BorderLayout (  ) );
        panelintern.add(lockscreen,BorderLayout.CENTER);
        setLocationRelativeTo ( null );
        setDefaultCloseOperation ( EXIT_ON_CLOSE );

    }


}
