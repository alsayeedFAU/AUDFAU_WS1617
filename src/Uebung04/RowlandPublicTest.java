import static org.junit.Assert.*;
import org.junit.*;
import java.lang.reflect.*;
import java.util.concurrent.atomic.*;

public class RowlandPublicTest {
	// ========== SYSTEM ==========
	public static final String ROWLANDNAIVE = "Rowland.rowlandNaive";
	public static final String OMITNAIVE = "Rowland.omitNaive";
	public static final String ROWLANDDP = "Rowland.rowlandDP";
	public static final String OMITDP = "Rowland.omitDP";

	// ==================== Intestines: Rowland ====================
	@Test(timeout = 500)
	public void test__Rowland_rowlandNaive__intestines__THIS_TEST_IS_VERY_IMPORTANT_IF_IT_FAILS_THEN_YOU_WILL_GET_NO_POINTS_AT_ALL() {
		test_Rowland_intestines_THIS_TEST_IS_VERY_IMPORTANT_IF_IT_FAILS_THEN_YOU_WILL_GET_NO_POINTS_AT_ALL();
	}

	@Test(timeout = 500)
	public void test__Rowland_omitNaive__intestines__THIS_TEST_IS_VERY_IMPORTANT_IF_IT_FAILS_THEN_YOU_WILL_GET_NO_POINTS_AT_ALL() {
		test_Rowland_intestines_THIS_TEST_IS_VERY_IMPORTANT_IF_IT_FAILS_THEN_YOU_WILL_GET_NO_POINTS_AT_ALL();
	}

	@Test(timeout = 500)
	public void test__Rowland_rowlandDP__intestines__THIS_TEST_IS_VERY_IMPORTANT_IF_IT_FAILS_THEN_YOU_WILL_GET_NO_POINTS_AT_ALL() {
		test_Rowland_intestines_THIS_TEST_IS_VERY_IMPORTANT_IF_IT_FAILS_THEN_YOU_WILL_GET_NO_POINTS_AT_ALL();
	}

	@Test(timeout = 500)
	public void test__Rowland_omitDP__intestines__THIS_TEST_IS_VERY_IMPORTANT_IF_IT_FAILS_THEN_YOU_WILL_GET_NO_POINTS_AT_ALL() {
		test_Rowland_intestines_THIS_TEST_IS_VERY_IMPORTANT_IF_IT_FAILS_THEN_YOU_WILL_GET_NO_POINTS_AT_ALL();
	}

