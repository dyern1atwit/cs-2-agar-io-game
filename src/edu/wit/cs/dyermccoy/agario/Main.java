package edu.wit.cs.dyermccoy.agario;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {

	public static ArrayList<Cell> cells = new ArrayList<>();

	public static Player User;
	static Pane playfield, menufield;
	static StackPane menu, HTPtext, layerPane;
	static BorderPane root;
	static Point mouse = new Point();

	static Scene MainMenu;

	static Scene Instruct, agario;

	static Label playerScore;
	static Button strt, HowToPlay, backBttn;
	static Text HTP;
	
	@Override
	public void start(Stage primaryStage) {
		// Creates Scenes and Game
		new BattleGround();

		// sets scene to main menu on launch
		primaryStage.setScene(MainMenu);
		primaryStage.setTitle("Main Menu");
		primaryStage.show();

		// event filters to switch between Scenes and track mouse movement
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
			BattleGround.game.play();

		});

		agario.addEventFilter(MouseEvent.ANY, e -> {
			mouse.setLocation(e.getX(), e.getY());
		});

		agario.setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.ESCAPE) {
				BattleGround.game.stop();
				System.exit(0);
			}
		});

	}

	public static void main(String[] args){
		launch(args);
	}
}
