package edu.wit.cs.dyermccoy.agario;

public class Point2D extends com.sun.javafx.geom.Point2D {
	
	public Point2D(double centerX, double centerY) {
		this.setLocation((float) centerX, (float) centerY);
	}

	public void moveDistanceTowardsPoint(Point2D p, double distance) {
		if (distance(p) <= distance) {
			distance = distance(p);
		}
		
		
		double x = this.x-p.x;
		double y = this.y-p.y;
		System.out.println("Distance to x: " + x);
		System.out.println("Distance to y: " + y);
		
		double angle = Math.atan2(y, x);
		
		double newX = this.x-(distance*Math.cos(angle));
		double newY = this.y-(distance*Math.sin(angle));
		
		this.setLocation((float) newX, (float) newY);
		
	}
}