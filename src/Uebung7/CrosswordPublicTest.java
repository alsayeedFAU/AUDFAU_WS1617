import static org.junit.Assert.*;
import org.junit.*;

public class CrosswordPublicTest {

	// ========== DATA ==========
	// {{y-coordinate, x-coordinate, length, direction: 0=horizontal, 1=vertical}}
	private static final int[][] gridSpec_PUBLIC_MAIN_TEST_BABY = { { 0, 0, 10, 1 }, { 0, 0, 8, 0 }, { 0, 4, 10, 1 }, { 0, 7, 4, 1 }, { 0, 9, 10, 1 }, { 0, 9, 9, 0 }, { 0, 12, 4, 1 }, { 0, 15, 6, 1 }, { 0, 17, 10, 1 }, { 2, 0, 5, 0 }, { 3, 4, 4, 0 }, { 3, 6, 7, 1 }, { 3, 9, 5, 0 }, { 3, 11, 7, 1 }, { 4, 0, 5, 0 }, { 5, 6, 4, 0 }, { 5, 11, 7, 0 }, { 6, 0, 5, 0 }, { 6, 2, 4, 1 }, { 7, 4, 4, 0 }, { 7, 9, 9, 0 }, { 9, 0, 5, 0 }, { 9, 6, 4, 0 }, { 9, 11, 7, 0 } };
	private static final String[] wordsSpec_PUBLIC_MAIN_TEST_BABY = { "INGE", "NOAH", "ORBI", "ROBE", "ROLL", "SEIL", "STAR", "DOLDE", "HAUPT", "INSEL", "KOPIE", "MASSE", "MOEBEL", "GASOLIN", "INSULIN", "PRIMAER", "RAUCHER", "SIGNATUR", "EIFOERMIG", "STAMMGAST", "ARTILLERIE", "ELEKTRISCH", "GESPENSTER", "SCHWIMMBAD" };
	private static final int[][] gridSpec_PUBLIC_MAIN_TEST_EASY = { { 14, 0, 9, 0 }, { 14, 11, 8, 0 }, { 12, 10, 9, 0 }, { 11, 0, 8, 0 }, { 10, 7, 8, 0 }, { 9, 0, 6, 1 }, { 9, 0, 8, 0 }, { 9, 7, 6, 1 }, { 8, 10, 9, 0 }, { 8, 14, 7, 1 }, { 7, 0, 9, 0 }, { 7, 4, 8, 1 }, { 7, 16, 6, 1 }, { 7, 18, 8, 1 }, { 6, 8, 6, 0 }, { 6, 11, 9, 1 }, { 5, 0, 7, 0 }, { 3, 6, 6, 0 }, { 3, 13, 6, 0 }, { 2, 0, 7, 0 }, { 2, 6, 6, 1 }, { 0, 0, 8, 1 }, { 0, 0, 9, 0 }, { 0, 3, 8, 1 }, { 0, 8, 8, 1 }, { 0, 10, 7, 1 }, { 0, 10, 9, 0 }, { 0, 13, 7, 1 }, { 0, 15, 6, 1 }, { 0, 18, 6, 1 } };
	private static final String[] wordsSpec_PUBLIC_MAIN_TEST_EASY = { "TOURISMUS", "RESIDENT", "STRAFE", "ATTACKE", "REISEWEG", "POSTKARTE", "BEISSER", "KONSERVE", "REGION", "PLAPPERER", "KRIEGEN", "PHRASE", "NUSSTORTE", "LAUSBUB", "PALETTE", "ORDNER", "TRAGZEIT", "ERNTEMOND", "HOFRAT", "ERZENGEL", "BEWACHUNG", "TELESKOP", "BANNER", "ABLENKUNG", "GALERIST", "GEAEST", "ANLASS", "SPRINGER", "ABREDE", "SCHLEPPE" };

	// ----- evaluateSpec -----
	@Test(timeout = 666)
	public void testCheckRiddle_PUBLIC_MAIN_TEST_BABY() {
		assertEquals("PUBLIC_MAIN_TEST BABY-riddle passes all checks.", 0, testRiddleSpec(gridSpec_PUBLIC_MAIN_TEST_BABY, wordsSpec_PUBLIC_MAIN_TEST_BABY));

	}

	@Test(timeout = 666)
	public void testCheckRiddle_PUBLIC_MAIN_TEST_BABY_JOHNDOE() {
		String[] wordsSpecCopy = java.util.Arrays.copyOf(wordsSpec_PUBLIC_MAIN_TEST_BABY, wordsSpec_PUBLIC_MAIN_TEST_BABY.length);
		wordsSpecCopy[0] = "JOHNDOE";
		assertEquals("PUBLIC_MAIN_TEST BABY-riddle with JOHNDOE replacing INGE hangs on check 8.", 8, testRiddleSpec(gridSpec_PUBLIC_MAIN_TEST_BABY, wordsSpecCopy));

	}

	@Test(timeout = 666)
	public void testCheckRiddle_PUBLIC_MAIN_TEST_EASY() {
		assertEquals("PUBLIC_MAIN_TEST EASY-riddle passes all checks.", 0, testRiddleSpec(gridSpec_PUBLIC_MAIN_TEST_EASY, wordsSpec_PUBLIC_MAIN_TEST_EASY));

	}

	private int testRiddleSpec(int[][] gridSpec, String[] wordsSpec) {
		try {
			AbstractCrosswordSpec.instantiate(gridSpec, wordsSpec);
			return 0;
		} catch (IllegalCrosswordSpecException e) {
			return e.getCode();
		}
	}

	// ----- generateGrid -----
	@Test(timeout = 666)
	public void testCreateGrid_PUBLIC_MAIN_TEST_BABY() {
		char[][] grid_expected = new char[10][18];
		assertArrayEquals("PUBLIC_MAIN_TEST BABY-riddle grid is 10x18.", grid_expected, new CrosswordGrid(AbstractCrosswordSpec.instantiate(gridSpec_PUBLIC_MAIN_TEST_BABY, wordsSpec_PUBLIC_MAIN_TEST_BABY)).getSolution());

	}

