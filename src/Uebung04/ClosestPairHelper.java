/*
 * Nomenclature:
 * 	- p:	a point P(x, y).
 *			Data type: double[] p = {x, y};
 *  - pd:	a point P(x, y) and a distance d to another point.
 *			Data type: double[] pd = {x, y, d};
 *  - pp:	a pair of points P1(x1, y1) and P2(x2, y2).
 *			Data type: double[] pp = {x1, y1, x2, y2};
 *  - ppd:	a pair of points P1(x1, y1), P2(x2, y2), and a distance d between those points.
 *			Data type: double[] ppd = {x1, y1, x2, y2, d};
 *  - ps:	a list of points P1...Pn.
 *			Data type: double[][] ps = {{x1, y1}, {x2, y2}, ..., {xn, yn}};
 */
public interface ClosestPairHelper {
	/**
	 * Constant value that represents the 'no result' value of a ppd.
	 */
	public static final double[] PPD_NO_RESULT = { Double.NaN, Double.NaN, Double.NaN, Double.NaN, Double.NaN };

	/**
	 * I AM THE MOST IMPORTANT! I MUST CONTRIBUTE TO CHECK YOUR RECURSION!
	 * 
	 * YOU MUST CALL ME EXACTLY ONCE IMMEDIATELY AT THE BEGINNING OF EACH OF YOUR METHODS!
	 */
	public void traceMe();

	/**
	 * Compute the euclidian distance between two points P1 and P2.
	 * 
	 * @param p1
	 *            First point.
	 * @param p2
	 *            Second point.
	 * @return Euclidian distance.
	 */
	public double distance(double[] p1, double[] p2);

	/**
	 * Append the coordinates of a point to an array.
	 * 
	 * @param array
	 *            An arbitrary double array {a1, a2, ..., an} with length n.
	 * @param p
	 *            A point P(x, y).
	 * @return A double array with length n + 2: {a1, a2, ..., an, x, y}.
	 */
	public double[] appendPoint(double[] array, double[] p);

	/**
	 * Append a distance to an array.
	 * 
	 * @param array
	 *            An arbitrary double array {a1, a2, ..., an} with length n.
	 * @param d
	 *            A distance.
	 * @return A double array with length n + 1: {a1, a2, ..., an, d}.
	 */
	public double[] appendDistance(double[] array, double d);

	/**
	 * Skips the first point in a point list.
	 * 
	 * @param ps
	 *            Arbitrary point list {x1, y1, x2, y2, ..., xn, yn}.
	 * @return A new point list: {x2, y2, ..., xn, yn}.
	 */
	public double[][] skipFirstPoint(double[][] ps);
}