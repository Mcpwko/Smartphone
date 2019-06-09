package PongGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author Mickaël
 * @author Kevin
 * @version 2.0
 */
public class Game extends JPanel implements Runnable, KeyListener, MouseMotionListener {
    /**
     * largeur de la zone de jeu
     */
    public static final int GAME_WIDTH = 480;
    /**
     * hauteur de la zone de jeu
     */
    public static final int GAME_HEIGHT = 750;


    /**
     * <p>police d'écriture du jeu</p>
     */
    private Font font;

    {
        try {
            font = Font.createFont(Font.TRUETYPE_FONT,new File("src\\PongGame\\ARCADECLASSIC.TTF"));
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * <p>déclaration d'une nouvelle tâche</p>
     */
    private Thread t;


    /**
     * score du joueur
     */
    public static int score;
    /**
     *le mot Score
     */
    public static String scoreString;
    /**
     * niveau de la partie
     */
    public static int level = 1;


    /**
     * Titre du jeu
     */
    public static String titre = "PONG GAME";
    /**
     * Instructions afin de lancer le jeu
     */
    public static String insctructionsMenu = "Press SPACE to play !";


    /**
     * le mot Level
     */
    public static String levelString;


    /**
     * un objet Player
     */
    protected Player player;
    /**
     * un objet Bot
     */
    protected Bot computer;


    /**
     * un objet Ball
     */
    protected Ball ball;
    /**
     * vitesse de la balle
     */
    protected static int ballSpeed;


    /**
     * <p>la direction dans laquelle part la balle</p>
     */
    public static boolean startDirection;


    /**
     * définit si le jeu tourne
     */
    private boolean running = false;
    /**
     * le mode menu
     */
    private boolean menu = true;
    /**
     * le mode jeu
     */
    private boolean game = false;
    /**
     * le mode en train de jouer
     */
    private boolean playMode = true;
    /**
     * le mode pause
     */
    private boolean pause = false;
    /**
     * le mode fin du jeu
     */
    private boolean scoreScreen = false;


    // Boolean qui rend les mouvements plus fluides
    /**
     * déclaration des touches du clavier
     */
    private boolean right = false, left = false, up = false, down = false, space = false, escape = false;
    //private boolean rightTwo = false, leftTwo = false, upTwo = false, downTwo = false;  // au cas ou si 2 joueurs

    /**
     * Constructeur du jeu
     * <p> construction d'un jeu avec un player, un bot et une balle</p>
     *
     */
    public Game(){
        player = new Player (0, 360, 15, 100, ID.PLAYER_ONE);
        computer = new Bot (340, 360, 15, 100, ID.COMPUTER);
        ball = new Ball (0, 0, 20, 20, ID.BALL);

    }


    /**
     * <p>dessine tous les éléments présent sur le panneau</p>
     * @param g
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setFont(font.deriveFont(Font.TRUETYPE_FONT,25));
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT);
        g.setColor(Color.WHITE);
        if (menu) {
            g.setFont(font.deriveFont(Font.TRUETYPE_FONT,90));
            g.drawString(titre,23,200);
            g.setFont(font.deriveFont(Font.TRUETYPE_FONT,30));
            g.drawString(insctructionsMenu,70,400);
            g.fillRoundRect(0,400,15,70,20,5);
            g.fillRoundRect(453,400,15,70,20,5);


        } else if (game) {

            g.drawString(scoreString, 20,20);
            g.drawString(levelString,350,20);

            player.render(g);
            computer.render(g);
            ball.render(g);
        } else if(scoreScreen){
            g.setFont(font.deriveFont(Font.TRUETYPE_FONT,50));
            g.drawString(scoreString,130,200);
            g.drawString(levelString,130,300 );
            g.setFont(font.deriveFont(Font.TRUETYPE_FONT,35));
            g.drawString(insctructionsMenu,40,600);

        } else  if (pause){
            g.drawString("Press SPACE to CONTINUE", 80,300);
            g.setFont(font.deriveFont(Font.TRUETYPE_FONT,50));
            g.drawString("GAME PAUSED", 90,200);


        }
    }


    /**
     * attend le début du jeu
     */
    public void waitForStart() {
        while (!space) {

        }
        if (startDirection) {
            ball.xSpeed = 1;
            ball.ySpeed = -1;
            computer.ySpeed = ball.ySpeed;
        } else {
            ball.xSpeed = -1;
                ball.ySpeed = 1;
                computer.ySpeed = ball.ySpeed;
            }
        }


    /**
     * modifier le boolean playMode
     * @param val
     */
    public void setPlayMode(boolean val) {
        playMode = val;
    }

    /**
     * modifier le boolean menu
     * @param val
     */
    public void setMenu(boolean val){
        menu = val;
    }

    /**
     * modifier le boolean game
     * @param val
     */
    public void setGame(boolean val){
        game = val;
    }

    /**
     * modifier le boolean pause
     * @param val
     */
    public void setPause(boolean val){
        pause = val;
    }

    /**
     * modifier le boolean startDirection
     * @param val
     */
    public void setstartDirection(boolean val){
        startDirection = val;
    }

    /**
     * modifier le boolean space
     * @param val
     */
    public void setSpace(boolean val){
        space = val;
    }


    /**
     * <p>les différentes actions quand certaines touches sont pressées</p>
     * @param e
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode ();


        if (playMode) {
            if (key == KeyEvent.VK_ESCAPE) {
                escape = true;
            }

            if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
                right = true;
            }

            if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
                left = true;
            }

            if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) {
                down = true;
            }

            if (key == KeyEvent.VK_UP || key == KeyEvent.VK_W) {
                up = true;
            }
            if (key == KeyEvent.VK_ESCAPE) {
                escape = true;
            }
            if (menu) {
                if (key == KeyEvent.VK_SPACE) {
                    space = true;
                    ball.reset ();
                    playMode = true;
                    menu = false;
                    game = true;
                    pause = false;
                    waitForStart ();

                }
            }
            if(pause){
                if(key==KeyEvent.VK_SPACE) {
                    space = true;
                    ball.reset ();
                    waitForStart ();
                }
            }


        } else {
            if (scoreScreen) {
                if (key == KeyEvent.VK_SPACE) {
                    space = true;
                    ball.reset ();
                    waitForStart ();
                }
            }

        }
    }

    /**
     * <p>méthode quand les différentes touches sont relachées</p>
     * @param e
     */
    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (playMode) {
            if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
                right = false;
            }

            if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
                left = false;
            }

            if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) {
                down = false;
            }

