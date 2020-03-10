package edu.wit.cs.dyermccoy.agario;


import java.util.ArrayList;


import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class Main extends Application{
	static Pane playfield;
	public static ArrayList<Player> cells = new ArrayList<>();
	
	Vector mouse = new Vector(0,0);
	public static Label playerScore;

	
	
	
		@Override
		public void start(Stage primaryStage) {
			
			BorderPane root = new BorderPane();
			StackPane layerPane = new StackPane();
			
			playfield = new Pane();
			playerScore = new Label();
			playerScore.setAlignment(Pos.TOP_CENTER);
			playerScore.setTextFill(Color.BLACK);
			
			playfield.getChildren().add(playerScore);
			
			layerPane.getChildren().addAll(playfield);
			
			root.setCenter(layerPane);
			
		
			Scene agario = new Scene(root, Settings.windowWidth, Settings.windowHeight);
			
			primaryStage.setScene(agario);
			primaryStage.setTitle("We do be eating circles (o.O)");
			primaryStage.show();
			primaryStage.setFullScreen(true);
				
				
			Player.addAPlayer();
				
			
			
		      
		       
		     
				
			agario.addEventFilter(MouseEvent.ANY, e -> {
				
				mouse.set(e.getX(), e.getY());
				
			});
			
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


