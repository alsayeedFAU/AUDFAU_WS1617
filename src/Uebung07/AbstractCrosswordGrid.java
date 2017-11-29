public abstract class AbstractCrosswordGrid {
	protected final char[][] grid;

	public AbstractCrosswordGrid(char[][] grid) {
		this.grid = grid;
	}

	/**
	 * This method MUST NOT modify the grid!
	 * 
	 * @return true, iff word can be placed in grid according to fieldSpec WITHOUT collision with existing entries.
	 */
	public abstract boolean isWordValid(FieldSpec fieldSpec, String word);

	/**
	 * Places word in grid according to fieldSpec and computes a mask of changed entries (i.e. chars of word) in grid.
	 * 
	 * @return The boolean mask of changed entries (i.e. chars of word actually/newly placed into grid).
	 */
	public abstract boolean[] setWord(FieldSpec fieldSpec, String word);

	/**
	 * Removes a word from the grid that was previously set with setWord.
	 * 
	 * @param charsPlaced
	 *            Result array of method setWord with the same fieldSpec, word, and grid parameters.
	 */
	public void removeWord(FieldSpec fieldSpec, String word, boolean[] charsPlaced) {
		if (fieldSpec.isHorizontal()) {
			for (int dx = 0; dx < word.length(); dx++) {
				if (charsPlaced[dx]) {
					grid[fieldSpec.y][fieldSpec.x + dx] = 0;
				}
			}
		} else {
			for (int dy = 0; dy < word.length(); dy++) {
				if (charsPlaced[dy]) {
					grid[fieldSpec.y + dy][fieldSpec.x] = 0;
				}
			}
		}
	}

	public char[][] getSolution() {
		return grid;
	}
}