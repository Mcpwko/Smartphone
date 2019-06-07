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
import java.util.concurrent.TimeUnit;

public class Gallery extends JPanel implements ActionListener {
    private ButtonWithIcon addphoto = new ButtonWithIcon("images//addphoto.png");
    //private ButtonWithIcon deletephoto = new ButtonWithIcon("images//deletephoto.png");
    private JPanel southpanel1 = new JPanel();
    private JPanel panelPictures = new JPanel();
    private CardLayout cardLayout = new CardLayout();
    private JPanel panel1 = new JPanel();
    private JScrollPane scrollPane = new JScrollPane(panelPictures);
    private JFileChooser fileChooser = new JFileChooser();



    private JPanel panelcont = new JPanel();
    private JPanel south = new JPanel();
    private ButtonWithIcon previous = new ButtonWithIcon("images//previousApp.png");
    //private ButtonWithIcon previousSouth = new ButtonWithIcon("images//previous.png");
    private JPanel northSelectedPicture = new JPanel();
    private JButton deletePicture = new JButton("Delete");
    private JOptionPane deletePermanent = new JOptionPane();
    private JLabel titre = new JLabel("Photos");
    private File monRepertoire=new File("Gallery");
    private JPanel selectedPicture = new JPanel();
    private JLabelPictureSelected image;


    public Gallery() throws IOException {
        setBackground(Color.BLACK);
        setLayout(new BorderLayout());
        add(panelcont,BorderLayout.CENTER);
        panelcont.setBackground(Color.BLACK);
        add(south,BorderLayout.SOUTH);
        south.setLayout(new FlowLayout(FlowLayout.CENTER));
        //previousSouth.setBounds(420,700,50,50);
        //add(previousSouth);

        south.setBackground(Color.BLACK);



        panelcont.setLayout(cardLayout);
        panelcont.add(panel1,"1");
        panel1.setBackground(Color.BLACK);
        panel1.setLayout(new BorderLayout());
        panel1.add(titre,BorderLayout.NORTH);
        titre.setForeground(Color.WHITE);
        titre.setFont(new Font("Arial", Font.BOLD,40));

        panel1.add(southpanel1,BorderLayout.SOUTH);
        southpanel1.add(addphoto);
        southpanel1.setBackground(Color.black);
        scrollPane.setBorder((BorderFactory.createLineBorder(Color.BLACK, 1)) );
        scrollPane.setBackground(Color.BLACK);
        addphoto.addActionListener(this);
        //southpanel1.add(deletephoto);
        //deletephoto.addActionListener ( this );
        scrollPane.setViewportView(panelPictures);
        panel1.add(scrollPane);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        panelPictures.setLayout(new MigLayout());
        panelPictures.setBackground(Color.BLACK);
        scrollPane.getVerticalScrollBar().setUnitIncrement(100);

        northSelectedPicture.setLayout(new BorderLayout());
        northSelectedPicture.setBackground(Color.BLACK);
        //northSelectedPicture.setBorder(BorderFactory.createLineBorder(Color.green,1));

        selectedPicture.setLayout(new BorderLayout());
        northSelectedPicture.add(previous,BorderLayout.WEST);
        previous.addActionListener(this);
        northSelectedPicture.add(deletePicture,BorderLayout.EAST);
        deletePicture.setContentAreaFilled(false);
        deletePicture.setBorderPainted(false);
        deletePicture.addActionListener(this);
        deletePicture.setForeground(Color.WHITE);
        deletePicture.setFont((new Font("Arial",Font.BOLD,20)));

        initComponentPictures();





    }

    public JPanel getPanelcont(){
        return panelcont;
    }



    public CardLayout getCardLayout() {
        return cardLayout;
    }

