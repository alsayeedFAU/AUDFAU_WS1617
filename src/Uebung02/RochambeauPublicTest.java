import static org.junit.Assert.*;
import org.junit.*;

public class RochambeauPublicTest {

	// ========== check ==========
	@Test(timeout = 666)
	public void test_check__valid_input__gives_0() {
		assertEquals(0, Rochambeau.check("BADECC"));
	}

	// ========== eval ==========
	@Test(timeout = 666)
	public void test_eval__player_Y_shalt_win__gives_m1() {
		assertEquals(-1, Rochambeau.eval('B', 'A'));
	}

	// ========== decide ==========
	@Test(timeout = 666)
	public void test_decide__example_input_from_sheet__gives_score_0_2() {
		assertArrayEquals(new int[] { 0, 2 }, Rochambeau.decide("BADECC"));
	}

	// ========== main ==========
	public static void main(String args[]) {
		// to compile on command line: javac -cp .:/usr/share/java/junit4.jar *.java
		// to run on command line: java -cp .:/usr/share/java/junit4.jar <nameOfThisClass>

		// starts junit runner - don't try to understand!
		org.junit.runner.JUnitCore.main(new Object() {
		}.getClass().getEnclosingClass().getSimpleName());
	}
}