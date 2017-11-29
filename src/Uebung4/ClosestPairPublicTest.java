import static org.junit.Assert.*;
import java.util.*;
import org.junit.*;

public class ClosestPairPublicTest {
	// ========== SYSTEM ==========
	protected static final double PRECISION = 1.0E-6d;
	static final String EX_ID = "ClosestPair";

	// ========== Big Brother ==========
	protected static final class BigBrother {
		protected final int[] callCounters = { 0, 0, 0, 0 };

		protected final void resetBigBrother() {
			for (int i = 0; i < callCounters.length; i++) {
				callCounters[i] = 0;
			}
		}

		protected final void checkStack(int[][] expectedCalls) {
			assertTrue("Wrong number of calls to ClosestPair.closestPointHelper: " + expectedCalls[0][0] + " <= [" + BIG_BROTHER.callCounters[0] + "] <= " + expectedCalls[0][1], expectedCalls[0][0] <= BIG_BROTHER.callCounters[0] && BIG_BROTHER.callCounters[0] <= expectedCalls[0][1]);
			assertTrue("Wrong number of calls to ClosestPair.closestPoint: " + expectedCalls[1][0] + " <= [" + BIG_BROTHER.callCounters[1] + "] <= " + expectedCalls[1][1], expectedCalls[1][0] <= BIG_BROTHER.callCounters[1] && BIG_BROTHER.callCounters[1] <= expectedCalls[1][1]);
			assertTrue("Wrong number of calls to ClosestPair.closestPair: " + expectedCalls[2][0] + " <= [" + BIG_BROTHER.callCounters[2] + "] <= " + expectedCalls[2][1], expectedCalls[2][0] <= BIG_BROTHER.callCounters[2] && BIG_BROTHER.callCounters[2] <= expectedCalls[2][1]);
			assertTrue("Wrong number of calls to ClosestPair.<unknownIllegalMethod>: " + expectedCalls[3][0] + " <= [" + BIG_BROTHER.callCounters[3] + "] <= " + expectedCalls[3][1], expectedCalls[3][0] <= BIG_BROTHER.callCounters[3] && BIG_BROTHER.callCounters[3] <= expectedCalls[3][1]);
		}

		protected final ClosestPairHelper cph = new ClosestPairHelper() {
			public final void traceMe() {
				int[] newCallCounters = { 0, 0, 0, 0 };
				StackTraceElement[] st = Thread.currentThread().getStackTrace();
				for (StackTraceElement ste : st) {
					if (ste.getClassName().equals("ClosestPair")) {
						if (ste.getMethodName().equals("closestPointHelper")) {
							newCallCounters[0]++;
						} else if (ste.getMethodName().equals("closestPoint")) {
							newCallCounters[1]++;
						} else if (ste.getMethodName().equals("closestPair")) {
							newCallCounters[2]++;
						} else {
							newCallCounters[3]++;
						}
					}
				}
				for (int i = 0; i < callCounters.length; i++) {
					callCounters[i] = (callCounters[i] < newCallCounters[i] ? newCallCounters[i] : callCounters[i]);
				}
			}

			public final double distance(double[] p1, double[] p2) {
				return Math.sqrt((p2[0] - p1[0]) * (p2[0] - p1[0]) + (p2[1] - p1[1]) * (p2[1] - p1[1]));
			}

			public final double[] appendPoint(double[] array, double[] p) {
				double[] newArray = java.util.Arrays.copyOf(array, array.length + 2);
				newArray[newArray.length - 2] = p[0];
				newArray[newArray.length - 1] = p[1];
				return newArray;
			}

			public final double[] appendDistance(double[] array, double d) {
				double[] newArray = java.util.Arrays.copyOf(array, array.length + 1);
				newArray[newArray.length - 1] = d;
				return newArray;
			}

			public final double[][] skipFirstPoint(double[][] ps) {
				return java.util.Arrays.copyOfRange(ps, 1, ps.length);
			}
		};
	}

	protected static final BigBrother BIG_BROTHER = new BigBrother();
	protected static final ClosestPairHelper CPH = BIG_BROTHER.cph;

	// ========== SOME CONSTANTS ==========
	private static final double[] p = { 5, 0.5 }; // a single point - say P1(5, 0.5)
	private static final double[] pd = { 6, 0.5, 1 }; // a point with distance - say P2(6, 0.5) with distance 1 from P1
	private static final double[][] ps = {}; // an empty list of points
	private static final String p_pd_ps = "p = " + Arrays.toString(p) + ", pd = " + Arrays.toString(pd) + ", ps = " + Arrays.deepToString(ps);

	// ========== SMOKE TESTS ==========
	@Test(timeout = 1000)
	public void test__closestPointHelper__PUBLIC_TEST() {
		BIG_BROTHER.resetBigBrother();
		double[] expecteds = new double[] { 6, 0.5, 1 }; // pd
		double[] actuals = ClosestPair.closestPointHelper(CPH, p, pd, ps);
		assertArrayEquals("ClosestPair.closestPointHelper(CPH, p, pd, ps)" + " mit " + p_pd_ps, expecteds, actuals, PRECISION);
		BIG_BROTHER.checkStack(new int[][] { { 1, 1 }, { 0, 0 }, { 0, 0 }, { 0, 0 } });
	}

