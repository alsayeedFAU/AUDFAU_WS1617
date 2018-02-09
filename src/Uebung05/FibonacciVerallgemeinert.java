public class FibonacciVerallgemeinert {
	public static double fibonacciVerallgemeinert(double a, double b, int c, int n) {
		double tmp = 0d;
		if (n >= 0) {
			if (n < c) {
				tmp += n;
				return tmp;
			} else if (n % 2 == 0) {
				tmp += a * fibonacciVerallgemeinert(a, b, c, n - 1) + fibonacciVerallgemeinert(a, b, c, n - c);
			} else {
				tmp += b * fibonacciVerallgemeinert(a, b, c, n - 1) + fibonacciVerallgemeinert(a, b, c, n - c);
			}
		}
		return tmp;
	}
}