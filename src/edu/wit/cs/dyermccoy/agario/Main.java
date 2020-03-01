package edu.wit.cs.dyermccoy.agario;

import java.util.ArrayList;

import java.util.Random;

import javafx.application.Application;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class Main extends Application{
	//dot array list
	ArrayList<Circle> dots  = new ArrayList<>();
	
	//static variables controlling width height and the amount of food spawned
	static int width = 600;
	static int height = 600;
	static int limitFood = 50;
		@Override
		public void start(Stage primaryStage) {
			Random random = new Random();
			
			try {
				//user circle is created
				Circle UserDot = new Circle(100, 100, 30, Color.BLUE);
				dots.add(UserDot);
				//user circle follows mouse only when mouse is inside dot
				UserDot.setOnMouseMoved(e -> {
				
					UserDot.setCenterX(e.getX());
					UserDot.setCenterY(e.getY());
					
				});
				//food is spawned & added to dot ArrayList
				for(int i = 0 ; i < limitFood; i++) {
				int randX = random.nextInt(width-1);
				int randY = random.nextInt(height-1);
				
					Circle Dot = new Circle(randX, randY, 10);
				 dots.add(Dot);
				
				}
				
				
			
				Pane f = new Pane();
				
				f.getChildren().addAll(dots);
				
				
				Scene scene = new Scene(f,width,height);
				
				
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


