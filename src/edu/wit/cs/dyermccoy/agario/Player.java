package edu.wit.cs.dyermccoy.agario;

import java.util.Random;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Player extends Circle {

	private Random random = new Random();

	double Maxspeed;

	public Player() {
		super(16);
		setStroke(Color.BLUE);
		setFill(Color.BLUE.deriveColor(1, 1, 1, .3));
		setCenterX(random.nextInt(Settings.windowWidth/2));
		setCenterY(random.nextInt(Settings.windowHeight/2));

		Maxspeed = 4;

	}

	public void step(Point mouse) {
		
		Point position = new Point(getCenterX(), getCenterY());
		Point mousePoint = new Point(mouse.x, mouse.y);
		
		position.moveDistanceTowardsPoint(mousePoint, Maxspeed);
		setCenterX(position.x);
		setCenterY(position.y);
		Main.playerScore.relocate(position.x, position.y);
		

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
			
			int foodRadius = (int) Food.foodObjects.get(i).getRadius();
			
			if ((getCenterX() + getRadius() + foodRadius > foodXPos) && (getCenterX() < foodXPos + getRadius() + foodRadius)) {

				if (((getCenterY() + getRadius() + foodRadius > foodYPos)
						&& (getCenterY() < foodYPos + getRadius() + foodRadius))) {

					grow();

					Main.playfield.getChildren().remove(Food.foodObjects.get(i));
					Food.foodObjects.remove(i);

					Food.foodObjects.add(i, new Food(i));
					Main.playfield.getChildren().add(i, Food.foodObjects.get(i));

					Main.playerScore.setText(Integer.toString((int) Math.round(getRadius())));

				}
			}
		}
	}

}
