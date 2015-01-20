package matrix;

import linguistics.NumberFormatter;


public class Vector  {

	
double[] origin ;
int length ;




public Vector (double[] a)
{
	
	origin = Vector.clone(a);
	length =a.length;
	
}
	
public double dotProduct (Vector v)
{
	if (this.length ==v.length)
	{
double result = 0;
for (int i=0; i< this.length; i++)
{
	result += origin[i]*v.origin[i];
}
	
return result;
	}
	
	else return Double.NaN;	
	
}

public static Vector NaN (int k)
{
	double[] a = new double[k];
	
	for (int i=0;i<k;i++)
	{
		a[i] = Double.NaN;
	}
	
	
	return new Vector(a);
}


public double partproduct (Vector v , int k)
{
	
double result =0;
	
	for (int i=0;i<k+1;i++)
	{
		result += this.origin[i]*v.origin[i];
	}
	
	
	return result;
	
	
	
	
} //partproduct


public static Vector e (int n, int k)
{
	if ( n>k)
	{
	double[] result = new double[n];
	
	result[k] =1d;
	
	return new Vector(result);
	}
	
	else return null;
}

public void scalarprod (double c)
{
	
	for (int i=0;i<this.length;i++)
	{
		this.origin[i]*=c ;
	}
	
	
}

public double backproduct (Vector v , int k)
{
	
double result =0;
	
	for (int i=this.length-1;i>=k;i--)
	{
		result += this.origin[i]*v.origin[i];
	}
	
	
	return result;
	
	
	
	
} //partproduct


public void format()
{
	
for (int i=0; i< length;i++)
{
	this.origin[i] =NumberFormatter.format(this.origin[i]);
}
	
	
}//format


public void print()
{
	this.format();
	

	
	for (int j =0; j< length; j++)
	{
		System.out.println("[	"+this.origin[j]+" 	]");
	}
	
	
	
	
}//print()


public static Vector zero (int k)
{
	return new Vector (new double[k]);
}



public static double[] clone (double[] A)
{
	int m = A.length;
	double[] result = new double[m];
	
	for (int i=0; i<m; i++)
	{				
			result[i] = A[i];
	} // i-for
	
	return result;
	
}// clone

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	Vector vec = Vector.zero(5);
	vec.print();
		
		
	}

}
