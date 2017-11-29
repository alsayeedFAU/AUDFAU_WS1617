import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.*;

public class MatrixPublicTest {
	// ========== SYSTEM ==========
	public static final String CLASS_Matrix = "Matrix";
	public static final String METH_anzahlZeilen = "anzahlZeilen";
	public static final String METH_anzahlSpalten = "anzahlSpalten";
	public static final String METH_setzeWert = "setzeWert";
	public static final String METH_holeWert = "holeWert";
	public static final String METH_addiere = "addiere";
	public static final String METH_multipliziere = "multipliziere";
	public static final String METH_multipliziereSkalar = "multipliziereSkalar";
	public static final String METH_transponiere = "transponiere";
	public static final String METH_unterMatrix = "unterMatrix";
	public static final String METH_determinante = "determinante";
	private static final java.util.Random random = new java.util.Random(4711_0815_666_42L);

	// ========== Konstruktor(int) + anzahlZeilen + anzahlSpalten ==========
	@Test(timeout = 666)
	public void test__Matrix__int_gt_0__PUBLIC_TEST() {
		int m = 42 + random.nextInt(42);
		Matrix matrix = new Matrix(m);
		assertEquals("new " + MatrixPublicTest.CLASS_Matrix + "(" + m + ")" + "." + MatrixPublicTest.METH_anzahlZeilen, m, matrix.anzahlZeilen());
		assertEquals("new " + MatrixPublicTest.CLASS_Matrix + "(" + m + ")" + "." + MatrixPublicTest.METH_anzahlSpalten, m, matrix.anzahlSpalten());
	}

	// ========== Konstruktor(int, int) + anzahlZeilen + anzahlSpalten ==========
	@Test(timeout = 666)
	public void test__Matrix__int_is_0__int_lt_0__PUBLIC_TEST() {
		int m = 0;
		int n = -666 + random.nextInt(42);
		Matrix matrix = new Matrix(m, n);
		assertEquals("new " + MatrixPublicTest.CLASS_Matrix + "(" + m + ", " + n + ")" + "." + MatrixPublicTest.METH_anzahlZeilen, 0, matrix.anzahlZeilen());
		assertEquals("new " + MatrixPublicTest.CLASS_Matrix + "(" + m + ", " + n + ")" + "." + MatrixPublicTest.METH_anzahlSpalten, 0, matrix.anzahlSpalten());
	}

	@Test(timeout = 666)
	public void test__Matrix__int__int__at_lest_one_is_0__PUBLIC_TEST() {
		for (int[] dim : new int[][] { { 0, 42 + random.nextInt(42) }, { 42 + random.nextInt(42), 0 } }) {
			int m = dim[0];
			int n = dim[1];
			Matrix matrix = new Matrix(m, n);
			assertEquals("new " + MatrixPublicTest.CLASS_Matrix + "(" + m + ", " + n + ")" + "." + MatrixPublicTest.METH_anzahlZeilen, m, matrix.anzahlZeilen());
			assertEquals("new " + MatrixPublicTest.CLASS_Matrix + "(" + m + ", " + n + ")" + "." + MatrixPublicTest.METH_anzahlSpalten, m == 0 ? 0 : n, matrix.anzahlSpalten());
		}
	}

