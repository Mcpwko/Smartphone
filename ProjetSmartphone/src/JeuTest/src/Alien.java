import java.awt.*;
import java.util.HashSet;

public class Alien extends GameObject {
    private transient Image img;
    private static int direction = 1;
    private int movement = 2;
    public static HashSet<Shot> laserList = new HashSet<Shot>();


    public Alien(String imageLocation, Point position, Dimension size) {
        super ( imageLocation, position, size );
        GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();

        try {
            img = Game.getImage(imageLocation, gc);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public void move(int x, int y) {
        getPosition().x += (getDirection()) * movement;
        getPosition().y += y;
        getRectangle().setRect(getPosition().x, getPosition().y, getSize().width, getSize().height);

    }

    @Override
    public void draw(Graphics g) {
        Point position = getPosition();
        Dimension size = getSize();

        g.drawImage(img, position.x, position.y, size.width, size.height, null);
    }

    public boolean switchDirection() {
        if ((getPosition().x + getSize().width) > SpaceInvaders.WIDTH || (getPosition().x <= 0)) {
            setDirection(getDirection() * -1);
            return true;
        }
        return false;
    }


    public void fire() {
        Point pos = new Point(getPosition());
        pos.x = pos.x + getSize().width/2;

        Shot l = new Shot(pos, Color.GREEN);
        l.setPosition(new Point(pos.x, pos.y + 40));
        l.setySpeed(-5);

        Game.addGameObject(l);
        laserList.add(l);
    }

    public static int getDirection() {
        return direction;
    }

    public  void setDirection(int direction) {
        this.direction = direction;
    }

}
