package AUD;

public class sdf {

	static int test(GCD gcd, int n) {
		int a;
		int i = 1;
		int counter = 0;
		while (counter < n) {
			a = rowlandNaive(gcd, i) - rowlandNaive(gcd, i - 1);
			if (a > 1) {
				counter++;
			}
			i++;
		}
		return a;
	}
}
