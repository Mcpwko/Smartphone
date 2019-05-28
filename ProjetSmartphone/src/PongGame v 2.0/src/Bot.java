import java.awt.*;

public class Bot extends Entity{
    public Bot(int x, int y, int width, int height, ID id) {
        super(x, y, width, height, id);
    }

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



    @Override
    public void render(Graphics g) {
        g.setColor(Color.RED);
        g.fillRoundRect(this.x, this.y, this.WIDTH, this.HEIGHT,20,5);
    }
}
