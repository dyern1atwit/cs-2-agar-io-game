package edu.wit.cs.dyermccoy.agario;

public class Vector {

	
		public double x;
	    public double y;
	 

	    public Vector(double x, double y) {
	    	
	    	super();
	       
	    	this.x = x;
	       
	    	this.y = y;
	    }

	    public void normalize() {
	        
	    	double m = mag(); 
	        
	    	if (m != 0 && m != 1) { 
	          
	    		div(m); 
	       
	    	} 
	    }

	    public void div(double value) {
	       
	    	x /= value;
	        y /= value;
	        
	    }

	    public void mult(double value) {
	        
	    	x *= value;
	        y *= value;
	       
	    }

	    public void add(Vector v) {
	       
	    	x += v.x;
	        y += v.y;
	       
	    }

	    public void sub(Vector v) {
	       
	    	x -= v.x;
	        y -= v.y;
	        
	    }

	    public void limit(double max) {
	       
	    	if (mag() > max) {
	            
	    		normalize();
	            
	    		mult(max);
	    		
	        }
	    }

	    public double mag() {
	        
	    	return Math.sqrt(x * x + y * y);
	    	
	    }

	    public static Vector sub(Vector v1, Vector v2) {
	       
	    	return sub(v1, v2, null);
	    	
	    }

	    public static Vector sub(Vector v1, Vector v2, Vector target) { 
	    	
	    	if (target == null) {
	           
	    		target = new Vector(v1.x - v2.x, v1.y - v2.y);
	    		
	        } else {
	        	
	            target.set(v1.x - v2.x, v1.y - v2.y);
	        }
	    	
	        return target;
	    }

	    public void set(double x, double y) {
	       
	    	this.x = x;
	        this.y = y;
	     
	    }
}
