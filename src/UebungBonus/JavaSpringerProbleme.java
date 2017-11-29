public class JavaSpringerProbleme {

	public static int[][] loese(int startSpalte, int startZeile, boolean[][] brett) {
		if (brett == null || brett.length < 2 || brettIllegal(brett)) {
			throw new IllegalArgumentException();
		}
		if (startSpalte < 0 || startZeile < 0 || startZeile > brett.length || startSpalte > brett[startZeile].length) {
			throw new IllegalArgumentException();
		}

		int[][] result = brettCreate(brett);
		if (helper(startSpalte, startZeile, result, 1)) {
			return result;
		}
		return null;

	}

	public static boolean helper(int startSpalte, int startZeile, int[][] brett, int counter) {
		if (brett[startZeile][startSpalte] < 0) {
			brett[startZeile][startSpalte] = counter;

			if (brettSolved(brett)) {
				return true;
			}

			int[][] zuege = turns(startSpalte, startZeile, brett);
			for (int[] z : zuege) {
				if (z != null) {
					if (helper(z[0], z[1], brett, counter + 1)) {
						return true;
					}
				}
			}
			brett[startZeile][startSpalte] = -1;
		}
		return false;

	}

	// fertig
	// prueft ob das Feld fertig ist(alle Felder mit einer Zahl belegt
	public static boolean brettSolved(int[][] brett) {
		for (int i = 0; i < brett.length; i++) {
			for (int j = 0; j < brett[i].length; j++) {
				if (brett[i][j] == -1) {
					return false;
				}
			}
		}
		return true;
	}

	// wandelt boolean Matrix in int Matrix um
	public static int[][] brettCreate(boolean[][] brett) {
		int[][] tmp = new int[brett.length][];
		for (int i = 0; i < brett.length; i++) {
			tmp[i] = new int[brett[i].length];
		}

		for (int i = 0; i < tmp.length; i++) {
			for (int j = 0; j < tmp[i].length; j++) {
				tmp[i][j] = brett[i][j] ? -1 : 0;
			}
		}
		return tmp;
	}

	// prueft ob das Feld gueltig ist
	public static boolean brettIllegal(boolean[][] brett) {
		for (boolean[] i : brett) {
			if (i == null || i.length == 0) {
				return true;
			}
		}
		return false;
	}

	// prueft gueltige Spielzuege
	public static int[][] turns(int spalte, int reihe, int[][] brett) {
		int[][] res = new int[8][];
		for (int i = 0; i < 8; i++) {
			switch (i) {
			case 0://
				if (reihe - 2 >= 0 && spalte - 1 >= 0 && brett[reihe - 1][spalte - 1] == -1) {
					int[] tmp = { spalte - 1, reihe - 2 };
					res[i] = tmp;
				}
				break;

			case 1://
				if (reihe - 2 >= 0 && spalte + 1 < brett[reihe - 2].length && brett[reihe - 2][spalte + 1] == -1) {
					int[] tmp = { spalte + 1, reihe - 2 };
					res[i] = tmp;
				}
				break;

			case 2:
				if (reihe - 1 >= 0 && spalte + 2 < brett[reihe - 1].length && brett[reihe - 1][spalte + 2] == -1) {
					int[] tmp = { spalte + 2, reihe - 1 };
					res[i] = tmp;
				}
				break;
			case 3:
				if (reihe + 1 < brett.length && spalte + 2 < brett[reihe + 1].length
						&& brett[reihe + 1][spalte + 2] == -1) {
					int[] tmp = { spalte + 2, reihe + 1 };
					res[i] = tmp;
				}
				break;
			case 4://
				if (reihe + 2 < brett.length && spalte + 1 < brett[reihe + 2].length
						&& brett[reihe + 2][spalte + 1] == -1) {
					int[] tmp = { spalte + 1, reihe + 2 };
					res[i] = tmp;
				}

				break;
			case 5://
				if (reihe + 2 < brett.length && spalte - 1 >= 0 && brett[reihe + 2][spalte - 1] == -1) {
					int[] tmp = { spalte - 1, reihe + 2 };
					res[i] = tmp;
				}
				break;
			case 6:
				if (reihe + 1 < brett.length && spalte - 2 >= 0 && brett[reihe + 1][spalte - 2] == -1) {
					int[] tmp = { spalte - 2, reihe + 1 };
					res[i] = tmp;
				}
				break;
			case 7:
				if (reihe - 1 >= 0 && spalte - 2 >= 0 && brett[reihe - 1][spalte - 2] == -1) {
					int[] tmp = { spalte - 2, reihe - 1 };
					res[i] = tmp;
				}
				break;
			default:
				res[i] = null;
			}
		}
		return res;
	}
}
