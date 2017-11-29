public abstract class AbstractCrosswordSpec {
	private FieldSpec[] fieldsSpec;
	private String[] wordsSpec;

	public final int getFieldsSpecLength() {
		return fieldsSpec.length;
	}

	public final int getWordsSpecLength() {
		return wordsSpec.length;
	}

	public final FieldSpec getFieldsSpec(int i) {
		return fieldsSpec[i];
	}

	public final String getWordsSpec(int i) {
		return wordsSpec[i];
	}

	// =========================================================================

	/**
	 * Check 1: gridSpec must have at least one empty field.
	 */
	public abstract void check1_gridSpecHasAtLeastOneEmptyField();

	/**
	 * Check 2: Number of empty fields (gridSpec) must be the same as the number of provided words (wordsSpec).
	 */
	public abstract void check2_numberOfEmptyFieldsEqualsNumberOfWords();

	/**
	 * Checks 3-5: gridSpec must be sound (3:coordinates, 4:length, 5:orientation).
	 */
	public abstract void check345_checkGridSpec();

	/**
	 * Check 6: Horizontal or vertical overlaps are not allowed.
	 */
	public abstract void check6_horizontalOrVerticalOverlaps();

	/**
	 * Check 7: Provided words have at least one char.
	 */
	public abstract void check7_wordsHaveAtLeastOneChar();

	/**
	 * Check 8: Required and provided word lengths match.
	 */
	public abstract void check8_requiredAndProvidedWordLengths();

	/**
	 * Get the required height of the final solution grid.
	 */
	public abstract int getGridHeight();

	/**
	 * Get the required width of the final solution grid.
	 */
	public abstract int getGridWidth();

	// =========================================================================

	public static final CrosswordSpec instantiate(int[][] gridSpec, String[] wordsSpec) {
		AbstractCrosswordSpec instance = new CrosswordSpec();
		instance.fieldsSpec = new FieldSpec[gridSpec.length];
		for (int i = 0; i < gridSpec.length; ++i) {
			instance.fieldsSpec[i] = new FieldSpec(gridSpec[i]);
		}
		instance.wordsSpec = java.util.Arrays.copyOf(wordsSpec, wordsSpec.length);
		instance.check();
		return (CrosswordSpec) instance;
	}

	private void check() {
		check1_gridSpecHasAtLeastOneEmptyField();
		check2_numberOfEmptyFieldsEqualsNumberOfWords();
		check345_checkGridSpec();
		check6_horizontalOrVerticalOverlaps();
		check7_wordsHaveAtLeastOneChar();
		check8_requiredAndProvidedWordLengths();
	}
}