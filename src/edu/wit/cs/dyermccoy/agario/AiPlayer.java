package edu.wit.cs.dyermccoy.agario;

import java.util.ArrayList;
import java.util.Random;

import javafx.scene.paint.Color;

public class AiPlayer extends Cell {

	public static ArrayList<AiPlayer> AiPlayers = new ArrayList<>();
	
	public AiPlayer() {
		super(randomColor());
		AiPlayers.add(this);
	}
	
	public Point AiControl() {
		Point food = Food.foodObjects.get(0).getPoint();
		double closestDistance = getPoint().distance(food);
		for (int i = 0; i < Food.foodObjects.size(); i++) {
			if (getPoint().distance(Food.foodObjects.get(i).getPoint()) < closestDistance) {
				closestDistance = getPoint().distance(Food.foodObjects.get(i).getPoint());
				food = Food.foodObjects.get(i).getPoint();
			}
		}
		return food;
	}
	
	@Override
	public void step(Point p) {
		Point position = getPoint();
		Point mousePoint = new Point(p.x, p.y);
		position.moveDistanceTowardsPoint(mousePoint, speed);
		setCenterX(position.x);
		setCenterY(position.y);
		
	}
	
	public static Color randomColor() {
		Random randomGen = new Random();
		
		int r = randomGen.nextInt(255);
        int g = randomGen.nextInt(255);
        int b = randomGen.nextInt(255);
        return Color.rgb(r, g, b);
	}

}
