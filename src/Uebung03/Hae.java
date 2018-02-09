public class Hae {
	public static long hae(long x, long y) {
		long z=0;
		while ((x > 0)&&(x >= y))
			if (!((x%y) == 0))
				x = x-1;
			else
				x = x/y; z = z+1;
				
				
		return z;		
			
	}
}