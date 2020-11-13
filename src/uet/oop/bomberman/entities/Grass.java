package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

public class Grass extends Entity {
    Image grass = new Image("/sprites/grass.png",Sprite.SCALED_SIZE, Sprite.SCALED_SIZE, false, false);

    public Grass(int x, int y) {
        super(x, y, Sprite.grass1);
    }

    public Grass(int x, int y, Image img) {
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
        //Image grass = new Image("/sprites/grass.png",Sprite.SCALED_SIZE, Sprite.SCALED_SIZE, false, false);
        //super.render(gc);
        gc.drawImage(grass, x * Sprite.SCALED_SIZE, y * Sprite.SCALED_SIZE);
    }
}
