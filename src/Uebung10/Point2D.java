public class Point2D<V> {
	private final long x;
	private final long y;
	private final V value;

	public Point2D(long x, long y, V value) {
		this.x = x;
		this.y = y;
		this.value = value;
	}

	public final long getX() {
		return x;
	}

	public final long getY() {
		return y;
	}

	public final V getValue() {
		return value;
	}

	@Override
	public final String toString() {
		return "P(" + x + "," + y + ": {" + value + "})";
	}
}