	private void test_Rowland_intestines_THIS_TEST_IS_VERY_IMPORTANT_IF_IT_FAILS_THEN_YOU_WILL_GET_NO_POINTS_AT_ALL() {
		try {
			Class<?> clazz = Class.forName("Rowland");
			assertFalse("Ist keine Klasse sondern eine Annotation.", clazz.isAnnotation());
			assertFalse("Ist keine Klasse sondern ein Enum.", clazz.isEnum());
			assertFalse("Ist keine Klasse sondern ein Interface.", clazz.isInterface());
			assertFalse("Ist faelschlicherweise >abstract<", Modifier.isAbstract(clazz.getModifiers()));
			assertEquals("Darf keine Schnittstelle implementieren.", 0, clazz.getInterfaces().length);
			assertEquals("Muss direkt von Object erben.", Object.class, clazz.getSuperclass());
			assertEquals("Hat falsche Anzahl an anderen Schweinereien.", 0, clazz.getDeclaredAnnotations().length);
			assertEquals("Hat falsche Anzahl an anderen Innereien.", 0, clazz.getDeclaredClasses().length);
			assertEquals("Hat falsche Anzahl an Konstruktoren.", 1, clazz.getDeclaredConstructors().length);
			int dpmid = 0;
			if (clazz.getDeclaredFields().length == 2) {
				if ("$assertionsDisabled".equals(clazz.getDeclaredFields()[0].getName())) {
					dpmid = 1;
				}
			} else {
				assertEquals("Hat falsche Anzahl an Attributen.", 1, clazz.getDeclaredFields().length);
			}
			assertNotEquals("Hat unerwartete Attribute.", "$assertionsDisabled", clazz.getDeclaredFields()[dpmid].getName());
			assertTrue("Attribut ist faelschlicherweise nicht >private<.", Modifier.isPrivate(clazz.getDeclaredFields()[dpmid].getModifiers()));
			assertTrue("Attribut ist faelschlicherweise Instanzattribut.", Modifier.isStatic(clazz.getDeclaredFields()[dpmid].getModifiers()));
			assertTrue("Attribut muss ein 1D-int[]-Feld sein.", clazz.getDeclaredFields()[dpmid].getType().isArray());
			assertFalse("Attribut muss ein 1D-int[]-Feld sein.", clazz.getDeclaredFields()[dpmid].getType().getComponentType().isArray());
			assertSame("Attribut muss ein 1D-int[]-Feld sein.", int.class, clazz.getDeclaredFields()[dpmid].getType().getComponentType());
			Constructor<?> constructor = clazz.getDeclaredConstructor(); // default cons!
			assertNotNull("Konstruktor inkl. -parameter: inkorrekt", constructor);
			assertTrue("Konstruktor inkl. -parameter: Sichtbarkeit ist nicht >public<", Modifier.isPublic(constructor.getModifiers()));
			assertEquals("Hat falsche Anzahl an Methoden.", 5, clazz.getDeclaredMethods().length);
			for (Method method : clazz.getDeclaredMethods()) {
				assertTrue(method + " - Methode: Ist faelschlicherweise Instanzmethode.", Modifier.isStatic(method.getModifiers()));
				assertFalse(method + " - Methode: Ist faelschlicherweise >abstract<", Modifier.isAbstract(method.getModifiers()));
				assertTrue(method + " - Methode: Sichtbarkeit ist nicht >public<", Modifier.isPublic(method.getModifiers()));
				if ("resetDP".equals(method.getName())) {
					assertEquals(method + " - Methode: Hat falsche Anzahl Parameter.", 0, method.getParameterTypes().length);
					assertEquals(method + " - Methode: Rueckgabetyp ist falsch.", void.class, method.getReturnType());
				} else {
					assertEquals(method + " - Methode: Hat falsche Anzahl Parameter.", 2, method.getParameterTypes().length);
					assertSame(method + " - Methode: Hat falsche Parametertypen.", GCD.class, method.getParameterTypes()[0]);
					assertSame(method + " - Methode: Hat falsche Parametertypen.", int.class, method.getParameterTypes()[1]);
					assertEquals(method + " - Methode: Rueckgabetyp ist falsch.", int.class, method.getReturnType());
				}
			}
		} catch (ClassNotFoundException e) {
			fail("Klasse nicht gefunden: " + e.getMessage());
		} catch (NoSuchMethodException e) {
			fail("Konstruktor oder Methoden nicht gefunden oder inkorrekt: " + e.getMessage());
		}
	}

	// ========== Big Brother ==========
	protected static final class BigBrother {
		protected final AtomicInteger[] callCounters = { new AtomicInteger(), new AtomicInteger(), new AtomicInteger(), new AtomicInteger(), new AtomicInteger() };

		protected final void resetBigBrother() {
			for (AtomicInteger ai : callCounters) {
				ai.set(0);
			}
		}

		protected final GCD gcd = new GCD() {
			public int gcd(int a, int b) {
				callCounters[0].addAndGet(1);
				StackTraceElement[] st = Thread.currentThread().getStackTrace();
				for (StackTraceElement ste : st) {
					if (ste.getClassName().equals("Rowland")) {
						if (ste.getMethodName().equals("rowlandNaive")) {
							callCounters[1].addAndGet(1);
						} else if (ste.getMethodName().equals("omitNaive")) {
							callCounters[2].addAndGet(1);
						} else if (ste.getMethodName().equals("rowlandDP")) {
							callCounters[3].addAndGet(1);
						} else if (ste.getMethodName().equals("omitDP")) {
							callCounters[4].addAndGet(1);
						}
					}
				}
				return gcdHelper(a, b);
			}

			private int gcdHelper(int a, int b) {
				if (b == 0) {
					return a;
				} else {
					return gcdHelper(b, a % b);
				}
			}
		};
	}

	protected static final BigBrother BIG_BROTHER = new BigBrother();

