package edu.wit.cs.dyermccoy.agario;

import java.util.ArrayList;
import javafx.scene.paint.Color;

public class Coronavirus extends Consumable {

	public static ArrayList<Coronavirus> virusObjects = new ArrayList<>();
	public static int VIRUS_SIZE = 30;

	// creates virus objects
	public Coronavirus() {

		super(VIRUS_SIZE);
		this.setFill(Color.FORESTGREEN);
		virusObjects.add(this);

	}
}
