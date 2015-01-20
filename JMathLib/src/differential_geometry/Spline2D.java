package differential_geometry;

import general.*;

import java.util.ArrayList;

import javax.vecmath.* ;

import matrix.Matrix;
import matrix.Vector;



public class Spline2D  {

Point2d[] waypoints ;
ArrayList<Polynomial> polys ;	


public Spline2D (Point2d... wpts)
{
	this.waypoints=wpts.clone();
	polys = populate();
}

public ArrayList<Polynomial> populate()
{
	int k = 4*waypoints.length-2 ;
	
Matrix A = Matrix.zero(k,k);
Vector b = Vector.zero(k);




	
	
	return null;
}


	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
