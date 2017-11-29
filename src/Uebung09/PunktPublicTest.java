import static org.junit.Assert.*;
import java.util.*;
import org.junit.*;

public class PunktPublicTest {

	// ========== TESTS ==========
	@Test(timeout = 100)
	public void pubTest_Punkt_erzwingtVererbung() {
		AbstrakterPunkt a = new Punkt(47, 11);
		assertEquals("AbstrakterPunkt a = new Punkt + a.getX", 47, a.getX());
		assertEquals("AbstrakterPunkt a = new Punkt + a.getY", 11, a.getY());
		Punkt b = new Punkt(47, 11);
		Comparable<Punkt> c = new Punkt(47, 11);
		assertEquals("Punkt b + Comparable<Punkt> c = new Punkt + c.compareTo(b)", 0, c.compareTo(b));
	}

	@Test(timeout = 100)
	public void pubTest_Dist() throws Exception {
		Punkt a = new Punkt(0, 0);
		Punkt b = new Punkt(1, 1);
		assertEquals("dist", 1.41421356237309504880, a.euklid(b), 1e-8);
		assertEquals("manhattan", 2, a.manhattan(b));
		assertFalse("equals", a.equals(b));
	}

	@Test(timeout = 100)
	public void pubTest_Comparable() throws Exception {
		Punkt a = new Punkt(-2, -2);
		Punkt b = new Punkt(1, 1);
		assertEquals("bigger", 1, a.compareTo(b));
	}

	@Test(timeout = 100)
	public void pubTest_Comparator() throws Exception {
		Punkt a = new Punkt(-2, -2);
		Punkt b = new Punkt(1, 1);
		Punkt z = new Punkt(-1, -1);
		Comparator<Punkt> cmp = new PunktVergleicher(z);
		assertEquals("samaller", -1, cmp.compare(a, b));
	}

	@Test(timeout = 100)
	public void pubTest_ComparatorEquals() throws Exception {
		Punkt a = new Punkt(1, 1);
		Punkt b = new Punkt(2, 2);
		Comparator<Punkt> cmp1 = new PunktVergleicher(a);
		Comparator<Punkt> cmp2 = new PunktVergleicher(b);
		assertFalse("not equal comparators", cmp1.equals(cmp2));
	}

	@Test(timeout = 100)
	public void pubTest_Sort() throws Exception {
		Punkt a[] = new Punkt[] { new Punkt(-3, 2), new Punkt(5, 7), new Punkt(-1, 100), new Punkt(6, 0) };
		AbstrakterPunkt s0[] = new AbstrakterPunkt[] { new Punkt(-3, 2), new Punkt(6, 0), new Punkt(5, 7), new Punkt(-1, 100) };
		AbstrakterPunkt s1[] = new AbstrakterPunkt[] { new Punkt(-1, 100), new Punkt(-3, 2), new Punkt(5, 7), new Punkt(6, 0) };
		Punkt z = new Punkt(-1, -1);
		Punkt.sortiere(a);
		assertArrayEquals("sort", s0, a);
		Punkt.sortiereZentrum(a, z);
		assertArrayEquals("sort with z", s1, a);
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