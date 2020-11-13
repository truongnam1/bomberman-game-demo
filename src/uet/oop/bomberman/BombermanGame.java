package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.graphics.Sprite;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BombermanGame extends Application {

    public static final int WIDTH = 31;
    public static final int HEIGHT = 13;
    public static final char SYSBOL_GRASS = ' ';
    public static final char SYSBOL_Tiles_WALL = '#';
    public static final char SYSBOL_Tiles_BRICK = '*';
    public static final char SYSBOL_Tiles_Portal = 'x';
    public static final char SYSBOL_Character_Bomber = 'p';
    public static final char SYSBOL_Character_Balloon = '1';
    public static final char SYSBOL_Character_Oneal = '2';
    public static final char SYSBOL_Item_Bomb = 'b';
    public static final char SYSBOL_Item_Flame = 'f';
    public static final char SYSBOL_Item_Speed = 's';
    public static int countLoop = 1;
    public static int MAX_LEVEL = 3;

    public static int levelHt;
    protected static char[][] mapHt;
    protected static int rowHt;
    protected static int columnHt;
    protected String fps = "1";


    public static ArrayList<String> input = new ArrayList<>();

    public static GraphicsContext gc;
    private Canvas canvas;
    public static List<Entity> entities = new ArrayList<>();
    public static List<Entity> stillObjects = new ArrayList<>();


    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
        //launch(args);
    }

    @Override
    public void start(Stage stage) {
        // Tao Canvas

        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        gc = canvas.getGraphicsContext2D();

        // Tao root container
        Group root = new Group();
        root.getChildren().add(canvas);

        // Tao scene
        Scene scene = new Scene(root);

        // Them scene vao stage
        stage.setScene(scene);
        stage.show();

        Timeline gameLoop = new Timeline();
        gameLoop.setCycleCount( Timeline.INDEFINITE );

        final long timeStart = System.currentTimeMillis();

        // ckeck fps
        KeyFrame kf = new KeyFrame(Duration.seconds(0.2), new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent ae) {
                        stage.setTitle(fps + " |");
                        System.out.println(fps);

                    }
        });

        gameLoop.getKeyFrames().add(kf);
        gameLoop.play();

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
                    public void handle(KeyEvent e)
                    {
                        String code = e.getCode().toString();
                        if ( !input.contains(code) )
                            input.add( code );
                    }
        });

        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
                    public void handle(KeyEvent e)
                    {
                        String code = e.getCode().toString();
                        input.remove( code );
                    }
        });

        final long[] startNanoTime = {System.nanoTime()};

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                double t = (l - startNanoTime[0]) / 1000000000.0;
                //System.out.println(t);
                startNanoTime[0] = l;
                fps = String.format("%.2f fps", 1/t);
                //System.out.println(String.format("%.2f", 1/t));
                render();
                update();
                countLoop = (++countLoop) % 5;
                if (countLoop == 0)
                    countLoop = 1;
            }
        };
        timer.start();


        createMap(1);

        //Entity bomberman = new Bomber(1, 1, Sprite.player_right.getFxImage());
        //Entity bomberman = new Bomber(1.01, 1.01, Sprite.player_left1, Sprite.player_left);
        //entities.add(bomberman);
        System.out.println("still = " + stillObjects.size() + " entity = " + entities.size());
    }

    public static void createMap() {
        entities.clear();
        stillObjects.clear();
        for (int i = 0; i < rowHt; i++) {
            for (int j = 0; j < columnHt; j++) {
                Entity object;
                Entity object1 = null;
                if (mapHt[i][j] == SYSBOL_Tiles_WALL) {
                    object = new Wall(j, i);
                } else {
                    object = new Grass(j, i);
                    if (mapHt[i][j] == SYSBOL_Tiles_BRICK) {
                        object1 = new Brick(j, i);
                    } else if (mapHt[i][j] == SYSBOL_Character_Balloon) {
                        object1 = new Balloon(j, i);
                        object1.setHeart(1);
                    } else if (mapHt[i][j] == SYSBOL_Character_Oneal) {
                        object1 = new Oneal(j, i);
                        object1.setHeart(1);
                    } else if (mapHt[i][j] == SYSBOL_Character_Bomber) {
                        object1 = new Bomber(j, i , Sprite.player_down.getFxImage(), Sprite.player_down);
                        object1.setHeart(1);
                    } else if (mapHt[i][j] == SYSBOL_Tiles_Portal) {
                        object1 = new Portal(j, i);
                    }

                    if (object1 != null)
                        entities.add(object1);
                }
                stillObjects.add(object);
            }

        }
    }

    public static void createMap(int levelMap) {
        ReadMapToFile(levelMap);

        createMap();
    }

    public static void ReadMapToFile(int levelMap) {
        String urlInput = "res\\levels\\Level"+ levelMap +".txt";

        Scanner scanner = null;
        try {
            scanner = new Scanner(Paths.get(urlInput));
            int level = scanner.nextInt();
            int rowMap = scanner.nextInt();
            int columnMap = scanner.nextInt();
            scanner.nextLine();

            char[][] map = new char[rowMap][columnMap];

            for (int r = 0; r <rowMap; r++) {
                String s = scanner.nextLine();
                for (int cl = 0; cl < columnMap; cl++) {
                    if (s.length() > 0)
                        map[r][cl] = s.charAt(cl);
                }
            }

            System.out.println("level = " + level + " row = " + rowMap + "  column =  " + columnMap);
            for (int r = 0; r <rowMap; r++) {
                for (int cl = 0; cl < columnMap; cl++) {
                    System.out.print(map[r][cl]);
                }
                System.out.println();
            }

            levelHt = level;
            mapHt = map;
            columnHt = columnMap;
            rowHt = rowMap;
        } catch (IOException e) {
            System.out.println("lỗi đọc file map");
            System.out.println(e.fillInStackTrace());
        }
        scanner.close();
    }

    public void update() {
        entities.forEach(Entity::update);

    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        stillObjects.forEach(g -> g.render(gc));
        entities.forEach(g -> g.render(gc));
    }
}
