package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;

public class Balloon extends Entity{
    Image balloom = Sprite.balloom_left1.getFxImage();
    int flag = -1;
    int changeDirectionX = 1;
    int change_directionY = 1;

    public Balloon(double x, double y) {
        super(x, y, Sprite.balloom_left1.getFxImage());
    }

    public Balloon(double x, double y, Image img) {
        super(x, y, img);
    }

    public Balloon(double x, double y, Image img, Sprite sprite) {
        super(x, y, img, sprite);
    }

    @Override
    public void update() {
        move();
        for (Entity entity : BombermanGame.stillObjects) {
            if (entity instanceof Wall && this.intersects(entity)) {
                flag *= -1;
                //System.out.println("va cham voi tuong");
            }
        }

        for (Entity entity : BombermanGame.entities) {
            //System.out.println(entity +"  xEnti =" + entity.x + " yEnti = " + entity.y);
            if (entity instanceof Brick && this.intersects(entity)) {
                flag *= -1;
                //System.out.println("va cham voi gach");
            }
        }
        this.setPosition();
    }

    @Override
    public void move() {
        this.setVelocity(0,0);
        this.speed = 0.1;
        this.addVelocity(0.1 * flag, 0);
    }



}
