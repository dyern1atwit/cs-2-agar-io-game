package edu.wit.cs.dyermccoy.agario;


import java.util.ArrayList;


import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class Main extends Application{
	static Pane playfield;
	public static ArrayList<Player> cells = new ArrayList<>();
	
	Vector mouse = new Vector(0,0);
	public static Label playerScore;

		@Override
		public void start(Stage primaryStage) {
			
			Button strt = new Button("Start");
			Button HowToPlay = new Button("How to play");
			Button backBttn = new Button("Back");
			Text HTP = new Text("HELLO");
			StackPane menu = new StackPane();
			
			StackPane HTPtext = new StackPane();
			menu.getChildren().add(strt);
			menu.getChildren().add(HowToPlay);
			
			HowToPlay.setTranslateY(30);
			
			HTPtext.getChildren().add(HTP);
			HTPtext.getChildren().add(backBttn);
			
			backBttn.setTranslateY(HTP.getY()+30);
			BorderPane root = new BorderPane();
			StackPane layerPane = new StackPane();
			
			
			playfield = new Pane();
			playerScore = new Label();
			playerScore.setAlignment(Pos.TOP_CENTER);
			playerScore.setTextFill(Color.BLACK);
			
			playfield.getChildren().add(playerScore);
			
			layerPane.getChildren().addAll(playfield);
			
			root.setCenter(layerPane);
			
			//adds player to playfield
			Player User = Player.addAPlayer();
			cells.add(User); 
			playfield.getChildren().add(User);
			
			
			Scene MainMenu = new Scene(menu,Settings.mainMenuWidth, Settings.mainMenuHeight);
			Scene Instruct = new Scene(HTPtext, Settings.mainMenuWidth, Settings.mainMenuHeight);
			Scene agario = new Scene(root, Settings.windowWidth, Settings.windowHeight);
			
			
			
			primaryStage.setScene(MainMenu);
			primaryStage.setTitle("Main Menu");
			primaryStage.show();
			
			
			 HowToPlay.addEventFilter(MouseEvent.MOUSE_CLICKED, e->{ 
				 	primaryStage.setScene(Instruct);		   
				 	primaryStage.setTitle("How To Play");  
			 			});  
			 
			 
			 backBttn.addEventFilter(MouseEvent.MOUSE_CLICKED, e->{ 
				 	primaryStage.setScene(MainMenu); 
				 	primaryStage.setTitle("MainMenu");
				 		}); 
			 
			 
			 strt.addEventFilter(MouseEvent.MOUSE_CLICKED, e->{ 
				 	primaryStage.setScene(agario); 
			 		primaryStage.setTitle("We do be eating circles (o.O)"); 
			 		primaryStage.setFullScreen(true);
			 			});    
		     
			 
			//tracks mouse movements
			agario.addEventFilter(MouseEvent.ANY, e -> {
					mouse.set(e.getX(), e.getY());
						});
			
			
		   //spawns amount of food based on limit in settings
			for(int i = Food.foodObjects.size(); i < Settings.limitFood; i++) {
				
				new Food(Settings.windowWidth, Settings.windowHeight);
			}
			
			playfield.getChildren().addAll(Food.foodObjects);
			
	
			
				AnimationTimer game = new AnimationTimer() {

				@Override
				public void handle(long now) {
				
				cells.forEach((UserDot) -> UserDot.step(mouse));
				
				cells.forEach(Player :: checkBoundaries);
				
				cells.forEach(Player :: moves);
				
				cells.forEach(Player :: eats);
				
				} 
					}; game.start();	
					
		
}
				
		public static void main(String[] args) {
			
			launch(args);
			
		}
	
		
	
		
		
	}