	@Test(timeout = 1000)
	public void test__closestPoint__PUBLIC_TEST() {
		BIG_BROTHER.resetBigBrother();
		double[] expecteds = new double[] { 5, 0.5, 0 }; // ClosestPairHelper.appendDistance(p, 0)
		double[] actuals = ClosestPair.closestPoint(CPH, p, ps);
		assertArrayEquals("ClosestPair.closestPoint(CPH, p, ps)" + " mit " + p_pd_ps, expecteds, actuals, PRECISION);
		BIG_BROTHER.checkStack(new int[][] { { 0, 0 }, { 1, 1 }, { 0, 0 }, { 0, 0 } });
	}

	@Test(timeout = 1000)
	public void test__closestPair__PUBLIC_TEST() {
		BIG_BROTHER.resetBigBrother();
		double[] expecteds = new double[] { Double.NaN, Double.NaN, Double.NaN, Double.NaN, Double.NaN }; // ClosestPairHelper.PPD_NO_RESULT
		double[] actuals = ClosestPair.closestPair(CPH, ps);
		assertArrayEquals("ClosestPair.closestPair(CPH, ps)" + " mit " + p_pd_ps, expecteds, actuals, PRECISION);
		BIG_BROTHER.checkStack(new int[][] { { 0, 0 }, { 0, 0 }, { 1, 1 }, { 0, 0 } });
	}

	// ========== MORE RECURSIVE TESTS ==========
	private static final double[] p_r = { 42, 42 };
	private static final double[] pd_r = { 666, 666, 882.469262920811310452 };
	private static final double[][] ps_r = { { 4711, 4711 }, { 123, 123 }, { 123456789, 987654321 }, { 100, 100 }, { 999, 999 }, { 43, 43 } };
	private static final String p_pd_ps_r = "p_r = " + Arrays.toString(p_r) + ", pd_r = " + Arrays.toString(pd_r) + ", ps_r = " + Arrays.deepToString(ps_r);

	@Test(timeout = 1000)
	public void test__closestPointHelper__RECURSION__PUBLIC_TEST__THIS_TEST_IS_VERY_IMPORTANT_IF_IT_FAILS_THEN_YOU_WILL_GET_NO_POINTS_AT_ALL() {
		BIG_BROTHER.resetBigBrother();
		double[] expecteds = new double[] { 43, 43, 1.414213562373095049 };
		double[] actuals = ClosestPair.closestPointHelper(CPH, p_r, pd_r, ps_r);
		assertArrayEquals("ClosestPair.closestPointHelper(CPH, p_r, pd_r, ps_r)" + " mit " + p_pd_ps_r, expecteds, actuals, PRECISION);
		BIG_BROTHER.checkStack(new int[][] { { ps_r.length, ps_r.length + 2 }, { 0, 0 }, { 0, 0 }, { 0, 0 } });
	}

	@Test(timeout = 1000)
	public void test__closestPoint__RECURSION__PUBLIC_TEST__THIS_TEST_IS_VERY_IMPORTANT_IF_IT_FAILS_THEN_YOU_WILL_GET_NO_POINTS_AT_ALL() {
		BIG_BROTHER.resetBigBrother();
		double[] expecteds = new double[] { 43, 43, 1.414213562373095049 };
		double[] actuals = ClosestPair.closestPoint(CPH, p_r, ps_r);
		assertArrayEquals("ClosestPair.closestPoint(CPH, p_r, pd_r, ps_r)" + " mit " + p_pd_ps_r, expecteds, actuals, PRECISION);
		BIG_BROTHER.checkStack(new int[][] { { ps_r.length, ps_r.length + 2 }, { 1, 1 }, { 0, 0 }, { 0, 0 } });
	}

	@Test(timeout = 1000)
	public void test__closestPair__RECURSION__PUBLIC_TEST__THIS_TEST_IS_VERY_IMPORTANT_IF_IT_FAILS_THEN_YOU_WILL_GET_NO_POINTS_AT_ALL() {
		BIG_BROTHER.resetBigBrother();
		double[] expecteds = new double[] { 123, 123, 100, 100, 32.526911934581186122 };
		double[] actuals = ClosestPair.closestPair(CPH, ps_r);
		System.out.println(Arrays.toString(actuals));
		assertArrayEquals("ClosestPair.closestPair(CPH, p_r, ps_r)" + " mit " + p_pd_ps_r, expecteds, actuals, PRECISION);
		BIG_BROTHER.checkStack(new int[][] { { ps_r.length - 1, ps_r.length + 1 }, { 1, 1 }, { ps_r.length, ps_r.length + 2 }, { 0, 0 } });
	}

