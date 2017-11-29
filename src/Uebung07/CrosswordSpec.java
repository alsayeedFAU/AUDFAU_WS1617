public class CrosswordSpec extends AbstractCrosswordSpec {

	@Override
	public void check1_gridSpecHasAtLeastOneEmptyField() {
		if (super.getFieldsSpecLength() <= 0) {
			throw new IllegalCrosswordSpecException(1);
		}
	}

	@Override
	public void check2_numberOfEmptyFieldsEqualsNumberOfWords() {
		if (super.getFieldsSpecLength() != super.getWordsSpecLength()) {
			throw new IllegalCrosswordSpecException(2);
		}
	}

	@Override
	public void check345_checkGridSpec() {
		for (int i = 0; i < super.getFieldsSpecLength(); i++) {
			if (super.getFieldsSpec(i).x < 0 || super.getFieldsSpec(i).y < 0) {
				throw new IllegalCrosswordSpecException(3);
			}
			if (super.getFieldsSpec(i).length <= 0) {
				throw new IllegalCrosswordSpecException(4);
			}

			if (!super.getFieldsSpec(i).isHorizontal() && !super.getFieldsSpec(i).isVertical()) {
				throw new IllegalCrosswordSpecException(5);
			}

		}
	}

	@Override
	public void check6_horizontalOrVerticalOverlaps() {
		for (int i = 0; i < super.getFieldsSpecLength(); i++) {
			if (super.getFieldsSpec(i).isHorizontal()) {
				for (int j = 0; j < super.getFieldsSpecLength(); j++) {
					if (super.getFieldsSpec(j).isHorizontal() && super.getFieldsSpec(i).y == super.getFieldsSpec(j).y
							&& super.getFieldsSpec(i).x > super.getFieldsSpec(j).x) {
						if (super.getFieldsSpec(j).x + super.getFieldsSpec(j).length - 1 >= super.getFieldsSpec(i).x) {
							throw new IllegalCrosswordSpecException(6);
						}
					}
				}
			}
		}

		for (int i = 0; i < super.getFieldsSpecLength(); i++) {
			if (super.getFieldsSpec(i).isVertical()) {
				for (int j = 0; j < super.getFieldsSpecLength(); j++) {
					if (super.getFieldsSpec(j).isVertical() && super.getFieldsSpec(i).x == super.getFieldsSpec(j).x
							&& super.getFieldsSpec(i).y > super.getFieldsSpec(j).y) {
						if (super.getFieldsSpec(j).y + super.getFieldsSpec(j).length - 1 >= super.getFieldsSpec(i).y) {
							throw new IllegalCrosswordSpecException(6);
						}
					}
				}
			}
		}

	}

	@Override
	public void check7_wordsHaveAtLeastOneChar() {
		for (int i = 0; i < super.getWordsSpecLength(); i++) {
			if (super.getWordsSpec(i).length() < 1) {
				throw new IllegalCrosswordSpecException(7);
			}
		}

	}

	@Override
	public void check8_requiredAndProvidedWordLengths() {
		int[] word = new int[super.getWordsSpecLength()];
		int longestWord = 0;
		for (int i = 0; i < word.length; i++) {
			word[i] = super.getWordsSpec(i).length();
			if (word[i] > longestWord) {
				longestWord = word[i];
			}
		}

		int[] wordLengthCount = new int[longestWord];
		for (int i = 0; i < word.length; i++) {
			wordLengthCount[word[i] - 1]++;
		}

		int[] field = new int[super.getFieldsSpecLength()];
		int longestfield = 0;
		for (int i = 0; i < field.length; i++) {
			field[i] = super.getFieldsSpec(i).length;
			if (field[i] > longestfield) {
				longestfield = field[i];
			}
		}

		int[] fieldLengthCount = new int[longestfield];
		for (int i = 0; i < field.length; i++) {
			fieldLengthCount[field[i] - 1]++;
		}

		if (wordLengthCount.length != fieldLengthCount.length) {
			throw new IllegalCrosswordSpecException(8);
		}
		for (int i = 0; i < wordLengthCount.length; i++) {
			if (wordLengthCount[i] != fieldLengthCount[i]) {
				throw new IllegalCrosswordSpecException(8);
			}
		}

	}

	@Override
	public int getGridHeight() {
		int ytmp = 0;
		for (int i = 0; i < super.getFieldsSpecLength(); i++) {
			if(getFieldsSpec(i).isVertical() && getFieldsSpec(i).y + getFieldsSpec(i).length > ytmp){
				ytmp =  getFieldsSpec(i).y + getFieldsSpec(i).length;
			}
		}
		return ytmp;
	}

	@Override
	public int getGridWidth() {
		int xtmp = 0;
		for (int i = 0; i < super.getFieldsSpecLength(); i++) {
			if(getFieldsSpec(i).isHorizontal() && getFieldsSpec(i).x + getFieldsSpec(i).length > xtmp){
				xtmp =  getFieldsSpec(i).x + getFieldsSpec(i).length;
			}
		}
		return xtmp;
	}
	

}
