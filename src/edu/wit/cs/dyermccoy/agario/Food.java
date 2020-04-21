package edu.wit.cs.dyermccoy.agario;

import java.util.ArrayList;
import javafx.scene.paint.Color;

public class Food extends Consumable {

	private Cell reservedTo = null;

	public static ArrayList<Food> foodObjects = new ArrayList<>();

	// creates food objects
	public Food() {

		super(5);
		this.setFill(Color.BLACK);
		foodObjects.add(this);

	}

	public Cell getReserved() {
		return reservedTo;
	}

	public void setReserved(Cell b) {
		reservedTo = b;
	}
}
