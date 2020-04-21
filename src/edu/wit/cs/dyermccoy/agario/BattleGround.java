package edu.wit.cs.dyermccoy.agario;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class BattleGround extends Main {
	// creates frame rate event handler to update game
	static EventHandler<ActionEvent> handler = new EventHandler<ActionEvent>() {

		@Override
		public void handle(ActionEvent event) {

			User.step(Main.mouse);

			for (int i = 0; i < AiPlayer.AiPlayers.size(); i++) {
				AiPlayer.AiPlayers.get(i).step(AiPlayer.AiPlayers.get(i).AiControl());
			}

			Cell.getCellArrayList().forEach(Cell::infected);

			Cell.getCellArrayList().forEach(Cell::eatsFood);
			
			Cell.getCellArrayList().forEach(Cell::eatsPlayer);
			
			Cell.getCellArrayList().forEach(Cell::checkBoundaries);

		}
	};
	static Timeline game = new Timeline(new KeyFrame(Duration.millis(30), handler));

	BattleGround() {
		// creates scenes and objects
		createMainMenu();
		createAgarioScene();
		spawnConsumables();
		spawnCells();

		game.setCycleCount(Timeline.INDEFINITE);

	}

	// main menu scene
	public void createMainMenu() {

		menu = new StackPane();
		HTPtext = new StackPane();
		menufield = new Pane();
		strt = new Button("Start");
		HowToPlay = new Button("How to play");
		backBttn = new Button("Back");
		HTP = new Text(Settings.HowTo);

		menu.getChildren().add(menufield);
		menu.getChildren().add(strt);
		menu.getChildren().add(HowToPlay);

		HowToPlay.setTranslateY(30);

		backBttn.setTranslateY(HTP.getY() + 100);

		HTPtext.getChildren().add(HTP);
		HTPtext.getChildren().add(backBttn);

		MainMenu = new Scene(menu, Settings.mainMenuWidth, Settings.mainMenuHeight);
		Instruct = new Scene(HTPtext, Settings.mainMenuWidth, Settings.mainMenuHeight);

	}

	// game scene
	public void createAgarioScene() {

		root = new BorderPane();
		layerPane = new StackPane();

		playfield = new Pane();

		playerScore = new Label();
		playerScore.setAlignment(Pos.TOP_CENTER);
		playerScore.setTextFill(Color.BLACK);

		playfield.getChildren().add(playerScore);

		layerPane.getChildren().addAll(playfield);

		root.setCenter(layerPane);

		agario = new Scene(root, Settings.windowWidth, Settings.windowHeight);
	}

	// food and virus objects
	public void spawnConsumables() {

		for (int i = Food.foodObjects.size(); i < Settings.limitFood; i++) {

			new Food();
		}
		for (int i = Coronavirus.virusObjects.size(); i < Settings.limitVirus; i++) {

			new Coronavirus();
		}

		playfield.getChildren().addAll(Food.foodObjects);
		playfield.getChildren().addAll(Coronavirus.virusObjects);

	}

	// cell objects
	public void spawnCells() {

		User = new Player();
		playfield.getChildren().add(User);

		for (int i = 0; i < Settings.limitAi; i++) {
			AiPlayer newAi = new AiPlayer();
			playfield.getChildren().add(newAi);
		}

	}

}
