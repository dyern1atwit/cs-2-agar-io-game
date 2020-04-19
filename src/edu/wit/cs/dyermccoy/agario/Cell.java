package edu.wit.cs.dyermccoy.agario;

import java.util.Random;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public abstract class Cell extends Circle {

	private Random random = new Random();
	double speed = 4;

	private static final double MIN_RADIUS = 16;
	private static final double GROWTH_RATE = .6;
	private static final double SHRINK_RATE = 30;
	private static final double MIN_SPEED = 1;

	public Cell(Color color) {
		super(MIN_RADIUS);

		setStroke(color);
		setFill(color.deriveColor(1, 1, 1, .3));
		setCenterX(random.nextInt(Settings.windowWidth));
		setCenterY(random.nextInt(Settings.windowHeight));
	}

	public abstract void step(Point p);

	// grows object if it eats a consumable allowing growth
	private void grow() {

		setRadius(getRadius() + GROWTH_RATE);
		if (speed > MIN_SPEED) {
			speed = newSpeed();
		}

	}

	// shrinks object if it eats a consumable allowing shrinking
	private void shrink() {

		double radius = getRadius() - SHRINK_RATE;
		if (radius < MIN_RADIUS)
			radius = MIN_RADIUS;
		setRadius(radius);
		speed = newSpeed();

	}

	// checks collision between objects
	private boolean checkCollision(Consumable c) {
		Point object = new Point(c.getCenterX(), c.getCenterY());
		Point cell = new Point(getCenterX(), getCenterY());

		double distance = cell.distance(object);
		if (distance <= Math.abs(this.getRadius() - c.getRadius()))
			return true;
		else
			return false;

	}

	// sets a new speed if it grows or shrinks
	public double newSpeed() {
		double outputSpeed = (-0.02 * getRadius()) + 4.32;
		if (outputSpeed < MIN_SPEED) {
			outputSpeed = MIN_SPEED;
		}
		return outputSpeed;
	}

	// if it collides with an object will eat and grow
	protected void eats() {

		for (int i = 0; i < Food.foodObjects.size(); i++) {
			if (checkCollision(Food.foodObjects.get(i))) {

				grow();

				Main.playfield.getChildren().remove(Food.foodObjects.get(i));
				Food.foodObjects.remove(Food.foodObjects.get(i));

				Food newFood = new Food();
				;
				Main.playfield.getChildren().add(newFood);

			}
		}
	}

	// if collides with a virus will be attacked and shrink
	protected void isAttacked() {
		if (getRadius() >= Coronavirus.VIRUS_SIZE) {
			for (int i = 0; i < Coronavirus.virusObjects.size(); i++) {
				if (checkCollision(Coronavirus.virusObjects.get(i))) {

					shrink();
					Main.playfield.getChildren().remove(Coronavirus.virusObjects.get(i));
					Coronavirus.virusObjects.remove(Coronavirus.virusObjects.get(i));

					Coronavirus newVirus = new Coronavirus();
					Main.playfield.getChildren().add(newVirus);
				}
			}
		}
	}

	public Point getPoint() {
		return new Point(getCenterX(), getCenterY());
	}
}
