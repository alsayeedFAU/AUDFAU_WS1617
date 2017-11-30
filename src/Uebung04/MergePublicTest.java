import static org.junit.Assert.*;
import java.util.*;
import org.junit.*;

public class MergePublicTest {

	@Test(timeout = 1000)
	public void mainTest() {
		checkMerge(new int[] { 1 }, 0, "1");
	}

	@Test(timeout = 1000)
	public void testExample1() {
		int[] input = new int[] { 1, 2, 5, 5, 4 };
		String expected = "1 2 25 4";
		checkMerge(input, 0, expected);
	}

	@Test(timeout = 1000)
	public void testExample2() {
		int[] input = new int[] { 2, 2, 4, 6, 8 };
		String expected = "4 4 6 8";
		checkMerge(input, 0, expected);
	}

	@Test(timeout = 1000)
	public void testExample3() {
		int[] input = new int[] { 5, 5, 5, 6 };
		String expected = "125 6";
		checkMerge(input, 0, expected);
	}

	private void checkMerge(int[] input, int i, String expectedSolution) {
		final List<String> stackTrace = new ArrayList<>();
		String solution = Merge.merge(input, 0, new Merge() {
			@Override
			public void check() {
				stackTrace.clear();
				for (StackTraceElement ste : Thread.currentThread().getStackTrace()) {
					stackTrace.add(ste.getMethodName());
				}
			}
		}).replace("\"", "").trim();

		assertTrue(Collections.frequency(stackTrace, "merge") > 1 || i >= input.length
				|| i + 1 == input.length);
		assertEquals("merge(" + input + ", " + i + ")", expectedSolution, solution);
	}
}