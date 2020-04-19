package edu.wit.cs.dyermccoy.agario;

import java.util.Random;

import javafx.scene.shape.Circle;

public abstract class Consumable extends Circle {

	Consumable(int radius) {

		super(radius);

		Random randomGen = new Random();

		int xValue = randomGen.nextInt(Settings.windowWidth);
		int yValue = randomGen.nextInt(Settings.windowHeight);

		this.setCenterX(xValue);
		this.setCenterY(yValue);

	}

	public Point getPoint() {
		return new Point(getCenterX(), getCenterY());
	}

}