	// ----- isWordValid -----
	@Test(timeout = 666)
	public void testCanPlaceWord__ELEKTRISCH_before_STAR__EMPTY_PUBLIC_MAIN_TEST_BABY_1() {
		char[][] grid = new char[10][18];
		for (int i = 0; i < "ELEKTRISCH".length(); i++) {
			grid[i][9] = "ELEKTRISCH".charAt(i);
		}
		assertTrue("PUBLIC_MAIN_TEST BABY-riddle (can place STAR after ELEKTRISCH)", new CrosswordGrid(grid).isWordValid(new FieldSpec(5, 6, "STAR".length(), 0), "STAR"));

	}

	@Test(timeout = 666)
	public void testCanPlaceWord__STAR_before_ELEKTRISCH__EMPTY_PUBLIC_MAIN_TEST_BABY_2() {
		char[][] grid = new char[10][18];
		for (int i = 0; i < "STAR".length(); i++) {
			grid[5][6 + i] = "STAR".charAt(i);
		}
		assertTrue("PUBLIC_MAIN_TEST BABY-riddle (can place ELEKTRISCH after STAR)", new CrosswordGrid(grid).isWordValid(new FieldSpec(0, 9, "ELEKTRISCH".length(), 1), "ELEKTRISCH"));

	}

	@Test(timeout = 666)
	public void testCanPlaceWord_DasGoldeneBlatt_1_Vertical_Gives_TrueTrueTrueFalse() {
		char[][] grid = new char[24][23];
		assertTrue("Das Goldene Blatt 1", new CrosswordGrid(grid).isWordValid(new FieldSpec(8, 8, "MAKRONE".length(), 1), "MAKRONE"));
		for (int i = 0; i < "MMMMMMMMM".length(); i++) {
			grid[8][i] = "MMMMMMMMM".charAt(i);
		}
		assertTrue("Das Goldene Blatt 1", new CrosswordGrid(grid).isWordValid(new FieldSpec(8, 8, "MAKRONE".length(), 1), "MAKRONE"));
		for (int i = 0; i < "KKKKKKKKKK".length(); i++) {
			grid[10][2 + i] = "KKKKKKKKKK".charAt(i);
		}
		assertTrue("Das Goldene Blatt 1", new CrosswordGrid(grid).isWordValid(new FieldSpec(8, 8, "MAKRONE".length(), 1), "MAKRONE"));
		for (int i = 0; i < "FOOOOOO".length(); i++) {
			grid[12][8 + i] = "FOOOOOO".charAt(i);
		}
		assertFalse("Das Goldene Blatt 1", new CrosswordGrid(grid).isWordValid(new FieldSpec(8, 8, "MAKRONE".length(), 1), "MAKRONE"));
	}

	@Test(timeout = 666)
	public void testCanPlaceWord_DasGoldeneBlatt_1_Horizontal_Gives_TrueTrueTrueFalse() {
		char[][] grid = new char[24][23];
		assertTrue("Das Goldene Blatt 1", new CrosswordGrid(grid).isWordValid(new FieldSpec(10, 2, "FEUERWACHE".length(), 0), "FEUERWACHE"));
		for (int i = 0; i < "FFFFFFF".length(); i++) {
			grid[10 + i][2] = "FFFFFFF".charAt(i);
		}
		assertTrue("Das Goldene Blatt 1", new CrosswordGrid(grid).isWordValid(new FieldSpec(10, 2, "FEUERWACHE".length(), 0), "FEUERWACHE"));
		for (int i = 0; i < "EEEEEEEEE".length(); i++) {
			grid[2 + i][3] = "EEEEEEEEE".charAt(i);
		}
		assertTrue("Das Goldene Blatt 1", new CrosswordGrid(grid).isWordValid(new FieldSpec(10, 2, "FEUERWACHE".length(), 0), "FEUERWACHE"));
		for (int i = 0; i < "FRRRRRR".length(); i++) {
			grid[10 + i][6] = "FRRRRRR".charAt(i);
		}
		assertFalse("Das Goldene Blatt 1", new CrosswordGrid(grid).isWordValid(new FieldSpec(10, 2, "FEUERWACHE".length(), 0), "FEUERWACHE"));
	}

	// ----- setWord -----
	@Test(timeout = 666)
	public void testPlaceWord__emptyGrid_before_ELEKTRISCH__PUBLIC_MAIN_TEST_BABY_1() {
		char[][] grid = new char[10][18];
		char[][] grid_expected = new char[10][18];
		for (int i = 0; i < "ELEKTRISCH".length(); i++) {
			grid_expected[i][9] = "ELEKTRISCH".charAt(i);
		}
		boolean[] charsPlaced = new CrosswordGrid(grid).setWord(new FieldSpec(0, 9, "ELEKTRISCH".length(), 1), "ELEKTRISCH");
		boolean[] charsPlaced_expected = { true, true, true, true, true, true, true, true, true, true };
		assertArrayEquals("PUBLIC_MAIN_TEST BABY-riddle (place ELEKTRISCH)", grid_expected, grid);
		assertEquals("PUBLIC_MAIN_TEST BABY-riddle (place ELEKTRISCH)", java.util.Arrays.toString(charsPlaced_expected), java.util.Arrays.toString(charsPlaced));

	}

