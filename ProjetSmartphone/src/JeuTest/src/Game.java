import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class Game extends JPanel implements ActionListener {
    private static final int NUMBER_ENEMIES = 28;
    private ArrayList<Alien> enemies = new ArrayList<Alien>();
    private ArrayList<Shot> playerLaserCleanUpList = new ArrayList<Shot>();
    private static HashSet<GameObject> gameObjects = new HashSet<GameObject>();
    private static HashMap<String, Image> imageCache = new HashMap<String, Image>();
    private ArrayList<Shot> enemyLaserCleanUpList = new ArrayList<Shot>();
    private Player player;
    private int enemyCount = NUMBER_ENEMIES;
    private int liveCount = 3;
    private int levelCount = 1;
    private int score = 0;
    private long lastFire;
    private static transient Game gameCanvas = null;


    private Timer leftPressed = new Timer(10, new LeftPressed());
    private Timer rightPressed = new Timer(10, new RightPressed());
    private Timer respawn = new Timer(2000, new redrawPlayer());
    private Timer gameTimer = new Timer(20, this);
    private Timer enemyFireTimer = new Timer(1000, new MyFireListener());




    public Game(){
        super();
        setDoubleBuffered(true);
        setFocusable(true);
        this.setBackground ( Color.BLACK );
        initialiseShips();
        createPlayer();
        addKeyListener(keyListener);
        enemyFireTimer.start();
        gameTimer.start();

    }





    public static Game getGameCanvas(boolean whichCanvas) {
        if (gameCanvas == null && whichCanvas == false) {
            gameCanvas = new Game();
        }

        if (gameCanvas == null && whichCanvas == true) {
            gameCanvas = new Game();
        }
        return gameCanvas;
    }









    private void initialiseShips() {
        for (int i = 0; i < 7; i++) {
        for (int j = 0; j<4; j++) {
        Alien e = new Alien("alien.png", new Point(100 + i*50, (50)+j*30), new Dimension (50, 30));
        enemies.add(e);
        gameObjects.add(e);
            }
        }
    }

    private void createPlayer() {
        player = new Player("player.png", new Point(300, 500), new Dimension(60, 30));
        gameObjects.add(player);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        g.drawString("Lives:  " + liveCount, 350, 30);
        g.drawString("Level: " + levelCount, 270, 30);
        g.drawString("Score:  " + score, 10, 30);

        Iterator<GameObject> itt = gameObjects.iterator();

        while (itt.hasNext()) {
            GameObject current = itt.next();
            if (!current.isAlive())
                continue;
            current.draw(g);
        }
    }

    public static void addGameObject(GameObject obj) {
        gameObjects.add(obj);
    }

    public class MyFireListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent arg0) {
            if (enemies.size() == 0)
                return;
            else {
                int randomNumber = new Random ().nextInt(enemies.size());
                enemies.get(randomNumber).fire();
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int moveY = 0;

        for (int i = 0; i < enemies.size(); i++) {
            if (enemies.get(i).switchDirection()) {
                moveY = 10;
                break;
            }
            else
                moveY = 0;
        }

        Iterator<GameObject> itt = gameObjects.iterator();

        while (itt.hasNext()) {
            GameObject current = itt.next();

            if (current instanceof Player)
                continue; // we don't want the player to be automatically updated

            current.move(0, moveY);
        }
        collision();
        cleanUp();
        repaint();

    }

    public static Image getImage(String location, GraphicsConfiguration gc) throws IOException {
        Image img = null;
        if (imageCache.containsKey(location))
            return imageCache.get(location);
        else {
            Image sourceImg;
            sourceImg = ImageIO.read(new File (location));
            img = gc.createCompatibleImage(sourceImg.getWidth(null), sourceImg.getHeight(null), Transparency.BITMASK);
            img.getGraphics().drawImage(sourceImg, 0, 0, null);
            imageCache.put(location, img);
        }
        return img;
    }




    private void collision() {
        // test players for hitting enemy
        for (Alien e : enemies) {

            Iterator<Shot> laserItt = Player.laserList.iterator();
            while (laserItt.hasNext()) {
                Shot currentLaser = (Shot) laserItt.next();

                if (currentLaser.getRectangle().intersects(e.getRectangle()) && currentLaser.isAlive()) {
                    currentLaser.setAlive(false);
                    playerLaserCleanUpList.add(currentLaser);
                    score += 10;
                    e.setAlive(false);
                    enemyCount--;
                    System.out.println ( enemyCount );
                }
            }
            if (enemyCount == 0) {
                liveCount++;
                levelCount++;
                System.out.println(enemyCount);
                enemyCount = NUMBER_ENEMIES;
                System.out.println(enemyCount);
                initialiseShips();
                enemyFireTimer.start();
                gameTimer.start();

            }
        }

        // test enemy laser hitting player
        Iterator<Shot> laserItt = Alien.laserList.iterator();
        while (laserItt.hasNext()) {
            Shot currentLaser = (Shot) laserItt.next();

            if (player.isAlive()) {
                if (currentLaser.getRectangle().intersects(player.getRectangle()) && currentLaser.isAlive()) {
                    currentLaser.setAlive(false);
                    enemyLaserCleanUpList.add(currentLaser);
                    enemyFireTimer.stop();
                    gameTimer.stop();
                    respawn.start();
                    player.setAlive(false);

                    if (liveCount >=  1)
                        liveCount--;
                }
            }
        }
    }


    private void cleanUp() {
        ArrayList<Alien> al = new ArrayList<Alien>();
        for (Alien e : enemies) {
            if (!e.isAlive())
                al.add(e);
        }
        enemies.removeAll(al);
        Player.laserList.removeAll(playerLaserCleanUpList);
        Alien.laserList.removeAll(enemyLaserCleanUpList);
    }


    public Alien[] getEnemies() {
        return (Alien[]) enemies.toArray();
    }

    class redrawPlayer implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent arg0) {
            respawn.stop();

            if (liveCount != 0) {
                createPlayer();
                player.setAlive(true);
                enemyFireTimer.start();
                gameTimer.start();
            }
        }
    }

    KeyListener keyListener = new KeyListener() {

        @Override
        public void keyPressed(KeyEvent e) {

            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                rightPressed.start();

                if (player.getPosition().x < 0) {
                    rightPressed.stop();
                }
            }

            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                leftPressed.start();

                if (player.getPosition().x > 550) {
                    leftPressed.stop();
                }
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                rightPressed.stop();
            }

            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                leftPressed.stop();
            }

            if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                int delay = 800;

                if (System.currentTimeMillis() - lastFire < delay) {
                    return;
                }
                if (player.isAlive()) {
                    player.fire();
                    lastFire = System.currentTimeMillis();
                }
            }
        }

        @Override
        public void keyTyped(KeyEvent e) {}
    };

    class LeftPressed implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (player.isAlive())
                player.move(player.getPosition().x+5, 0);
            repaint();
        }
    }

    class RightPressed implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (player.isAlive())
                player.move(player.getPosition().x-5, 0);
            repaint();
        }

    }



}