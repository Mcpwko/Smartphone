import java.awt.Color;
import java.awt.Graphics;

public class Ball extends Entity {

    public Ball(int x, int y, int width, int height, ID id) {
        super(x, y, width, height, id);
        this.x = Game.GAME_WIDTH / 2 - (this.WIDTH / 2);
        this.y = Game.GAME_HEIGHT / 2 - (this.HEIGHT / 2);
    }

    public boolean isColliding(Player player) {
            return (this.x < player.WIDTH && ((this.y >= player.y && this.y < player.y + player.HEIGHT)
                    || (this.y + this.HEIGHT >= player.y && this.y + this.HEIGHT < player.y + player.HEIGHT)));
    }
    public boolean isCollidingBot(Bot computer) {
            return (this.x + this.WIDTH > computer.x && ((this.y >= computer.y && this.y < computer.y + computer.HEIGHT)
                    || (this.y + this.HEIGHT >= computer.y && this.y + this.HEIGHT < computer.y + computer.HEIGHT)));

    }

    public void reset() {
        this.x = Game.GAME_WIDTH / 2 - (this.WIDTH / 2);
        this.y = Game.GAME_HEIGHT / 2 - (this.HEIGHT / 2);
        this.xSpeed = 0;
        this.ySpeed = 0;

    }

    @Override
    public void update() {
        if ((this.x + this.WIDTH) > Game.GAME_WIDTH) {
            Game.startDirection = true;
            reset();

            } // AU CAS OU !
        /*if (this.x < 0) {
            Game.startDirection = false;
            reset();
            //Game.setLevel(1);
            //Game.setScore(0);
            }*/
        if (this.y + this.HEIGHT + 30 > Game.GAME_HEIGHT) {
            this.ySpeed = -this.ySpeed;
            }
        if (this.y < 0) {
            this.ySpeed = -this.ySpeed;
            }
        this.x += this.xSpeed;
        this.y += this.ySpeed;


    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillOval (this.x, this.y, this.WIDTH, this.HEIGHT);
    }

}
