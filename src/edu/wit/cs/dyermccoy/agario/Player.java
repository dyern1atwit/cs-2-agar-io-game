package edu.wit.cs.dyermccoy.agario;

import java.util.Random;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Player extends Circle {

	private Random random = new Random();

	Vector location;
	Vector velocity;
	Vector acceleration;

	double Maxspeed;

	public Player() {
		super(16);
		setStroke(Color.BLUE);
		setFill(Color.BLUE.deriveColor(1, 1, 1, .3));
		setCenterX(random.nextInt(Settings.windowWidth/2));
		setCenterY(random.nextInt(Settings.windowHeight/2));
		velocity = new Vector(0, 0);

		Maxspeed = 4;

	}

	public void step(Vector mouse) {
		
		Point2D position = new Point2D(getCenterX(), getCenterY());
		Point2D mousePoint = new Point2D(mouse.x, mouse.y);
		
		position.moveDistanceTowardsPoint(mousePoint, Maxspeed);
		this.setCenterX(position.x);
		this.setCenterY(position.y);
		

	}

	public void checkBoundaries() {

		if (getCenterX() > Settings.windowWidth) {
			setCenterX(0);
		} 
		
		if (getCenterX() < 0) {
			setCenterX(Settings.windowWidth);
		}

		if (getCenterY() > Settings.windowHeight) {
			setCenterY(0);
		}
		
		if (getCenterY() < 0) {
			setCenterY(Settings.windowHeight);
		}
	}

	public void moves() {

		relocate(getCenterX(), getCenterY());

		Main.playerScore.relocate(getCenterX(), getCenterY());

	}

	public void grow() {

		setRadius(getRadius() + 0.6);
		
		if (Maxspeed > .2) {

			Maxspeed -= .006;

		}


	}

	public void eats() {

		for (int i = 0; i < Food.foodObjects.size(); i++) {

			int foodXPos = (int) Food.foodObjects.get(i).getCenterX();
			int foodYPos = (int) Food.foodObjects.get(i).getCenterY();
			
			
			
			int userRadius = (int) Main.cells.get(0).getRadius();
			int foodRadius = (int) Food.foodObjects.get(i).getRadius();

			/*
			if ((userXPos + userRadius + foodRadius > foodXPos) && (userXPos < foodXPos + userRadius + foodRadius)) {

				if (((userYPos + userRadius + foodRadius > foodYPos)
						&& (userYPos < foodYPos + userRadius + foodRadius))) {

					grow();

					Main.playfield.getChildren().remove(Food.foodObjects.get(i));
					Food.foodObjects.remove(i);

					Food.foodObjects.add(i, new Food(i));
					Main.playfield.getChildren().add(i, Food.foodObjects.get(i));

					Main.playerScore.setText(Integer.toString((int) Math.round(radius)));

				}
			}
			*/
		}
	}

}
