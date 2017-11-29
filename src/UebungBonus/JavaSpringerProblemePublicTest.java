import static org.junit.Assert.*;
import org.junit.*;

public class JavaSpringerProblemePublicTest {
	// ========== SYSTEM ==========
	private static final java.util.Random random = new java.util.Random(4711_0815_666L);

	// ========== PUBLIC TESTS ==========
	@Test//(timeout = 666)
	public void pubTest_bonus_VonAllemEtwas__MIT_PUBLIC_MINI_ANTI_CHEAT_TEST__THIS_TEST_IS_VERY_IMPORTANT_IF_IT_FAILS_THEN_YOU_WILL_GET_NO_POINTS_AT_ALL() {
		run_pubTest_VonAllemEtwas__MIT_PUBLIC_MINI_ANTI_CHEAT_TEST__THIS_TEST_IS_VERY_IMPORTANT_IF_IT_FAILS_THEN_YOU_WILL_GET_NO_POINTS_AT_ALL();
		run_pubTest_VonAllemEtwas__MIT_PUBLIC_MINI_ANTI_CHEAT_TEST__THIS_TEST_IS_VERY_IMPORTANT_IF_IT_FAILS_THEN_YOU_WILL_GET_NO_POINTS_AT_ALL();
		run_pubTest_VonAllemEtwas__MIT_PUBLIC_MINI_ANTI_CHEAT_TEST__THIS_TEST_IS_VERY_IMPORTANT_IF_IT_FAILS_THEN_YOU_WILL_GET_NO_POINTS_AT_ALL();
		run_pubTest_VonAllemEtwas__MIT_PUBLIC_MINI_ANTI_CHEAT_TEST__THIS_TEST_IS_VERY_IMPORTANT_IF_IT_FAILS_THEN_YOU_WILL_GET_NO_POINTS_AT_ALL();
		run_pubTest_VonAllemEtwas__MIT_PUBLIC_MINI_ANTI_CHEAT_TEST__THIS_TEST_IS_VERY_IMPORTANT_IF_IT_FAILS_THEN_YOU_WILL_GET_NO_POINTS_AT_ALL();
	}

	@Test(timeout = 666)
	public void pubTest_malus_VonAllemEtwas__MIT_PUBLIC_MINI_ANTI_CHEAT_TEST__THIS_TEST_IS_VERY_IMPORTANT_IF_IT_FAILS_THEN_YOU_WILL_GET_NO_POINTS_AT_ALL() {
		run_pubTest_VonAllemEtwas__MIT_PUBLIC_MINI_ANTI_CHEAT_TEST__THIS_TEST_IS_VERY_IMPORTANT_IF_IT_FAILS_THEN_YOU_WILL_GET_NO_POINTS_AT_ALL();
		run_pubTest_VonAllemEtwas__MIT_PUBLIC_MINI_ANTI_CHEAT_TEST__THIS_TEST_IS_VERY_IMPORTANT_IF_IT_FAILS_THEN_YOU_WILL_GET_NO_POINTS_AT_ALL();
		run_pubTest_VonAllemEtwas__MIT_PUBLIC_MINI_ANTI_CHEAT_TEST__THIS_TEST_IS_VERY_IMPORTANT_IF_IT_FAILS_THEN_YOU_WILL_GET_NO_POINTS_AT_ALL();
		run_pubTest_VonAllemEtwas__MIT_PUBLIC_MINI_ANTI_CHEAT_TEST__THIS_TEST_IS_VERY_IMPORTANT_IF_IT_FAILS_THEN_YOU_WILL_GET_NO_POINTS_AT_ALL();
		run_pubTest_VonAllemEtwas__MIT_PUBLIC_MINI_ANTI_CHEAT_TEST__THIS_TEST_IS_VERY_IMPORTANT_IF_IT_FAILS_THEN_YOU_WILL_GET_NO_POINTS_AT_ALL();
	}

