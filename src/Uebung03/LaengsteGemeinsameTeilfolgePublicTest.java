import static org.junit.Assert.*;
import java.util.*;
import org.junit.*;

public class LaengsteGemeinsameTeilfolgePublicTest {

	private static final void check(long[] x, long[] y, long[] expecteds) {
		long[] actuals = LaengsteGemeinsameTeilfolge.lgt(x, y);
		assertArrayEquals("LaengsteGemeinsameTeilfolge.lgt(" + Arrays.toString(x) + ", " + Arrays.toString(y) + ") liefert falsche Ergebnisse: " + Arrays.toString(actuals) + " statt " + Arrays.toString(expecteds), expecteds, actuals);
	}

	private static final void check(long[][] z, long[] expecteds) {
		long[] actuals = LaengsteGemeinsameTeilfolge.lgt(z);
		assertArrayEquals("LaengsteGemeinsameTeilfolge.lgt(" + Arrays.deepToString(z) + ") liefert falsche Ergebnisse: " + Arrays.toString(actuals) + " statt " + Arrays.toString(expecteds), expecteds, actuals);
	}

	// ---------- lgt_LongLong ----------
	@Test(timeout = 666)
	public void pubTest_lgt_LongLong() {
		long[] x = { 42, 4711, 42, 0x815, 42, 4711, 0x815 };
		long[] y = { 4711, 42, 0x815, 0x815, 42, 4711, 4711, 0x815 };
		long[] expecteds = { 4711, 42, 0x815, 42, 4711, 0x815 };
		check(x, y, expecteds);
	}

	// ---------- lgt_LongArray ----------
	@Test(timeout = 666)
	public void pubTest_lgt_LongArray() {
		long[][] z = { { 42, 4711, 42, 0x815, 42, 4711, 0x815 }, { 4711, 42, 0x815, 0x815, 42, 4711, 4711, 0x815 } };
		long[] expecteds = { 4711, 42, 0x815, 42, 4711, 0x815 };
		check(z, expecteds);
	}

	@Test(timeout = 666)
	public void pubTest_lgt_LongArray_with_null() {
		long[][] z = { { 42, 4711, 42, 0x815, 42, 4711, 0x815 }, null, { 4711, 42, 0x815, 0x815, 42, 4711, 4711, 0x815 } };
		long[] expecteds = {};
		check(z, expecteds);
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