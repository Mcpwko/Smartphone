import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.metal.MetalSliderUI;
import java.awt.*;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author
 * @version 1.0
 */
public class Lockscreen extends JPanel implements ChangeListener {
    /**
     * Le slider servant à dévérouiller le smartphone
     */
    private JSlider slide = new JSlider(0,100,0);
    /**
     * l'icone se trouvant au millieu du slider
     */
    Icon icon = new ImageIcon("images//unlock.png");
    /**
     * paramètre permettant de modifier l'apparence du slider
     */
    UIDefaults defaults = UIManager.getDefaults();
    /**
     * l'arrière-plan de lockscreen
     * @see JPanelWithBackground
     */
    private JPanelWithBackground jp = new JPanelWithBackground ( "images//Background.jpg");
    /**
     * affichage de l'heure
     * @see TimeLabel
     */
    private TimeLabel clock = new TimeLabel();
    /**
     * affichage de la date du jour
     * @see TimeLabel
     */
    private DateLabel datejour = new DateLabel();


    /**
     * Constructeur du Lockscreen
     * <p>La construction du panneau Locsckreen affiche le slider ainsi que l'heure et la date
     * du jour</p>
     * @throws IOException
     */
    public Lockscreen() throws IOException {
        setLayout(new BorderLayout());
        add(jp,BorderLayout.CENTER);
        jp.setLayout (null);
        datejour.setBounds(130,200,220,80);
        clock.setBounds ( 120,100,220,80 );
        slide.setBounds ( 0,650,460,60 );
        jp.add(datejour);
        jp.add(slide);
        datejour.setForeground ( Color.white );
        clock.setForeground ( Color.white );
        datejour.setFont(new Font("Courrier New", Font.BOLD,20));
        clock.setFont(new Font("Courier New", Font.BOLD, 70));
        jp.add(clock);
        defaults.put("Slider.horizontalThumbIcon", icon);
        slide.addChangeListener(this);
        slide.setUI(new MetalSliderUI());
        slide.setOpaque ( false );

    }

    /**
     * @param e
     */
    public void stateChanged(ChangeEvent e) {
        if(e.getSource()== slide) {
            if(slide.getValue()==100){
                HomeScreen hm = null;
                try {
                    hm = new HomeScreen();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                add(hm);
                remove(this);
                remove(slide);
                remove(jp);
            }
            if(slide.getValue()<100){
                slide.setValue (0);
            }
        }
    }

}

/**
 * @author Mickaël
 * @version 1.0
 */
class JPanelWithBackground extends JPanel {
    private Image backgroundImage;

    /**
     * @param fileName le nom de l'image
     * @throws IOException si jamais l'image n'est pas trouvé
     */
    public JPanelWithBackground(String fileName) throws IOException {
        backgroundImage = Toolkit.getDefaultToolkit().createImage(fileName);
    }

    /**
     * méthode qui dessine l'image qui sert d'arrière-plan
     * @param g
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, this);

    }
}