	private void run_pubTest_VonAllemEtwas__MIT_PUBLIC_MINI_ANTI_CHEAT_TEST__THIS_TEST_IS_VERY_IMPORTANT_IF_IT_FAILS_THEN_YOU_WILL_GET_NO_POINTS_AT_ALL() {
		// ----- ungueltiges Brett -----
		boolean[][] brettOhneGueltigkeit = new boolean[42 + random.nextInt(666)][];
		for (int zeile = 0; zeile < brettOhneGueltigkeit.length; zeile++) {
			brettOhneGueltigkeit[zeile] = new boolean[42 + random.nextInt(666)];
		}
		try {
			int startSpalte = 0, startZeile = 0;
			int i = random.nextInt(8);
			switch (i) {
			case 1:
				brettOhneGueltigkeit = new boolean[42 + random.nextInt(666)][0];
				break;
			case 2:
				brettOhneGueltigkeit = new boolean[0][42 + random.nextInt(666)];
				break;
			case 3:
				brettOhneGueltigkeit[random.nextInt(brettOhneGueltigkeit.length)] = null;
				break;
			case 4:
				startSpalte = -(42 + random.nextInt(666));
				break;
			case 5:
				startSpalte = 42 + 666 + 42 + random.nextInt(666);
				break;
			case 6:
				startZeile = -(42 + random.nextInt(666));
				break;
			case 7:
				startZeile = 42 + 666 + 42 + random.nextInt(666);
				break;
			default:
				brettOhneGueltigkeit = null;
			}
			JavaSpringerProbleme.loese(startSpalte, startZeile, brettOhneGueltigkeit);
			fail(i + "  Expected: IllegalArgumentException, but was not...");
		} catch (IllegalArgumentException e) {
			// ok...
		}
		// ----- ungueltiges Brett -----
		boolean[][] brettOhneLoesung = { { true, true }, { true, true } };
		JavaSpringerProblemePublicTest.checkFail("board2x2_noSolution", brettOhneLoesung, random.nextInt(2), random.nextInt(2));
		// ----- Beispiel vom Blatt -----
		boolean[][] brettMitLoesung = { //
				{ true, true, true }, //
				{ true, true, true, false, true }, //
				{ true, true, true }, //
				{ true, false, true, true } //
		};
		checkSolution("Beispiel vom Blatt", brettMitLoesung, 1, 4);
	}

	// ========== HELPER ==========
	private static final boolean[][] arrayDeepCopy(boolean[][] in) {
		boolean[][] out = null;
		if (in != null) {
			out = new boolean[in.length][];
			for (int row = 0; row < in.length; row++) {
				if (in[row] != null) {
					out[row] = new boolean[in[row].length];
					for (int col = 0; col < in[row].length; col++) {
						out[row][col] = in[row][col];
					}
				}
			}
		}
		return out;
	}

	protected static final void checkFail(String nachricht, boolean[][] brett, int startZeile, int startSpalte) {
		boolean[][] brettKopie = arrayDeepCopy(brett);
		int[][] loesung = JavaSpringerProbleme.loese(startSpalte, startZeile, brettKopie);
		assertArrayEquals(nachricht + ": Your code illegally modified the board!", brett, brettKopie);
		assertNull(nachricht + ": Your code unexpectedly returned something that looks like a solution, but I think, there is none.", loesung);
	}

