import static org.junit.Assert.*;
import org.junit.*;

class VARIABLEKONSTANTEN {
	public static final String ALLOCATE = "ArrayFun.allocate";
	public static final String STUFF = "ArrayFun.stuff";
	public static final String CONVOLVE = "ArrayFun.convolve";
}

public class ArrayFunPublicTest {

	private static String poliere(String s) {
		return s.replaceAll(" ", "").replaceAll("\t", "").replaceAll("\n", "");
	}

	@Test(timeout = 666)
	public void test_ArrayFun_erzeuge_PUBLIC_TEST() {
		String expected = "[[[(0,0,0), (0,0,1), (0,0,2), (0,0,3)], [(0,1,0), (0,1,1), (0,1,2), (0,1,3)]], [[(1,0,0), (1,0,1), (1,0,2), (1,0,3)], [(1,1,0), (1,1,1), (1,1,2), (1,1,3)]], [[(2,0,0), (2,0,1), (2,0,2), (2,0,3)], [(2,1,0), (2,1,1), (2,1,2), (2,1,3)]]]";
		String[][][] r = ArrayFun.allocate(3, 2, 4);
		String actual = java.util.Arrays.deepToString(r);
		assertEquals(VARIABLEKONSTANTEN.ALLOCATE + "(3, 2, 4) funktioniert nicht.", poliere(expected), poliere(actual));
	}

	@Test(timeout = 666)
	public void test_ArrayFun_fuelle_PUBLIC_TEST() {
		final String[][][][] f = { { { { null, "null", null }, { "full", null }, { "null" }, null }, null }, { { {} } }, { null, { { "full" } } }, {} };
		String expected = "[[[[(0,0,0,0), (0,0,0,1), (0,0,0,2)], [(0,0,1,0), (0,0,1,1)], [(0,0,2,0)], null], null], [[[]]], [null, [[(2,1,0,0)]]], []]";
		String[][][][] r = ArrayFun.stuff(f);
		String actual = java.util.Arrays.deepToString(r);
		assertEquals(VARIABLEKONSTANTEN.STUFF + "(" + java.util.Arrays.deepToString(f) + ") funktioniert nicht.", poliere(expected), poliere(actual));
	}

	@Test(timeout = 666)
	public void test_ArrayFun_falte_PUBLIC_TEST() {
		final String[][][] f = { { { null, "Au", null, "D", null }, null, { null, null, null } }, {} };
		String expected = "[[AuD, null, null], []]";
		String[][] r = ArrayFun.convolve(f);
		String actual = java.util.Arrays.deepToString(r);
		assertEquals(VARIABLEKONSTANTEN.CONVOLVE + "(" + java.util.Arrays.deepToString(f) + ") funktioniert nicht.", poliere(expected), poliere(actual));
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