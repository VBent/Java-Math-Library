package matrix;


import linguistics.NumberFormatter;
import static java.lang.Math.*;


public class Matrix {

	

	
	double[][] origin ;		
	int rows ;
	int columns;

	
	
	public Matrix (double[][] A)
	{
		origin = Matrix.clone(A);
		rows = origin.length;
		columns = origin[0].length;
		
	}
	
	
public static Matrix zero (int m, int n)
{
	
 double[][] result = new double[m][n];
 
 return new Matrix (result);
	
}

public static Matrix uni_mat (int k)
{
	double[][] result = new double[k][k];
	 
	for (int i =0 ; i <k ;i++)
	{
		result[i][i] = 1d;
	}
	
	 return new Matrix (result);
}


public Matrix getTranspose ()
{
	double[][] transpose = new double[columns][rows];
	
	for (int i =0; i<rows; i++)
	{
		for (int j=0; j<columns;j++)
		{
			transpose[j][i] = this.origin[i][j];
		} // j-for
	} // i-for
	
	
	
	return new Matrix(transpose);
}
	
public Vector getRow (int i)
{
	double[] a = new double[columns];
	
	for (int j=0; j<columns;j++)
	{
		a[j] = origin[i][j];
	} // j-for
	
	
	return new Vector (a);
	
} //getRow

public Vector getColumn (int j)
{
	double[] a = new double [rows];
	
	for (int i =0; i<rows;i++)
	{
		a[i] = origin[i][j];
	} // i-for
	
	
	return new Vector (a);
}// getColumn
	

public Matrix getLU ()
{
	if (rows == columns)
	{
		 double[][] result = new double[rows][columns];
		 
		
		 
		 for (int i=0 ; i< columns; i++)
		 {
			 result[i][0]=get(i,0)/get(0,0);
			 result[0][i] = get(0,i);
			 
		 } // U_row 1 and L_column 1 initialization
		 
		 Matrix Result = new Matrix(result);
		 
		 for (int i =1; i< rows;i++)
		 {
			for (int j =i ; j< columns; j++)
			{
				Result.origin[i][j] = this.get(i, j)-Result.getRow(i).partproduct(Result.getColumn(j), i-1);
				
				
				
				
			}
			 
			for (int j =i+1 ; j< columns; j++)
			{
				
				Result.origin[j][i]=  (1d/Result.get(i, i)) *(this.get(j, i)-Result.getRow(j).partproduct(Result.getColumn(i), i-1) );
				
			}
		 }
	 
		 
		 return Result;
	} // endif
	
	
	
	
	
	else return null;
	
} // getLU


public void setColumn (int k, Vector v)
{
	if (k<columns && v.length == this.columns)
	{
		
	       for (int i =0 ; i < rows; i++)
	          {
	          	this.origin[i][k]=v.origin[i];
	          }
	}
	
}

public void setRow (int k, Vector v)
{
	if (k<rows && v.length == this.rows)
	{
		
	       for (int j =0 ; j < columns; j++)
	          {
	          	this.origin[k][j]=v.origin[j];
	          }
	}
	
}


public Matrix inverse ()
{
if (rows == columns)
{
Matrix Result = zero (rows, columns);	
	
	
for (int i =0; i <columns; i++)
{
	Result.setColumn(i, LinSys.LUsolve(this, Vector.e(columns, i)));
}
	
	
	
	
	return Result ;
}
else return null;
}

public double determinant()
{
	if (rows ==columns)
	{
	double det = 1;
	
	
	Matrix a = this.getLU();
	
	for (int i =0;i<this.rows;i++)
	{
		det =det*a.get(i, i);
	}
	
	
	return det;}
	
	else return Double.NaN;
	
}
	

public Matrix Mat_times (Matrix A)
{
 
	double[][] result = new double[rows][A.columns];
	
	for (int i =0; i < rows;i++)
	{
		for (int j =0 ; j< A.columns;j++)
		{
			result[i][j] = this.getRow(i).dotProduct(A.getColumn(j));
		}
	}
	
	
	return new Matrix(result);
	
	
}

public Vector Vec_times (Vector A)
{
 
	double[] result = new double[rows];
	
	for (int i =0; i < rows;i++)
	{
		result[i] = this.getRow(i).dotProduct(A);
	}
	
	
	return new Vector (result);
	
	
}

public void print()
{
	
	

for (int i =0; i < rows; i++)
{
	System.out.print("[	");

	NumberFormatter.hprint(this.origin[i]);
	/**
	for (int j =0; j< columns; j++)
	{
	
	System.out.print(NumberFormatter.formatter.format(this.origin[i][j])+"    ");
	}
	
	**/
	
	System.out.println("	]");
	
	
}
	
	
	
}//print()

public double get(int i, int j)
{
	
return this.origin[i][j];	
	
	
}


public static double[][] clone (double[][] A)
	{
		int m = A.length;
		int n = A[0].length;
		
		double[][] result = new double[m][n];
		
		for (int i=0; i<m; i++)
		{
			for (int j =0; j<n;j++)
			{
				result[i][j] = A[i][j];
			} //j-for
			
			
		} // i-for
		
		return result;
		
	}// clone
	
public Matrix getL ()
{
	Matrix lu = getLU();
	
	double[][] result = new double[rows][columns];
	
	for (int i =0 ; i<rows; i++)
	{
		for (int j=0 ; j < columns ; j++)
		{
			if (j>i)
			result[i][j]= 0 ;
			
			else if (j==i)
				result[i][j] =1d;

			else result[i][j] = lu.origin[i][j];
		}
	}
	
	return new Matrix (result);
	
}

public Matrix getU ()
{
	Matrix lu = getLU();
	
	double[][] result = new double[rows][columns];
	
	for (int i =0 ; i<rows; i++)
	{
		for (int j=0 ; j < columns ; j++)
		{
			if (j<i)
			result[i][j]= 0 ;
			
			
			else result[i][j] = lu.origin[i][j];
		}
	}
	
	return new Matrix (result);
	
}


public static Matrix random (int m , int n, double coeff)
{
	double[][] random = new double[m][n];
	
	for (int i=0; i< m;i++)
	{
		for (int j=0; j<n;j++)
		{
			if (j!=0 && abs(random[i][j-1]/coeff) <0.5)
			random[i][j] = -coeff*Math.random();
			
			else random[i][j] = coeff*Math.random();
		}
	}
	
	return new Matrix(random);
	
}


	
	public static void main(String[] args) {
		
Matrix mat = Matrix.random(5, 5, 10);
mat.print();
System.out.println();
System.out.println();

mat.getLU().print();

System.out.println();
System.out.println();

System.out.println();
System.out.println("determinant =  "+mat.determinant());
System.out.println();


Matrix matinv = mat.inverse();
matinv.print();
System.out.println();
System.out.println();
System.out.println(mat.determinant()*matinv.determinant());
System.out.println();

mat.Mat_times(matinv).print();


	}

}
