package PongGame;

import java.awt.Color;
import java.awt.Graphics;

/**
 * @author Mickaël
 * @version 1.0
 */
public class Ball extends Entity {

    /**
     * Contructeur Ball
     * <p>Construction d'une ball qui possède une position avec x et y et qui a une taille
     * </p>
     * @param x position x de la balle
     * @param y position y de la balle
     * @param width largeur de la balle
     * @param height hauteur de la balle
     * @param id identité de l'objet utilisé
     */
    public Ball(int x, int y, int width, int height, ID id) {
        super(x, y, width, height, id);
        this.x = Game.GAME_WIDTH / 2 - (this.WIDTH / 2);
        this.y = Game.GAME_HEIGHT / 2 - (this.HEIGHT / 2);
    }

    /**
     * @param player le padlle du joueur
     * @return retourne un boleaan afin de savoir si la balle touche le joueur
     */
    public boolean isColliding(Player player) {
            return (this.x < player.WIDTH && ((this.y >= player.y && this.y < player.y + player.HEIGHT)
                    || (this.y + this.HEIGHT >= player.y && this.y + this.HEIGHT < player.y + player.HEIGHT)));
    }

    /**
     * @param computer
     * @return retourne un boleaan afin de savoir si la balle touche le bot
     */
    public boolean isCollidingBot(Bot computer) {
            return (this.x + this.WIDTH > computer.x && ((this.y >= computer.y && this.y < computer.y + computer.HEIGHT)
                    || (this.y + this.HEIGHT >= computer.y && this.y + this.HEIGHT < computer.y + computer.HEIGHT)));

    }

    /**
     * <p>repositionne la balle au millieu de l'écran et annule les mouvements de la balle</p>
     */
    public void reset() {
        this.x = Game.GAME_WIDTH / 2 - (this.WIDTH / 2);
        this.y = Game.GAME_HEIGHT / 2 - (this.HEIGHT / 2);
        this.xSpeed = 0;
        this.ySpeed = 0;

    }

    /**
     *<p>méthode qui définit les limitations de la position de la balle</p>
     */
    @Override
    public void update() {
        if ((this.x + this.WIDTH) > Game.GAME_WIDTH) {
            Game.startDirection = true;
            reset();

            }
        if (this.y + this.HEIGHT + 30 > Game.GAME_HEIGHT) {
            this.ySpeed = -this.ySpeed;
            }
        if (this.y < 0) {
            this.ySpeed = -this.ySpeed;
            }
        this.x += this.xSpeed;
        this.y += this.ySpeed;


    }

    /**
     * méthode qui va dessiner la balle
     * @param g
     */
    @Override
    public void render(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillOval (this.x, this.y, this.WIDTH, this.HEIGHT);
    }

}
