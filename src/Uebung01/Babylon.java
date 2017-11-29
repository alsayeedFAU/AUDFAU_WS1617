
public class Babylon {

	static double wurzel(double a, int k, double eps) {
		double x0, x1, tmp;

		if (k < 0 || (a < 0 && k % 2 == 0)) {
			return -1;
		}

		if (a < 0) {
			x0 = -2;
		} else {
			x0 = 2;
		}

		tmp = 1;
		for (int i = 0; i < k; i++) {
			tmp *= x0;
		}

		x1 = ((k - 1) * tmp + a) / (k * (tmp / x0));

		while ((x1 - x0 > 0 ? x1 - x0 : (x1 - x0) * -1) >= eps) {
			x0 = x1;
			tmp = 1;
			for (int i = 0; i < k; i++) {
				tmp *= x0;
			}

			x1 = ((k - 1) * tmp + a) / (k * (tmp / x0));
		}

		return x0;

	}
}