	@Test(timeout = 666)
	public void testPlaceWord__emptyGrid_before_ELEKTRISCH_before_STAR__PUBLIC_MAIN_TEST_BABY_2() {
		char[][] grid = new char[10][18];
		char[][] grid_expected = new char[10][18];
		for (int i = 0; i < "ELEKTRISCH".length(); i++) {
			grid[i][9] = "ELEKTRISCH".charAt(i);
			grid_expected[i][9] = "ELEKTRISCH".charAt(i);
		}
		for (int i = 0; i < "STAR".length(); i++) {
			grid_expected[5][6 + i] = "STAR".charAt(i);
		}
		boolean[] charsPlaced = new CrosswordGrid(grid).setWord(new FieldSpec(5, 6, "STAR".length(), 0), "STAR");
		boolean[] charsPlaced_expected = { true, true, true, false };
		assertArrayEquals("PUBLIC_MAIN_TEST BABY-riddle (place STAR after ELEKTRISCH)", grid_expected, grid);
		assertEquals("PUBLIC_MAIN_TEST BABY-riddle (place STAR after ELEKTRISCH)", java.util.Arrays.toString(charsPlaced_expected), java.util.Arrays.toString(charsPlaced));

	}

	@Test(timeout = 666)
	public void testPlaceWord__emptyGrid_before_STAR_before_STAR__PUBLIC_MAIN_TEST_BABY_3() {
		char[][] grid = new char[10][18];
		char[][] grid_expected = new char[10][18];
		for (int i = 0; i < "STAR".length(); i++) {
			grid_expected[5][6 + i] = "STAR".charAt(i);
		}
		boolean[] charsPlaced = new CrosswordGrid(grid).setWord(new FieldSpec(5, 6, "STAR".length(), 0), "STAR");
		boolean[] charsPlaced_expected = { true, true, true, true };
		assertArrayEquals("PUBLIC_MAIN_TEST BABY-riddle (place STAR)", grid_expected, grid);
		assertEquals("PUBLIC_MAIN_TEST BABY-riddle (place STAR)", java.util.Arrays.toString(charsPlaced_expected), java.util.Arrays.toString(charsPlaced));

	}

	@Test(timeout = 666)
	public void testPlaceWord__emptyGrid_before_STAR_before_ELEKTRISCH__PUBLIC_MAIN_TEST_BABY_4() {
		char[][] grid = new char[10][18];
		char[][] grid_expected = new char[10][18];
		for (int i = 0; i < "STAR".length(); i++) {
			grid[5][6 + i] = "STAR".charAt(i);
			grid_expected[5][6 + i] = "STAR".charAt(i);
		}
		for (int i = 0; i < "ELEKTRISCH".length(); i++) {
			grid_expected[i][9] = "ELEKTRISCH".charAt(i);
		}
		boolean[] charsPlaced = new CrosswordGrid(grid).setWord(new FieldSpec(0, 9, "ELEKTRISCH".length(), 1), "ELEKTRISCH");
		boolean[] charsPlaced_expected = { true, true, true, true, true, false, true, true, true, true };
		assertArrayEquals("PUBLIC_MAIN_TEST BABY-riddle (place ELEKTRISCH after STAR)", grid_expected, grid);
		assertEquals("PUBLIC_MAIN_TEST BABY-riddle (place ELEKTRISCH after STAR)", java.util.Arrays.toString(charsPlaced_expected), java.util.Arrays.toString(charsPlaced));

	}

	@Test(timeout = 666)
	public void testPlaceWord_DasGoldeneBlatt_1_Horizontal_Corners_NoCrossing() {
		char[][] grid, grid_expected;
		boolean[] charsPlaced, charsPlaced_expected;
		grid = new char[24][23];
		grid_expected = new char[24][23];
		for (int i = 0; i < "SCHULUNG".length(); i++) {
			grid_expected[0][0 + i] = "SCHULUNG".charAt(i);
		}
		charsPlaced = new CrosswordGrid(grid).setWord(new FieldSpec(0, 0, "SCHULUNG".length(), 0), "SCHULUNG");
		charsPlaced_expected = new boolean[] { true, true, true, true, true, true, true, true };
		assertArrayEquals("Das Goldene Blatt 1 - setWord SCHULUNG", grid_expected, grid);
		assertEquals("Das Goldene Blatt 1 - setWord SCHULUNG", java.util.Arrays.toString(charsPlaced_expected), java.util.Arrays.toString(charsPlaced));
		for (int i = 0; i < "GANGBAR".length(); i++) {
			grid_expected[0][16 + i] = "GANGBAR".charAt(i);
		}
		charsPlaced = new CrosswordGrid(grid).setWord(new FieldSpec(0, 16, "GANGBAR".length(), 0), "GANGBAR");
		charsPlaced_expected = new boolean[] { true, true, true, true, true, true, true };
		assertArrayEquals("Das Goldene Blatt 1 - setWord GANGBAR", grid_expected, grid);
		assertEquals("Das Goldene Blatt 1 - setWord GANGBAR", java.util.Arrays.toString(charsPlaced_expected), java.util.Arrays.toString(charsPlaced));
		for (int i = 0; i < "MURMELN".length(); i++) {
			grid_expected[23][0 + i] = "MURMELN".charAt(i);
		}
		charsPlaced = new CrosswordGrid(grid).setWord(new FieldSpec(23, 0, "MURMELN".length(), 0), "MURMELN");
		charsPlaced_expected = new boolean[] { true, true, true, true, true, true, true };
		assertArrayEquals("Das Goldene Blatt 1 - setWord MURMELN", grid_expected, grid);
		assertEquals("Das Goldene Blatt 1 - setWord MURMELN", java.util.Arrays.toString(charsPlaced_expected), java.util.Arrays.toString(charsPlaced));
		for (int i = 0; i < "NAERRIN".length(); i++) {
			grid_expected[23][16 + i] = "NAERRIN".charAt(i);
		}
		charsPlaced = new CrosswordGrid(grid).setWord(new FieldSpec(23, 16, "NAERRIN".length(), 0), "NAERRIN");
		charsPlaced_expected = new boolean[] { true, true, true, true, true, true, true };
		assertArrayEquals("Das Goldene Blatt 1 - setWord NAERRIN", grid_expected, grid);
		assertEquals("Das Goldene Blatt 1 - setWord NAERRIN", java.util.Arrays.toString(charsPlaced_expected), java.util.Arrays.toString(charsPlaced));
	}

