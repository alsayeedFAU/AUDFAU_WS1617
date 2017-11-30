public class LabRat {

	// lab rat directions
	protected final static int NORTH = 0;
	protected final static int EAST = 1;
	protected final static int SOUTH = 2;
	protected final static int WEST = 3;

	private Point currentPosition;
	private int currentDirection;
	private Lab lab;

	public LabRat(Lab lab) {
		if (lab == null)
			throw new IllegalArgumentException("Lab object is null");

		this.lab = lab;
		this.currentDirection = NORTH;
		this.currentPosition = new Point(lab.getStartPosition());
	}

	public LabRat(LabRat labRat) {
		if (labRat == null)
			throw new IllegalArgumentException("LabRat object is null");

		this.lab = labRat.lab;
		this.currentDirection = labRat.currentDirection;
		this.currentPosition = new Point(labRat.currentPosition);
	}

	public Point getCurrentPosition() {
		return currentPosition;
	}

	public int getCurrentDirection() {
		return currentDirection;
	}

	public void stepForward() {
		//TODO
	}

	public boolean facingWall() {
		//TODO
		return false;
	}

	public boolean isAtEndPosition() {
		//TODO
		return false;
	}

	public boolean isAtStartPosition() {
		//TODO
		return false;
	}

	public void turnRight() {
		//TODO
	}

	public void turnLeft() {
		//TODO
	}
}
