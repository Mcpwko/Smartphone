package PongGame;

import java.awt.*;

public abstract class Entity {
    protected int x;
    protected int y;
    protected final int WIDTH;
    protected final int HEIGHT;
    protected int xSpeed;
    protected int ySpeed;
    protected ID id;

    public Entity(int x, int y, int width, int height, ID id) {
        this.x = x;
        this.y = y;
        this.WIDTH = width;
        this.HEIGHT = height;
        this.id = id;
    }

    public String toString() {
        return "\nCoordinates: (" + this.x + ", " + this.y + ") \nxSpeed = " + this.xSpeed + "\nySpeed = " + this.ySpeed
                + "\nID = " + this.id;
    }

    public void update() {
    }

    public void render(Graphics g) {
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