	@Test(timeout = 666)
	public void testPlaceWord_DasGoldeneBlatt_1_Vertical_Corners_NoCrossing() {
		char[][] grid, grid_expected;
		boolean[] charsPlaced, charsPlaced_expected;
		grid = new char[24][23];
		grid_expected = new char[24][23];
		for (int i = 0; i < "SCHAF".length(); i++) {
			grid_expected[0 + i][0] = "SCHAF".charAt(i);
		}
		charsPlaced = new CrosswordGrid(grid).setWord(new FieldSpec(0, 0, "SCHAF".length(), 1), "SCHAF");
		charsPlaced_expected = new boolean[] { true, true, true, true, true };
		assertArrayEquals("Das Goldene Blatt 1 - setWord SCHAF", grid_expected, grid);
		assertEquals("Das Goldene Blatt 1 - setWord SCHAF", java.util.Arrays.toString(charsPlaced_expected), java.util.Arrays.toString(charsPlaced));
		for (int i = 0; i < "REALITAET".length(); i++) {
			grid_expected[0 + i][22] = "REALITAET".charAt(i);
		}
		charsPlaced = new CrosswordGrid(grid).setWord(new FieldSpec(0, 22, "REALITAET".length(), 1), "REALITAET");
		charsPlaced_expected = new boolean[] { true, true, true, true, true, true, true, true, true };
		assertArrayEquals("Das Goldene Blatt 1 - setWord REALITAET", grid_expected, grid);
		assertEquals("Das Goldene Blatt 1 - setWord REALITAET", java.util.Arrays.toString(charsPlaced_expected), java.util.Arrays.toString(charsPlaced));
		for (int i = 0; i < "KAMERATEAM".length(); i++) {
			grid_expected[14 + i][0] = "KAMERATEAM".charAt(i);
		}
		charsPlaced = new CrosswordGrid(grid).setWord(new FieldSpec(14, 0, "KAMERATEAM".length(), 1), "KAMERATEAM");
		charsPlaced_expected = new boolean[] { true, true, true, true, true, true, true, true, true, true };
		assertArrayEquals("Das Goldene Blatt 1 - setWord KAMERATEAM", grid_expected, grid);
		assertEquals("Das Goldene Blatt 1 - setWord KAMERATEAM", java.util.Arrays.toString(charsPlaced_expected), java.util.Arrays.toString(charsPlaced));
		for (int i = 0; i < "NATRON".length(); i++) {
			grid_expected[18 + i][22] = "NATRON".charAt(i);
		}
		charsPlaced = new CrosswordGrid(grid).setWord(new FieldSpec(18, 22, "NATRON".length(), 1), "NATRON");
		charsPlaced_expected = new boolean[] { true, true, true, true, true, true };
		assertArrayEquals("Das Goldene Blatt 1 - setWord NATRON", grid_expected, grid);
		assertEquals("Das Goldene Blatt 1 - setWord NATRON", java.util.Arrays.toString(charsPlaced_expected), java.util.Arrays.toString(charsPlaced));
	}

	@Test(timeout = 666)
	public void testPlaceWord_DasGoldeneBlatt_1_Horizontal_Corners_Crossing_HV() {
		char[][] grid, grid_expected;
		boolean[] charsPlaced, charsPlaced_expected;
		grid = new char[24][23];
		grid_expected = new char[24][23];
		for (int i = 0; i < "SCHULUNG".length(); i++) {
			grid_expected[0][0 + i] = "SCHULUNG".charAt(i);
		}
		charsPlaced = new CrosswordGrid(grid).setWord(new FieldSpec(0, 0, "SCHULUNG".length(), 0), "SCHULUNG");
		charsPlaced_expected = new boolean[] { true, true, true, true, true, true, true, true };
		assertArrayEquals("Das Goldene Blatt 1 - setWord SCHULUNG", grid_expected, grid);
		assertEquals("Das Goldene Blatt 1 - setWord SCHULUNG", java.util.Arrays.toString(charsPlaced_expected), java.util.Arrays.toString(charsPlaced));
		for (int i = 0; i < "SCHAF".length(); i++) {
			grid_expected[0 + i][0] = "SCHAF".charAt(i);
		}
		charsPlaced = new CrosswordGrid(grid).setWord(new FieldSpec(0, 0, "SCHAF".length(), 1), "SCHAF");
		charsPlaced_expected = new boolean[] { false, true, true, true, true };
		assertArrayEquals("Das Goldene Blatt 1 - setWord SCHAF", grid_expected, grid);
		assertEquals("Das Goldene Blatt 1 - setWord SCHAF", java.util.Arrays.toString(charsPlaced_expected), java.util.Arrays.toString(charsPlaced));
	}

	@Test(timeout = 666)
	public void testPlaceWord_DasGoldeneBlatt_1_Vertical_Corners_Crossing_VH() {
		char[][] grid, grid_expected;
		boolean[] charsPlaced, charsPlaced_expected;
		grid = new char[24][23];
		grid_expected = new char[24][23];
		for (int i = 0; i < "NATRON".length(); i++) {
			grid_expected[18 + i][22] = "NATRON".charAt(i);
		}
		charsPlaced = new CrosswordGrid(grid).setWord(new FieldSpec(18, 22, "NATRON".length(), 1), "NATRON");
		charsPlaced_expected = new boolean[] { true, true, true, true, true, true };
		assertArrayEquals("Das Goldene Blatt 1 - setWord NATRON", grid_expected, grid);
		assertEquals("Das Goldene Blatt 1 - setWord NATRON", java.util.Arrays.toString(charsPlaced_expected), java.util.Arrays.toString(charsPlaced));
		for (int i = 0; i < "NAERRIN".length(); i++) {
			grid_expected[23][16 + i] = "NAERRIN".charAt(i);
		}
		charsPlaced = new CrosswordGrid(grid).setWord(new FieldSpec(23, 16, "NAERRIN".length(), 0), "NAERRIN");
		charsPlaced_expected = new boolean[] { true, true, true, true, true, true, false };
		assertArrayEquals("Das Goldene Blatt 1 - setWord NAERRIN", grid_expected, grid);
		assertEquals("Das Goldene Blatt 1 - setWord NAERRIN", java.util.Arrays.toString(charsPlaced_expected), java.util.Arrays.toString(charsPlaced));
	}

