import java.lang.reflect.*;
import java.util.*;
import java.io.*;
import org.junit.*;

public class BranchCoveragePublicTest {
	// ========== SYSTEM ==========
	protected static final String TEST = "BranchCoverage-Test";

	// ========== Test the test #-) ==========
	@org.junit.Test(timeout = 666)
	public void test__BrachCoverage_of__top__PUBLIC_TEST() {
		scan_test_case_methods();
		String[][] edges = { { "I", "1" }, { "1", "2" }, { "2", "3" }, { "3", "4" }, { "4", "E" }, };
		BranchCoveragePublicTest.runTestsAndCheckTrace(testCaseMethods, "PriorityQueue", "top", edges, true);
	}

	@org.junit.Test(timeout = 666)
	public void test__use_of_Assert__PUBLIC_TEST() {
		scan_test_case_methods();
		BranchCoveragePublicTest.runTestsAndCheckTrace(testCaseMethods, "PriorityQueue", "top", null, true);
	}

	// ========== AuD-fake-runner and coverage checker ==========
	private List<Method> testCaseMethods = new LinkedList<>();

	@org.junit.Test(timeout = 666)
	@Ignore // DO NOT DELETE ME ;)...
	public void scan_test_case_methods() {
		testCaseMethods = new LinkedList<>();
		for (Method testCaseMethod : PriorityQueueTest.class.getMethods()) {
			if (testCaseMethod.isAnnotationPresent(Test.class) && testCaseMethod.getParameterTypes().length == 0) {
				testCaseMethods.add(testCaseMethod);
			}
		}
		org.junit.Assert.assertNotEquals("You should provide at least one test case (test method)!", 0, testCaseMethods.size());
	}

	protected static void runTestsAndCheckTrace(List<Method> testCaseMethods, String className, String methodName, String[][] edges, boolean noFailedTest) {
		Log.resetTrace();
		Class<PriorityQueueTest> testClass = PriorityQueueTest.class;
		int testsRun = 0, testsFailed = 0;
		String result = "";
		for (Method testCaseMethod : testCaseMethods) {
			try {
				testsRun++;
				testCaseMethod.invoke(testClass.newInstance());
			} catch (Throwable t) {
				testsFailed++;
				result += testsFailed + ") " + testCaseMethod.getName() + "(" + testClass.getName() + ")" + "\n";
				StringWriter sw = new StringWriter();
				PrintWriter pw = new PrintWriter(sw);
				t.getCause().printStackTrace(pw);
				result += sw.toString();
			}
		}
		org.junit.Assert.assertNotEquals("You should provide at least one test case (test method)!", 0, testsRun);
		if (noFailedTest) {
			org.junit.Assert.assertEquals("Your test cases had failures, although nothing should have gone wrong here...", "", result);
		} else {
			org.junit.Assert.assertNotEquals("Your test cases should have had failures due to your asserts, but it doesn't seem that you detected them...", "", result);
		}
		String trace = Log.getTrace();
		if (edges != null) {
			String[] expectedTraceEntries = new String[edges.length];
			for (int i = 0; i < edges.length; i++) {
				expectedTraceEntries[i] = "[" + edges[i][0] + "@" + className + "." + methodName + "][" + edges[i][1] + "@" + className + "." + methodName + "]";
			}
			for (String expectedTraceEntry : expectedTraceEntries) {
				org.junit.Assert.assertTrue("Kante nicht ueberdeckt: " + expectedTraceEntry, trace.contains(expectedTraceEntry));
			}
		} else {
			int assertCalls = 0;
			String assertLogString = "[A@Assert.assertEquals<";
			for (String last : new String[] { "[E@PriorityQueue.length]", "[E@PriorityQueue.top]", "[E@PriorityQueue.toString]" }) {
				int pos = 0;
				while (pos >= 0) {
					pos = trace.indexOf(last, pos);
					if (pos >= 0) {
						pos += last.length();
						org.junit.Assert.assertTrue("You should have called Assert.assertEquals(...) immediately after " + last, trace.startsWith(assertLogString, pos));
						if (!noFailedTest && last.endsWith("length]")) {
							org.junit.Assert.assertTrue("I am missing the real actual value in the assert message after *.length!", trace.startsWith("-47110815", pos + assertLogString.length()));
						} else if (!noFailedTest) {
							org.junit.Assert.assertTrue("I am missing the real actual value in the assert message after *.top/toString!", trace.startsWith("###You###Got###PRANKED###AuD###LOL###", pos + assertLogString.length()));
						}
						assertCalls++;
					}
					pos = trace.indexOf(last, pos);
				}
			}
			org.junit.Assert.assertNotEquals("You should have called Assert.assertEquals(...) at least three times in your tests (at least once per test call to each of the three methods returning a value)!", 0, assertCalls);
		}
	}

	// ========== main ==========
	// nothing to do ;) - please do nothing here:
	public static void main(String args[]) {
		// to compile on command line: javac -cp .:/usr/share/java/junit4.jar *.java
		// to run on command line: java -cp .:/usr/share/java/junit4.jar <nameOfThisClass>

		if (args.length == 0) {
			// starts junit runner - don't try to understand!
			org.junit.runner.JUnitCore.main(new Object() {
			}.getClass().getEnclosingClass().getSimpleName());
			System.out.println("HINT: Run me with exactly one argument denoting the name of your test class (e.g. \"TestSuite\")...");
		} else if (args.length == 1) {
			try {
				Class<?> testClass = ClassLoader.getSystemClassLoader().loadClass(args[0]);
				System.out.println("JUnit AuD-fake-version 0.1");
				int testsRun = 0, testsFailed = 0;
				String result = "";
				long startTime = System.currentTimeMillis();
				System.setErr(new PrintStream(new ByteArrayOutputStream()));
				for (Method testCaseMethod : testClass.getMethods()) {
					if (testCaseMethod.isAnnotationPresent(Test.class) && testCaseMethod.getParameterTypes().length == 0) {
						try {
							testsRun++;
							testCaseMethod.invoke(testClass.newInstance());
							System.out.print(".");
						} catch (Throwable t) {
							testsFailed++;
							System.out.print("E");
							result += testsFailed + ") " + testCaseMethod.getName() + "(" + testClass.getName() + ")" + "\n";
							StringWriter sw = new StringWriter();
							PrintWriter pw = new PrintWriter(sw);
							t.getCause().printStackTrace(pw);
							result += sw.toString();
						}
					}
				}
				long endTime = System.currentTimeMillis();
				System.out.println();
				System.out.println("Time: " + (endTime - startTime) / 1000d);
				if (testsFailed != 0) {
					System.out.println("There " + (testsFailed == 1 ? "was " : "were ") + testsFailed + " failure" + (testsFailed == 1 ? "" : "s") + ":");
					System.out.println(result);
					System.out.println("FAILURES!!!");
					System.out.println("Tests run: " + testsRun + ",  Failures: " + testsFailed);
				} else {
					System.out.println();
					System.out.println("OK (" + testsRun + " test" + (testsRun == 1 ? "" : "s") + ")");
				}
			} catch (ClassNotFoundException e) {
				System.out.println("Error: Could not find or load main class " + args[0]);
			}
		} else {
			System.out.println("HINT: Run me with NO or EXACTLY ONE argument denoting the name of your test class (e.g. \"TestSuite\")...");
		}
	}
}