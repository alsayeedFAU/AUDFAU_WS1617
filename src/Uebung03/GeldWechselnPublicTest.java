import static org.junit.Assert.*;
import java.util.*;
import org.junit.*;

public class GeldWechselnPublicTest {

	private static final List<Integer> EUR_CT_LIST = Arrays.asList(1, 2, 5, 10, 20, 50, 100, 200);

	private static int[] listToArr(List<Integer> in) {
		Integer[] inArr = in.toArray(new Integer[0]);
		int[] out = new int[inArr.length];
		for (int i = 0; i < out.length; i++) {
			out[i] = inArr[i];
		}
		return out;
	}

	@Test(timeout = 666)
	public void pubTest_staatspleite() {
		final LinkedList<int[]> actualsList = new LinkedList<>();
		final Wechsel w = new Wechsel() {
			public void merke(int[] moeglichkeit) {
				actualsList.add(Arrays.copyOf(moeglichkeit, moeglichkeit.length));
			}
		};
		GeldWechseln.wechseln(w, null, 8);
		assertEquals("Man kann keinen Betrag wechseln wenn es keinerlei Muenzen gibt...", 0, actualsList.size());
	}

	@Test(timeout = 666)
	public void pubTest_aufgabenblatt_mitRekCheck() {
		final LinkedList<int[]> actualsList = new LinkedList<>();
		final Wechsel wMitRekCheck = new Wechsel() {
			public void merke(int[] moeglichkeit) {
				actualsList.add(Arrays.copyOf(moeglichkeit, moeglichkeit.length));
				int anzMuenzen = 0;
				for (int i = 0; i < moeglichkeit.length; i++) {
					if (moeglichkeit[i] > 0) {
						anzMuenzen++;
					}
				}
				StackTraceElement[] st = Thread.currentThread().getStackTrace();
				assertTrue("Die Anzahl der (rekursiven) Aufrufe stimmt nicht (der Stacktrace ist zu kurz).", st.length >= 2 + anzMuenzen);
				assertEquals("Der vorletzte Aufruf sollten an uns gehen...", "merke", st[1].getMethodName());
				String nameDerRekHelferMethode = st[2].getMethodName();
				for (int i = 0; i <= anzMuenzen; i++) {
					assertEquals("... davor sollten mind. " + (anzMuenzen + 1) + " (rekursive) Aufrufe an *GeldWechseln*." + nameDerRekHelferMethode + " stehen...", "GeldWechseln", st[2 + i].getClassName());
					assertEquals("... davor sollten mind. " + (anzMuenzen + 1) + " (rekursive) Aufrufe an GeldWechseln.*" + nameDerRekHelferMethode + "* stehen...", nameDerRekHelferMethode, st[2 + i].getMethodName());
				}
			}
		};
		Collections.shuffle(EUR_CT_LIST);
		GeldWechseln.wechseln(wMitRekCheck, listToArr(EUR_CT_LIST), 8);
		int[][] expectedsArray = { { 5, 2, 1, 0, 0, 0, 0, 0 }, { 5, 1, 1, 1, 0, 0, 0, 0 }, { 2, 2, 2, 2, 0, 0, 0, 0 }, { 2, 2, 2, 1, 1, 0, 0, 0 }, { 2, 2, 1, 1, 1, 1, 0, 0 }, { 2, 1, 1, 1, 1, 1, 1, 0 }, { 1, 1, 1, 1, 1, 1, 1, 1 } };
		String expecteds = "", actuals = "";
		assertEquals("Anzahl der Wechselmoeglichkeiten stimmt nicht. Bitte KEINE Mehrfachnenunngen gleicher Loesungen angeben!", expectedsArray.length, actualsList.size());
		for (int[] e : expectedsArray) {
			Arrays.sort(e);
			expecteds += Arrays.toString(e);
		}
		for (int[] e : actualsList) {
			Arrays.sort(e);
			String es = Arrays.toString(e);
			actuals += es;
			assertTrue("Unerwartete/unbekannte Wechselmoeglichkeit gefunden: " + es, expecteds.contains(es));
		}
		for (int[] e : expectedsArray) {
			Arrays.sort(e);
			String es = Arrays.toString(e);
			assertTrue("Vermisse erwartete/bekannte Wechselmoeglichkeit: " + es, actuals.contains(es));
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