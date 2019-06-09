package PongGame;

import java.awt.*;

/**
 * @author Mickaël
 * @version 1.0
 */
public class Player extends Entity {

    /**
     * <p>Constructeur du player qui se trouve à gauche de l'écran</p>
     * @param x position x du Player
     * @param y position y du Player
     * @param width largeur du player
     * @param height hauteur du player
     * @param id identité de l'objet player
     */
    public Player(int x, int y, int width, int height, ID id) {
        super(x, y, width, height, id);
    }

    /**
     * <p>méthode qui définit les limitations de la position du player</p>
     */
    @Override
    public void update() {
        if (this.id == ID.PLAYER_ONE) {
            if (this.x < 0) {
                this.x = 0;
            }
            if (this.x + this.WIDTH > this.WIDTH) {
                this.x = 0;
            }
            if (this.y < 0) {
                this.y = 0;
            }
            if (this.y + this.HEIGHT + 30 > Game.GAME_HEIGHT) {
                this.y = Game.GAME_HEIGHT - this.HEIGHT - 30;
            }

            this.x += this.xSpeed;
            this.y += this.ySpeed;
        }
    }


    /**
     * méthode qui dessine le player
     * @param g
     */
    @Override
    public void render(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillRoundRect(this.x, this.y, this.WIDTH, this.HEIGHT,20,5);
    }

}