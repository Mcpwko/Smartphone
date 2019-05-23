import java.awt.*;

public class Shot extends GameObject{
    private static final int WIDTH = 5;
    private static final int HEIGHT = 17;
    private Color colour;
    private int ySpeed;

    public Shot(Point position, Color colour) {
        super(null, position, new Dimension(WIDTH, HEIGHT));
        this.colour = colour;
    }

    public void setySpeed(int ySpeed) {
        this.ySpeed = ySpeed;
    }

    @Override
    public void move(int x, int y) {
        getPosition().y -= ySpeed;
        getRectangle().setRect(getPosition().x, getPosition().y, WIDTH, HEIGHT);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(colour);
        g.fillRect(getPosition().x-5, getPosition().y, WIDTH, HEIGHT);
    }
}