	// ----- solvePuzzle -----
	@Test(timeout = 666)
	public void testSolveRiddle_PUBLIC_MAIN_TEST_BABY() {
		checkSolution(10, 18, gridSpec_PUBLIC_MAIN_TEST_BABY, wordsSpec_PUBLIC_MAIN_TEST_BABY);
	}

	@Test(timeout = 666)
	public void testSolveRiddle_PUBLIC_MAIN_TEST_EASY() {
		checkSolution(15, 19, gridSpec_PUBLIC_MAIN_TEST_EASY, wordsSpec_PUBLIC_MAIN_TEST_EASY);
	}

	// ========== HELPER ==========
	private static final void checkSolution(int sizeY, int sizeX, int[][] gridSpec, String[] wordsSpec) {
		CrosswordSpec spec = AbstractCrosswordSpec.instantiate(gridSpec, wordsSpec);
		CrosswordGrid grid = CrosswordSolver.solve(spec);
		assertNotNull("Returned solution object is null.", grid);
		char[][] grid_actual = grid.getSolution();
		assertNotNull("Solution grid is null.", grid_actual);
		assertEquals("Wrong number of rows in solution.", sizeY, grid_actual.length);
		for (char[] row : grid_actual) {
			assertNotNull("Solution contains null-rows.", row);
			assertEquals("Wrong number of cols in solution.", sizeX, row.length);
		}
		java.util.List<String> wordsPlaced = new java.util.ArrayList<String>();
		boolean[][] gridMask = new boolean[sizeY][sizeX];
		for (int[] gridSpecEntry : gridSpec) {
			String word = "";
			if (gridSpecEntry[3] == 0) {
				for (int x = 0; x < gridSpecEntry[2]; x++) {
					word += grid_actual[gridSpecEntry[0]][gridSpecEntry[1] + x];
					gridMask[gridSpecEntry[0]][gridSpecEntry[1] + x] = true;
				}
			} else {
				for (int y = 0; y < gridSpecEntry[2]; y++) {
					word += grid_actual[gridSpecEntry[0] + y][gridSpecEntry[1]];
					gridMask[gridSpecEntry[0] + y][gridSpecEntry[1]] = true;
				}
			}
			wordsPlaced.add(word);
		}
		for (int y = 0; y < grid_actual.length; y++) {
			for (int x = 0; x < grid_actual[y].length; x++) {
				assertTrue("Unexpected character in solution.", gridMask[y][x] || grid_actual[y][x] == 0);
			}
		}
		java.util.List<String> wordsToPlace;
		wordsToPlace = new java.util.ArrayList<String>();
		wordsToPlace.addAll(java.util.Arrays.asList(wordsSpec));
		for (String word : wordsPlaced) {
			assertTrue("Unexpected word in solution.", wordsToPlace.remove(word));
		}
		assertEquals("Not all or other words have been placed.", 0, wordsToPlace.size());
		wordsToPlace = new java.util.ArrayList<String>();
		wordsToPlace.addAll(java.util.Arrays.asList(wordsSpec));
		for (String word : wordsToPlace) {
			assertTrue("Word not found in solution.", wordsPlaced.remove(word));
		}
		assertEquals("More or other words have been placed.", 0, wordsPlaced.size());
	}

