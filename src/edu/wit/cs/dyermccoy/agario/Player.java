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
		setCenterX(random.nextInt(Settings.windowWidth / 2));
		setCenterY(random.nextInt(Settings.windowHeight / 2));

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

	public void grows() {

		setRadius(getRadius() + Settings.growthRate);
		if (Maxspeed > Settings.Minspeed) {
			Maxspeed -= getRadius() * Settings.speedRate;

			System.out.println(Maxspeed);
		}

	}

	public void shrinks() {

		for (double i = getRadius(); i > getRadius()/2; i -= Settings.growthRate) {
			Maxspeed += ((getRadius() * Settings.speedRate));
		}
		
		setRadius(getRadius() / 2);

		System.out.println(Maxspeed);
	}

	public void eats() {

		for (int i = 0; i < Food.foodObjects.size(); i++) {
			if (checkCollision( Food.foodObjects.get(i).getCenterX(),
					Food.foodObjects.get(i).getCenterY(), Food.foodObjects.get(i).getRadius())) {

				grows();

				Main.playfield.getChildren().remove(Food.foodObjects.get(i));
				Food.foodObjects.remove(i);

				Food.foodObjects.add(i, new Food());
				Main.playfield.getChildren().add(i, Food.foodObjects.get(i));

				Main.playerScore.setText(Integer.toString((int) (getRadius())));

			}

		}
	}

	public void attacked() {
		if (getRadius() >= 31.5) {
			for (int i = 0; i < Coronavirus.virusObjects.size(); i++) {
				if (checkCollision( Coronavirus.virusObjects.get(i).getCenterX(),
						 Coronavirus.virusObjects.get(i).getCenterY(),
						 Coronavirus.virusObjects.get(i).getRadius())) {

					shrinks();

					Main.playfield.getChildren().remove(Coronavirus.virusObjects.get(i));
					Coronavirus.virusObjects.remove(i);

					Coronavirus.virusObjects.add(i, new Coronavirus());
					Main.playfield.getChildren().add(i, Coronavirus.virusObjects.get(i));
					Main.playerScore.setText(Integer.toString((int) (getRadius())));
				}

			}
		}
	}

	public boolean checkCollision(double ObjXPos, double ObjYPos, double ObjRadius) {
		
		if ((getCenterX() + getRadius() + ObjRadius > ObjXPos)
				&& (getCenterX() < ObjXPos + getRadius() + ObjRadius)) {

			if (((getCenterY() + getRadius() + ObjRadius > ObjYPos)
					&& (getCenterY() < ObjYPos + getRadius() + ObjRadius))) {
				return true;
			}

		}

		return false;
	}
}