            if (key == KeyEvent.VK_UP || key == KeyEvent.VK_W) {
                up = false;
            }
        }

    }


    /**
     * <p>met à jour tous les positions des objets et définit l'état de jeu dans lequel on est</p>
     */
    public void updateLogic() {
        if (menu) {
            if (space) {
                playMode = true;
                menu = false;
                game = true;
                pause = false;

            }

        } else if (game) {

            removeMouseMotionListener(this);

            addMouseMotionListener(this);

            // X DIRECTION MOVEMENT PLAYER ONE
            if (right)
                player.xSpeed = 3;
            else if (left)
                player.xSpeed = -3;
            else
                player.xSpeed = 0;

            // Y DIRECTION MOVEMENT PLAYER ONE
            if (down)
                player.ySpeed = 3;
            else if (up)
                player.ySpeed = -3;
            else
                player.ySpeed = 0;

            /**
             * COMPUTER
             */
            // X DIRECTION MOVEMENT COMPUTER


            // Y DIRECTION MOVEMENT COMPUTER

            computer.ySpeed = ball.ySpeed;
            computer.y = ball.y;


            if (ball.isColliding(player)) {
                ball.x = 15;
                score+=10;
                if(score%100 == 0 && score !=0) {
                    ball.xSpeed-=1;
                    System.out.println(ball.xSpeed);
                    level++;
                }
                ball.xSpeed = -ball.xSpeed; // Changement de direction de la balle

            }
            if (ball.isCollidingBot(computer)) {
                ball.x = GAME_WIDTH - 21 - ball.WIDTH;
                //ball.xSpeed++;
                ball.xSpeed = -ball.xSpeed;
            }

            // UPDATE OBJECT LOGIC
            player.update();
            computer.update();
            ball.update();
            scoreString = "Score " + score;
            levelString = "Level " + level;

            if(ball.x<0){


                playMode = false;
                menu = false;
                game = false;
                pause = false;

                startDirection = false;
                ball.reset();
                scoreScreen = true;
                space=false;


            }

            if(escape){
                playMode = true;
                menu = false;
                game = false;
                pause = true;
                startDirection = false;
                space = false;


            }

        }else if(scoreScreen){
            if (space) {
                playMode = true;
                menu = false;
                game = true;
                pause = false;
                Game.setLevel(1);
                Game.setScore(0);
                scoreScreen = false;
            }

        }else if(pause){
            if (space) {
                playMode = true;
                menu = false;
                game = true;
                pause = false;
                escape = false;
                ball.xSpeed= level * (-1);
                System.out.println("J'ai une vitesse de " + ball.xSpeed);
            }

        }
    }


    /**
     * methode qui n'est pas utilisé
     * @param e
     */
    @Override
    public void keyTyped(KeyEvent e) {
    }


    /**
     * <p>initialise une nouvelle tâche qui appelle la méthode run </p>
     */

    public void start() {
        if (t == null) {
            t = new Thread(this);
        }
        t.start();
        running = true;
    }

    /**
     * principale boucle du jeu
     */
    public void run() {
        while (running) {
            updateLogic();
            repaint();

            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * <p>obtenir le score du jeu</p>
     * @return score de la game
     */
    public static int getScore() {
        return score;
    }

    /**
     * <p>modifier le score du jeu</p>
     * @param score score du jeu
     */
    public static void setScore(int score) {
        Game.score = score;
    }

    /**
     * <p>obtenir le niveau du jeu</p>
     * @return niveau du jeu
     */
    public static int getLevel() {
        return level;
    }

    /**
     * <p>modifier le niveau du jeu</p>
     * @param level niveau du jeu
     */
    public static void setLevel(int level) {
        Game.level = level;
    }

    /**
     * méthode qui n'est pas utilisé
     * @param e
     */
    @Override
    public void mouseDragged(MouseEvent e) {


    }

    /**
     * suit les déplacement de la souris
     * @param e
     */
    @Override
    public void mouseMoved(MouseEvent e) {
        if(running) {
            player.setY(e.getY());
        }


    }

}
