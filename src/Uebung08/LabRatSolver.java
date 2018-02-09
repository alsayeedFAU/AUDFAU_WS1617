public abstract class LabRatSolver {

	// Don't delete!! Tests will fail otherwise
	public LabRatSolver() {
	}

	/**
	 * Simulates rat movements through the maze from start to end point.
	 * 
	 * @param labRat
	 *            The rat object
	 * @return The end point
	 */
	public static Point solve(LabRat labRat) {
		// TODO
		return null;
	}

	/**
	 * Simulates the shortest way through the maze from start to end point.
	 * 
	 * @param labRat
	 *            The rat object
	 * @param hops
	 *            The shortest amount of hops through the maze
	 * @param r
	 *            Ignore this, only for test purposes!
	 * @return Shortest amount of hops through the maze
	 */
	public static int solveShortestPath(LabRat labRat, int hops, LabRatSolver r) {
		// Don't delete!! Tests will fail otherwise
		// Pass object r to other function calls of solveShortestPath (e.g. for
		// recursion). Do not create other instances of object r
		// Function solveShortestPath(...) must be recursive, don't implement
		// other recursive helper functions
		r.check();

		// TODO
		if (labRat.isAtEndPosition()) {
			return hops;
		}

		int spruenge = -1;
		if (hops == 0) {
			for (int i = 0; i <= 3; i++) {
				if (i == 1) {
					labRat.turnRight();
				}
				if (i == 2) {
					labRat.turnLeft();
				}
				if (i == 3) {
					labRat.turnRight();
					labRat.turnRight();
				}
				if (!labRat.facingWall()) {
					labRat.stepForward();
					int tmp = solveShortestPath(labRat, hops + 1, r);
					if (spruenge == -1 || spruenge > tmp) {
						spruenge = tmp;
					}
					backStep(labRat);
				}
				if (i == 1) {
					labRat.turnLeft();
				} else if (i == 2) {
					labRat.turnRight();
				}

			}

		} else {
			for (int i = 0; i <= 2; i++) {
				if (i == 1) {
					labRat.turnRight();
				}
				if (i == 2) {
					labRat.turnLeft();
				}
				if (i == 3) {
					labRat.turnRight();
					labRat.turnRight();
				}
				if (!labRat.facingWall()) {
					labRat.stepForward();
					int tmp = solveShortestPath(labRat, hops + 1, r);
					if (spruenge == -1 || spruenge > tmp) {
						spruenge = tmp;
					}
					backStep(labRat);
				}
				if (i == 1) {
					labRat.turnLeft();
				} else if (i == 2) {
					labRat.turnRight();
				} else if (i == 3) {
					labRat.turnLeft();
					labRat.turnLeft();
				}
			}
		}

		return spruenge;
	}

	public static void backStep(LabRat labRat) {
		labRat.turnLeft();
		labRat.turnLeft();
		labRat.stepForward();
		labRat.turnRight();
		labRat.turnRight();
	}

	// Don't delete!! Tests will fail otherwise
	public abstract void check();
}
