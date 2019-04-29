import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.metal.MetalSliderUI;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Lockscreen extends JPanel implements ChangeListener {
    private JSlider slide = new JSlider(0,100,0);
    Icon icon = new ImageIcon("lock1.png");
    UIDefaults defaults = UIManager.getDefaults();
    private JPanelWithBackground jp = new JPanelWithBackground ( "Background.jpg");



    public Lockscreen() throws IOException {
        setLayout(new BorderLayout());
        add(jp);
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
            }
            if(slide.getValue()<100){
                slide.setValue (0);
            }
        }
    }

}

class JPanelWithBackground extends JPanel {

    private Image backgroundImage;

    // Some code to initialize the background image.
    // Here, we use the constructor to load the image. This
    // can vary depending on the use case of the panel.
    public JPanelWithBackground(String fileName) throws IOException {
        backgroundImage = ImageIO.read(new File (fileName));
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw the background image.
        g.drawImage(backgroundImage, 0, 0, this);
    }
}