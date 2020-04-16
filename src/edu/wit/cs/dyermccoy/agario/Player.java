package edu.wit.cs.dyermccoy.agario;

import javafx.scene.paint.Color;

public class Player extends Cell {

	public Player() {
		super(Color.BLUE);
	}

	@Override
	public void step(Point mouse) {
		Point position = getPoint();
		Point mousePoint = new Point(mouse.x, mouse.y);
		position.moveDistanceTowardsPoint(mousePoint, speed);
		setCenterX(position.x);
		setCenterY(position.y);
		Main.playerScore.relocate(position.x, position.y);
		
		Main.playerScore.setText(Integer.toString((int) (getRadius())));
		
	}
}

	
