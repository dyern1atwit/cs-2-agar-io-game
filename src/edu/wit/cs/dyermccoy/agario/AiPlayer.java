package edu.wit.cs.dyermccoy.agario;

import java.util.ArrayList;
import java.util.Random;

import javafx.scene.paint.Color;

public class AiPlayer extends Cell {

	// private Circle aiPointer = new Circle(5, Color.RED);
	Random random = new Random();
	public static ArrayList<AiPlayer> AiPlayers = new ArrayList<>();
	public int aggressiveness = random.nextInt(20) + 1;

	public AiPlayer() {
		super(Color.GRAY);
		Color c = randomColor();
		setStroke(c);
		setFill(c.deriveColor(1, 1, 1, .3));
		AiPlayers.add(this);
	}

	public Point AiControl() {
		if (getClosestCell().getPoint().distance(this.getPoint()) <= 200 + getRadius()) {
			return playerInteraction(getClosestCell());
		} else {
			return getClosestFood();
		}
	}
	//finds closest food
	public Point getClosestFood() {
		Point food = Food.foodObjects.get(0).getPoint();
		double closestDistance = getPoint().distance(food);
		for (int i = 0; i < Food.foodObjects.size(); i++) {
			Food checkedFood = Food.foodObjects.get(i);
			if (checkedFood.equals(this)) {
				continue;
			}
			if (getPoint().distance(checkedFood.getPoint()) < closestDistance) {
				closestDistance = getPoint().distance(checkedFood.getPoint());
				food = checkedFood.getPoint();
			}
		}
		return food;

	}
	//searches for a random food in the food arraylist
	public Point getRandomFood() {
		Point food = Food.foodObjects.get(random.nextInt(Food.foodObjects.size())).getPoint();
		return food;
	}

	public Point playerInteraction(Cell c) {
		Point cell = null;
		if (c.getRadius() >= this.getRadius() + aggressiveness - 1) {
			cell = c.getPoint().getOppositePoint(this.getPoint());
		} else if (c.getRadius() < this.getRadius() - aggressiveness) {
			cell = c.getPoint();
		} else {
			return getClosestFood();
		}
		return cell;

	}
	//finds the closest cell
	public Cell getClosestCell() {
		Point cell = Cell.getCellArrayList().get(0).getPoint();
		double closestDistance = getPoint().distance(cell);
		Cell c = Cell.getCellArrayList().get(0);
		for (int i = 0; i < Cell.getCellArrayList().size(); i++) {
			Cell checkedCell = Cell.getCellArrayList().get(i);
			if (checkedCell.equals(this)) {
				continue;
			}
			if (getPoint().distance(checkedCell.getPoint()) < closestDistance) {
				closestDistance = getPoint().distance(checkedCell.getPoint());
				c = checkedCell;
			}
		}
		return c;
	}

	@Override
	public void step(Point p) {
		// aiPointer.relocate(p.x, p.y);
		Point position = getPoint();
		position.moveDistanceTowardsPoint(p, speed);
		setCenterX(position.x);
		setCenterY(position.y);

	}

	public Color randomColor() {
		Random randomGen = new Random();

		int r = (int) ((20 / aggressiveness) * 12.75);
		int g = randomGen.nextInt(64);
		int b = randomGen.nextInt(64);
		return Color.rgb(r, g, b);
	}

}
