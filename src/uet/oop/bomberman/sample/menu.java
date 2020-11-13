package uet.oop.bomberman.sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class menu extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        HightScore hightScore = new HightScore();

        //Pane
        Pane paneMenu = new Pane();
        Pane paneHightScore = new Pane();

        //image
        File file = new File("res\\textures\\background.png");
        String localUrl = file.toURI().toURL().toString();
        Image image = new Image(localUrl);
        ImageView backGround = new ImageView(image);
        backGround.setX(0); //position of image
        backGround.setY(0);
        backGround.setFitHeight(600); //size
        backGround.setFitWidth(1200);

        //listView
        ListView<String> listView = new ListView<>();
        listView.setLayoutX(357);
        listView.setLayoutY(6);
        listView.setPrefSize(494,493);
        listView.setStyle("-fx-font-size: 2.0em ;");
        listView.getItems().setAll(hightScore.list);

        //button
        Font font = new Font("System Bold",20);

        Button playButton = new Button("PLAY");
        playButton.setFont(font);
        playButton.setLayoutX(140);
        playButton.setLayoutY(290);
        playButton.setPrefSize(190,40);

        Button hightScoreButton = new Button("HIGHTSCORE");
        hightScoreButton.setFont(font);
        hightScoreButton.setLayoutX(140);
        hightScoreButton.setLayoutY(375);
        hightScoreButton.setPrefSize(190,40);
        hightScoreButton.setOnAction(actionEvent -> {
            paneHightScore.setVisible(true);
            paneMenu.setVisible(false);
        });

        Button exitButton = new Button("EXIT");
        exitButton.setFont(font);
        exitButton.setLayoutX(140);
        exitButton.setLayoutY(460);
        exitButton.setPrefSize(190,40);
        exitButton.setOnAction(actionEvent -> {
            Stage stage = (Stage) exitButton.getScene().getWindow();
            stage.close();
        });

        Button returnMenu = new Button("OK");
        returnMenu.setFont(font);
        returnMenu.setLayoutX(524);
        returnMenu.setLayoutY(514);
        returnMenu.setPrefSize(160,40);
        returnMenu.setOnAction(actionEvent -> {
            paneHightScore.setVisible(false);
            paneMenu.setVisible(true);
        });

        //paneMenu
        paneMenu.setLayoutX(1200);
        paneMenu.setLayoutY(600);
        paneMenu.getChildren().addAll(backGround,playButton,hightScoreButton,exitButton);

        //paneHightScore
        paneHightScore.setLayoutX(1200);
        paneHightScore.setLayoutY(600);
        paneHightScore.getChildren().addAll(returnMenu,listView);

        //stackPane
        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(paneHightScore,paneMenu);

        //menuScene
        Scene menu = new Scene(stackPane,1200,600);

        //menuStage
        primaryStage.setTitle("Bomberman");
        primaryStage.setWidth(1200);
        primaryStage.setHeight(600);
        primaryStage.setScene(menu);
        primaryStage.show();
    }
}
