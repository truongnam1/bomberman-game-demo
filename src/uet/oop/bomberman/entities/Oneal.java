package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;

public class Oneal extends Entity{
    Image balloom = Sprite.oneal_left1.getFxImage();
    int flag = -1;
    int changeDirectionX = 1;
    int change_directionY = 1;

    public Oneal(double x, double y) {
        super(x, y, Sprite.oneal_left1.getFxImage());
    }

    public Oneal(double x, double y, Image img) {
        super(x, y, img);
    }

    public Oneal(double x, double y, Image img, Sprite sprite) {
        super(x, y, img, sprite);
    }

    @Override
    public void update() {
        move();
        for (Entity entity : BombermanGame.stillObjects) {
            if (entity instanceof Wall && this.intersects(entity)) {
                flag *= -1;
            }
        }

        for (Entity entity : BombermanGame.entities) {
            //System.out.println(entity +"  xEnti =" + entity.x + " yEnti = " + entity.y);
            if (entity instanceof Brick && this.intersects(entity)) {
                flag *= -1;
            }
        }
        this.setPosition();
    }

    @Override
    public void move() {
        this.setVelocity(0,0);
        this.speed = 0.5;
        this.addVelocity(0.1 * flag, 0);
    }
}