	private int[] rowlands = { 7, 8, 9, 10, 15, 18, 19, 20, 21, 22, 33, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 69, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 141, 144, 145, 150, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168 }; // http://oeis.org/A106108
	private int[] omiteds = { 5, 3, 11, 3, 23, 3, 47, 3, 5, 3, 101, 3, 7, 11, 3, 13, 233, 3, 467, 3, 5, 3, 941, 3, 7, 1889, 3, 3779, 3, 7559, 3, 13, 15131, 3, 53, 3, 7, 30323, 3, 60647, 3, 5, 3, 101, 3, 121403, 3, 242807, 3, 5, 3, 19, 7, 5, 3, 47, 3, 37, 5, 3, 17, 3, 199, 53, 3, 29, 3, 486041, 3, 7, 421, 23 }; // http://oeis.org/A137613

	// ========== tests ==========
	@Test(timeout = 500000)
	public void test__Rowland_omitNaive_V() {
		/*
		int i = Rowland.omitNaive(BIG_BROTHER.gcd, 1);
		System.out.println(i);
		i = Rowland.omitNaive(BIG_BROTHER.gcd, 2);
		System.out.println(i);
		i = Rowland.omitNaive(BIG_BROTHER.gcd, 3);
		System.out.println(i);
		i = Rowland.omitNaive(BIG_BROTHER.gcd, 4);
		System.out.println(i);
		i = Rowland.omitNaive(BIG_BROTHER.gcd, 5);
		System.out.println(i);
		i = Rowland.omitNaive(BIG_BROTHER.gcd, 6);
		System.out.println(i);
		i = Rowland.omitNaive(BIG_BROTHER.gcd, 7);
		System.out.println(i);
		*/
		
		for (int i = 1; i < 1; i++){
			int x = Rowland.omitNaive(BIG_BROTHER.gcd, i);
			System.out.println(x);
		}
	}
	
	
	@Test(timeout = 2000)
	public void test__Rowland_rowlandNaive__1to15__PUBLIC_TEST() {
		int[] expected_gcd = { 0, 1, 3, 7, 15, 31, 63, 127, 255, 511, 1023, 2047, 4095, 8191, 16383 };
		int[] expected_rowlandNaive = { 0, 1, 5, 17, 49, 129, 321, 769, 1793, 4097, 9217, 20481, 45057, 98305, 212993 };
		Rowland.resetDP();
		for (int n = 1; n <= 15; n++) {
			BIG_BROTHER.resetBigBrother();
			int actual = Rowland.rowlandNaive(BIG_BROTHER.gcd, n);
			assertEquals("Wrong value at n = " + n + ".", rowlands[n - 1], actual);
			assertEquals("Wrong number of calls to GCD.gcd at n = " + n + ".", expected_gcd[n - 1], BIG_BROTHER.callCounters[0].get());
			assertEquals("Wrong number of calls to " + RowlandPublicTest.ROWLANDNAIVE + " at n = " + n + ".", expected_rowlandNaive[n - 1], BIG_BROTHER.callCounters[1].get());
			assertEquals("Wrong number of calls to " + RowlandPublicTest.OMITNAIVE + " at n = " + n + ".", 0, BIG_BROTHER.callCounters[2].get());
			assertEquals("Wrong number of calls to " + RowlandPublicTest.ROWLANDDP + " at n = " + n + ".", 0, BIG_BROTHER.callCounters[3].get());
			assertEquals("Wrong number of calls to " + RowlandPublicTest.OMITDP + " at n = " + n + ".", 0, BIG_BROTHER.callCounters[4].get());
		}
	}

