import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.metal.MetalSliderUI;
import java.awt.*;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Lockscreen extends JPanel implements ChangeListener {
    private JSlider slide = new JSlider(0,100,0);
    Icon icon = new ImageIcon("unlock.png");
    UIDefaults defaults = UIManager.getDefaults();
    private JPanelWithBackground jp = new JPanelWithBackground ( "background1.jpg");
    private Time clock = new Time();
    private DateFormat df = new SimpleDateFormat ("EEEE d MMMM yyyy");
    private JLabel datejour = new JLabel ( );


    public Lockscreen() throws IOException {
        setLayout(new BorderLayout());
        add(jp,BorderLayout.CENTER);
        jp.setLayout (null);
        datejour.setText(df.format(new Date()));
        datejour.setBounds(130,200,220,80);
        clock.setBounds ( 120,100,220,80 );
        slide.setBounds ( 0,650,480,60 );
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
    public void stateChanged(ChangeEvent e) {
        if(e.getSource()== slide) {
            if(slide.getValue()==100){
                HomeScreen hm = new HomeScreen();
                add(hm);
                remove(slide);
                remove(jp);
            }
            if(slide.getValue()<100){
                slide.setValue (0);
            }
        }
    }

}

class JPanelWithBackground extends JPanel {
    private Image backgroundImage;

    public JPanelWithBackground(String fileName) throws IOException {
        backgroundImage = Toolkit.getDefaultToolkit().createImage(fileName);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw the background image.
        g.drawImage(backgroundImage, 0, 0, this);

    }
}