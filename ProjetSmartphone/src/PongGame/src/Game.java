import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

class Game extends JPanel implements Runnable, KeyListener {
    public static final int GAME_WIDTH = 480;
    public static final int GAME_HEIGHT = 800;

    private String[] instructions = {"PONG GAME"};

    private Thread t;


    public static int score;
    public static String scoreString;


    protected Player player;
    protected Player computer;


    protected Ball ball;
    protected static int ballSpeed;


    public static boolean startDirection;


    private boolean running = false;
    private boolean menu = true;
    private boolean game = false;
    private boolean playMode = true;


    // BOOLEANS THAT WILL BE USED FOR SMOOTHER MOVEMENT
    private boolean right = false, left = false, up = false, down = false, space = false;
    //private boolean rightTwo = false, leftTwo = false, upTwo = false, downTwo = false;

    public Game(){
        player = new Player (0, 360, 15, 100, ID.PLAYER_ONE);
        computer = new Player (GAME_WIDTH - 21, 360, 15, 100, ID.COMPUTER);
        ball = new Ball (0, 0, 20, 20, ID.BALL);

    }



    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setFont(new Font("Arial", Font.BOLD, 18));
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT);
        g.setColor(Color.WHITE);
        if (menu) {
            for (int i = 0; i < instructions.length; i++) {
                drawCenteredString(instructions[i], Game.GAME_WIDTH, Game.GAME_HEIGHT + (i * 45) - 150, g);
            }
        } else if (game) {

            g.drawString(scoreString, (Game.GAME_WIDTH / 4) - (scoreString.length() * 8 / 2),
                    Game.GAME_HEIGHT - 40);

            player.render(g);
            computer.render(g);
            ball.render(g);
        }
    }

    public void drawCenteredString(String s, int w, int h, Graphics g) {
        FontMetrics fm = g.getFontMetrics();
        int x = (w - fm.stringWidth(s)) / 2;
        int y = (fm.getAscent() + (h - (fm.getAscent() + fm.getDescent())) / 2);
        g.drawString(s, x, y);
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



    // KEYBOARD INPUT EVENTS TRIGGERED BY OS
    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if(key == KeyEvent.VK_ESCAPE){
            System.exit(0);
        }
        if (playMode) {
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
        }

        if (key == KeyEvent.VK_SPACE) {
            space = true;
            ball.reset();
            waitForStart();
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
            if (left) {
                playMode = true;
                menu = false;
                game = true;
            }

        } else if (game) {
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
                ball.xSpeed--;
                ball.xSpeed = -ball.xSpeed; // AUGMENTATION DE LA VITESSE

            }
            if (ball.isColliding(computer)) {
                ball.x = GAME_WIDTH - 21 - ball.WIDTH;
                //ball.xSpeed++;
                ball.xSpeed = -ball.xSpeed;
            }

            // UPDATE OBJECT LOGIC
            player.update();
            computer.update();
            ball.update();
            scoreString = "Player One: " + score;
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
}