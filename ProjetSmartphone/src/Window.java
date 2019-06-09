import javax.swing.*;
import java.awt.*;
import java.io.IOException;


/**
 * La fenêtre principal qui va contenir tout le programme
 * @author Mickaël
 * @version 2.0
 */
public class Window extends JFrame {
    /**
     *l'image qui va être visible autour du panneau
     */
    private JLabel label = new JLabel(new ImageIcon(getClass().getResource("images\\smartphone.png")));
    /**
     *Le panneau qui contient tout le reste du programme
     */
    private JPanel panelintern = new JPanel ( );
    /**
     *Le panneau laucnher qui permet de déverouiller le smartphone
     */
    private Lockscreen lockscreen = new Lockscreen();


    /**
     * Constructeur Window
     * <p>La construction de la fenêtre se fait avec un panneau central oû toutes les actions du programme
     * se feront à l'intérieur et à l'extérieur de ce panneau il y a une image qui entoure le panneau
     * afin de ressembler à un veritable smartphone</p>
     * @throws IOException Lorsque le fond d'écran n'arrive pas à charger
     */
    public Window() throws IOException {
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
