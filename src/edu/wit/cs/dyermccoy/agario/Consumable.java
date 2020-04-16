package edu.wit.cs.dyermccoy.agario;

import java.util.Random;

import javafx.scene.shape.Circle;

public abstract class Consumable extends Circle{
	
	Consumable(int n) {
		
		super(n);
		
		Random randomGen = new Random();

		int xValue = randomGen.nextInt(Settings.windowWidth-50);
		int yValue = randomGen.nextInt(Settings.windowHeight-50);

		this.setCenterX(xValue);
		this.setCenterY(yValue);
		
	}

}
