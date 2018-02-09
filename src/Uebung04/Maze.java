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
	 * @param y
	 *            y-coordinate
	 * @param x
	 *            x-coordinate
	 * @return Boolean if position (x,y) inside maze is dead end (true) or not
	 *         (false)
	 */
	public boolean isDeadEnd(int y, int x) {
		int tmp = 0;
		if (deadEnds[x][y] == false) {
			if (maze[x][y][NORTH] == false) {
				tmp += 1;
			} else if (x > 0) {
				if (deadEnds[x - 1][y] == true)
					tmp += 1;}
			if (maze[x][y][SOUTH] == false) {
				tmp += 1;
			} else if (x < (getHeight() - 1)) {
				if (deadEnds[x + 1][y] == true)
					tmp += 1;}
			if (maze[x][y][WEST] == false) {
				tmp += 1;
			} else if (y > 0) {
				if (deadEnds[x][y - 1] == true)
					tmp += 1;}
			if (maze[x][y][EAST] == false) {
				tmp += 1;
			} else if (y < (getWidth() - 1)) {
				if (deadEnds[x][y + 1] == true)
					tmp += 1;
			}
			if (tmp >= 3) {
				return true;
			} else {
				return false;
			}
		} else
			return false;
	}
	/** @return First dead end that was found when iterating the maze (int[0] =
	 *         y, int[1] = x)*/
	public int[] seekDeadEnd() {
		int x = 0;
		int y = 0;
		while (isDeadEnd(y, x) == false) {
			if (y == (getWidth() - 1) && x == getHeight()-1) {
				return null;
			}
			if (y == getWidth() - 1) {
				x += 1;
				y = -1;
			}
			y++;
		}
		int tmp[] = { x, y };
		return tmp;
	}

	/**
	 * @return 2d-boolean array containing all marked dead ends (see attribute
	 *         deadEnds)
	 */
	public boolean[][] solveMaze() {
		// TODO
		if (seekDeadEnd()==null){
			return deadEnds;
		}
		int tmp[] = seekDeadEnd();
		deadEnds[tmp[0]][tmp[1]] = true;
		return solveMaze();
	}

	/**
	 * @return Minimal amount of steps through the maze (from entry to exit)
	 */
	public int getSteps() {
		boolean test[][] = solveMaze();
		int tmp = 0;
		for(int x=0;x<getHeight();x++){
			for (int y=0;y<getWidth();y++){
				if (test[x][y]==false){
					tmp +=1;
				}
			}
		}	
		return tmp;
	}
}