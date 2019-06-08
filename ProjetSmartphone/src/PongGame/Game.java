package PongGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class Game extends JPanel implements Runnable, KeyListener, MouseMotionListener {
    public static final int GAME_WIDTH = 480;
    public static final int GAME_HEIGHT = 750;




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


    private Thread t;


    public static int score;
    public static String scoreString;
    public static int level = 1;


    public static String titre = "PONG GAME";
    public static String insctructionsMenu = "Press SPACE to play !";


    public static String levelString;


    protected Player player;
    protected Bot computer;


    protected Ball ball;
    protected static int ballSpeed;


    public static boolean startDirection;


    private boolean running = false;
    private boolean menu = true;
    private boolean game = false;
    private boolean playMode = true;
    private boolean pause = false;
    private boolean scoreScreen = false;


    // BOOLEANS THAT WILL BE USED FOR SMOOTHER MOVEMENT
    private boolean right = false, left = false, up = false, down = false, space = false, escape = false;
    //private boolean rightTwo = false, leftTwo = false, upTwo = false, downTwo = false;

    public Game(){
        player = new Player (0, 360, 15, 100, ID.PLAYER_ONE);
        computer = new Bot (340, 360, 15, 100, ID.COMPUTER);
        ball = new Ball (0, 0, 20, 20, ID.BALL);

    }



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


    public void setPlayMode(boolean val) {
        playMode = val;
    }

    public void setMenu(boolean val){
        menu = val;
    }

    public void setGame(boolean val){
        game = val;
    }

    public void setPause(boolean val){
        pause = val;
    }

    public void setstartDirection(boolean val){
        startDirection = val;
    }

    public void setSpace(boolean val){
        space = val;
    }




    // KEYBOARD INPUT EVENTS TRIGGERED BY OS
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
     * ICI C?EST LES DIFFöRENTS ETATS DU JEU (PAUSE; GAME; MENU ...)
     */

    //UPDATES ALL OBJECT POSITIONS AND USED TO DECIDE GAME STATE
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


    // UNUSED METHOD REQUIRED BY KEYLISTENER INTERFACE
    @Override
    public void keyTyped(KeyEvent e) {
    }


    // INITIALIZES NEW THREAD AND STARTS IT (MAKES IT CALL RUN METHOD)
    public void start() {
        if (t == null) {
            t = new Thread(this);
        }
        t.start();
        running = true;
    }

    // MAIN GAME LOOP (NEEDS REVISING)
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




    public static int getScore() {
        return score;
    }

    public static void setScore(int score) {
        Game.score = score;
    }

    public static int getLevel() {
        return level;
    }

    public static void setLevel(int level) {
        Game.level = level;
    }

    @Override
    public void mouseDragged(MouseEvent e) {


    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if(running) {
            player.setY(e.getY());
        }


    }

}
