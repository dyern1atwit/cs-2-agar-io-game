package edu.wit.cs.dyermccoy.agario;

import java.util.ArrayList;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {
	static Pane playfield;
	public static ArrayList<Player> cells = new ArrayList<>();

	Point mouse = new Point();
	public static Label playerScore;

	@Override
	public void start(Stage primaryStage) {

		// grid pane background
		GridPane grid = new GridPane();

		// This assumes square window and graphic!
		// Stroke size must be subtracted...
		int graphicSize = Settings.windowHeight / Settings.GRIDHEIGHT - 3;

		// create all of the grid location and
		// add them to the GridPane;
		for (int i = 0; i < Settings.GRIDHEIGHT; i++) {
			for (int j = 0; j < Settings.GRIDWIDTH; j++) {
				grid.add(new GridElement(i, j, graphicSize).getGraphic(), i, j);
			}
		}

		Button strt = new Button("Start");
		Button HowToPlay = new Button("How to play");
		Button backBttn = new Button("Back");
		
		Text HTP = new Text(Settings.HowTo);

		StackPane menu = new StackPane();

		StackPane HTPtext = new StackPane();
		menu.getChildren().add(strt);
		menu.getChildren().add(HowToPlay);

		HowToPlay.setTranslateY(30);

		HTPtext.getChildren().add(HTP);
		HTPtext.getChildren().add(backBttn);

		backBttn.setTranslateY(HTP.getY() + 100);
		BorderPane root = new BorderPane();
		StackPane layerPane = new StackPane();

		playfield = new Pane();

		playerScore = new Label();
		playerScore.setAlignment(Pos.TOP_CENTER);
		playerScore.setTextFill(Color.BLACK);

		playfield.getChildren().add(playerScore);

		layerPane.getChildren().addAll(playfield);

		root.setCenter(layerPane);

		// adds player to playfield
		Player User = new Player();
		cells.add(User);
		playfield.getChildren().add(User);

		// scene creation
		Scene MainMenu = new Scene(menu, Settings.mainMenuWidth, Settings.mainMenuHeight);
		Scene Instruct = new Scene(HTPtext, Settings.mainMenuWidth, Settings.mainMenuHeight);
		Scene agario = new Scene(root, Settings.windowWidth, Settings.windowHeight);

		// spawns amount of food based on limit in settings
		for (int i = Food.foodObjects.size(); i < Settings.limitFood; i++) {

			new Food(Settings.windowWidth, Settings.windowHeight);
		}
		for (int i = Coronavirus.virusObjects.size(); i < Settings.limitVirus; i++) {
			
			new Coronavirus(Settings.windowWidth, Settings.windowHeight);
		}

		playfield.getChildren().addAll(Food.foodObjects);
		playfield.getChildren().addAll(Coronavirus.virusObjects);

		primaryStage.setScene(MainMenu);
		primaryStage.setTitle("Main Menu");
		primaryStage.show();

		HowToPlay.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
			primaryStage.setScene(Instruct);
			primaryStage.setTitle("How To Play");
		});

		backBttn.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
			primaryStage.setScene(MainMenu);
			primaryStage.setTitle("MainMenu");
		});

		strt.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
			primaryStage.setScene(agario);
			primaryStage.setTitle("We do be eating circles (o.O)");
		});

		// tracks mouse movements
		agario.addEventFilter(MouseEvent.ANY, e -> {
			mouse.setLocation(e.getX(), e.getY());
		});

		EventHandler<ActionEvent> handler = new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				cells.forEach((UserDot) -> UserDot.step(mouse));

				cells.forEach(Player::checkBoundaries);

				cells.forEach(Player::attacked);

				cells.forEach(Player::eats);

			}
		};
		Timeline game = new Timeline(new KeyFrame(Duration.millis(30), handler));
		game.setCycleCount(Timeline.INDEFINITE);
		game.play();

		agario.setOnKeyPressed(e -> {

			if (e.getCode() == KeyCode.ESCAPE) {
				game.stop();
				System.exit(0);

			}
		});

	}

	public static void main(String[] args) {

		launch(args);

	}

}
