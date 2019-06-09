package Contact;

import net.miginfocom.layout.CC;
import net.miginfocom.swing.MigLayout;
import org.apache.commons.io.FileUtils;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * <p>L'application contact permet de gérer une liste de contact enregistré </p>
 * @author Kevin Coppey
 * @version 2.0
 */
public class ContactPanel extends JPanel implements ActionListener, FocusListener {

    //Création des panneaux de base


    /**
     * <p> Voici le panel principal. Il contient tous les éléments de l'apllication </p>
     */
    private JPanel panelcontent = new JPanel();
    /**
     * <p> Ce panel contient la liste des contacts</p>
     */
    private JPanel contactlist = new JPanel ();
    /**
     * <p>Ce panel contient les informations de chaque contact </p>
     */
    private JPanel information = new JPanel();
    /**
     * <p> Ce panel sert à afficher la page d'ajout de contact </p>
     */
    private JPanel contactnew = new JPanel();

    //Création du CardLayout pour naviguer entre les panels

    /**
     * <p> La création d'un card layout pour naviguer entre les panels</p>
     */
    private CardLayout cards = new CardLayout();

    //Création des panneaux du panneau de la liste de contact
    /**
     * <p> Le panel nord de l'application contacts</p>
     */
    private JPanel northpanel = new JPanel();
    /**
     * <p> Le deuxième panneau nord de l'application contacts</p>
     */
    private JPanel northpanel2 = new JPanel();
    /**
     * <p> Le panneau central de l'application contacts </p>
     */
    private JPanel centerpanel = new JPanel();
    /**
     * <p> Le panneau contenant la liste des contacts </p>
     */
    private JPanel listdecontact = new JPanel();
    /**
     * <p> Le label qui donne le titre de l'application</p>
     */
    private JLabel label = new JLabel("Contacts");
    /**
     * <p> Le bouton plus servant à ajouter un contact</p>
     * @see ButtonWithIcon
     */
    private ButtonWithIcon buttonplus = new ButtonWithIcon(".\\src\\Contact\\addcontact1.png");
    /**
     * <p> Une police d'écriture pour le texte</p>
     */
    private Font font = new Font("Arial",Font.BOLD,30);
    /**
     * <p> Une police d'écriture pour le texte</p>
     */
    private Font font2 = new Font("Arial",Font.BOLD,15);
    /**
     * <p> La création d'une bordure blanche </p>
     */
    private Border border = BorderFactory.createLineBorder(Color.WHITE, 1);


    /**
     * <p> Un tableau de bouton permettant de créer plusieurs contacts</p>
     */
    private JButton listContactButton[] = new JButton[10000000];


    //Création des élément du panneau d'ajout de contact

