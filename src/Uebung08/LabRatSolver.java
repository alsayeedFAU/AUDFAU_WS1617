public abstract class LabRatSolver {

	// Don't delete!! Tests will fail otherwise
	public LabRatSolver() {
	}

	/**
	 * Simulates rat movements through the maze from start to end point.
	 * 
	 * @param labRat The rat object
	 * @return The end point
	 */
	public static Point solve(LabRat labRat) {
		// TODO
		return null;
	}

	/**
	 * Simulates the shortest way through the maze from start to end point.
	 * 
	 * @param labRat The rat object
	 * @param hops The shortest amount of hops through the maze
	 * @param r Ignore this, only for test purposes!
	 * @return Shortest amount of hops through the maze
	 */
	public static int solveShortestPath(LabRat labRat, int hops, LabRatSolver r) {
		// Don't delete!! Tests will fail otherwise
		// Pass object r to other function calls of solveShortestPath (e.g. for recursion). Do not create other instances of object r
		// Function solveShortestPath(...) must be recursive, don't implement other recursive helper functions
		r.check();

		// TODO
		return -1;
	}

	// Don't delete!! Tests will fail otherwise
	public abstract void check();
}
