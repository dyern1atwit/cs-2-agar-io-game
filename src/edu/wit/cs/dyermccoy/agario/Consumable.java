package edu.wit.cs.dyermccoy.agario;

import java.util.Random;

import javafx.scene.shape.Circle;

public abstract class Consumable extends Circle {

	Consumable(int radius) {

		super(radius);

		Random randomGen = new Random();

		int xValue = randomGen.nextInt(Settings.windowWidth-40)+20;
		int yValue = randomGen.nextInt(Settings.windowHeight-40)+20;

		this.setCenterX(xValue);
		this.setCenterY(yValue);

	}

	public Point getPoint() {
		return new Point(getCenterX(), getCenterY());
	}

}
