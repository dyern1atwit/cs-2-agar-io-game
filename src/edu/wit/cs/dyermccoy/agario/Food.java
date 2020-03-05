package edu.wit.cs.dyermccoy.agario;

import java.util.ArrayList;
import java.util.Random;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Food extends Circle{
	
	public static ArrayList<Food> foodObjects  = new ArrayList<>();
	
	public Food(int xRange, int yRange) {
		super(5, Color.BLACK);
		foodObjects.add(this);
		
		Random randomGen = new Random();
		
		int xValue = randomGen.nextInt(xRange);
		int yValue = randomGen.nextInt(yRange);
		
		this.setCenterX(xValue);
		this.setCenterY(yValue);
	}
	
}
