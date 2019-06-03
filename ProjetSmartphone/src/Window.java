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


class ExternWindow extends JFrame {
    private JPanel pane = new JPanel ();
    private JInternalFrame frame2 = new JInternalFrame ( "My Telephone" ); //INTERNAL FRAME
    private JLabel phone = new JLabel ( new ImageIcon ( ImageIO.read ( getClass ().getResource ( "/test7.png" ) ) ) );
    private Lockscreen lockscreen = new Lockscreen();




    public ExternWindow() throws IOException {
        setDefaultCloseOperation ( JFrame.EXIT_ON_CLOSE );
        setUndecorated ( true );
        setVisible ( true );
        setLayout ( null );
        setSize ( 600,1000 );
        this.setLocationRelativeTo ( null );


        setBackground ( new Color ( 0, 0, 0, 0 ) );



        //frame2.setClosable ( true );
        javax.swing.plaf.InternalFrameUI ui = frame2.getUI();
        ((javax.swing.plaf.basic.BasicInternalFrameUI)ui).setNorthPane(null);
        frame2.setDefaultCloseOperation ( JFrame.EXIT_ON_CLOSE );
        frame2.setVisible ( true );
        //frame2.setBackground ( Color.RED );

        //phone.setSize ( 500,870 );

        phone.setBounds ( 0,0,540,900 );
        this.add (phone );

        frame2.setSize ( 480, 800 );
        //frame2.setContentPane ( pane );

        //pane.setBackground ( Color.red );

        frame2.setBounds (30,49,480,800);
        add ( frame2 );

        frame2.setContentPane (lockscreen);







        //this.pack();

    }
}

