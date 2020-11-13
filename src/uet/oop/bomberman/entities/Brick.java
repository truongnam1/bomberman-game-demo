package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

public class Brick extends Entity{

    Image brick = Sprite.brick.getFxImage();

    public Brick(double x, double y) {
        super(x, y, Sprite.brick1);
    }
    public Brick(double x, double y, Image img) {
        super(x, y, img);
    }

    public Brick(double x, double y, Image img, Sprite sprite) {
        super(x, y, img, sprite);
    }

    @Override
    public void update() {

    }

    @Override
    public void move() {

    }

    @Override
    public void render(GraphicsContext gc) {
        gc.drawImage(brick, x * Sprite.SCALED_SIZE, y * Sprite.SCALED_SIZE);
    }
}