    /**
     * <p> Le panel central de l'ajout de contact </p>
     */
    private JPanel panelCenter = new JPanel();
    /**
     * <p> Le titre du panneau d'ajout d'un nouveau contact</p>
     */
    private JLabel nouveaucontact = new JLabel("Nouveau Contact");
    /**
     * <p> Le bouton qui créer l'image à choisir pour l'utilisateur</p>
     */
    private ButtonWithIcon photo = new ButtonWithIcon(".\\src\\Contact\\photo.png");
    /**
     * <p> Le champs à remplir concernant le prénom de l'utilisateur</p>
     */
    private JTextField prenom = new JTextField("Prénom");
    /**
     * <p> Le champs à remplir concernant le nom de l'utilisateur</p>
     */
    private JTextField nom = new JTextField("Nom");
    /**
     * <p> Le champs à remplir concernant l'email de l'utilisateur</p>
     */
    private JTextField emailtext = new JTextField("E-mail");
    /**
     * <p> Le champs à remplir concernant le numéro de téléphone privé de l'utilisateur</p>
     */
    private JTextField phonenumber = new JTextField("Téléphone privé");
    /**
     * <p> Le champs à remplir concernant le numéro de téléphone professionnel de l'utilisateur</p>
     */
    private JTextField phonenumber2 = new JTextField("Téléphone professionnel");
    /**
     * <p> Le champs à remplir concernant le numéro de téléphone du domicile de l'utilisateur</p>
     */
    private JTextField phonenumber3 = new JTextField("Téléphone domicile");
    /**
     * <p> Le champs à remplir concernant l'adresse de l'utilisateur</p>
     */
    private JTextField adress = new JTextField("Adresse");
    /**
     * <p> Le logo concernant le téléphone privé de l'utilisateur</p>
     * @see LabelWithIcon
     */
    private LabelWithIcon privatephone = new LabelWithIcon(".\\src\\Contact\\smartphone.png");
    /**
     * <p> Le logo concernant le téléphone professionnel de l'utilisateur</p>
     * @see LabelWithIcon
     */
    private LabelWithIcon telprof2 = new LabelWithIcon(".\\src\\Contact\\factory.png");
    /**
     * <p> Le logo concernant le téléphone du domciile de l'utilisateur  </p>
     * @see LabelWithIcon
     */
    private LabelWithIcon home = new LabelWithIcon(".\\src\\Contact\\home.png");
    /**
     * <p> Le logo concernant l'adresse mail de l'utilisateur</p>
     * @see LabelWithIcon
     */
    private LabelWithIcon adress2 = new LabelWithIcon(".\\src\\Contact\\mail.png");
    /**
     * <p> Le logo concernant l'adresse de l'utilisateur</p>
     * @see LabelWithIcon
     */
    private LabelWithIcon location = new LabelWithIcon(".\\src\\Contact\\location.png");
    /**
     * <p> Un label qui ne contient rien</p>
     */
    private JLabel blankLabel = new JLabel();
    /**
     * <p> Le bouton de sauvegarde de l'ajout de contact </p>
     * @see ButtonWithIcon
     */
    private ButtonWithIcon save = new ButtonWithIcon(".\\src\\Contact\\save.png");
    /**
     * <p>Le JFileChooser permettant de choisir une image pour l'ajouter </p>
     */
    private JFileChooser fileChooser = new JFileChooser();
    /**
     * <p>La police d'écriture pour l'ajout de contact </p>
     */
    private Font fontadd = new Font("arial",Font.TRUETYPE_FONT,30);
    /**
     * <p> Une dimension qui sera utilisée sur des éléments de la page d'ajout de contact </p>
     */
    private Dimension dimensionadd = new Dimension(300,50);
    /**
     * <p> Une dimension qui sera utilisée sur des éléments de la page d'ajout de contact</p>
     */
    private Dimension dimensionadd2 = new Dimension(420,50);

    //Création panneau information
    /**
     * <p> Le champs contenant l'identité de l'utilisateur</p>
     */
    private JTextField identite;
    /**
     * <p> Le champs contenant le nom de l'utilisateur</p>
     */
    private JTextField name;
    /**
     * <p> Le champs contenant le prénom de l'utilisateur</p>
     */
    private JTextField firstname;
    /**
     * <p> Le champs contenant le mail de l'utilisateur</p>
     */
    private JTextField email;
    /**
     * <p> Le champs contenant le téléphone privé de l'utilisateur</p>
     */
    private JTextField telprive;
    /**
     * <p> Le champs contenant le téléphone professionnel de l'utilisateur</p>
     */
    private JTextField telprof;
    /**
     * <p> Le champs contenant le téléphone du domicile de l'utilisateur</p>
     */
    private JTextField teldom;
    /**
     * <p> Le champs contenant l'adresse de l'utilisateur</p>
     */
    private JTextField address;
    /**
     * <p> Le bouton d'édition de la page d'information</p>
     * @see ButtonWithIcon
     */
    private ButtonWithIcon editbutton = new ButtonWithIcon(".\\src\\Contact\\editbutton.png");
    /**
     * <p> Le bouton de sauvegarde d'édition de la page d'information</p>
     * @see ButtonWithIcon
     */
    private ButtonWithIcon saveedit = new ButtonWithIcon(".\\src\\Contact\\save.png");
    /**
     * <p> Le bouton de suppression de la page d'information</p>
     * @see ButtonWithIcon
     */
    private ButtonWithIcon deletebutton = new ButtonWithIcon(".\\src\\Contact\\deletebutton.png");


