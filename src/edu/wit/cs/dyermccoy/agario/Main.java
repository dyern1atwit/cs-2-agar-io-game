package edu.wit.cs.dyermccoy.agario;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class Main extends Application{
	//Array list for 
	ArrayList<Circle> cells = new ArrayList<>();
	
	//static variables controlling width height and the amount of food spawned
	
	
		@Override
		public void start(Stage primaryStage) {
			double pointerX = 0;
		double 	pointerY= 0;
			try {
				Circle pointer = new Circle(pointerX, pointerY, 0);
				//user circle is created
				Circle UserDot = new Circle(100, 100, 30, Color.BLUE);
				cells.add(UserDot);
				//user circle follows mouse only when mouse is inside dot
				Pane f = new Pane();
				f.setOnMouseMoved(e -> {
					
					pointer.setCenterX(e.getX());
					pointer.setCenterY(e.getY());
					
					
					Point2D p = new Point2D(pointer.getCenterX(), pointer.getCenterY());
					Point2D u = new Point2D(UserDot.getCenterX(), UserDot.getCenterY());
					double angleUToP = u.angle(p);
					
					
				});
				
			
                
				//food is spawned & added to dot ArrayList
				for(int i = 0 ; i < Settings.limitFood; i++) {
					new Food(Settings.windowWidth, Settings.windowHeight);
				}
				
				

				f.getChildren().add(pointer);
				f.getChildren().addAll(Food.foodObjects);
				f.getChildren().addAll(UserDot);
				
				
				Scene scene = new Scene(f,Settings.windowWidth,Settings.windowHeight);
				
				
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


