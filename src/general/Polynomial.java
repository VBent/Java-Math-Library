package general;

import static java.lang.Math.*;

public class Polynomial {

	
int degree ;
double[] coeffs ;


public Polynomial (double... coeffs)
{
	this.coeffs=coeffs.clone() ;
	degree = coeffs.length-1;
	
}
	


public double valueAt (double x)
{
	double result = this.coeffs[0];
	
	for (int i =1; i<this.coeffs.length ;i++)
	{
		result+=coeffs[i]*pow (x,i);
		
	}
	
	
	return result;
}

public double deriv_valueAt (double x)
{
double result = 0;
	
	for (int i =1; i<coeffs.length ;i++)
	{
		result+=(i)*coeffs[i]*pow (x,i-1);
		
	}
	
	
	return result;
}


public Polynomial deriv ()
{
	double[] dcoefs = new double[this.degree];
	
	for (int i =0; i<dcoefs.length;i++)
	{
		dcoefs[i]=(i+1)*coeffs[i+1];
	}
	
	return new Polynomial (dcoefs);
	
	
}

public void print()
{
	String s = new String();
	
	for (int i=0; i< this.coeffs.length;i++)
	{
		s+= Double.toString(this.coeffs[i])+ "    ";
	}
	
	System.out.println(s);
}


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	Polynomial poly = new Polynomial (1,1,3,4);
	System.out.println(poly.degree);
	System.out.println(poly.deriv().degree);
	System.out.println();
	System.out.println (poly.valueAt(3)+ "     "+poly.deriv_valueAt(3));
	System.out.println();
	poly.print();
	poly.deriv().print();

	}

}
