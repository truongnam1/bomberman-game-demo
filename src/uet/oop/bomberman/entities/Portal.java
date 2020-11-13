package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

public class Portal extends Entity{
    public Portal(double x, double y) {
        super(x, y, Sprite.portal.getFxImage());
    }
    public Portal(double x, double y, Image img) {
        super(x, y, img);
    }

    public Portal(double x, double y, Image img, Sprite sprite) {
        super(x, y, img, sprite);
    }

    @Override
    public void update() {

    }

    @Override
    public void move() {

    }
}
