public class CrosswordGrid extends AbstractCrosswordGrid {

	public CrosswordGrid(char[][] grid) {
		super(grid);
	}

	public CrosswordGrid(CrosswordSpec spec) {
		super(new char[spec.getGridHeight()][spec.getGridWidth()]);
	}

	@Override
	public boolean isWordValid(FieldSpec fieldSpec, String word) {
		if (fieldSpec.length == word.length()) {
			if (fieldSpec.isVertical()) {
				for (int i = fieldSpec.y; i < fieldSpec.y + fieldSpec.length; i++) {
					if (grid[i][fieldSpec.x] != 0 && grid[i][fieldSpec.x] != word.charAt(i - fieldSpec.y)) {
						return false;
					}
				}
				return true;
			} else if (fieldSpec.isHorizontal()) {
				for (int i = fieldSpec.x; i < fieldSpec.x + fieldSpec.length; i++) {
					if (grid[fieldSpec.y][i] != 0 && grid[fieldSpec.y][i] != word.charAt(i - fieldSpec.x)) {
						return false;
					}
				}
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

	@Override
	public boolean[] setWord(FieldSpec fieldSpec, String word) {
		boolean[] tmp = new boolean[word.length()];

		if (fieldSpec.isVertical()) {
			for (int i = fieldSpec.y; i < fieldSpec.y + fieldSpec.length; i++) {
				if (grid[i][fieldSpec.x] == 0) {
					grid[i][fieldSpec.x] = word.charAt((i - fieldSpec.y));
					tmp[i - fieldSpec.y] = true;
				} else {
					tmp[i - fieldSpec.y] = false;
				}
			}
			return tmp;
		} else if (fieldSpec.isHorizontal()) {
			for (int i = fieldSpec.x; i < fieldSpec.x + fieldSpec.length; i++) {
				if (grid[fieldSpec.y][i] == 0) {
					grid[fieldSpec.y][i] = word.charAt((i - fieldSpec.x));
					tmp[i - fieldSpec.x] = true;
				} else {
					tmp[i - fieldSpec.x] = false;
				}
			}
			return tmp;
		}
		return null;
	}

}
