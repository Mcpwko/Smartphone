package PongGame;

import java.awt.*;

/**
 * @author Mickaël
 * @version 1.0
 */
public class Bot extends Entity{
    /**
     * <p>Constructeur de l'enemi qui se trouve à droite de l'écran</p>
     * @param x Coordonnées x du bot
     * @param y Coordonnées y du bot
     * @param width largeur du bot
     * @param height hauteur du bot
     * @param id identité de l'objet
     */
    public Bot(int x, int y, int width, int height, ID id) {
        super(x, y, width, height, id);
    }

    /**
     * <p>méthode qui définit les limitations de la position du bot</p>
     */
    @Override
    public void update() {
        if (this.id == ID.COMPUTER) {

            if (this.y < 0) {
                this.y = 0;
            }
            if (this.y + this.HEIGHT + 30 > Game.GAME_HEIGHT) {
                this.y = Game.GAME_HEIGHT - this.HEIGHT - 30;
            }

            this.x = 460;
            this.y += this.ySpeed;
        }
    }


    /**
     *méthode qui va dessiner le bot
     * @param g
     */
    @Override
    public void render(Graphics g) {
        g.setColor(Color.RED);
        g.fillRoundRect(this.x, this.y, this.WIDTH, this.HEIGHT,20,5);
    }
}
