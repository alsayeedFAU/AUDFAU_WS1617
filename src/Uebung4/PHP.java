public class PHP extends KnotPoint {
	private static String path = "";

	// returns the x-coordinate of the field next to the current field with
	// position x in direction d
	public static long nextX(long x, long d) {
		// TODO
		if (d == 1) {
			return x + 1;
		}
		if (d == 3) {
			return x - 1;
		}

		return x;
	}

	// returns the y-coordinate of the field next to the current field with
	// position y in direction d
	public static long nextY(long y, long d) {
		// TODO
		if (d == 0) {
			return y + 1;
		}
		if (d == 2) {
			return y - 1;
		}
		return y;
	}

	// returns the new direction after turning clockwise 90 degrees from the
	// current position d
	public static long nextD(long d) {
		// TODO
		return (d + 1) % 4;
	}

	// marks the current field at position (x,y) as hoovered
	// a subsequent call to isHoovered must return true for this field
	public static void markHoovered(long x, long y) {
		// TODO
		path += "(" + x + "|" + y + ")";
	}

	// returns true iff the current field at position (x,y) has been marked as
	// hoovered
	// in a preceding call to markHoovered for this field
	public static boolean isHoovered(long x, long y) {
		// TODO
		return path.contains("(" + x + "|" + y + ")");
	}

	// hoovering starts here (resp. in the battery-charging station ;)...)
	public static void hoover() {
		// TODO
		int direction = 0;
		path = "";
		long moveCounter = battery() / 2;
		for (direction = 0; direction < 4; direction++) {
			if (!hasObstacle() && !isHoovered(nextX(0, direction), nextY(0, direction))) {
				hooverHlp(0, 0, direction, moveCounter);
			}
		}
	}

	private static void hooverHlp(long x, long y, long direction, long moveCounter) {
		if (moveCounter > 0 || !binFull()) {
			for (int i = 0; i < 4; i++) {
				if (!hasObstacle() && !isHoovered(nextX(x, direction), nextY(y, direction)) && !binFull() && moveCounter > 0 && !isHoovered(nextX(x, direction), nextY(y, direction))) {
					markHoovered(nextX(x, direction), nextY(y, direction));
					move();
					moveCounter--;
					hooverHlp(nextX(x, direction), nextY(y, direction), direction, moveCounter);
				}
				turnClockwise();
			}
			turnClockwise();
		}
		moveBack();
	}

}