	// ################################################################################
	// ========== MANUAL TESTS WITH VISUALISATION ==========
	// ################################################################################
	private static final void mainTestSmoky() {
		int[][] gridSpec = { { 0, 0, 10, 1 }, { 0, 0, 8, 0 }, { 0, 4, 10, 1 }, { 0, 7, 4, 1 }, { 0, 9, 10, 1 }, { 0, 9, 9, 0 }, { 0, 12, 4, 1 }, { 0, 15, 6, 1 }, { 0, 17, 10, 1 }, { 2, 0, 5, 0 }, { 3, 4, 4, 0 }, { 3, 6, 7, 1 }, { 3, 9, 5, 0 }, { 3, 11, 7, 1 }, { 4, 0, 5, 0 }, { 5, 6, 4, 0 }, { 5, 11, 7, 0 }, { 6, 0, 5, 0 }, { 6, 2, 4, 1 }, { 7, 4, 4, 0 }, { 7, 9, 9, 0 }, { 9, 0, 5, 0 }, { 9, 6, 4, 0 }, { 9, 11, 7, 0 } };
		String[] wordsSpec = { "INGE", "NOAH", "ORBI", "ROBE", "ROLL", "SEIL", "STAR", "DOLDE", "HAUPT", "INSEL", "KOPIE", "MASSE", "MOEBEL", "GASOLIN", "INSULIN", "PRIMAER", "RAUCHER", "SIGNATUR", "EIFOERMIG", "STAMMGAST", "ARTILLERIE", "ELEKTRISCH", "GESPENSTER", "SCHWIMMBAD" };
		// check riddle
		CrosswordSpec spec;
		int evaluateSpec;
		try {
			spec = AbstractCrosswordSpec.instantiate(gridSpec, wordsSpec);
			evaluateSpec = 0;
		} catch (IllegalCrosswordSpecException e) {
			spec = null;
			evaluateSpec = e.getCode();
		}
		System.out.println("# evaluateSpec: " + evaluateSpec);
		System.out.println("========================================");
		if (0 == evaluateSpec) {
			boolean[] placementV, placementH;
			// create grid
			System.out.println("# generate empty grid");
			CrosswordGrid grid = new CrosswordGrid(spec);
			printGrid(grid, true);
			System.out.println("~~~~~~~~~~ If everything went fine, you should see above the same as below this line: ~~~~~~~~~~" + "\n" + //
					"---------------------------------------" + "\n" + //
					"| . . . . . . . . . . . . . . . . . . |" + "\n" + //
					"| . . . . . . . . . . . . . . . . . . |" + "\n" + //
					"| . . . . . . . . . . . . . . . . . . |" + "\n" + //
					"| . . . . . . . . . . . . . . . . . . |" + "\n" + //
					"| . . . . . . . . . . . . . . . . . . |" + "\n" + //
					"| . . . . . . . . . . . . . . . . . . |" + "\n" + //
					"| . . . . . . . . . . . . . . . . . . |" + "\n" + //
					"| . . . . . . . . . . . . . . . . . . |" + "\n" + //
					"| . . . . . . . . . . . . . . . . . . |" + "\n" + //
					"| . . . . . . . . . . . . . . . . . . |" + "\n" + //
					"---------------------------------------" + "\n" + //
					"");
			System.out.println("========================================");
			// place/unplace V-H
			System.out.println("# set word \"" + wordsSpec[wordsSpec.length - 3] + "\"");
			placementV = grid.setWord(new FieldSpec(gridSpec[4]), wordsSpec[wordsSpec.length - 3]);
			printGrid(grid, true);
			System.out.println("~~~~~~~~~~ If everything went fine, you should see above the same as below this line: ~~~~~~~~~~" + "\n" + //
					"---------------------------------------" + "\n" + //
					"| . . . . . . . . . E . . . . . . . . |" + "\n" + //
					"| . . . . . . . . . L . . . . . . . . |" + "\n" + //
					"| . . . . . . . . . E . . . . . . . . |" + "\n" + //
					"| . . . . . . . . . K . . . . . . . . |" + "\n" + //
					"| . . . . . . . . . T . . . . . . . . |" + "\n" + //
					"| . . . . . . . . . R . . . . . . . . |" + "\n" + //
					"| . . . . . . . . . I . . . . . . . . |" + "\n" + //
					"| . . . . . . . . . S . . . . . . . . |" + "\n" + //
					"| . . . . . . . . . C . . . . . . . . |" + "\n" + //
					"| . . . . . . . . . H . . . . . . . . |" + "\n" + //
					"---------------------------------------" + "\n" + //
					"");
			System.out.println("========================================");
			System.out.println("# is word valid \"" + wordsSpec[6] + "\": " + grid.isWordValid(new FieldSpec(gridSpec[15]), wordsSpec[6]));
			System.out.println("========================================");
			System.out.println("# set word \"" + wordsSpec[6] + "\"");
			placementH = grid.setWord(new FieldSpec(gridSpec[15]), wordsSpec[6]);
			printGrid(grid, true);
			System.out.println("~~~~~~~~~~ If everything went fine, you should see above the same as below this line: ~~~~~~~~~~" + "\n" + //
					"---------------------------------------" + "\n" + //
					"| . . . . . . . . . E . . . . . . . . |" + "\n" + //
					"| . . . . . . . . . L . . . . . . . . |" + "\n" + //
					"| . . . . . . . . . E . . . . . . . . |" + "\n" + //
					"| . . . . . . . . . K . . . . . . . . |" + "\n" + //
					"| . . . . . . . . . T . . . . . . . . |" + "\n" + //
					"| . . . . . . S T A R . . . . . . . . |" + "\n" + //
					"| . . . . . . . . . I . . . . . . . . |" + "\n" + //
					"| . . . . . . . . . S . . . . . . . . |" + "\n" + //
					"| . . . . . . . . . C . . . . . . . . |" + "\n" + //
					"| . . . . . . . . . H . . . . . . . . |" + "\n" + //
					"---------------------------------------" + "\n" + //
					"");
			System.out.println("========================================");
			System.out.println("# remove word \"" + wordsSpec[6] + "\"");
			grid.removeWord(new FieldSpec(gridSpec[15]), wordsSpec[6], placementH);
			printGrid(grid, true);
			System.out.println("~~~~~~~~~~ If everything went fine, you should see above the same as below this line: ~~~~~~~~~~" + "\n" + //
					"---------------------------------------" + "\n" + //
					"| . . . . . . . . . E . . . . . . . . |" + "\n" + //
					"| . . . . . . . . . L . . . . . . . . |" + "\n" + //
					"| . . . . . . . . . E . . . . . . . . |" + "\n" + //
					"| . . . . . . . . . K . . . . . . . . |" + "\n" + //
					"| . . . . . . . . . T . . . . . . . . |" + "\n" + //
					"| . . . . . . . . . R . . . . . . . . |" + "\n" + //
					"| . . . . . . . . . I . . . . . . . . |" + "\n" + //
					"| . . . . . . . . . S . . . . . . . . |" + "\n" + //
					"| . . . . . . . . . C . . . . . . . . |" + "\n" + //
					"| . . . . . . . . . H . . . . . . . . |" + "\n" + //
					"---------------------------------------" + "\n" + //
					"");
			System.out.println("========================================");
			System.out.println("# remove word \"" + wordsSpec[wordsSpec.length - 3] + "\"");
			grid.removeWord(new FieldSpec(gridSpec[4]), wordsSpec[wordsSpec.length - 3], placementV);
			printGrid(grid, true);
			System.out.println("~~~~~~~~~~ If everything went fine, you should see above the same as below this line: ~~~~~~~~~~" + "\n" + //
					"---------------------------------------" + "\n" + //
					"| . . . . . . . . . . . . . . . . . . |" + "\n" + //
					"| . . . . . . . . . . . . . . . . . . |" + "\n" + //
					"| . . . . . . . . . . . . . . . . . . |" + "\n" + //
					"| . . . . . . . . . . . . . . . . . . |" + "\n" + //
					"| . . . . . . . . . . . . . . . . . . |" + "\n" + //
					"| . . . . . . . . . . . . . . . . . . |" + "\n" + //
					"| . . . . . . . . . . . . . . . . . . |" + "\n" + //
					"| . . . . . . . . . . . . . . . . . . |" + "\n" + //
					"| . . . . . . . . . . . . . . . . . . |" + "\n" + //
					"| . . . . . . . . . . . . . . . . . . |" + "\n" + //
					"---------------------------------------" + "\n" + //
					"");
			System.out.println("========================================");
			// place/unplace H-V
			System.out.println("# set word \"" + wordsSpec[6] + "\"");
			placementH = grid.setWord(new FieldSpec(gridSpec[15]), wordsSpec[6]);
			printGrid(grid, true);
			System.out.println("~~~~~~~~~~ If everything went fine, you should see above the same as below this line: ~~~~~~~~~~" + "\n" + //
					"---------------------------------------" + "\n" + //
					"| . . . . . . . . . . . . . . . . . . |" + "\n" + //
					"| . . . . . . . . . . . . . . . . . . |" + "\n" + //
					"| . . . . . . . . . . . . . . . . . . |" + "\n" + //
					"| . . . . . . . . . . . . . . . . . . |" + "\n" + //
					"| . . . . . . . . . . . . . . . . . . |" + "\n" + //
					"| . . . . . . S T A R . . . . . . . . |" + "\n" + //
					"| . . . . . . . . . . . . . . . . . . |" + "\n" + //
					"| . . . . . . . . . . . . . . . . . . |" + "\n" + //
					"| . . . . . . . . . . . . . . . . . . |" + "\n" + //
					"| . . . . . . . . . . . . . . . . . . |" + "\n" + //
					"---------------------------------------" + "\n" + //
					"");
			System.out.println("========================================");
			System.out.println("# is word valid \"" + wordsSpec[wordsSpec.length - 3] + "\": " + grid.isWordValid(new FieldSpec(gridSpec[4]), wordsSpec[wordsSpec.length - 3]));
			System.out.println("========================================");
			System.out.println("# set word \"" + wordsSpec[wordsSpec.length - 3] + "\"");
			placementV = grid.setWord(new FieldSpec(gridSpec[4]), wordsSpec[wordsSpec.length - 3]);
			printGrid(grid, true);
			System.out.println("~~~~~~~~~~ If everything went fine, you should see above the same as below this line: ~~~~~~~~~~" + "\n" + //
					"---------------------------------------" + "\n" + //
					"| . . . . . . . . . E . . . . . . . . |" + "\n" + //
					"| . . . . . . . . . L . . . . . . . . |" + "\n" + //
					"| . . . . . . . . . E . . . . . . . . |" + "\n" + //
					"| . . . . . . . . . K . . . . . . . . |" + "\n" + //
					"| . . . . . . . . . T . . . . . . . . |" + "\n" + //
					"| . . . . . . S T A R . . . . . . . . |" + "\n" + //
					"| . . . . . . . . . I . . . . . . . . |" + "\n" + //
					"| . . . . . . . . . S . . . . . . . . |" + "\n" + //
					"| . . . . . . . . . C . . . . . . . . |" + "\n" + //
					"| . . . . . . . . . H . . . . . . . . |" + "\n" + //
					"---------------------------------------" + "\n" + //
					"");
			System.out.println("========================================");
			System.out.println("# remove word \"" + wordsSpec[wordsSpec.length - 3] + "\"");
			grid.removeWord(new FieldSpec(gridSpec[4]), wordsSpec[wordsSpec.length - 3], placementV);
			printGrid(grid, true);
			System.out.println("~~~~~~~~~~ If everything went fine, you should see above the same as below this line: ~~~~~~~~~~" + "\n" + //
					"---------------------------------------" + "\n" + //
					"| . . . . . . . . . . . . . . . . . . |" + "\n" + //
					"| . . . . . . . . . . . . . . . . . . |" + "\n" + //
					"| . . . . . . . . . . . . . . . . . . |" + "\n" + //
					"| . . . . . . . . . . . . . . . . . . |" + "\n" + //
					"| . . . . . . . . . . . . . . . . . . |" + "\n" + //
					"| . . . . . . S T A R . . . . . . . . |" + "\n" + //
					"| . . . . . . . . . . . . . . . . . . |" + "\n" + //
					"| . . . . . . . . . . . . . . . . . . |" + "\n" + //
					"| . . . . . . . . . . . . . . . . . . |" + "\n" + //
					"| . . . . . . . . . . . . . . . . . . |" + "\n" + //
					"---------------------------------------" + "\n" + //
					"");
			System.out.println("========================================");
			System.out.println("# remove word \"" + wordsSpec[6] + "\"");
			grid.removeWord(new FieldSpec(gridSpec[15]), wordsSpec[6], placementH);
			printGrid(grid, true);
			System.out.println("~~~~~~~~~~ If everything went fine, you should see above the same as below this line: ~~~~~~~~~~" + "\n" + //
					"---------------------------------------" + "\n" + //
					"| . . . . . . . . . . . . . . . . . . |" + "\n" + //
					"| . . . . . . . . . . . . . . . . . . |" + "\n" + //
					"| . . . . . . . . . . . . . . . . . . |" + "\n" + //
					"| . . . . . . . . . . . . . . . . . . |" + "\n" + //
					"| . . . . . . . . . . . . . . . . . . |" + "\n" + //
					"| . . . . . . . . . . . . . . . . . . |" + "\n" + //
					"| . . . . . . . . . . . . . . . . . . |" + "\n" + //
					"| . . . . . . . . . . . . . . . . . . |" + "\n" + //
					"| . . . . . . . . . . . . . . . . . . |" + "\n" + //
					"| . . . . . . . . . . . . . . . . . . |" + "\n" + //
					"---------------------------------------" + "\n" + //
					"");
			System.out.println("========================================");
		}
		// change slightly and than re-check riddle
		wordsSpec[0] = "JOHNDOE";
		try {
			spec = AbstractCrosswordSpec.instantiate(gridSpec, wordsSpec);
			evaluateSpec = 0;
		} catch (IllegalCrosswordSpecException e) {
			spec = null;
			evaluateSpec = e.getCode();
		}
		System.out.println("# evaluateSpec: " + evaluateSpec); // => code 8
		System.out.println("========================================");
	}

