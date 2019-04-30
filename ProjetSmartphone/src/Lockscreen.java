import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.metal.MetalSliderUI;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class Lockscreen extends JPanel implements ChangeListener {
    private JSlider slide = new JSlider(0,100,0);
    Icon icon = new ImageIcon("lock1.png");
    UIDefaults defaults = UIManager.getDefaults();
    private JPanelWithBackground jp = new JPanelWithBackground ( "background1.jpg");

    public Lockscreen() throws IOException {
        setLayout(new BorderLayout());
        add(jp,BorderLayout.CENTER);
        jp.setLayout ( new BorderLayout (  ) );
        jp.add(slide,BorderLayout.SOUTH);
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