    /**
     * @throws IOException
     * <p> Permet de gérer les différents éléments du panel contact</p>
     */
    public ContactPanel() throws IOException {

        setLayout ( new BorderLayout (  ) );



        //Paramétrage des panels

        add(panelcontent,BorderLayout.CENTER);
        panelcontent.setLayout(cards);
        panelcontent.add(contactlist,"1");
        panelcontent.add(information,"2");
        panelcontent.add(contactnew,"3");

        //Paramétrage de la liste de contact

        contactlist.setLayout(new BorderLayout());
        contactlist.add(northpanel,BorderLayout.NORTH);
        northpanel.setLayout(new BorderLayout());
        JScrollPane listing = new JScrollPane ( centerpanel );


        listdecontact.setLayout(new GridLayout(0,1));
        centerpanel.add(listdecontact,BorderLayout.CENTER);
        contactlist.add(listing,BorderLayout.CENTER);
        listing.setHorizontalScrollBarPolicy ( JScrollPane.HORIZONTAL_SCROLLBAR_NEVER );
        listing.setBackground ( Color.BLACK );
        listing.setBorder((BorderFactory.createLineBorder(Color.BLACK, 1)) );
        listing.getVerticalScrollBar().setUnitIncrement(100);

        label.setFont(font);
        blankLabel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));


        northpanel.setBackground(Color.BLACK);
        centerpanel.setBackground(Color.BLACK);
        label.setForeground(Color.WHITE);

        northpanel.add(northpanel2.add(label), BorderLayout.WEST);
        northpanel.add(northpanel2.add(buttonplus), BorderLayout.EAST);

        buttonplus.addActionListener(this);


        //Paramètrage de la page d'ajout de contact

        contactnew.setLayout(new BorderLayout());
        contactnew.add(panelCenter,BorderLayout.CENTER);
        contactnew.add(nouveaucontact,BorderLayout.NORTH);
        contactnew.setBackground(Color.BLACK);

        nouveaucontact.setFont(font);

        contactnew.add(save,BorderLayout.SOUTH);

        save.addActionListener(this);

        nouveaucontact.setBorder(BorderFactory.createEmptyBorder(20, 20, 35, 20));
        nouveaucontact.setForeground(Color.white);


        panelCenter.setLayout(new MigLayout( "", "", "[]20[]"));
        panelCenter.setBackground(Color.BLACK);
        photo.setMaximumSize(new Dimension(150,150));
        panelCenter.add("cell 0 0 2 2",photo);
        photo.addActionListener(new AddphotoListener());
        prenom.setPreferredSize(dimensionadd);
        prenom.setFont(fontadd);
        panelCenter.add("cell 3 0 2 1",prenom);
        nom.setPreferredSize(dimensionadd);
        nom.setFont(fontadd);
        panelCenter.add("cell 3 1 2 1", nom);


        panelCenter.add("cell 0 3 1 1", adress2);
        panelCenter.add("cell 0 4 1 1",privatephone);
        panelCenter.add("cell 0 5 1 1",telprof2 );
        panelCenter.add("cell 0 6 1 1 ", home);
        panelCenter.add("cell 0 7 1 1",location);

        emailtext.setPreferredSize(dimensionadd2);
        emailtext.setFont(fontadd);
        panelCenter.add("cell 1 3 3 1", emailtext);
        phonenumber.setPreferredSize(dimensionadd2);
        phonenumber.setFont(fontadd);
        panelCenter.add("cell 1 4 3 1",phonenumber);
        phonenumber2.setPreferredSize(dimensionadd2);
        phonenumber2.setFont(fontadd);
        panelCenter.add("cell 1 5 3 1",phonenumber2);
        phonenumber3.setPreferredSize(dimensionadd2);
        phonenumber3.setFont(fontadd);
        panelCenter.add("cell 1 6 3 1",phonenumber3);
        adress.setPreferredSize(dimensionadd2);
        adress.setFont(fontadd);
        panelCenter.add("cell 1 7 3 1",adress);


        prenom.setMinimumSize(new Dimension(150,40));
        prenom.setForeground(Color.GRAY);
        prenom.addFocusListener(this);

        nom.setMinimumSize(new Dimension(150,40));
        nom.setForeground(Color.GRAY);
        nom.addFocusListener(this);

        emailtext.setMinimumSize(new Dimension(150,40));
        emailtext.setForeground(Color.GRAY);
        emailtext.addFocusListener(this);

        phonenumber.setMinimumSize(new Dimension(150,40));
        phonenumber.setForeground(Color.GRAY);
        phonenumber.addFocusListener(this);

        phonenumber2.setMinimumSize(new Dimension(150,40));
        phonenumber2.setForeground(Color.GRAY);
        phonenumber2.addFocusListener(this);

        phonenumber3.setMinimumSize(new Dimension(150,40));
        phonenumber3.setForeground(Color.GRAY);
        phonenumber3.addFocusListener(this);

        adress.setMinimumSize(new Dimension(150,40));
        adress.setForeground(Color.GRAY);
        adress.addFocusListener(this);

        //Paramétrage de la liste d'information

        information.setBackground(Color.BLACK);


        // initialiser la liste des contacts
        initContactList();






    }

    /**
     * <p> La méthode resetNewContact permet de réinitialiser la liste de contact</p>
     */
    public void resetNewContact(){
        prenom.setText("Prénom");
        nom.setText("Nom");
        emailtext.setText("E-mail");
        phonenumber.setText("Téléphone privé");
        phonenumber2.setText("Téléphone professionnel");
        phonenumber3.setText("Téléphone domicile");
        adress.setText("Adresse");
        ImageIcon ii = new ImageIcon(".\\src\\Contact\\photo.png");
        photo.setIcon(ii);
    }

    /**
     * @return contactnew
     */
    public JPanel getContactnew() {
        return contactnew;
    }

    /**
     * @return cards
     */
    public CardLayout getCards() {
        return cards;
    }

    /**
     * @return panelcontent
     */
    public JPanel getPanelcontent() {
        return panelcontent;
    }

    /**
     * @param e
     * <p> L'action listner qui affiche le panneau d'ajout de contact lorsque l'on clique sur le bouton plus, qui
     * sauvegarde les informations du contact créée, qui regarde si le mail entré par l'utilisateur est valide, qui
     * qui gère si un contact existe déjà et qui crée la liste de contact.</p>
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if(source == buttonplus)
            cards.show(panelcontent,"3");
        else
        if(source == save) {

            if (validateEmail(emailtext.getText())) {


                File monRepertoire = new File(".\\src\\Contact\\contact");
                File[] f = monRepertoire.listFiles();
                String newContact = prenom.getText() + nom.getText() + ".txt";
                boolean twice = false;

                for (int i = 0; i < f.length; i++) {
                    System.out.println(f[i].getName());
                    System.out.println(newContact);


                    if (newContact.equals(f[i].getName())) {
                        twice = true;
                        break;
                    } else
                        twice = false;
                    System.out.println("JE NAI PAS DOUBLE");
                }

                if (twice == true) {

                    JOptionPane pane = new JOptionPane();

                    int answer = JOptionPane.showConfirmDialog(null, "This contact already exists, Do you want to overwrite it ?",
                            "Choice", JOptionPane.YES_NO_OPTION);

                    if (answer == JOptionPane.YES_OPTION) {
                        JOptionPane.showMessageDialog(null, "the contact has been overwritten");

                        System.out.println(photo.getName());
                        if(photo.getName()==null){

                        }else{
                            File imageChoosed = new File(photo.getName());
                            File imagePasted = new File ( ".\\src\\Contact\\ImageContact\\" + prenom.getText()+""+nom.getText() + ".jpg" );
                            try {
                                FileUtils.copyFile ( imageChoosed, imagePasted );
                            } catch (IOException e1) {
                                e1.printStackTrace ();
                            }
                            photo.getName();
                        }

                        FileWriter monFichier = null;
                        BufferedWriter tampon = null;
                        String[] data = new String[7];

                        // Entrer les données dans le tableau

                        data[0] = prenom.getText();
                        data[1] = nom.getText();
                        data[2] = emailtext.getText();
                        data[3] = phonenumber.getText();
                        data[4] = phonenumber2.getText();
                        data[5] = phonenumber3.getText();
                        data[6] = adress.getText();


                        try {
                            File file = new File(".\\src\\Contact\\contact\\" + prenom.getText() + nom.getText() + ".txt");
                            monFichier = new FileWriter(file);
                            tampon = new BufferedWriter(monFichier);

                            for (int i = 0; i < data.length; i++) {
                                tampon.write((data[i]) + "\r\n");
                            }

                        } catch (IOException exception) {
                            exception.printStackTrace();
                        } finally {
                            try {
                                tampon.flush();
                                tampon.close();
                                monFichier.close();
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                        }

                        listdecontact.removeAll();
                        try {
                            initContactList();
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }

                        cards.show(panelcontent, "1");
                        prenom.setText("Prénom");
                        nom.setText("Nom");
                        emailtext.setText("E-mail");
                        phonenumber.setText("Téléphone privé");
                        phonenumber2.setText("Téléphone professionnel");
                        phonenumber3.setText("Téléphone domicile");
                        adress.setText("Adresse");
                    } else if (answer == JOptionPane.NO_OPTION) {
                        System.out.println("Non");
                        prenom.setText("Prénom");
                        nom.setText("Nom");
                        emailtext.setText("E-mail");
                        phonenumber.setText("Téléphone privé");
                        phonenumber2.setText("Téléphone professionnel");
                        phonenumber3.setText("Téléphone domicile");
                        adress.setText("Adresse");
                        cards.show(panelcontent, "1");
                    }


                } else {

                    System.out.println(monRepertoire.length());
                    int numberFiles = (int) monRepertoire.length();
                    listContactButton[numberFiles] = new JButton(nom.getText() + " " + prenom.getText());
                    listContactButton[numberFiles].setActionCommand(prenom.getText()+nom.getText() + ".txt");
                    listContactButton[numberFiles].addActionListener(new ListenerListContact());

                    listContactButton[numberFiles].setContentAreaFilled(false);
                    listContactButton[numberFiles].setOpaque(true);
                    listContactButton[numberFiles].setPreferredSize(new Dimension(500, 20));
                    listContactButton[numberFiles].setBorder(border);
                    listdecontact.add(listContactButton[numberFiles]);
                    listdecontact.add("cell 1 3", blankLabel);
                    blankLabel.setBackground(Color.BLACK);
                    blankLabel.setOpaque(true);
                    listContactButton[numberFiles].setFont(font2);
                    listContactButton[numberFiles].setBackground(Color.BLACK);
                    listContactButton[numberFiles].setForeground(Color.WHITE);

                    System.out.println(photo.getName());
                    if(photo.getName()==null){

                    }else{
                        File imageChoosed = new File(photo.getName());
                        File imagePasted = new File ( ".\\src\\Contact\\ImageContact\\" + prenom.getText()+""+nom.getText() + ".jpg" );
                        try {
                            FileUtils.copyFile ( imageChoosed, imagePasted );
                        } catch (IOException e1) {
                            e1.printStackTrace ();
                        }
                        photo.getName();
                    }


                    FileWriter monFichier = null;
                    BufferedWriter tampon = null;
                    String[] data = new String[7];

                    // Entrer les données dans le tableau

                    data[0] = prenom.getText();
                    data[1] = nom.getText();
                    data[2] = emailtext.getText();
                    data[3] = phonenumber.getText();
                    data[4] = phonenumber2.getText();
                    data[5] = phonenumber3.getText();
                    data[6] = adress.getText();


                    try {
                        File file = new File(".\\src\\Contact\\contact\\" + prenom.getText() + nom.getText() + ".txt");
                        monFichier = new FileWriter(file);
                        tampon = new BufferedWriter(monFichier);


                        for (int i = 0; i < data.length; i++) {
                            tampon.write((data[i]) + "\r\n");
                        }

                    } catch (IOException exception) {
                        exception.printStackTrace();
                    } finally {
                        try {
                            tampon.flush();
                            tampon.close();
                            monFichier.close();
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    }

                    listdecontact.removeAll();
                    try {
                        initContactList();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }


                    prenom.setText("Prénom");
                    nom.setText("Nom");
                    emailtext.setText("E-mail");
                    phonenumber.setText("Téléphone privé");
                    phonenumber2.setText("Téléphone professionnel");
                    phonenumber3.setText("Téléphone domicile");
                    adress.setText("Adresse");
                    cards.show(panelcontent, "1");
                }

            }else {
                emailtext.setForeground(Color.RED);

            }



        }else{

            panelcontent.add(information,(e.getActionCommand()+4)+"");
            cards.show(panelcontent,""+(e.getActionCommand()+4));


        }


    }

    /**
     * @return save
     */
    public ButtonWithIcon getButton(){
        return save;
    }


    /**
     * <p> Classe qui contient un listener concernant l'ajout de la photo </p>
     * @author Kwvin Coppey
     */
    class AddphotoListener implements ActionListener{

        /**
         * @param e
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println(photo.getName());
            File dir = new File(".\\Gallery\\");
            fileChooser.setCurrentDirectory(dir);
            int returnVal = fileChooser.showOpenDialog (null);
            if (returnVal == JFileChooser.APPROVE_OPTION) {


                File file = fileChooser.getSelectedFile ();
                String chemin = file.getPath();

                ImageIcon ii = new ImageIcon(chemin);
                ImageIcon ii2 = getScaledImage(ii,100,100);
                photo.setIcon(ii2);



                photo.setName(chemin);
                System.out.println(photo.getName());


            }
        }

    }

    /**
     * <p> Une classe qui crée un label qui est utilisé pour l'image de profil  </p>
     * @author Kevin Coppey
     */
    class LabelNewContact extends JLabel{

        /**
         * @param icon
         */
        public LabelNewContact(String icon){
            ImageIcon ii = new ImageIcon(icon);
            ImageIcon ii2 = getScaledImage(ii,150,150);
            setIcon(ii2);

        }


    }

    /**
     * @param srcImg
     * @param w
     * @param h
     * <p> Permet de gérer la taille de l'image</p>
     * @return new ImageIcon(resizedImg)
     */
    private ImageIcon getScaledImage(ImageIcon srcImg, int w, int h){
        Image img = srcImg.getImage();
        BufferedImage resizedImg = new BufferedImage(w,h,BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();

        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(img,0,0,w,h,null);
        g2.dispose();

        return new ImageIcon(resizedImg);
    }


    /**
     * @param e
     * <p> Permet d'écire du texte par défaut dans les champs à remplir de l'ajout de contact</p>
     */
    @Override
    public void focusGained(FocusEvent e) {
        Object source = e.getSource();
        if(source == prenom)
            prenom.setText("");
        else
        if(source == nom)
            nom.setText("");
        else
        if(source == emailtext )
            emailtext.setText("");
        else
        if(source == phonenumber)
            phonenumber.setText("");
        else
        if (source == phonenumber2)
            phonenumber2.setText("");
        else
        if(source == phonenumber3)
            phonenumber3.setText("");
        else
        if(source == adress)
            adress.setText("");
    }

    /**
     * @param e
     */
    @Override
    public void focusLost(FocusEvent e) {

    }

    /**
     * @author Kevin Coppey
     * <p> Classe permettant de créer un bouton avec une image</p>
     */
    class ButtonWithIcon extends JButton{

        /**
         * @param icon
         */
        public ButtonWithIcon(String icon) {
            ImageIcon ii = new ImageIcon(icon);
            setIcon ( ii );
            setContentAreaFilled(false);
            setBorderPainted(false);

        }
    }

    /**
     * <p> Classe permettant de créer un label avec une image</p>
     * @author Kevin Coppey
     */
    class LabelWithIcon extends JLabel {


        /**
         * @param icon
         */
        public LabelWithIcon(String icon) {
            ImageIcon ii = new ImageIcon(icon);
            setIcon(ii);
        }
    }

    /**
     * @param email
     * <p> Permet de forcer l'utilisateur à entrer une adresse mail valide</p>
     * @return m.matches()
     */
    public boolean validateEmail(String email) {
        Pattern p = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}$" );
        Matcher m = p.matcher(email.toUpperCase());
        return m.matches();
    }

    /**
     * @throws IOException
     * <p> Méthode permettant de créer la liste de contact en allant chercher les fichiers pour les lire</p>
     */
    public void initContactList() throws IOException {
        File monRepertoire=new File(".\\src\\Contact\\contact");
        File[] f = monRepertoire.listFiles();


        System.out.println(f.length);

        for(int i = 0; i<f.length ;i++){
            BufferedReader Buff = null;
            try {
                Buff = new BufferedReader(new FileReader(f[i]));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            System.out.println(f[i].getName());


            String prenom = Buff.readLine();
            String nom = Buff.readLine();

            listContactButton[i] = new JButton(nom + " " + prenom);
            listContactButton[i].setContentAreaFilled(false);
            listContactButton[i].setOpaque(true);
            listContactButton[i].setPreferredSize(new Dimension(500, 20));
            listContactButton[i].setBorder(border);
            listdecontact.add(listContactButton[i]);
            listdecontact.add("cell 1 3", blankLabel);
            blankLabel.setBackground(Color.BLACK);
            blankLabel.setOpaque(true);
            listContactButton[i].setFont(font2);
            listContactButton[i].setBackground(Color.BLACK);
            listContactButton[i].setForeground(Color.WHITE);
            listContactButton[i].addActionListener(new ListenerListContact());
            listContactButton[i].setActionCommand(prenom+nom+".txt");
            System.out.println(listContactButton[i].getActionCommand());

            Buff.close();
        }

    }

    /**
     * @return information
     */
    public JPanel getInformation() {
        return information;
    }

    /**
     * <p> Classe qui va lire les fichiers présents dans contact afin de gérer l'apparition de l'image
     * de profil. Elle gère aussi le placements des champs de texte et leur position. </p>
     * @author Kevin Coppey
     */
    class ListenerListContact implements ActionListener{

        /**
         * @param e
         */
        @Override
        public void actionPerformed(ActionEvent e){
            File selected =new File(".\\src\\Contact\\contact\\" + e.getActionCommand());
            System.out.println(e.getActionCommand());
            String[] data = new String[7];

            LabelNewContact bonhomme;





            Font font3  = new Font("Arial",Font.BOLD,20);
            Font font4 = new Font ("Arial",Font.TRUETYPE_FONT,25);
            Font fontnom = new Font("Arial",Font.TRUETYPE_FONT,40);

            BufferedReader Buff = null;
            try {
                Buff = new BufferedReader(new FileReader(selected));
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            }


            for(int i = 0; i<=6 ; i++) {
                try {
                    data[i] = Buff.readLine();
                    System.out.println("SALUT");
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }

            File imageProfil = new File(".\\src\\Contact\\ImageContact\\" + data[0] + "" +data[1] +".jpg");
            System.out.println(".\\src\\Contact\\ImageContact\\" + data[0] + "" +data[1] +".jpg");
            if(imageProfil.exists()){
                bonhomme = new LabelNewContact (".\\src\\Contact\\ImageContact\\" + data[0] + "" +data[1] +".jpg");
            }else {
                bonhomme = new LabelNewContact (".\\src\\Contact\\icone.png");
            }
            bonhomme.setMinimumSize(new Dimension(150,150));
            bonhomme.setMaximumSize(new Dimension(150,150));


            name = new JTextField();
            name.setText(data[1]);
            name.setEditable(false);
            name.setBackground(Color.BLACK);
            name.setBorder(javax.swing.BorderFactory.createEmptyBorder());
            name.setMinimumSize(new Dimension(60,10));
            name.setMaximumSize(new Dimension(200,50));

            firstname = new JTextField();
            firstname.setText(data[0]);
            firstname.setEditable(false);
            firstname.setBackground(Color.BLACK);
            firstname.setBorder(javax.swing.BorderFactory.createEmptyBorder());
            firstname.setMinimumSize(new Dimension(80,10));
            firstname.setMinimumSize(new Dimension(150,50));

            email = new JTextField();
            email.setText(data[2]);
            email.setEditable(false);
            email.setBackground(Color.BLACK);
            email.setBorder(javax.swing.BorderFactory.createEmptyBorder());
            email.setMinimumSize(new Dimension(400,10));

            telprive = new JTextField();
            telprive.setText(data[3]);
            telprive.setEditable(false);
            telprive.setBackground(Color.BLACK);
            telprive.setBorder(javax.swing.BorderFactory.createEmptyBorder());
            telprive.setMinimumSize(new Dimension(400,10));

            telprof = new JTextField();
            telprof.setText(data[4]);
            telprof.setEditable(false);
            telprof.setBackground(Color.BLACK);
            telprof.setBorder(javax.swing.BorderFactory.createEmptyBorder());

            teldom = new JTextField();
            teldom.setText(data[5]);
            teldom.setEditable(false);
            teldom.setBackground(Color.BLACK);
            teldom.setBorder(javax.swing.BorderFactory.createEmptyBorder());

            address = new JTextField();
            address.setText(data[6]);
            address.setEditable(false);
            address.setBackground(Color.BLACK);
            address.setBorder(javax.swing.BorderFactory.createEmptyBorder());

            JLabel telPrive = new JLabel("Private Phone:");
            JLabel telProf = new JLabel("Professionnal Phone :");
            JLabel telDom = new JLabel("House Phone :");
            JLabel eMail = new JLabel("Email :");
            JLabel address1 = new JLabel("Adress : ");


            name.setFont(fontnom);
            name.setForeground(Color.WHITE);
            firstname.setFont(fontnom);
            firstname.setForeground(Color.WHITE);

            telprive.setFont(font4);
            telprive.setForeground(Color.WHITE);
            teldom.setFont(font4);
            teldom.setForeground(Color.WHITE);
            telprof.setFont(font4);
            telprof.setForeground(Color.WHITE);
            email.setFont(font4);
            email.setForeground(Color.WHITE);
            address.setFont(font4);
            address.setForeground(Color.WHITE);

            telPrive.setFont(font3);
            telPrive.setForeground(Color.WHITE);
            telDom.setFont(font3);
            telDom.setForeground(Color.WHITE);
            telProf.setFont(font3);
            telProf.setForeground(Color.WHITE);
            eMail.setFont(font3);
            eMail.setForeground(Color.WHITE);
            address1.setFont(font3);
            address1.setForeground(Color.WHITE);


            JLabel labelvide = new JLabel("ESPACEME");
            labelvide.setForeground(Color.BLACK);
            CC componentConstraints = new CC();
            componentConstraints.alignX("center").spanX();
            MigLayout mig = new MigLayout (  "", "fill", "[]20[]");
            information.setLayout(mig);
            information.add("pos 0 10",deletebutton);
            information.add("pos 165 10,wrap",bonhomme);
            information.add("pos 380 -5",editbutton);
            //information.add("gapleft 90,wrap",identite);
            information.add("pos 20 170",name);
            information.add("pos 245 170,wrap",firstname);


            information.add(",gaptop 180,cell 0 6 6 1",telPrive);
            information.add("cell 0 7 6 1", telprive);

            information.add("cell 0 8 6 1" , telProf);
            information.add("cell 0 9 6 1", telprof);

            information.add("cell 0 10 6 1", telDom);
            information.add("cell 0 11 6 1", teldom);

            information.add("cell 0 12 6 1", eMail);
            information.add("cell 0 13 6 1", email);

            information.add("cell 0 14 6 1",address1);
            information.add("cell 0 15 6 1", address);

            deletebutton.addActionListener(new DeleteListener());

            editbutton.addActionListener(new EditListener());

            cards.show(panelcontent,"2");

            try {
                Buff.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }


        }

    }

    /**
     * <p> Cette Classe permet de gérer la modification des données par l'utilisateur</p>
     * @author Kevin Coppey
     */
    class EditListener implements ActionListener{

        /**
         * @param e
         */
        @Override
        public void actionPerformed(ActionEvent e) {

            name.setEditable(true);
            name.setBackground(Color.WHITE);
            name.setForeground(Color.BLACK);

            firstname.setEditable(true);
            firstname.setBackground(Color.WHITE);
            firstname.setForeground(Color.BLACK);

            email.setEditable(true);
            email.setBackground(Color.WHITE);
            email.setForeground(Color.BLACK);

            telprive.setEditable(true);
            telprive.setBackground(Color.WHITE);
            telprive.setForeground(Color.BLACK);

            telprof.setEditable(true);
            telprof.setBackground(Color.WHITE);
            telprof.setForeground(Color.BLACK);

            teldom.setEditable(true);
            teldom.setBackground(Color.WHITE);
            teldom.setForeground(Color.BLACK);

            address.setEditable(true);
            address.setBackground(Color.WHITE);
            address.setForeground(Color.BLACK);
            //information.revalidate();
            information.remove(deletebutton);
            information.remove(editbutton);
            information.repaint();
            information.add("pos 380 -5",saveedit); // CREATION NOUVEAU BOUTON
            saveedit.addActionListener(new SaveEditContact());
            information.revalidate();



        }
    }

    /**
     * <p> Cette classe gère la sauvegarde de la modification des contacts</p>
     * @author Kevin Coppey
     */
    class SaveEditContact implements ActionListener{

        /**
         * @param e
         */
        @Override
        public void actionPerformed(ActionEvent e) {



            FileWriter monFichier = null;
            BufferedWriter tampon = null;
            String[] data = new String[7];

            // Entrer les données dans le tableau

            data[0] = firstname.getText() ;
            data[1] = name.getText();
            data[2] = email.getText();
            data[3] = telprive.getText();
            data[4] = telprof.getText();
            data[5] = teldom.getText();
            data[6] = address.getText();


            try {
                File file = new File(".\\src\\Contact\\contact\\" + firstname.getText() + name.getText() + ".txt");
                monFichier = new FileWriter(file);
                tampon = new BufferedWriter(monFichier);

                for (int i = 0; i < data.length; i++) {
                    tampon.write((data[i]) + "\r\n");
                }

            } catch (IOException exception) {
                exception.printStackTrace();
            } finally {
                try {
                    tampon.flush();
                    tampon.close();
                    monFichier.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }

            cards.show(panelcontent, "1");


            name.setEditable(false);
            name.setBackground(Color.BLACK);
            name.setForeground(Color.WHITE);

            firstname.setEditable(false);
            firstname.setBackground(Color.BLACK);
            firstname.setForeground(Color.WHITE);

            email.setEditable(false);
            email.setBackground(Color.BLACK);
            email.setForeground(Color.WHITE);

            telprive.setEditable(false);
            telprive.setBackground(Color.BLACK);
            telprive.setForeground(Color.WHITE);

            telprof.setEditable(false);
            telprof.setBackground(Color.BLACK);
            telprof.setForeground(Color.WHITE);

            teldom.setEditable(false);
            teldom.setBackground(Color.BLACK);
            teldom.setForeground(Color.WHITE);

            address.setEditable(false);
            address.setBackground(Color.BLACK);
            address.setForeground(Color.WHITE);


            information.removeAll();
            information.revalidate();

        }

    }

    /**
     * <p> Cette classe gère les actions concernant la suppression des contacts</p>
     * @author Kevin Coppey
     */
    class DeleteListener implements ActionListener{

        /**
         * @param e
         */
        @Override
        public void actionPerformed(ActionEvent e) {


            File file = new File (".\\src\\Contact\\contact\\"+firstname.getText()+ name.getText()+".txt");
            File fileimage = new File ( ".\\src\\Contact\\ImageContact\\" +firstname.getText ()+name.getText ()+".jpg" );
            System.out.println(".\\src\\Contact\\contact\\"+firstname.getText()+ name.getText()+".txt");
            System.out.println(file.getName());
            System.out.println(file.exists());

            if(fileimage.exists ()){
                fileimage.delete ();
            }

            file.delete();


            listdecontact.removeAll();
            try {
                initContactList();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            cards.show(panelcontent,"1");
            information.removeAll ();
        }
    }




}









