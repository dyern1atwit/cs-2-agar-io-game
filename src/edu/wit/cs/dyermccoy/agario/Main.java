package edu.wit.cs.dyermccoy.agario;

import java.util.ArrayList;

import javafx.application.Application;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class Main extends Application{
	//Array list for 
	ArrayList<Circle> cells = new ArrayList<>();
	
	//static variables controlling width height and the amount of food spawned
	static int windowWidth = 1280;
	static int windowHeight = 720;
	static int limitFood = 50;
	
		@Override
		public void start(Stage primaryStage) {
			
			try {
				//user circle is created
				Circle UserDot = new Circle(100, 100, 30, Color.BLUE);
				cells.add(UserDot);
				//user circle follows mouse only when mouse is inside dot
				UserDot.setOnMouseMoved(e -> {
				
					UserDot.setCenterX(e.getX());
					UserDot.setCenterY(e.getY());
					
				});
				//food is spawned & added to dot ArrayList
				for(int i = 0 ; i < limitFood; i++) {
					new Food(windowWidth, windowHeight);
				}
				
				
			
				Pane f = new Pane();
				
				f.getChildren().addAll(Food.foodObjects);
				f.getChildren().addAll(cells);
				
				
				Scene scene = new Scene(f,windowWidth,windowHeight);
				
				
				primaryStage.setScene(scene);
				primaryStage.show();
				
				primaryStage.requestFocus();;
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		public static void main(String[] args) {
			launch(args);
		}
	
	}


