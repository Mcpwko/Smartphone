import net.miginfocom.swing.MigLayout;
import org.apache.commons.io.FileUtils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * <p> Classe qui gère les dispositions et les paramètres de l'application Gallery</p>
 * @author Mickaël Puglisi
 * @version 2.0
 */
public class Gallery extends JPanel implements ActionListener {
    /**
     * <p> Objet ButtonWithIcon qui ajoute le bouton permettant d'ajouter une image</p>
     */
    private ButtonWithIcon addphoto = new ButtonWithIcon("images//addphoto.png");
    /**
     * <p> Pannel qui crée un panneau au sud</p>
     */
    private JPanel southpanel1 = new JPanel();
    /**
     * <p> Panel principal où sont affichés les images</p>
     */
    private JPanel panelPictures = new JPanel();
    /**
     * <p> Le Cardlayout qui permet de changer de panel</p>
     */
    private CardLayout cardLayout = new CardLayout();
    /**
     * <p> Pannel qui s'affiche lorsqu'une image est sélectionnée</p>
     */
    private JPanel panel1 = new JPanel();
    /**
     * <p> Objet JScrollPane qui ajoute une barre de défilement</p>
     */
    private JScrollPane scrollPane = new JScrollPane(panelPictures);
    /**
     * <p> Objet JFileChooser qui demande à l'utilisateur de rajouter une image</p>
     */
    private JFileChooser fileChooser = new JFileChooser();


    /**
     * <p> Panel qui gère le mouvement entre les panels principaux</p>
     */
    private JPanel panelcont = new JPanel();
    /**
     * <p> Panel se trouvant au sud pour ajouter une photo </p>
     */
    private JPanel south = new JPanel();
    /**
     * <p> Crée le bouton previous </p>
     */
    private ButtonWithIcon previous = new ButtonWithIcon("images//previousApp.png");
    /**
     * <p> Panneau qui s'ajoute lorsqu'une image est sélectionnée</p>
     */
    private JPanel northSelectedPicture = new JPanel();
    /**
     * <p> Crée ke bouton de suppression d'image</p>
     */
    private JButton deletePicture = new JButton("Delete");
    /**
     * <p> Objet JOptionPane qui demande à l'utilisateur de valider la suppression</p>
     */
    private JOptionPane deletePermanent = new JOptionPane();
    /**
     * <p> Titre de l'application</p>
     */
    private JLabel titre = new JLabel("Photos");
    /**
     * <p> Objet de type File qui donne la localisation des images enregistrées</p>
     */
    private File monRepertoire=new File("Gallery");
    /**
     * <p> Panel qui s'affiche lorsqu'une image est sélectionnée</p>
     */
    private JPanel selectedPicture = new JPanel();
    /**
     * <p> Appel l'image et la redimensionne</p>
     * @see JLabelPictureSelected
     */
    private JLabelPictureSelected image;


    /**
     * <p> Constructeur qui gère le positionnement des éléments déclarés</p>
     * @throws IOException
     */
    public Gallery() throws IOException {
        setBackground(Color.BLACK);
        setLayout(new BorderLayout());
        add(panelcont,BorderLayout.CENTER);
        panelcont.setBackground(Color.BLACK);
        add(south,BorderLayout.SOUTH);
        south.setLayout(new FlowLayout(FlowLayout.CENTER));


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
        scrollPane.setViewportView(panelPictures);
        panel1.add(scrollPane);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        panelPictures.setLayout(new MigLayout());
        panelPictures.setBackground(Color.BLACK);
        scrollPane.getVerticalScrollBar().setUnitIncrement(100);

        northSelectedPicture.setLayout(new BorderLayout());
        northSelectedPicture.setBackground(Color.BLACK);

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

    /**
     * @return panelPictures
     */
    public JPanel getPanelPictures() {
        return panelPictures;
    }

    /**
     * @return panelcont
     */
    public JPanel getPanelcont(){
        return panelcont;
    }


    /**
     * @return cardLayout
     */
    public CardLayout getCardLayout() {
        return cardLayout;
    }

    /**
     * @throws IOException
     * <p> Méthode qui lit les images stockées</p>
     */
    //initialiser les composants
    private void initComponentPictures() throws IOException {

        File [] f = monRepertoire.listFiles();
        for(int i =0; i< f.length; i++){
            System.out.println("Chargement de l'image : " + i);
            //ButtonWithIcon button = new ButtonWithIcon ( "Gallery\\" + i +".jpg" );

            JButton button = new JButton (  );
            Image img = null;
            try {
                img = ImageIO.read(new File ("Gallery/" + i +".jpg"));
            } catch (IOException e) {
                e.printStackTrace ();
            }
            ImageIcon ii = new ImageIcon(img);
            ImageIcon iiNew = getScaledImage ( ii,110,110 );
            button.setIcon ( iiNew );



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


    /**
     * <p> Méthode qui sauvegarde les nouvelles images</p>
     * @param fileNewName
     */
    public void addNewScreenshot(String fileNewName){

        JButton button = new JButton (  );
        Image img = null;
        try {
            img = ImageIO.read(new File ("Gallery/" + fileNewName));
        } catch (IOException e) {
            e.printStackTrace ();
        }
        ImageIcon ii = new ImageIcon(img);
        ImageIcon iiNew = getScaledImage ( ii,110,110 );
        button.setIcon ( iiNew );
        button.setMaximumSize ( new Dimension ( 112, 112 ) );
        button.setMinimumSize ( new Dimension ( 112, 112 ) );
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);

        if ((panelPictures.getComponentCount () + 1) % 4 == 0 && panelPictures.getComponentCount () != 0) {
            button.setActionCommand ( "" + panelPictures.getComponentCount () );
            panelPictures.add ( button, "wrap" );
        } else {
            button.setActionCommand ( "" + panelPictures.getComponentCount () );
            panelPictures.add ( button );
        }

        button.addActionListener ( new Gallery.newImage () );

        panelPictures.revalidate ();
        panelPictures.repaint (  );

    }


    /**
     * @param e
     */
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
                //ImageIcon img = new ImageIcon ( path );
                /*ButtonWithIcon button = null;
                try {
                    button = new ButtonWithIcon ( path );
                } catch (IOException e1) {
                    e1.printStackTrace ();
                }*/


                JButton button = new JButton (  );
                Image img = null;
                try {
                    img = ImageIO.read(new File (path));
                } catch (IOException e2) {
                    e2.printStackTrace ();
                }
                ImageIcon ii = new ImageIcon(img);
                ImageIcon iiNew = getScaledImage ( ii,110,110 );
                button.setIcon ( iiNew );




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

    /**
     * <p> Gère le placement d'une nouvelle image dans l'application</p>
     */
    class newImage implements  ActionListener{
        /**
         * @param e
         */
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

    /**
     * <p> Méthode permettant de redimensionner une image</p>
     * @param srcImg
     * @param w
     * @param h
     * @return ImageIcon
     */
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

    /**
     * <p> Classe qui gère la dimension d'une image sélectionnée</p>
     */
    class JLabelPictureSelected extends JLabel{

        /**
         * @param icon
         * @throws IOException
         */
        public JLabelPictureSelected(String icon) throws IOException {
            Image img = ImageIO.read(new File (icon));
            ImageIcon ii = new ImageIcon(img);
            ImageIcon ii2 = getScaledImage(ii,480,700);
            setIcon(ii2);

        }


    }


}