    //initialiser les composants
    private void initComponentPictures() throws IOException {

        File [] f = monRepertoire.listFiles();
        for(int i =0; i< f.length; i++){
            System.out.println("Chargement de l'image : " + i);
            ButtonWithIcon button = new ButtonWithIcon ( "Gallery\\" + i +".jpg" );
            button.setMaximumSize(new Dimension(112,112));
            button.setMinimumSize(new Dimension(112,112));
            if((panelPictures.getComponentCount()+1)%4==0 && panelPictures.getComponentCount()!=0){
                button.setActionCommand ( panelPictures.getComponentCount ()+ "" );
                panelPictures.add(button,"wrap");
                button.addActionListener ( new newImage() );
            }
            else{
                button.setActionCommand ( panelPictures.getComponentCount ()+ "" );
                panelPictures.add(button);
                button.addActionListener ( new newImage() );

            }

        }
    }





    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println ( "J'ai appuyé sur l'image : " + e.getActionCommand () );
        if (e.getSource () == addphoto) {
            int returnVal = fileChooser.showOpenDialog ( this );
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile ();
                File destination = new File ( "Gallery\\" + panelPictures.getComponentCount () + ".jpg" );
                try {
                    FileUtils.copyFile ( file, destination );
                } catch (IOException e1) {
                    e1.printStackTrace ();
                }
                String path = file.getAbsolutePath ();
                ImageIcon img = new ImageIcon ( path );
                ButtonWithIcon button = null;
                try {
                    button = new ButtonWithIcon ( path );
                } catch (IOException e1) {
                    e1.printStackTrace ();
                }
                button.setMaximumSize ( new Dimension ( 112, 112 ) );
                button.setMinimumSize ( new Dimension ( 112, 112 ) );

                if ((panelPictures.getComponentCount () + 1) % 4 == 0 && panelPictures.getComponentCount () != 0) {
                    button.setActionCommand ( "" + panelPictures.getComponentCount () );
                    panelPictures.add ( button, "wrap" );
                } else {
                    button.setActionCommand ( "" + panelPictures.getComponentCount () );
                    panelPictures.add ( button );
                }

                button.addActionListener ( new newImage () );

                panelPictures.revalidate ();
                System.out.println ( panelPictures.getComponentCount () );
            }
        } else {
            if ( e.getSource () == previous) {
                cardLayout.show ( panelcont, "1" );

            } else {
                if (e.getSource () == deletePicture) {
                    int newName = Integer.valueOf ( image.getName () );
                    int rep = deletePermanent.showConfirmDialog ( this, "Do you really want to delete the picture ?",
                            "Delete", JOptionPane.OK_CANCEL_OPTION );
                    if (rep == JOptionPane.OK_OPTION) {

                        //panelcont.remove(panel);

                        System.out.println ( "J'ai appuyé sur OK" );
                        System.out.println("J'ai supprimé l'image : " + image.getName ());

                        File file = new File ( "Gallery//" + image.getName () + ".jpg" );
                        file.delete ();


                        boolean exists = file.exists();
                        System.out.println(exists);
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e1) {
                            e1.printStackTrace();
                        }

                        for (int i = Integer.valueOf ( newName ) + 1; i < panelPictures.getComponentCount (); i++) {
                            File fileName = new File ( "Gallery//" + i + ".jpg" );
                            File fileNewName = new File ( "Gallery//" + (i - 1) + ".jpg" );

                            System.out.println("Le nouveau nom des images : " + fileNewName.getName ());
                            fileName.renameTo ( fileNewName );
                        }



                        cardLayout.show ( panelcont, "1" );
                        panelPictures.removeAll ();

                        System.out.println("Nombre de composants : " +panelPictures.getComponentCount ());

                        try {
                            initComponentPictures();
                        } catch (IOException e1) {
                            e1.printStackTrace ();
                        }
                    }
                }
            }
        }
    }
        class newImage implements  ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            selectedPicture.removeAll();
            System.out.println("Gallery//"+(e.getActionCommand())+".jpg");
            try {
                image = new JLabelPictureSelected("Gallery//"+(e.getActionCommand())+".jpg");
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            image.setName(e.getActionCommand());
            System.out.println(image.getName());

            selectedPicture.add(image,BorderLayout.CENTER);
            selectedPicture.add(northSelectedPicture,BorderLayout.NORTH);
            selectedPicture.revalidate();
            selectedPicture.repaint();


            panelcont.add(selectedPicture,  "2" );
            cardLayout.show(panelcont, "2");
        }

    }


}

class JLabelPictureSelected extends JLabel{

    public JLabelPictureSelected(String icon) throws IOException {
        Image img = ImageIO.read(new File (icon));
        ImageIcon ii = new ImageIcon(img);
        ImageIcon ii2 = getScaledImage(ii,480,700);
        setIcon(ii2);

    }

    private ImageIcon getScaledImage(ImageIcon srcImg, int w, int h){
        Image img = srcImg.getImage();
        int width   = img.getWidth(null);
        int height  = img.getHeight(null);
        System.out.println(height + " ET " + width);

        if(width >=480 || height>=700) {
            BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2 = resizedImg.createGraphics();

            g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g2.drawImage(img, 0, 0, w, h, null);
            g2.dispose();
            return new ImageIcon(resizedImg);
        }else

        return new ImageIcon(img);


    }
}
