import net.miginfocom.swing.MigLayout;
import org.apache.commons.io.FileUtils;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

public class Gallery extends JPanel implements ActionListener {
    private ButtonWithIcon addphoto = new ButtonWithIcon("images//addphoto.png");
    private ButtonWithIcon deletephoto = new ButtonWithIcon("images//deletephoto.png");
    private JPanel southpanel1 = new JPanel();
    private JPanel panelPictures = new JPanel();
    private CardLayout cardLayout = new CardLayout();
    private JPanel panel1 = new JPanel();
    private JScrollPane scrollPane = new JScrollPane(panelPictures);
    private JFileChooser fileChooser = new JFileChooser();
    private File monRepertoire=new File("Gallery");
    private File [] f = monRepertoire.listFiles();
    private JButton photos = new JButton("Photos");
    private JButton albums = new JButton("Albums");
    private JPanel panelcont = new JPanel();
    private JPanel south = new JPanel();
    private ButtonWithIcon previous = new ButtonWithIcon("images//previousApp.png");
    private JPanel northSelectedPicture = new JPanel();
    private JButton deletePicture = new JButton("Delete");
    private JOptionPane deletePermanent = new JOptionPane();



    public Gallery(){
        setBackground(Color.BLACK);
        setLayout(new BorderLayout());
        add(panelcont,BorderLayout.CENTER);
        panelcont.setBackground(Color.BLACK);
        add(south,BorderLayout.SOUTH);
        south.setLayout(new FlowLayout(FlowLayout.CENTER));
        south.add(photos);
        photos.setContentAreaFilled(false);
        photos.setBorderPainted(false);
        photos.addActionListener(this);
        photos.setForeground(Color.WHITE);
        photos.setFont((new Font("Arial",Font.BOLD,20)));

        south.add(albums);
        south.setBackground(Color.BLACK);
        albums.setContentAreaFilled(false);
        albums.setBorderPainted(false);
        albums.addActionListener(this);
        albums.setForeground(Color.WHITE);
        albums.setFont((new Font("Arial",Font.BOLD,20)));


        panelcont.setLayout(cardLayout);
        panelcont.add(panel1,"1");
        panel1.setBackground(Color.BLACK);
        panel1.setLayout(new BorderLayout());
        panel1.add(southpanel1,BorderLayout.SOUTH);
        southpanel1.add(addphoto);
        southpanel1.setBackground(Color.black);
        scrollPane.setBorder((BorderFactory.createLineBorder(Color.BLACK, 1)) );
        scrollPane.setBackground(Color.BLACK);
        addphoto.addActionListener(this);
        southpanel1.add(deletephoto);
        scrollPane.setViewportView(panelPictures);
        panel1.add(scrollPane);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        panelPictures.setLayout(new MigLayout());
        panelPictures.setBackground(Color.BLACK);
        scrollPane.getVerticalScrollBar().setUnitIncrement(100);

        northSelectedPicture.setLayout(new BorderLayout());
        northSelectedPicture.setBackground(Color.BLACK);
        //northSelectedPicture.setBorder(BorderFactory.createLineBorder(Color.green,1));
        northSelectedPicture.add(previous,BorderLayout.WEST);
        previous.addActionListener(this);
        northSelectedPicture.add(deletePicture,BorderLayout.EAST);
        deletePicture.setContentAreaFilled(false);
        deletePicture.setBorderPainted(false);
        deletePicture.addActionListener(this);
        deletePicture.setForeground(Color.WHITE);
        deletePicture.setFont((new Font("Arial",Font.BOLD,20)));

        for(int i =0; i< f.length; i++){
            ButtonWithIcon button = new ButtonWithIcon ( "Gallery\\" + i +".jpg" );
            button.setMaximumSize(new Dimension(112,112));
            button.setMinimumSize(new Dimension(112,112));
            if((panelPictures.getComponentCount()+1)%4==0 && panelPictures.getComponentCount()!=0){
                button.setActionCommand ( panelPictures.getComponentCount ()+ "" );
                panelPictures.add(button,"wrap");
                button.addActionListener ( this );
            }
            else{
                button.setActionCommand ( panelPictures.getComponentCount ()+ "" );
                panelPictures.add(button);
                button.addActionListener ( this );

            }

        }


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());
        if (e.getSource() == addphoto) {
            int returnVal = fileChooser.showOpenDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                File destination = new File("Gallery\\" + panelPictures.getComponentCount() + ".jpg");
                try {
                    FileUtils.copyFile(file, destination);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                String path = file.getAbsolutePath();
                ImageIcon img = new ImageIcon(path);
                ButtonWithIcon button = new ButtonWithIcon(path);
                button.setMaximumSize(new Dimension(112, 112));
                button.setMinimumSize(new Dimension(112, 112));

                if ((panelPictures.getComponentCount() + 1) % 4 == 0 && panelPictures.getComponentCount() != 0) {
                    button.setActionCommand("" + panelPictures.getComponentCount());
                    panelPictures.add(button, "wrap");
                } else {
                    button.setActionCommand("" + panelPictures.getComponentCount());
                    panelPictures.add(button);
                }

                button.addActionListener(this);

                panelPictures.revalidate();
                System.out.println(panelPictures.getComponentCount());
            }
        } else {
            if (e.getSource() == photos || e.getSource()==previous) {
                cardLayout.show(panelcont,"1");

            } else {
                if(e.getSource()==deletePicture){
                    deletePermanent.showMessageDialog( this, "Do you really want to delete the picture ?",
                            "Delete",JOptionPane.OK_CANCEL_OPTION);

                }
                JPanel panel = new JPanel();
                JLabelWithIcon image = new JLabelWithIcon("Gallery//"+(e.getActionCommand())+".jpg");
                panel.setLayout(new BorderLayout());
                panel.add(image,BorderLayout.CENTER);
                panel.add(northSelectedPicture,BorderLayout.NORTH);
                panelcont.add(panel, e.getActionCommand() + 2 + "");
                cardLayout.show(panelcont, "" + (e.getActionCommand() + 2));


            }
        }
    }

}

class JLabelWithIcon extends JLabel{
    public JLabelWithIcon(String icon) {
        ImageIcon ii = new ImageIcon(icon);
        Image img = ii.getImage();


        setIcon(ii);
    }
}
