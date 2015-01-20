package matrix;

public class LinSys {

	

	
public static Vector LUsolve (Matrix A, Vector b)
{
	Vector Solution = new Vector (new double[b.length]);
	Vector intermed = new Vector(new double[b.length]);
	
	Matrix LU = A.getLU();
	
	for (int i =0; i< b.length; i++)
	{
		intermed.origin[i]=b.origin[i]-LU.getRow(i).partproduct(intermed, i);
	}

	for (int i =b.length-1; i>=0;i--)
	{
		Solution.origin[i]=(1d/LU.get(i, i)) * (intermed.origin[i] -LU.getRow(i).backproduct(Solution, i+1));
	}
	
	
//	System.out.println("success");
return Solution;	
	
}
	


	



	

}