	private static final void mainTestBaby() {
		try {
			printGrid(CrosswordSolver.solve(AbstractCrosswordSpec.instantiate(gridSpec_PUBLIC_MAIN_TEST_BABY, wordsSpec_PUBLIC_MAIN_TEST_BABY)), false);
		} catch (IllegalCrosswordSpecException e) {
			System.out.println(e);
		}
		System.out.println("~~~~~~~~~~ If everything went fine, you should see above the same as below this line: ~~~~~~~~~~" + "\n" + //
				"---------------------------------------" + "\n" + //
				"| S I G N A T U R   E I F O E R M I G |" + "\n" + //
				"| C       R     O   L     R     O   E |" + "\n" + //
				"| H A U P T     B   E     B     E   S |" + "\n" + //
				"| W       I N G E   K O P I E   B   P |" + "\n" + //
				"| I N S E L   A     T   R       E   E |" + "\n" + //
				"| M       L   S T A R   I N S U L I N |" + "\n" + //
				"| M A S S E   O     I   M           S |" + "\n" + //
				"| B   E   R O L L   S T A M M G A S T |" + "\n" + //
				"| A   I   I   I     C   E           E |" + "\n" + //
				"| D O L D E   N O A H   R A U C H E R |" + "\n" + //
				"---------------------------------------" + "\n" + //
				"");
	}