	protected static final void checkSolution(String nachricht, boolean[][] brett, int startZeile, int startSpalte) {
		boolean[][] brettKopie = arrayDeepCopy(brett);
		int[][] loesung = JavaSpringerProbleme.loese(startSpalte, startZeile, brettKopie);
		printResult(loesung);
		assertArrayEquals(nachricht + ": Your code illegally modified the board!", brett, brettKopie);
		assertNotNull(nachricht + ": Your code returned null, but I think, there is at least one solution.", loesung);
		int openFields = 0;
		for (int row = 0; row < brett.length; row++) {
			for (int col = 0; col < brett[row].length; col++) {
				if (brett[row][col]) {
					openFields++;
				}
			}
		}
		boolean[] fieldsToPass = new boolean[openFields + 1];
		assertEquals(nachricht + ": Your code returned a solution with a strange number of rows.", brett.length, loesung.length);
		for (int row = 0; row < brett.length; row++) {
			assertEquals(nachricht + ": Your code returned a solution with a strange number of cols in row " + row + ".", brett[row].length, loesung[row].length);
			for (int col = 0; col < brett[row].length; col++) {
				if (brett[row][col]) {
					assertNotEquals(nachricht + ": Your code skipped a field in row " + row + " at column " + col + ".", 0, loesung[row][col]);
					assertTrue(nachricht + ": Your code did not number the steps from 1 to " + openFields + " in row " + row + " at column " + col + " - found: " + loesung[row][col], 1 <= loesung[row][col] && loesung[row][col] <= openFields);
					assertFalse(nachricht + ": Your code used the same step number more than once (found " + loesung[row][col] + " twice) - one of the duplicates occurs in row " + row + " at column " + col + ".", fieldsToPass[loesung[row][col]]);
					fieldsToPass[loesung[row][col]] = true;
				} else {
					assertEquals(nachricht + ": Your code trampled through a gap in row " + row + " at column " + col + ".", 0, loesung[row][col]);
				}
			}
		}
		for (int number = 1; number <= openFields; number++) {
			assertTrue(nachricht + ": Your code did not use the step number " + number + " at all.", fieldsToPass[number]);
		}
		int currentRow = startZeile;
		int currentCol = startSpalte;
		assertEquals(nachricht + ": Your code did not start in row " + currentRow + " at column " + currentCol + " with step number 1.", 1, loesung[currentRow][currentCol]);
		int[][] deltas = { { 2, -1 }, { 1, -2 }, { -1, -2 }, { -2, -1 }, { -2, 1 }, { -1, 2 }, { 1, 2 }, { 2, 1 } };
		indexRunner: for (int currentIndex = 2; currentIndex <= openFields; currentIndex++) {
			for (int[] delta : deltas) {
				int nextRow = currentRow + delta[0];
				int nextCol = currentCol + delta[1];
				if (0 <= nextRow && nextRow < brett.length && 0 <= nextCol && nextCol < brett[nextRow].length && brett[nextRow][nextCol] && loesung[nextRow][nextCol] == currentIndex) {
					currentRow = nextRow;
					currentCol = nextCol;
					continue indexRunner;
				}
			}
			fail(nachricht + ": Your code did not place the step number " + currentIndex + " correctly.");
		}
	}

	private static final void printResult(int[][] loesung) {
		if (loesung == null) {
			System.out.println("No solution.");
		} else {
			String output = "";
			for (int row = 0; row < loesung.length; row++) {
				for (int col = 0; col < (row == 0 ? loesung[row].length : Math.max(loesung[row - 1].length, loesung[row].length)); col++) {
					output += String.format(col == 0 ? "\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588" : "\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588");
				}
				output += "\n";
				for (int col = 0; col < loesung[row].length; col++) {
					if (loesung[row][col] != 0) {
						output += String.format(col == 0 ? "\u2588 %1$5d \u2588" : " %1$5d \u2588", loesung[row][col]);
					} else {
						output += String.format(col == 0 ? "\u2588\u2592\u2592\u2592\u2592\u2592\u2592\u2592\u2588" : "\u2592\u2592\u2592\u2592\u2592\u2592\u2592\u2588");
					}
				}
				output += "\n";
			}
			for (int col = 0; col < loesung[loesung.length - 1].length; col++) {
				output += String.format(col == 0 ? "\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588" : "\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588");
			}
			System.out.println(output);
			System.out.println();
		}
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