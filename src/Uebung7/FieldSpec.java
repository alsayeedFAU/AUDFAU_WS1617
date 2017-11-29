public final class FieldSpec {
	public final int y;
	public final int x;
	public final int length;
	public final int orientation;

	public FieldSpec(int y, int x, int length, int orientation) {
		this.y = y;
		this.x = x;
		this.length = length;
		this.orientation = orientation;
	}

	public FieldSpec(int[] fieldSpec) {
		this(fieldSpec[0], fieldSpec[1], fieldSpec[2], fieldSpec[3]);
	}

	public final boolean isHorizontal() {
		return orientation == 0;
	}

	public final boolean isVertical() {
		return orientation == 1;
	}
}