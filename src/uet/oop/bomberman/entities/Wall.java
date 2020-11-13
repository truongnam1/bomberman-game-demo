package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;

public class Wall extends Entity {
    Image wall = Sprite.wall.getFxImage();

    public Wall(int x, int y) {
        super(x, y, Sprite.wall1);
    }

    public Wall(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {


    }

    @Override
    public void move() {

    }

    @Override
    public void render(GraphicsContext gc) {
        gc.drawImage(wall, x * Sprite.SCALED_SIZE, y * Sprite.SCALED_SIZE);
    }


}