	// ========== Konstruktor(int, int) + Konstruktor(Matrix) + anzahlZeilen + anzahlSpalten + setzeWert + holeWert ==========
	@Test(timeout = 666)
	public void test__Matrix__Matrix__nonNull__PUBLIC_TEST() {
		int m = 42 + random.nextInt(42);
		int n = 42 + random.nextInt(42);
		Matrix matrix1 = new Matrix(m, n);
		int actualZeilen1 = matrix1.anzahlZeilen();
		int actualSpalten1 = matrix1.anzahlSpalten();
		assertEquals("new " + MatrixPublicTest.CLASS_Matrix + "(" + m + ", " + n + ")" + "." + MatrixPublicTest.METH_anzahlZeilen, m, actualZeilen1);
		assertEquals("new " + MatrixPublicTest.CLASS_Matrix + "(" + m + ", " + n + ")" + "." + MatrixPublicTest.METH_anzahlSpalten, n, actualSpalten1);
		long[][] matrix1Copy = new long[m][n];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				matrix1Copy[i][j] = -4711_0815L + random.nextInt(2 * 4711_0815);
				matrix1.setzeWert(i, j, matrix1Copy[i][j]);
			}
		}
		// create a deep copy...
		Matrix matrix2 = new Matrix(matrix1);
		assertEquals("new " + MatrixPublicTest.CLASS_Matrix + "(Matrix)" + "." + MatrixPublicTest.METH_anzahlZeilen, m, matrix2.anzahlZeilen());
		assertEquals("new " + MatrixPublicTest.CLASS_Matrix + "(Matrix)" + "." + MatrixPublicTest.METH_anzahlSpalten, n, matrix2.anzahlSpalten());
		
		
		// modify a few cells to ensure a really REALLY DEEP COPY...
		java.util.HashMap<String, Long> changes1 = new java.util.HashMap<>();
		java.util.HashMap<String, Long> changes2 = new java.util.HashMap<>();
		for (int c = 0; c < m * n / 2; c++) {
			int i1 = random.nextInt(m), i2 = random.nextInt(m);
			int j1 = random.nextInt(n), j2 = random.nextInt(n);
			long w1 = 666L + random.nextLong(), w2 = 666L + random.nextLong();
			matrix1.setzeWert(i1, j1, w1);
			changes1.put("[" + i1 + "][" + j1 + "]", w1);
			matrix2.setzeWert(i2, j2, w2);
			changes2.put("[" + i2 + "][" + j2 + "]", w2);
		}

		
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				long w1 = matrix1.holeWert(i, j);
				if (changes1.containsKey("[" + i + "][" + j + "]")) {
					assertEquals("Original matrix returns wrong value for " + MatrixPublicTest.METH_holeWert + "(" + i + ", " + j + ") after " + MatrixPublicTest.METH_setzeWert + "(" + i + ", " + j + ")", changes1.get("[" + i + "][" + j + "]").longValue(), w1);
				} else {
					assertEquals("Original matrix returns wrong value for " + MatrixPublicTest.METH_holeWert + "(" + i + ", " + j + ") without any " + MatrixPublicTest.METH_setzeWert + "(" + i + ", " + j + ")", matrix1Copy[i][j], w1);
				}
				long w2 = matrix2.holeWert(i, j);
				if (changes2.containsKey("[" + i + "][" + j + "]")) {
					assertEquals("Copy of original matrix returns wrong value for " + MatrixPublicTest.METH_holeWert + "(" + i + ", " + j + ") after " + MatrixPublicTest.METH_setzeWert + "(" + i + ", " + j + ")", changes2.get("[" + i + "][" + j + "]").longValue(), w2);
				} else {
					assertEquals("Copy of original matrix returns wrong value for " + MatrixPublicTest.METH_holeWert + "(" + i + ", " + j + ") without any " + MatrixPublicTest.METH_setzeWert + "(" + i + ", " + j + ")", matrix1Copy[i][j], w2);
				}
			}
		}
	}

	// ========== Konstruktor(int, int) + addiere ==========
	@Test(timeout = 666)
	public void test__Matrix__addiere__nonNull__nonNull__PUBLIC_TEST() {
		int m = 42 + random.nextInt(42);
		int n = 42 + random.nextInt(42);
		Matrix a = new Matrix(m, n), b = new Matrix(m, n);
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				long wa = -4711_0815L + random.nextInt(2 * 4711_0815), wb = -4711_0815L + random.nextInt(2 * 4711_0815);
				a.setzeWert(i, j, wa);
				b.setzeWert(i, j, wb);
			}
		}
		Matrix c = Matrix.addiere(a, b);
		assertNotNull("Result matrix should NOT be null here after " + MatrixPublicTest.METH_addiere, c);
		assertEquals("Result matrix - dimensions (" + MatrixPublicTest.METH_anzahlZeilen + ") are wrong after " + MatrixPublicTest.METH_addiere, m, c.anzahlZeilen());
		assertEquals("Result matrix - dimensions (" + MatrixPublicTest.METH_anzahlSpalten + ") are wrong after " + MatrixPublicTest.METH_addiere, n, c.anzahlSpalten());
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				long waPwb = a.holeWert(i, j) + b.holeWert(i, j);
				long wc = c.holeWert(i, j);
				assertEquals("Result matrix - returns wrong value for " + MatrixPublicTest.METH_holeWert + "(" + i + ", " + j + ") after " + MatrixPublicTest.METH_addiere, waPwb, wc);
			}
		}
	}

	// ========== Konstruktor(int, int) + multipliziere ==========
	@Test(timeout = 666)
	public void test__Matrix__multipliziere__nonNull__nonNull__PUBLIC_TEST() {
		// Beispiel von https://de.wikipedia.org/wiki/Matrizenmultiplikation#Beispiel
		int l = 2;
		int m = 3;
		int n = 2;
		Matrix a = new Matrix(l, m), b = new Matrix(m, n), cExp = new Matrix(l, n);
		a.setzeWert(0, 0, 3);
		a.setzeWert(0, 1, 2);
		a.setzeWert(0, 2, 1);
		a.setzeWert(1, 0, 1);
		a.setzeWert(1, 1, 0);
		a.setzeWert(1, 2, 2);
		b.setzeWert(0, 0, 1);
		b.setzeWert(0, 1, 2);
		b.setzeWert(1, 0, 0);
		b.setzeWert(1, 1, 1);
		b.setzeWert(2, 0, 4);
		b.setzeWert(2, 1, 0);
		cExp.setzeWert(0, 0, 7);
		cExp.setzeWert(0, 1, 8);
		cExp.setzeWert(1, 0, 9);
		cExp.setzeWert(1, 1, 2);
		Matrix c = Matrix.multipliziere(a, b);
		assertNotNull("Result matrix should NOT be null here after " + MatrixPublicTest.METH_multipliziere, c);
		assertEquals("Result matrix - dimensions (" + MatrixPublicTest.METH_anzahlZeilen + ") are wrong after " + MatrixPublicTest.METH_multipliziere, l, c.anzahlZeilen());
		assertEquals("Result matrix - dimensions (" + MatrixPublicTest.METH_anzahlSpalten + ") are wrong after " + MatrixPublicTest.METH_multipliziere, n, c.anzahlSpalten());
		for (int i = 0; i < l; i++) {
			for (int j = 0; j < n; j++) {
				long wcExp = cExp.holeWert(i, j);
				long wc = c.holeWert(i, j);
				System.out.println(c.holeWert(0, 1));
				System.out.println(c.holeWert(1, 0));
				assertEquals("Result matrix - returns wrong value for " + MatrixPublicTest.METH_holeWert + "(" + i + ", " + j + ") after " + MatrixPublicTest.METH_addiere, wcExp, wc);
			}
		}
	}

	// ========== Konstruktor(int, int) + multipliziere (Skalar) ==========
	@Test(timeout = 666)
	public void test__Matrix__multipliziere_Skalar__PUBLIC_TEST() {
		int m = 42 + random.nextInt(42);
		int n = 42 + random.nextInt(42);
		Matrix a = new Matrix(m, n);
		long[][] aCopy = new long[m][n];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				aCopy[i][j] = -4711_0815L + random.nextInt(2 * 4711_0815);
				a.setzeWert(i, j, aCopy[i][j]);
			}
		}
		long l = -4711_0815L + random.nextInt(2 * 4711_0815);
		a.multipliziere(l);
		assertEquals("Result matrix - dimensions (" + MatrixPublicTest.METH_anzahlZeilen + ") are wrong after " + MatrixPublicTest.METH_multipliziere, m, a.anzahlZeilen());
		assertEquals("Result matrix - dimensions (" + MatrixPublicTest.METH_anzahlSpalten + ") are wrong after " + MatrixPublicTest.METH_multipliziere, n, a.anzahlSpalten());
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				long waExp = aCopy[i][j] * l;
				long wa = a.holeWert(i, j);
				assertEquals("Result matrix - returns wrong value for " + MatrixPublicTest.METH_holeWert + "(" + i + ", " + j + ") after " + MatrixPublicTest.METH_addiere, waExp, wa);
			}
		}
	}

	// ========== Konstruktor(int, int) + transponiere ==========
	@Test(timeout = 666)
	public void test__Matrix__transponiere__PUBLIC_TEST() {
		int m = 42 + random.nextInt(42);
		int n = 42 + random.nextInt(42);
		Matrix a = new Matrix(m, n);
		long[][] aCopy = new long[m][n];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				aCopy[i][j] = -4711_0815L + random.nextInt(2 * 4711_0815);
				a.setzeWert(i, j, aCopy[i][j]);
			}
		}
		Matrix at = a.transponiere();
		assertEquals("Original matrix seems modified - dimensions (" + MatrixPublicTest.METH_anzahlZeilen + ") are wrong after " + MatrixPublicTest.METH_transponiere, m, a.anzahlZeilen());
		assertEquals("Original matrix seems modified - dimensions (" + MatrixPublicTest.METH_anzahlSpalten + ") are wrong after " + MatrixPublicTest.METH_transponiere, n, a.anzahlSpalten());
		assertEquals("Result matrix - dimensions (" + MatrixPublicTest.METH_anzahlZeilen + ") are wrong after " + MatrixPublicTest.METH_transponiere, n, at.anzahlZeilen());
		assertEquals("Result matrix - dimensions (" + MatrixPublicTest.METH_anzahlSpalten + ") are wrong after " + MatrixPublicTest.METH_transponiere, m, at.anzahlSpalten());
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				assertEquals("Original matrix seems modified - returns wrong value for " + MatrixPublicTest.METH_holeWert + "(" + i + ", " + j + ") after " + MatrixPublicTest.METH_transponiere, aCopy[i][j], a.holeWert(i, j));
				assertEquals("Result matrix - returns wrong value for " + MatrixPublicTest.METH_holeWert + "(" + i + ", " + j + ") after " + MatrixPublicTest.METH_transponiere, aCopy[i][j], at.holeWert(j, i));
			}
		}
	}

	// ========== Konstruktor(int, int) + unterMatrix ==========
	@Test(timeout = 666)
	public void test__Matrix__unterMatrix__ex__PUBLIC_TEST() {
		// Beispiel von https://de.wikipedia.org/wiki/Untermatrix#Beispiel
		int m = 3;
		int n = 4;
		Matrix a = new Matrix(m, n);
		long[][] aCopy = new long[m][n];
		int z = 1;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				aCopy[i][j] = z++;
				a.setzeWert(i, j, aCopy[i][j]);
			}
		}
		Matrix u = a.unterMatrix(1, 2);
		///////
		
		
		long[][] uExp = { { 1, 2, 4 }, { 9, 10, 12 } };
		assertNotNull("Result matrix should NOT be null here after " + MatrixPublicTest.METH_unterMatrix, u);
		assertEquals("Original matrix seems modified - dimensions (" + MatrixPublicTest.METH_anzahlZeilen + ") are wrong after " + MatrixPublicTest.METH_unterMatrix, m, a.anzahlZeilen());
		assertEquals("Original matrix seems modified - dimensions (" + MatrixPublicTest.METH_anzahlSpalten + ") are wrong after " + MatrixPublicTest.METH_unterMatrix, n, a.anzahlSpalten());
		assertEquals("Result matrix - dimensions (" + MatrixPublicTest.METH_anzahlZeilen + ") are wrong after " + MatrixPublicTest.METH_unterMatrix, m - 1, u.anzahlZeilen());
		assertEquals("Result matrix - dimensions (" + MatrixPublicTest.METH_anzahlSpalten + ") are wrong after " + MatrixPublicTest.METH_unterMatrix, n - 1, u.anzahlSpalten());
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				assertEquals("Original matrix seems modified - returns wrong value for " + MatrixPublicTest.METH_holeWert + "(" + i + ", " + j + ") after " + MatrixPublicTest.METH_unterMatrix, aCopy[i][j], a.holeWert(i, j));
				if (i < m - 1 && j < n - 1) {
					assertEquals("Result matrix - returns wrong value for " + MatrixPublicTest.METH_holeWert + "(" + i + ", " + j + ") after " + MatrixPublicTest.METH_unterMatrix, uExp[i][j], u.holeWert(i, j));
				}
			}
		}
	}

	@Test(timeout = 666)
	public void test__Matrix__unterMatrix__tiny_empty__PUBLIC_TEST() {
		Matrix a = new Matrix(1, 1).unterMatrix(0, 0);
		assertEquals("Result matrix - dimensions (" + MatrixPublicTest.METH_anzahlZeilen + ") are wrong after " + MatrixPublicTest.METH_unterMatrix, 0, a.anzahlZeilen());
		assertEquals("Result matrix - dimensions (" + MatrixPublicTest.METH_anzahlSpalten + ") are wrong after " + MatrixPublicTest.METH_unterMatrix, 0, a.anzahlSpalten());
	}

	// ========== Konstruktor(int, int) + determinante ==========
	@Test(timeout = 666)
	public void test__Matrix__determinante__ex__PUBLIC_TEST() {
		// https://de.wikipedia.org/wiki/Determinante#Laplacescher_Entwicklungssatz
		int m = 3;
		int n = 3;
		Matrix a = new Matrix(m, n);
		long[][] aCopy = { { 0, 1, 2 }, { 3, 2, 1 }, { 1, 1, 0 } };
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				a.setzeWert(i, j, aCopy[i][j]);
			}
		}
		long d = a.determinante();
		long dExp = 3;
		assertEquals("Original matrix seems modified - dimensions (" + MatrixPublicTest.METH_anzahlZeilen + ") are wrong after " + MatrixPublicTest.METH_determinante, m, a.anzahlZeilen());
		assertEquals("Original matrix seems modified - dimensions (" + MatrixPublicTest.METH_anzahlSpalten + ") are wrong after " + MatrixPublicTest.METH_determinante, n, a.anzahlSpalten());
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				assertEquals("Original matrix seems modified - returns wrong value for " + MatrixPublicTest.METH_holeWert + "(" + i + ", " + j + ") after " + MatrixPublicTest.METH_determinante, aCopy[i][j], a.holeWert(i, j));
			}
		}
		assertEquals("Result of " + MatrixPublicTest.METH_determinante + " is wrong", dExp, d);
	}

	@Test(timeout = 666)
	public void test__Matrix__determinante__rnd_2x2__PUBLIC_TEST() {
		// https://de.wikipedia.org/wiki/Determinante#Laplacescher_Entwicklungssatz
		int m = 2;
		int n = 2;
		Matrix a = new Matrix(m, n);
		long[][] aCopy = new long[m][n];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				aCopy[i][j] = -4711_0815L + random.nextInt(2 * 4711_0815);
				a.setzeWert(i, j, aCopy[i][j]);
			}
		}
		long d = a.determinante();
		long dExp = aCopy[0][0] * aCopy[1][1] - aCopy[1][0] * aCopy[0][1];
		assertEquals("Original matrix seems modified - dimensions (" + MatrixPublicTest.METH_anzahlZeilen + ") are wrong after " + MatrixPublicTest.METH_determinante, m, a.anzahlZeilen());
		assertEquals("Original matrix seems modified - dimensions (" + MatrixPublicTest.METH_anzahlSpalten + ") are wrong after " + MatrixPublicTest.METH_determinante, n, a.anzahlSpalten());
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				assertEquals("Original matrix seems modified - returns wrong value for " + MatrixPublicTest.METH_holeWert + "(" + i + ", " + j + ") after " + MatrixPublicTest.METH_determinante, aCopy[i][j], a.holeWert(i, j));
			}
		}
		assertEquals("Result of " + MatrixPublicTest.METH_determinante + " is wrong", dExp, d);
	}

	@Test(timeout = 666)
	public void test__Matrix__determinante__rnd_3x3__PUBLIC_TEST() {
		// https://de.wikipedia.org/wiki/Determinante#Laplacescher_Entwicklungssatz
		int m = 3;
		int n = 3;
		Matrix a = new Matrix(m, n);
		long[][] aCopy = new long[m][n];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				aCopy[i][j] = -4711_0815L + random.nextInt(2 * 4711_0815);
				a.setzeWert(i, j, aCopy[i][j]);
			}
		}
		long d = a.determinante();
		long dExp = aCopy[0][0] * aCopy[1][1] * aCopy[2][2] + aCopy[0][1] * aCopy[1][2] * aCopy[2][0] + aCopy[0][2] * aCopy[1][0] * aCopy[2][1] // Sarrus
				- aCopy[2][0] * aCopy[1][1] * aCopy[0][2] - aCopy[2][1] * aCopy[1][2] * aCopy[0][0] - aCopy[2][2] * aCopy[1][0] * aCopy[0][1]; // was here
		assertEquals("Original matrix seems modified - dimensions (" + MatrixPublicTest.METH_anzahlZeilen + ") are wrong after " + MatrixPublicTest.METH_determinante, m, a.anzahlZeilen());
		assertEquals("Original matrix seems modified - dimensions (" + MatrixPublicTest.METH_anzahlSpalten + ") are wrong after " + MatrixPublicTest.METH_determinante, n, a.anzahlSpalten());
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				assertEquals("Original matrix seems modified - returns wrong value for " + MatrixPublicTest.METH_holeWert + "(" + i + ", " + j + ") after " + MatrixPublicTest.METH_determinante, aCopy[i][j], a.holeWert(i, j));
			}
		}
		assertEquals("Result of " + MatrixPublicTest.METH_determinante + " is wrong", dExp, d);
	}

	// ========== main ==========
	// nothing to do ;) - please do nothing here:
	public static void main(String args[]) {
		// to compile on command line: javac -cp .:/usr/share/java/junit4.jar *.java
		// to run on command line: java -cp .:/usr/share/java/junit4.jar <nameOfThisClass>

		// starts junit runner - don't try to understand!
		org.junit.runner.JUnitCore.main(new Object() {
		}.getClass().getEnclosingClass().getSimpleName());
	}
}