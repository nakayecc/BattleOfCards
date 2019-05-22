package com.codecool.klondike;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.File;
import java.util.concurrent.atomic.AtomicInteger;


public class Main extends Application {

    private static final double WINDOW_WIDTH = 1280;
    private static final double WINDOW_HEIGHT = 720;

    Stage window;
    Scene menuScene, gameScene;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        AtomicInteger playersAmount = new AtomicInteger();

        String musicFile = "got.mp3";
        Media sound = new Media(new File(musicFile).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();
        // MENU --------------------------------------------------------
        Button twoPlayers = new Button("2 Players");
        Button fourPlayers = new Button("4 Players");

        twoPlayers.setOnAction(e -> {
            window.setScene(gameScene);
            playersAmount.set(2);
            mediaPlayer.stop();
        });

        fourPlayers.setOnAction(e -> {
            window.setScene(gameScene);
            playersAmount.set(4);
            mediaPlayer.stop();
        });
        // player 1
        Label labelPlayer1 = new Label("Player 1");
        labelPlayer1.setLayoutX(110);
        labelPlayer1.setLayoutY(350);
        labelPlayer1.setFont(new Font(40));
        labelPlayer1.setTextFill(Color.web("#FFFFFF"));

        //player 2
        Label labelPlayer2 = new Label("Player 2");
        labelPlayer2.setLayoutX(1000);
        labelPlayer2.setLayoutY(350);
        labelPlayer2.setFont(new Font(40));
        labelPlayer2.setTextFill(Color.web("#FFFFFF"));

        // VS label
        Label versus = new Label("VS");
        versus.setLayoutX((WINDOW_WIDTH / 2) - 25);
        versus.setLayoutY(WINDOW_HEIGHT / 5);
        versus.setFont(new Font(50));
        versus.setTextFill(Color.web("#FFFFFF"));

        // Statistics label
        Label stats = new Label("Statistics");
        stats.setLayoutX((WINDOW_WIDTH / 2) - 100);
        stats.setLayoutY((WINDOW_HEIGHT / 2) + 20);
        stats.setFont(new Font(40));
        stats.setTextFill(Color.web("#FFFFFF"));

        // stats label
        Label str = new Label("");
        str.setLayoutX((WINDOW_WIDTH / 2) - 120);
        str.setLayoutY((WINDOW_HEIGHT / 2) + 20);
        str.setFont(new Font(40));
        str.setTextFill(Color.web("#FFFFFF"));


        VBox layout = new VBox(50);
        BackgroundImage myBI= new BackgroundImage(new Image("/table/green.png",640,480, false,true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);

        layout.setBackground(new Background(myBI));

        layout.getChildren().addAll(twoPlayers, fourPlayers);
        menuScene = new Scene(layout, 300, 300);

        // GAME --------------------------------------------------------

        Card.loadCardImages();
        Game game = new Game();
        game.setTableBackground(new Image("/table/background.png"));

        gameScene = new Scene(game, WINDOW_WIDTH, WINDOW_HEIGHT);
        primaryStage.setResizable(false);
        // ------------- HUD --------------------------------------------

        game.getChildren().addAll(labelPlayer1, labelPlayer2, versus, stats, str);

        //---------------------------------------------------------------

        window.setTitle("Battle of Cards");
        window.setScene(menuScene);
        window.show();
    }

}
