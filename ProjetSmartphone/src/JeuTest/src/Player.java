import java.awt.*;
import java.io.IOException;
import java.util.HashSet;

public class Player extends GameObject{
    public static HashSet<Shot> laserList = new HashSet<Shot>();
    private transient Image img;

    public Player(String imageLocation, Point position, Dimension size){
        super (imageLocation, position, size);
        GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();

        try {
            img = Game.getImage(imageLocation, gc);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    @Override
    public void draw(Graphics g) {
        Point position = getPosition();
        Dimension size = getSize();

        g.drawImage(img, position.x, position.y, size.width, size.height, null);
    }

    @Override
    public void move(int x, int y) {
        Point position = getPosition();
        Dimension size = getSize();
        position.x = x;
        getRectangle().setRect(position.x, position.y, size.width, size.height);
    }

    public void fire() {
        Point pos = new Point(getPosition());
        pos.x = pos.x + getSize().width/2;
        pos.y = pos.y - 20;
        Shot l = new Shot(pos, Color.WHITE);
        l.setySpeed(5);

        Game.addGameObject(l);
        laserList.add(l);
    }


}
