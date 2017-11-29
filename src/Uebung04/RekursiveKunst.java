public class RekursiveKunst {
	public static int branches;
	public static double openAngle;
	public static double lenFactor;
	public static double angleRandomness;
	public static double lenRandomness;

	public static Canvas canvas;
	public static java.util.Random angleRNG = new java.util.Random(23);
	public static java.util.Random lenRNG = new java.util.Random(4711);

	// computes a new angle derivation
	public static double getNextRandomAngle() {
		double ret = angleRNG.nextDouble();
		return ret * angleRandomness - angleRandomness / 2.0;
	}

	// computes a new len derivation
	public static double getNextRandomLen() {
		double ret = lenRNG.nextDouble();
		return ret * lenRandomness - lenRandomness / 2.0;
	}

	// computes new value for x based on old value for x, the len of the line
	// and an angle rounds towards zero
	public static int getNewX(int oldX, double len, double angle) {
		// TODO
		double tmp = (Math.sin(angle) * len) + oldX;
		return (int) tmp;
	}

	// computes new value for y based on old value for y, the len of the line
	// and an angle rounds towards zero
	public static long nextY(long y, long d) {
		if (d == 0) {
			return y + 1L;
		}
		if (d == 1 || d == 3) {
			return y;
		} else {
			return y - 1L;
		}
	}
	// computes new len of line based on
	// - the old line length,
	// - the lenFactor, and
	// - a random factor from getNextRandomLen (only necessary for subtask f)
	public static double getNewLen(double oldLen) {
		// TODO
		return (oldLen * (lenFactor + getNextRandomLen()));
	}

	// computes new angle of line based on
	// - the startAngle (leftmost angle),
	// - the angleDiff,
	// - the branch number (from left to right), and
	// - a random factor from getNextRandomAngle (only necessary for subtask f)
	public static double getNewAngle(double startAngle, double angleDiff, int branch) {
		// TODO
		return (startAngle + angleDiff * branch) + getNextRandomAngle();
	}

	// the real recursion:
	// draw line from (x,y) with len/angle if necessary
	// and recurse with further drawings
	public static void draw(int x, int y, double len, double angle, int steps) {
		// TODO
		if (steps == 0) {

		} else {
			int newX = getNewX(x, len, angle);
			int newY = getNewY(y, len, angle);
			canvas.drawLine(x, y, newX, newY, steps);

			double startAngle = angle - (openAngle / 2);
			double angleDiff = openAngle / (branches - 1);
			for (int i = 0; i < branches; i++) {
				double newLen = getNewLen(len);
				double newAngle = getNewAngle(startAngle, angleDiff, i);
				draw(newX, newY, newLen, newAngle, steps - 1);

			}

		}
	}
}
