package edu.wit.cs.dyermccoy.agario;

import java.util.ArrayList;
import java.util.Random;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Food extends Consumable {

	public static ArrayList<Food> foodObjects = new ArrayList<>();

	public Food() {

		super(5);
		this.setFill(Color.BLACK);
		foodObjects.add(this);
		
	}
}
