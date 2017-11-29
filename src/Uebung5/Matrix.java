import java.util.Arrays;

public class Matrix {
	long[][] matrix;

	public Matrix(int m) {
		if (m < 0) {
			matrix = new long[m * -1][m * -1];
		} else {
			matrix = new long[m][m];
		}
	}

	public Matrix(int m, int n) {
		if (m == 0) {
			matrix = new long[0][0];
		} else {

			if (m < 0) {
				if (n < 0) {
					matrix = new long[m * -1][n * -1];
				}
				matrix = new long[m * -1][n];
			} else {
				if (n < 0) {
					matrix = new long[m][n * -1];
				} else {
					matrix = new long[m][n];
				}
			}
		}
	}

	public Matrix(Matrix andere) {
		if (andere == null) {
			matrix = new long[0][0];
		} else {
			matrix = new long[andere.anzahlZeilen()][andere.anzahlSpalten()];
			for (int i = 0; i < matrix.length; i++) {
				for (int j = 0; j < matrix[0].length; j++) {
					setzeWert(i, j, andere.holeWert(i, j));
				}
			}
			// matrix = new long[andere.anzahlZeilen()][andere.anzahlSpalten()];

		}

	}

	public int anzahlZeilen() {
		if (matrix == null) {
			return 0;
		}
		return matrix.length;
	}

	public int anzahlSpalten() {
		if (matrix == null || matrix.length == 0) {
			return 0;
		}
		return matrix[0].length;
	}

	public void setzeWert(int i, int j, long l) {
		if (i >= 0 && j >= 0 && i <= matrix.length && j <= matrix[0].length) {
			matrix[i][j] = l;
		}

	}

	public long holeWert(int i, int j) {
		if (i >= 0 && j >= 0 && i < matrix.length && j < matrix[0].length) {
			return matrix[i][j];
		}
		return 0;
	}

	public static Matrix addiere(Matrix a, Matrix b) {
		if (a.matrix.length == b.matrix.length && a.matrix[0].length == b.matrix[0].length) {
			Matrix tmp = new Matrix(a);
			for (int i = 0; i < a.matrix.length; i++) {
				for (int j = 0; j < a.matrix[0].length; j++) {
					tmp.setzeWert(i, j, (a.holeWert(i, j) + b.holeWert(i, j)));
				}
			}
			return tmp;
		}
		return null;
	}

	public static Matrix multipliziere(Matrix a, Matrix b) {
		// TODO Auto-generated method stub
		long x = 0;
		if (a.anzahlSpalten() != b.anzahlZeilen() && a.anzahlZeilen() != b.anzahlSpalten()) {
			return null;
		}else {
			Matrix tmp = new Matrix(a.anzahlZeilen(), b.anzahlSpalten());
			for (int i = 0; i < a.anzahlZeilen(); i++) {
				for (int j = 0; j < b.anzahlSpalten(); j++) {
					for (int k = 0; k < a.anzahlSpalten(); k++) {
						x += (tmp.holeWert(i, j) + (a.holeWert(i, k) * b.holeWert(k, j)));
					}
					tmp.setzeWert(i, j, x);
					x = 0;
				}
			}
			return tmp;
		}

	}

	public void multipliziere(long l) {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				setzeWert(i, j, holeWert(i, j) * l);
			}
		}
	}

	public Matrix transponiere() {
		Matrix tmp = new Matrix(anzahlSpalten(), anzahlZeilen());
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				tmp.setzeWert(j, i, holeWert(i, j));
			}
		}
		return tmp;
	}

	public Matrix unterMatrix(int i, int j) {
		if (i > anzahlZeilen() && j > anzahlSpalten()) {
			return this;
		}
		if (i < anzahlZeilen() && j > anzahlSpalten()) {
			Matrix tmp = new Matrix(anzahlZeilen() - 1, anzahlSpalten());
			for (int x = 0; x < i; i++) {
				tmp.matrix[i] = this.matrix[i];
			}

			for (int x = i + 1; x < anzahlZeilen(); x++) {
				tmp.matrix[x - 1] = this.matrix[x];
			}
			return tmp;
		} else if (i > anzahlZeilen() && j < anzahlSpalten()) {
			Matrix tmp = new Matrix(anzahlZeilen(), anzahlSpalten() - 1);
			for (int x = 0; x < anzahlZeilen(); x++) {
				for (int y = 0; y < j; y++) {
					tmp.setzeWert(x, y, this.holeWert(x, y));
				}
				for (int y = j + 1; y < anzahlSpalten(); y++) {
					tmp.setzeWert(x, y - 1, this.holeWert(x, y));
				}
			}
			return tmp;

		} else {
			Matrix tmp = new Matrix(anzahlZeilen() - 1, anzahlSpalten() - 1);
			for (int x = 0; x < i; x++) {
				for (int y = 0; y < j; y++) {
					tmp.setzeWert(x, y, this.holeWert(x, y));
				}
				for (int y = j + 1; y < anzahlSpalten(); y++) {
					tmp.setzeWert(x, y - 1, this.holeWert(x, y));
				}
			}
			for (int x = i + 1; x < anzahlZeilen(); x++) {
				for (int y = 0; y < j; y++) {
					tmp.setzeWert(x - 1, y, this.holeWert(x, y));
				}
				for (int y = j + 1; y < anzahlSpalten(); y++) {
					tmp.setzeWert(x - 1, y - 1, this.holeWert(x, y));
				}
			}
			return tmp;
		}

	}

	public long determinante() {
		if (anzahlSpalten() != anzahlZeilen() || anzahlSpalten() == 0 || anzahlZeilen() == 0) {
			return 0;
		} else if (anzahlSpalten() == 1 || anzahlZeilen() == 1) {
			return holeWert(0, 0);
		} else if (anzahlSpalten() == 2 || anzahlZeilen() == 2) {
			return (holeWert(0, 0) * holeWert(1, 1) - holeWert(1, 0) * holeWert(0, 1));
		} else {
			long tmp = 0;
			int vorzeichen = 0;

			for (int i = 0; i < anzahlSpalten(); i++) {
				if (i % 2 != 0) {
					vorzeichen = -1;
				} else {
					vorzeichen = 1;
				}
				tmp += vorzeichen * holeWert(0, i) * unterMatrix(0, i).determinante();
			}
			return tmp;

		}
	}
}
