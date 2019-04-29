import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.metal.MetalSliderUI;
import java.awt.*;

public class Lockscreen extends JPanel implements ChangeListener {
    private JSlider slide = new JSlider(0,100,0);
    Icon icon = new ImageIcon("home.png");
    UIDefaults defaults = UIManager.getDefaults();



    public Lockscreen(){
        setLayout(new BorderLayout());
        setBackground(Color.red);
        add(slide,BorderLayout.SOUTH);
        defaults.put("Slider.horizontalThumbIcon", icon);
        slide.addChangeListener(this);
        slide.setBackground(Color.BLACK);
        slide.setUI(new MetalSliderUI());

    }
    public void stateChanged(ChangeEvent e) {
        if(e.getSource()== slide) {
            if(slide.getValue()==100){
                HomeScreen hm = new HomeScreen();
                add(hm);
                remove(slide);
            }
        }
    }

}