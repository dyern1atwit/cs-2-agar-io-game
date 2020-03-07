package edu.wit.cs.dyermccoy.agario;

import java.util.Random;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Player extends Pane{

	 private static Random random = new Random();

	    Vector location;
	    Vector velocity;
	    Vector acceleration;
	    float Maxspeed;

	    double width = 30;
	    double height = width;
	    double centerX = width / 2.0;
	    double centerY = height / 2.0;
	    double radius = width / 2.0;

	    Circle circle;

	    public Player() {

	        location = new Vector(random.nextDouble() * width, random.nextDouble() * height);
	        velocity = new Vector(0, 0);
	       
	        //THIS WOULD CHANGE AS CIRCLE GETS BIGGER!!!!
	        Maxspeed = 4;

	        circle = new Circle(radius);
	        circle.setCenterX(radius);
	        circle.setCenterY(radius);

	        circle.setStroke(Color.BLUE);
	        circle.setFill(Color.BLUE.deriveColor(1, 1, 1, 0.3));

	        getChildren().add(circle);

	    }

	    public void step(Vector mouse) {

	        Vector dir = Vector.sub(mouse, location);
	        dir.normalize();
	        dir.mult(0.5);
	        acceleration = dir;
	        velocity.add(acceleration);
	        velocity.limit(Maxspeed);
	        location.add(velocity);

	    }

	    public void checkBoundaries() {

	        if (location.x > Settings.windowWidth) {
	            location.x = 0;
	        } else if (location.x < 0) {
	            location.x = Settings.windowWidth;
	        }

	        if (location.y > Settings.windowHeight) {
	            location.y = 0;
	        } else if (location.y < 0) {
	            location.y = Settings.windowHeight;
	        }
	    }

	    public void move() {
	        relocate(location.x - centerX, location.y - centerY);
	    }
	    
		public static void addAPlayer() {
			
			Player UserDot = new Player();
			Main.cells.add(UserDot);
			Main.playfield.getChildren().add(UserDot);
			
			
		}
	}
	