	@Test(timeout = 1000)
	public void test__Rowland_omitNaive__1to3__PUBLIC_TEST() {
		int[] expected_gcd = { 37, 83, 3049 };
		int[] expected_rowlandNaive = { 95, 273, 23579 };
		int[] expected_omitNaive = expected_gcd;
		Rowland.resetDP();
		for (int n = 1; n <= 3; n++) {
			BIG_BROTHER.resetBigBrother();
			int actual = Rowland.omitNaive(BIG_BROTHER.gcd, n);
			//System.out.println(omiteds[n - 1]);
			assertEquals("Wrong value at n = " + n + ".", omiteds[n - 1], actual);
			assertEquals("Wrong number of calls to GCD.gcd at n = " + n + ".", expected_gcd[n - 1], BIG_BROTHER.callCounters[0].get());
			assertEquals("Wrong number of calls to " + RowlandPublicTest.ROWLANDNAIVE + " at n = " + n + ".", expected_rowlandNaive[n - 1], BIG_BROTHER.callCounters[1].get());
			assertEquals("Wrong number of calls to " + RowlandPublicTest.OMITNAIVE + " at n = " + n + ".", expected_omitNaive[n - 1], BIG_BROTHER.callCounters[2].get());
			assertEquals("Wrong number of calls to " + RowlandPublicTest.ROWLANDDP + " at n = " + n + ".", 0, BIG_BROTHER.callCounters[3].get());
			assertEquals("Wrong number of calls to " + RowlandPublicTest.OMITDP + " at n = " + n + ".", 0, BIG_BROTHER.callCounters[4].get());
		}
	}

	@Test(timeout = 1000)
	public void test__Rowland_rowlandDP__1to66__PUBLIC_TEST() {
		Rowland.resetDP();
		for (int n = 1; n <= rowlands.length; n++) {
			BIG_BROTHER.resetBigBrother();
			int actual = Rowland.rowlandDP(BIG_BROTHER.gcd, n);
			assertEquals("Wrong value at n = " + n + ".", rowlands[n - 1], actual);
			assertEquals("Wrong number of calls to GCD.gcd at n = " + n + ".", (n == 1 ? 0 : 1), BIG_BROTHER.callCounters[0].get());
			assertEquals("Wrong number of calls to " + RowlandPublicTest.ROWLANDNAIVE + " at n = " + n + ".", 0, BIG_BROTHER.callCounters[1].get());
			assertEquals("Wrong number of calls to " + RowlandPublicTest.OMITNAIVE + " at n = " + n + ".", 0, BIG_BROTHER.callCounters[2].get());
			assertEquals("Wrong number of calls to " + RowlandPublicTest.ROWLANDDP + " at n = " + n + ".", (n == 1 ? 0 : 1), BIG_BROTHER.callCounters[3].get());
			assertEquals("Wrong number of calls to " + RowlandPublicTest.OMITDP + " at n = " + n + ".", 0, BIG_BROTHER.callCounters[4].get());
		}
	}

	@Test(timeout = 9000)
	public void test__Rowland_omitDP__1to45__PUBLIC_TEST() {
		int[] expected_gcd = { 4, 1, 5, 1, 11, 1, 23, 1, 2, 1, 50, 1, 3, 5, 1, 6, 116, 1, 233, 1, 2, 1, 470, 1, 3, 944, 1, 1889, 1, 3779, 1, 6, 7565, 1, 26, 1, 3, 15161, 1, 30323, 1, 2, 1, 50, 1 };
		int[] expected_rowlandDP = expected_gcd;
		int[] expected_omitNaive = expected_gcd;
		Rowland.resetDP();
		for (int n = 1; n <= expected_gcd.length; n++) {
			BIG_BROTHER.resetBigBrother();
			int actual = Rowland.omitDP(BIG_BROTHER.gcd, n);
			assertEquals("Wrong value at n = " + n + ".", omiteds[n - 1], actual);
			assertEquals("Wrong number of calls to GCD.gcd at n = " + n + ".", expected_gcd[n - 1], BIG_BROTHER.callCounters[0].get());
			assertEquals("Wrong number of calls to " + RowlandPublicTest.ROWLANDNAIVE + " at n = " + n + ".", 0, BIG_BROTHER.callCounters[1].get());
			assertEquals("Wrong number of calls to " + RowlandPublicTest.OMITNAIVE + " at n = " + n + ".", 0, BIG_BROTHER.callCounters[2].get());
			assertEquals("Wrong number of calls to " + RowlandPublicTest.ROWLANDDP + " at n = " + n + ".", expected_rowlandDP[n - 1], BIG_BROTHER.callCounters[3].get());
			assertEquals("Wrong number of calls to " + RowlandPublicTest.OMITDP + " at n = " + n + ".", expected_omitNaive[n - 1], BIG_BROTHER.callCounters[4].get());
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