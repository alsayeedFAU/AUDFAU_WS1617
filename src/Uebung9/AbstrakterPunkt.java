public abstract class AbstrakterPunkt {
	private final int x;
	private final int y;

	public AbstrakterPunkt(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public final int getX() {
		return x;
	}

	public final int getY() {
		return y;
	}

	/**
	 * Computes the Euclidean distance (see https://en.wikipedia.org/wiki/Euclidean_distance) between {@code this} and the argument {@code that}.
	 * 
	 * @param that
	 * @return the Euclidean distance between {@code this} and {@code that} if {@code that} is not null, {@code Double.NaN} otherwise.
	 */
	public abstract double euklid(Punkt that);

	/**
	 * Computes the Manhattan distance (see https://en.wikipedia.org/wiki/Manhattan_distance) between {@code this} and the argument {@code that}.
	 * 
	 * @param that
	 * @return the Manhattan distance between {@code this} and {@code that}.
	 * @throws NullPointerException
	 *             if {@code that} is null
	 */
	public abstract int manhattan(Punkt that);

	/**
	 * Indicates whether {@code this} denotes the same point wrt. to the coordinates (x, y) as {@code that}.
	 * 
	 * @return {@code true} if {@code that} is non-null and an instance of exactly the same class and its coordinates (x, y) are the same as those of {@code this}; {@code false} otherwise.
	 */
	@Override
	public abstract boolean equals(Object that);

	@Override
	public final String toString() {
		return "P(" + x + "," + y + ")";
	}
}