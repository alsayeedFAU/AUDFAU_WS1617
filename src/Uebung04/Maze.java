public class Maze {

	// public constants for directions
	public static final int NORTH = 0;
	public static final int EAST = 1;
	public static final int SOUTH = 2;
	public static final int WEST = 3;

	// array containing all directions
	protected static final int[] DIRECTIONS = new int[] { NORTH, EAST, SOUTH, WEST };
	// 3d-boolean array containing the actual maze
	protected boolean[][][] maze;
	// 2d-boolean array containing marked dead ends
	protected boolean[][] deadEnds;

	public Maze(Maze maze) {
		this.maze = maze.maze.clone();
		this.deadEnds = new boolean[getHeight()][getWidth()];
	}

	public Maze(boolean[][][] maze) {
		this.maze = maze;
		this.deadEnds = new boolean[getHeight()][getWidth()];
	}

	/**
	 * @return maze width as integer
	 */
	public int getWidth() {
		return maze[0].length;
	}

	/**
	 * @return maze height as integer
	 */
	public int getHeight() {
		return maze.length;
	}

	/**
	 * @param y y-coordinate
	 * @param x x-coordinate
	 * @return Boolean if position (x,y) inside maze is dead end (true) or not (false)
	 */
	public boolean isDeadEnd(int y, int x) {
		// TODO
		return false;
	}

	/**
	 * @return First dead end that was found when iterating the maze (int[0] = y, int[1] = x)
	 */
	public int[] seekDeadEnd() {
		// TODO
		return null;
	}

	/**
	 * @return 2d-boolean array containing all marked dead ends (see attribute deadEnds)
	 */
	public boolean[][] solveMaze() {
		// TODO
		return null;
	}

	/**
	 * @return Minimal amount of steps through the maze (from entry to exit)
	 */
	public int getSteps() {
		// TODO
		return -1;
	}
}