package edu.wit.cs.dyermccoy.agario;

import java.util.ArrayList;


import javafx.animation.AnimationTimer;
import javafx.application.Application;

import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import javafx.stage.Stage;

public class Main extends Application{
	static Pane playfield;
	public static ArrayList<Player> cells = new ArrayList<>();
	
	Vector mouse = new Vector(0,0);
	
	Player UserDot;
	

	
		@Override
		public void start(Stage primaryStage) {
			
			BorderPane root = new BorderPane();
			StackPane layerPane = new StackPane();
			playfield = new Pane();
			
			layerPane.getChildren().addAll(playfield);
			root.setCenter(layerPane);
		
			Scene agario = new Scene(root, Settings.windowWidth, Settings.windowHeight);
			primaryStage.setScene(agario);
			primaryStage.setTitle("We do be eating circles (o.O)");
			primaryStage.show();
				
				
			
				Player.addAPlayer();
				
				
				
			agario.addEventFilter(MouseEvent.ANY, e -> {
				mouse.set(e.getX(), e.getY());
				
			});
			
			AnimationTimer loop = new AnimationTimer() {

				@Override
				public void handle(long now) {
				cells.forEach((UserDot) -> UserDot.step(mouse));
				
				
				
				cells.forEach(Player :: checkBoundaries);
				cells.forEach(Player :: move);
				
				
					
				}
				
			};
				loop.start();	
					
			
				
			
                
			
				for(int i = 0 ; i < Settings.limitFood; i++) {
					new Food(Settings.windowWidth, Settings.windowHeight);
				}
				
				

				
				playfield.getChildren().addAll(Food.foodObjects);
				
				
				
			} 
		
		
		public static void main(String[] args) {
			
			launch(args);
			
		}
	
		
	
		
		
	}


