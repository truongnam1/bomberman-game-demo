package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;

public class Bomber extends Entity {
    private String statusMove;

    public Bomber(double x, double y, Image img) {
        super( x, y, img);
        super.speed = 0.25;
    }

    public Bomber(double x, double y, Image img, Sprite sprite) {
        super( x, y, img);
        super.speed = 0.25;
        this.sprite = sprite;
    }

    @Override
    public void update() {
        move();
        
        for (Entity entity : BombermanGame.stillObjects) {
            if (entity instanceof Wall && this.intersects(entity)) {
                speed = 0;
                moveImpact(entity);
                //System.out.println("xBom =" + x + " yBom = " + y + "\t|" + "xEnti =" + entity.x + " yEnti = " + entity.y);
                System.out.println("va cham voi tuong");
            }
        }

        for (Entity entity : BombermanGame.entities) {
            //System.out.println(entity +"  xEnti =" + entity.x + " yEnti = " + entity.y);
            if (entity instanceof Brick && this.intersects(entity)) {
                this.speed = 0.0;
                moveImpact(entity);
                //System.out.println("xBom =" + x + " yBom = " + y + "\t|" + "xEnti =" + entity.x + " yEnti = " + entity.y);
                System.out.println("va cham voi gach");

            }
            if (((entity instanceof Balloon ||  entity instanceof Oneal) && this.intersects(entity)) || heart == 0) {
                    heart = 0;
                    speed = 0;
                    impactEnemy();
            }

            else if (entity instanceof Portal && this.intersects(entity)) {
                impactPortal();
            }

        }
        //System.out.println("ket thuc\n\n");
        super.setPosition();
    }

    // di chuyen
    @Override
    public void move() {
        this.speed = 0.5;
        this.setVelocity(0,0);
        if (BombermanGame.input.contains("LEFT")) {
            statusMove = "LEFT";
            this.addVelocity(-0.1, 0);

            switch (this.index_left) {
                case 1:
                    this.img = Sprite.player_left.getFxImage();
                    this.sprite = Sprite.player_left;
                    break;
                case 2:
                    this.img = Sprite.player_left_1.getFxImage();
                    this.sprite = Sprite.player_left_1;
                    break;
                case 3:
                    this.img = Sprite.player_left_2.getFxImage();
                    this.sprite = Sprite.player_left_2;

            }
            if (BombermanGame.countLoop == 4)
                this.index_left = (this.index_left + 1) % 4;
            if (this.index_left == 0)
                this.index_left = 1;

        }
        if (BombermanGame.input.contains("RIGHT")) {
            statusMove = "RIGHT";
            this.speed = 0.5;
            this.addVelocity(0.1, 0);
            switch (this.index_right) {
                case 1:
                    this.img = Sprite.player_right.getFxImage();
                    this.sprite = Sprite.player_right;
                    break;
                case 2:
                    this.img = Sprite.player_right_1.getFxImage();
                    this.sprite = Sprite.player_right_1;
                    break;
                case 3:
                    this.img = Sprite.player_right_2.getFxImage();
                    this.sprite = Sprite.player_right_2;

            }
            if (BombermanGame.countLoop == 4)
                this.index_right = (this.index_right + 1) % 4;
            if (this.index_right == 0)
                this.index_right = 1;
        }
        if (BombermanGame.input.contains("UP")) {
            statusMove = "UP";
            this.speed = 0.4;
            this.addVelocity(0, -0.1);
            switch (this.index_up) {
                case 1:
                    this.img = Sprite.player_up.getFxImage();
                    this.sprite = Sprite.player_up;
                    break;
                case 2:
                    this.img = Sprite.player_up_1.getFxImage();
                    this.sprite = Sprite.player_up_1;
                    break;
                case 3:
                    this.img = Sprite.player_up_2.getFxImage();
                    this.sprite = Sprite.player_up_2;

            }
            if (BombermanGame.countLoop == 4)
                this.index_up = (this.index_up + 1) % 4;
            if (this.index_up == 0)
                this.index_up = 1;
        }
        if (BombermanGame.input.contains("DOWN")) {
            statusMove = "DOWN";
            this.speed = 0.4;
            this.addVelocity(0, 0.1);
            switch (this.index_down) {
                case 1:
                    this.img = Sprite.player_down.getFxImage();
                    this.sprite = Sprite.player_down;
                    break;
                case 2:
                    this.img = Sprite.player_down_1.getFxImage();
                    this.sprite = Sprite.player_down_1;
                    break;
                case 3:
                    this.img = Sprite.player_down_2.getFxImage();
                    this.sprite = Sprite.player_down_2;

            }
            if (BombermanGame.countLoop == 4)
                this.index_down = (this.index_down + 1) % 4;
            if (this.index_down == 0)
                this.index_down = 1;
        }
        this.updateSize();
    }

    public void moveImpact(Entity entity) {
        //System.out.println(".h = " + height + " .w = " + width);
        System.out.println("hD = " + heightDraw + " wD = " + widthDraw);
        double khoangCach = -1;
        if (statusMove.equals("UP")) {
            khoangCach = Math.abs(x - (entity.x + SIZE_DRAW_DEFAULT));

            if (khoangCach < SIZE_DRAW_DEFAULT - 0.45) {
                x += 0.05;
            } else if (khoangCach > SIZE_DRAW_DEFAULT + 0.1) {
                x -= 0.05;
            }
        }

        else if (statusMove.equals("DOWN")) {

            khoangCach = Math.abs(x - (entity.x + SIZE_DRAW_DEFAULT));

            if (khoangCach < SIZE_DRAW_DEFAULT - 0.45) {
                x += 0.05;
            } else if (khoangCach > SIZE_DRAW_DEFAULT + 0.1) {
                x -= 0.05;
            }
        }

        else if (statusMove.equals("LEFT")) {
            khoangCach = Math.abs(y + heightDraw - entity.y);

            if (khoangCach < SIZE_DRAW_DEFAULT - 0.45) {
                y -= 0.05;
            } else if (khoangCach > SIZE_DRAW_DEFAULT + 0.1) {
                y += 0.05;
            }
        }

        else if (statusMove.equals("RIGHT")) {
            khoangCach = Math.abs(entity.y - (y + heightDraw));

            if (khoangCach < SIZE_DRAW_DEFAULT - 0.45) {
                y -= 0.05;
            } else if (khoangCach > SIZE_DRAW_DEFAULT + 0.1) {
                y += 0.05;
            }
        }
        statusMove = "";
    }

    public void impactEnemy() {
        switch (this.index_dead) {
            case 1:
                this.img = Sprite.player_dead1.getFxImage();
                this.sprite = Sprite.player_dead1;
                break;
            case 2:
                this.img = Sprite.player_dead2.getFxImage();
                this.sprite = Sprite.player_dead2;
                break;
            case 3:
                this.img = Sprite.player_dead3.getFxImage();
                this.sprite = Sprite.player_dead3;

        }
        System.out.println("index dead = " + index_dead);

        if (BombermanGame.countLoop == 4)
            this.index_dead = (this.index_dead + 1) % 4;
        if (this.index_dead == 0)
            this.index_dead = 1;
        if (index_dead == 3) {
            heart = 1;
            BombermanGame.createMap();
        }
    }

    public void impactPortal() {
        if (BombermanGame.levelHt < BombermanGame.MAX_LEVEL) {
            BombermanGame.createMap(BombermanGame.levelHt + 1);
        } else if (BombermanGame.levelHt == BombermanGame.MAX_LEVEL) {
            BombermanGame.createMap();
        }
    }
}
