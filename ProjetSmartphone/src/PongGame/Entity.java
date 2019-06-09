package PongGame;

import java.awt.*;

/**
 * @author Mickaël
 * @version 1.0
 */
public abstract class Entity {
    /**
     * position x d'un objet
     */
    protected int x;
    /**
     * position y d'un objet
     */
    protected int y;
    /**
     * largeur d'un objet
     */
    protected final int WIDTH;
    /**
     * hauteur d'un objet
     */
    protected final int HEIGHT;
    /**
     * vitesse x d'un objet
     */
    protected int xSpeed;
    /**
     * vitesse y d'un objet
     */
    protected int ySpeed;
    /**
     * identité de l'objet
     */
    protected ID id;

    /**
     * Constructeur d'un objet
     * <p>permet de construire un objet du jeu Pong</p>
     * @param x position x d'un objet
     * @param y position y d'un objet
     * @param width largeur d'un objet
     * @param height hauteur d'un objet
     * @param id identité de l'objet
     */
    public Entity(int x, int y, int width, int height, ID id) {
        this.x = x;
        this.y = y;
        this.WIDTH = width;
        this.HEIGHT = height;
        this.id = id;
    }

    /**
     * <p>retourne les coordonnées d'un objet ainsi que son identité</p>
     * @return position vitesse et identité d'un objet
     */
    public String toString() {
        return "\nCoordinates: (" + this.x + ", " + this.y + ") \nxSpeed = " + this.xSpeed + "\nySpeed = " + this.ySpeed
                + "\nID = " + this.id;
    }

    /**
     * <p>permet de limiter les déplacement d'un objet</p>
     */
    public void update() {
    }

    /**
     * <p>permet de dessiner un objet</p>
     * @param g
     */
    public void render(Graphics g) {
    }

    /**
     * <p>retourne la position x d'un objet</p>
     * @return position x d'un objet
     */
    public int getX() {
        return x;
    }

    /**
     * <p>permet de modifier la position x d'un objet</p>
     * @param x position x d'un objet
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * <p>retourne position y d'un objet</p>
     * @return position y d'un objet
     */
    public int getY() {
        return y;
    }

    /**
     * <p>permet de modifier la position y d'un objet</p>
     * @param y position y de l'objet
     */
    public void setY(int y) {
        this.y = y;
    }
}
