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
	   
	    double Maxspeed;
	   
	    static double width = 30;
	    static double height = width;
	    static double radius = width / 2.0;
	    static Circle circle;
	    
	    double centerX = width / 2.0;
	    double centerY = height / 2.0;
	   
	    
	    

	    public Player() {

	        location = new Vector(random.nextDouble() * width, random.nextDouble() * height);
	        velocity = new Vector(0, 0);
	       
	      
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

	    public void moves() {
	    	
	        relocate(location.x - centerX, location.y - centerY);
	        
	      Main.playerScore.relocate(location.x, location.y);
	      
	    }
	   
	    
	    
		public static void addAPlayer() {
			
			Player UserDot = new Player();
			
			Main.cells.add(UserDot);
			
			Main.playfield.getChildren().add(UserDot);
			
			
		}
		
		public double getCenterX() {
			
			return location.x;
		}
		
		public double getCenterY() {
			
			return location.y;
		}
		public double getRadius() {
			
			return radius;
		}
		
		public void grow() {
			
				radius+=.6;
				
				if(Maxspeed > .2) {
					
					Maxspeed -= .006;
				
				}
				
				Player.circle.setRadius(radius);
				
		}
		
		
		public void eats() {
			
			for	(int i = 0; i < Food.foodObjects.size(); i++) {
			
				int userXPos = ((int)Main.cells.get(0).getCenterX());
				int userYPos = ((int)Main.cells.get(0).getCenterY());
			
				int foodXPos = (int)Food.foodObjects.get(i).getCenterX();
				int foodYPos = (int)Food.foodObjects.get(i).getCenterY();
			
				int userRadius = (int) Main.cells.get(0).getRadius();
				int foodRadius = (int)Food.foodObjects.get(i).getRadius();
			
			if ((userXPos + userRadius + foodRadius > foodXPos) && (userXPos < foodXPos + userRadius + foodRadius)){
			
					if (((userYPos + userRadius + foodRadius > foodYPos) && (userYPos < foodYPos + userRadius + foodRadius))){   
				
						
				
						grow();
					
						Main.playfield.getChildren().remove(Food.foodObjects.get(i));
						Food.foodObjects.remove(i);
				
						Food.foodObjects.add(i, new Food(i));
						Main.playfield.getChildren().add(i, Food.foodObjects.get(i));
				
						Main.playerScore.setText( Integer.toString((int) Math.round(radius)));
				
				
				}
				}
	}
			}
	
		}
		
	
	

