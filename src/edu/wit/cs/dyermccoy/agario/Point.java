package edu.wit.cs.dyermccoy.agario;

public class Point extends com.sun.javafx.geom.Point2D {

	public Point() {
		super();
	}

	public Point(double centerX, double centerY) {
		this.setLocation(centerX, centerY);
	}

	public void moveDistanceTowardsPoint(Point p, double distance) {
		if (distance(p) <= distance) {
			distance = distance(p);
		}

		double x = this.x - p.x;
		double y = this.y - p.y;

		double angle = Math.atan2(y, x);

		double newX = this.x - (distance * Math.cos(angle));
		double newY = this.y - (distance * Math.sin(angle));

		this.setLocation(newX, newY);

	}
	
	public Point getOppositePoint(Point p) {
		double distance = this.distance(p);

		double x = this.x - p.x;
		double y = this.y - p.y;

		double angle = Math.atan2(y, x);

		double newX = this.x - (distance * Math.cos(angle)*2);
		double newY = this.y - (distance * Math.sin(angle)*2);

		return new Point(newX, newY);
	}

	public void setLocation(double x, double y) {
		setLocation((float) x, (float) y);

	}
}
