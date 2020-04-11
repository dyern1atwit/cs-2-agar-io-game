package edu.wit.cs.dyermccoy.agario;

import java.util.ArrayList;
import java.util.Random;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Coronavirus extends Circle {

	public static ArrayList<Coronavirus> virusObjects = new ArrayList<>();

	public Coronavirus(int xRange, int yRange) {
		super(30, Color.DARKGREEN);
		virusObjects.add(this);

		Random randomGen = new Random();

		int xValue = randomGen.nextInt(xRange);
		int yValue = randomGen.nextInt(yRange);

		this.setCenterX(xValue);
		this.setCenterY(yValue);
	}

	public Coronavirus() {
		super(30, Color.DARKGREEN);

		Random rand = new Random();

		this.setCenterX(rand.nextInt(Settings.windowWidth));

		this.setCenterY(rand.nextInt(Settings.windowHeight));
	}
}
