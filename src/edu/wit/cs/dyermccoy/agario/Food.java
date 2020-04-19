package edu.wit.cs.dyermccoy.agario;

import java.util.ArrayList;
import javafx.scene.paint.Color;

public class Food extends Consumable {

	public static ArrayList<Food> foodObjects = new ArrayList<>();

	// creates food objects
	public Food() {

		super(5);
		this.setFill(Color.BLACK);
		foodObjects.add(this);

	}
}
