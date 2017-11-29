/**
 * An object that maps 2D coordinates to values, and permits {@code null} values. <br />
 * A QuadTree cannot contain duplicate coordinates; each coordinate tuple ({@code x}, {@code y}) can map to at most one value. <br />
 * This class makes no guarantees as to the order of the map; in particular, it does not guarantee that the order will remain constant over time. <br />
 * Implementations of this class <b>must ensure best effort low memory consumption</b> by allocating containers (i.e. {@code data} and the four children) as late as possible (lazy) and freeing the container objects as early as possible (eager).
 * 
 * @author JohnDoe
 *
 * @param <V>
 *            the type of mapped values
 */
public abstract class AbstractQuadTree<V> {
	protected final long minX, minY, delta;
	protected final int capacity;
	protected java.util.ArrayList<Point2D<V>> data;
	protected AbstractQuadTree<V> northWest, northEast, southWest, southEast;

	/**
	 * 
	 * @param minX
	 *            the leftmost coordinate (inclusive) of the 2D area covered by this QuadTree
	 * @param minY
	 *            the lowermost coordinate (inclusive) of the 2D area covered by this QuadTree
	 * @param delta
	 *            the maximum range to the right of {@code minX} resp. above of {@code minY} of the 2D area covered by this QuadTree (inclusive)
	 * @param capacity
	 *            the maximum number (inclusive) of values stored in one leaf node - inserting values into the same node beyond its capacity will increase the depth of the QuadTree by splitting up the node into four equally big sub-areas represented by the four children
	 */
	protected AbstractQuadTree(long minX, long minY, long delta, int capacity) {
		this.minX = minX;
		this.minY = minY;
		this.delta = delta;
		this.capacity = capacity;
		if (delta <= 0 || capacity <= 0) {
			throw new IllegalArgumentException("Both delta and capacity must be positive.");
		}
		if (Long.MAX_VALUE - delta < minX) {
			delta = Long.MAX_VALUE - minX;
		}
		if (Long.MAX_VALUE - delta < minY) {
			delta = Long.MAX_VALUE - minY;
		}
		if (delta <= 0 || capacity <= 0) {
			throw new IllegalArgumentException("Both delta and capacity must be positive.");
		}
	}

	/**
	 * Associates the specified {@code value} with the specified position ({@code x}, {@code y}) in this QuadTree. If the QuadTree previously contained a value for the position, the old value is replaced and returned. <br />
	 * <b>Note</b> that implementations of this class <b>must ensure best effort low memory consumption</b> by allocating containers (i.e. {@code data} and the four children) as late as possible (lazy).
	 * 
	 * @param x
	 *            first coordinate of the position ({@code x}, {@code y}) of the {@code value}
	 * @param y
	 *            second coordinate of the position ({@code x}, {@code y}) of the {@code value}
	 * @param value
	 *            the {@code value} to be associated with its position at ({@code x}, {@code y})
	 * @return the previous value associated with position ({@code x}, {@code y}), or {@code null} if there was no value at position ({@code x}, {@code y}). (A {@code null} return can also indicate that the QuadTree previously associated {@code null} with ({@code x}, {@code y}).)
	 * @throws IndexOutOfBoundsException
	 *             if the position ({@code x}, {@code y}) does not belong to the 2D area covered by this QuadTree
	 */
	public abstract V insert(long x, long y, V value);

	/**
	 * Returns the value associated with position ({@code x}, {@code y}) if any.
	 * 
	 * @param x
	 *            first coordinate of the position ({@code x}, {@code y}) to be queried for a value
	 * @param y
	 *            second coordinate of the position ({@code x}, {@code y}) to be queried for a value
	 * @return the value associated with position ({@code x}, {@code y})
	 * @throws NoSuchElementException
	 *             if there is no value associated with position ({@code x}, {@code y})
	 */
	public abstract V get(long x, long y);

	/**
	 * Returns a list containing all values associated with points within {@code radius} (inclusive) around the center ({@code x}, {@code y}).
	 * 
	 * @param x
	 *            first coordinate of the center position ({@code x}, {@code y}) of the area (i.e. circle with center ({@code x}, {@code y}) and {@code radius}) to be queried for values
	 * @param y
	 *            second coordinate of the center position ({@code x}, {@code y}) of the area (i.e. circle with center ({@code x}, {@code y}) and {@code radius}) to be queried for values
	 * @param radius
	 *            radius of the area (i.e. circle with center ({@code x}, {@code y}) and {@code radius}) around the center position ({@code x}, {@code y}) to be queried for values (inclusive)
	 * @return a list view of the values associated with points within {@code radius} (inclusive) around the center ({@code x}, {@code y})
	 */
	public abstract java.util.LinkedList<V> get(long x, long y, long radius);

	/**
	 * Removes the element at the specified position ({@code x}, {@code y}) in this QuadTree. <br />
	 * <b>Note</b> that implementations of this class <b>must ensure best effort low memory consumption</b> by freeing the container objects (resetting {@code data} and the four children to {@code null}) as early as possible (eager), i.e. as soon as they become empty.
	 * 
	 * @param x
	 *            first coordinate of the position ({@code x}, {@code y}) to be queried for a value that is to be removed
	 * @param y
	 *            second coordinate of the position ({@code x}, {@code y}) to be queried for a value that is to be removed
	 * @return the value that was removed from the QuadTree, or {@code null} if there was no value at position ({@code x}, {@code y}). (A {@code null} return can also indicate that the QuadTree previously associated {@code null} with ({@code x}, {@code y}).)
	 */
	public abstract V remove(long x, long y);
}