	// ========== MORE RECURSIVE TESTS WITH ANTICHEAT ==========
	private static final Random random = new Random(4711_0815_666_42L);
	private static final double[] p_mr = new double[2];
	private static final double[] pd_mr = new double[3];
	private static final double[][] ps_mr = new double[42 + random.nextInt(42)][2]; // "random" number of "randomly" distributed points
	private static int p1 = random.nextInt(ps_mr.length);
	private static int p2 = random.nextInt(ps_mr.length);
	static {
		// create "randomly" distributed points
		ps_mr[0][0] = 42 + random.nextInt(42);
		ps_mr[0][1] = 42 + random.nextInt(42);
		for (int i = 1; i < ps_mr.length; i++) {
			ps_mr[i][0] = ps_mr[i - 1][0] + 42 + random.nextInt(42);
			ps_mr[i][1] = ps_mr[i - 1][1] + 42 + random.nextInt(42);
		}
		// make two "randomly" chosen points neighbours
		p2 = p1 == p2 ? (p2 + 1) % ps_mr.length : p2;
		if (p1 > p2) {
			int t = p1;
			p1 = p2;
			p2 = t;
		}
		ps_mr[p2][0] = ps_mr[p1][0] + 7 + random.nextInt(7);
		ps_mr[p2][1] = ps_mr[p1][1] + 7 + random.nextInt(7);
		// "randomly" choose an additional neighbouring point
		p_mr[0] = ps_mr[p1][0] - 7 - random.nextInt(7);
		p_mr[1] = ps_mr[p1][1] - 7 - random.nextInt(7);
		// "randomly" choose an initial point far away
		int d0 = 666 + random.nextInt(42), d1 = 666 + random.nextInt(42);
		pd_mr[0] = ps_mr[ps_mr.length - 1][0] + d0;
		pd_mr[1] = ps_mr[ps_mr.length - 1][0] + d1;
		pd_mr[2] = CPH.distance(ps_mr[ps_mr.length - 1], pd_mr);
	}
	private static final String p_pd_ps_mr = "p_mr = " + Arrays.toString(p_mr) + ", pd_mr = " + Arrays.toString(pd_mr) + ", ps_mr = " + Arrays.deepToString(ps_mr);

	@Test(timeout = 1000)
	public void test__closestPointHelper__MORE_RECURSION__PUBLIC_TEST__THIS_TEST_IS_VERY_IMPORTANT_IF_IT_FAILS_THEN_YOU_WILL_GET_NO_POINTS_AT_ALL() {
		BIG_BROTHER.resetBigBrother();
		double[] expecteds = new double[] { ps_mr[p1][0], ps_mr[p1][1], CPH.distance(p_mr, ps_mr[p1]) };
		double[] actuals = ClosestPair.closestPointHelper(CPH, p_mr, pd_mr, ps_mr);
		assertArrayEquals("ClosestPair.closestPointHelper(CPH, p_mr, pd_mr, ps_mr)" + " mit " + p_pd_ps_mr, expecteds, actuals, PRECISION);
		BIG_BROTHER.checkStack(new int[][] { { ps_mr.length, ps_mr.length + 2 }, { 0, 0 }, { 0, 0 }, { 0, 0 } });
	}

	@Test(timeout = 1000)
	public void test__closestPoint__MORE_RECURSION__PUBLIC_TEST__THIS_TEST_IS_VERY_IMPORTANT_IF_IT_FAILS_THEN_YOU_WILL_GET_NO_POINTS_AT_ALL() {
		BIG_BROTHER.resetBigBrother();
		double[] expecteds = new double[] { ps_mr[p1][0], ps_mr[p1][1], CPH.distance(p_mr, ps_mr[p1]) };
		double[] actuals = ClosestPair.closestPoint(CPH, p_mr, ps_mr);
		assertArrayEquals("ClosestPair.closestPoint(CPH, p_mr, pd_mr, ps_mr)" + " mit " + p_pd_ps_mr, expecteds, actuals, PRECISION);
		BIG_BROTHER.checkStack(new int[][] { { ps_mr.length, ps_mr.length + 2 }, { 1, 1 }, { 0, 0 }, { 0, 0 } });
	}

	@Test(timeout = 1000)
	public void test__closestPair__MORE_RECURSION__PUBLIC_TEST__THIS_TEST_IS_VERY_IMPORTANT_IF_IT_FAILS_THEN_YOU_WILL_GET_NO_POINTS_AT_ALL() {
		BIG_BROTHER.resetBigBrother();
		double[] expecteds = new double[] { ps_mr[p1][0], ps_mr[p1][1], ps_mr[p2][0], ps_mr[p2][1], CPH.distance(ps_mr[p1], ps_mr[p2]) };
		double[] actuals = ClosestPair.closestPair(CPH, ps_mr);
		assertArrayEquals("ClosestPair.closestPair(CPH, p_mr, ps_mr)" + " mit " + p_pd_ps_mr, expecteds, actuals, PRECISION);
		BIG_BROTHER.checkStack(new int[][] { { ps_mr.length - 1, ps_mr.length + 1 }, { 1, 1 }, { ps_mr.length, ps_mr.length + 2 }, { 0, 0 } });
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