	private static final void mainTestEasy() {
		try {
			printGrid(CrosswordSolver.solve(AbstractCrosswordSpec.instantiate(gridSpec_PUBLIC_MAIN_TEST_EASY, wordsSpec_PUBLIC_MAIN_TEST_EASY)), false);
		} catch (IllegalCrosswordSpecException e) {
			System.out.println(e);
		}
		System.out.println("~~~~~~~~~~ If everything went fine, you should see above the same as below this line: ~~~~~~~~~~" + "\n" + //
				"-----------------------------------------" + "\n" + //
				"| T O U R I S M U S   B E W A C H U N G |" + "\n" + //
				"| E     E         C   E     T   O     E |" + "\n" + //
				"| L A U S B U B   H   I     T   F     A |" + "\n" + //
				"| E     I     A N L A S S   A B R E D E |" + "\n" + //
				"| S     D     N   E   S     C   A     S |" + "\n" + //
				"| K R I E G E N   P   E     K   T     T |" + "\n" + //
				"| O     N     E   P H R A S E           |" + "\n" + //
				"| P O S T K A R T E     B         O   T |" + "\n" + //
				"|         O           P L A P P E R E R |" + "\n" + //
				"| S P R I N G E R       E     A   D   A |" + "\n" + //
				"| T       S     E R Z E N G E L   N   G |" + "\n" + //
				"| R E I S E W E G       K     E   E   Z |" + "\n" + //
				"| A       R     I     N U S S T O R T E |" + "\n" + //
				"| F       V     O       N     T       I |" + "\n" + //
				"| E R N T E M O N D     G A L E R I S T |" + "\n" + //
				"-----------------------------------------" + "\n" + //
				"");
	}

	private static final void printGrid(CrosswordGrid grid, boolean gridFill) {
		if (grid != null && grid.getSolution() != null && grid.getSolution()[0] != null) {
			char[][] grid_actual = grid.getSolution();
			for (int i = grid_actual[0].length; i >= 0; i--) {
				System.out.print(i != 0 ? "--" : "---\n");
			}
			for (char[] row : grid_actual) {
				System.out.print("| ");
				if (row != null) {
					for (char field : row) {
						System.out.print((field != 0 ? field : gridFill ? "." : " ") + " ");
					}
				}
				System.out.println("|");
			}
			for (int i = grid_actual[0].length; i >= 0; i--) {
				System.out.print(i != 0 ? "--" : "---\n");
			}
		}
	}

	// ========== SPECIAL MAIN ==========
	// nothing to do ;) - please do nothing here:
	public static void main(String args[]) {
		// to compile on command line: javac -cp .:/usr/share/java/junit4.jar *.java
		// to run on command line: java -cp .:/usr/share/java/junit4.jar <nameOfThisClass>

		if (args.length == 0) {
			// starts junit runner - don't try to understand!
			org.junit.runner.JUnitCore.main(new Object() {
			}.getClass().getEnclosingClass().getSimpleName());
			System.out.println("########## HINWEIS: Starte mich mal mit mind. einem beliebigen Argument... ;) ##########");
		} else {
			System.out.println("--- SMOKY --- SMOKY --- SMOKY --- SMOKY --- SMOKY --- SMOKY --- SMOKY --- SMOKY ---");
			mainTestSmoky();
			System.out.println("\n\n.oO BABY Oo.oO BABY Oo.oO BABY Oo.oO BABY Oo.oO BABY Oo.oO BABY Oo.oO BABY Oo.oO BABY Oo.");
			mainTestBaby();
			System.out.println("\n\n### EASY ### EASY ### EASY ### EASY ### EASY ### EASY ### EASY ### EASY ### EASY ###");
			mainTestEasy();
		}
	}
}