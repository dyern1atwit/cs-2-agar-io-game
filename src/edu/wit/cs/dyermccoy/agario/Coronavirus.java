package edu.wit.cs.dyermccoy.agario;

import java.util.ArrayList;
import java.util.Random;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Coronavirus extends Consumable {

	public static ArrayList<Coronavirus> virusObjects = new ArrayList<>();

	public Coronavirus(int xRange, int yRange) {
		
		super(30);
		
		this.setFill(Color.FORESTGREEN);
		
		virusObjects.add(this);

		
	}

	public Coronavirus() {
		
		super(30);
		
		this.setFill(Color.FORESTGREEN);
		
	}
}
