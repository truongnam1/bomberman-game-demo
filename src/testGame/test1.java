package testGame;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.shape.Rectangle;

public class test1 extends Application {
    @Override
    public void start(Stage stage) {
        //Drawing a Rectangle
        Rectangle rectangle = new Rectangle(1,1,32,32);
        Rectangle rectangle1 = new Rectangle(10,1,32,32);
        rectangle.setFill(Color.RED);
        rectangle1.setFill(Color.BISQUE);
        //Setting the properties of the rectangle

        //Creating a Group object
        Group root = new Group();
        root.getChildren().addAll(rectangle, rectangle1);

        //Creating a scene object
        Scene scene = new Scene(root, 600, 300);

        //Setting title to the Stage
        stage.setTitle("Drawing a Rectangle");

        //Adding scene to the stage
        stage.setScene(scene);

        //Displaying the contents of the stage
        stage.show();
    }
    public static void main(String args[]){
        launch(args);
    }
}
