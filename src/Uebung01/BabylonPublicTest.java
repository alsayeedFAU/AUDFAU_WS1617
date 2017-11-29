import static org.junit.Assert.*;
import org.junit.*;

public class BabylonPublicTest {

	@Test(timeout = 666)
	public void test_Babylon_2_2_1em7_PUBLIC_TEST() {
		double expected = 1.41421_35623_73095_04880_16887_24209_69807_85696_71875_37694;
		double actual = Babylon.wurzel(2d, 2, 0.00000001d);
		assertEquals("\"Quadratwurzel aus 2 auf sieben Nachkommastellen genau\" ist zu ungenau.", expected, actual, 0.00000001);
	}

	@Test(timeout = 666)
	public void test_Babylon_m42_3_1em7_PUBLIC_TEST() {
		double expected = -3.47602664488644978673986521900453743400483853878696742147;
		double actual = Babylon.wurzel(-42d, 3, 0.00000001d);
		assertEquals("\"Kubikwurzel aus -42 auf sieben Nachkommastellen genau\" ist zu ungenau.", expected, actual, 0.00000001);
	}
	
	@Test(timeout = 666)
	public void test_PUBLIC_TEST() {
		double expected = -1;
		double actual = Babylon.wurzel(-42d, -3, 0.00000001d);
		assertEquals("\"Kubikwurzel aus -42 auf sieben Nachkommastellen genau\" ist zu ungenau.", expected, actual, 0.00000001);
	}
	
	@Test(timeout = 666)
	public void test_Babylon_PUBLIC_TEST1() {
		double expected = -1;
		double actual = Babylon.wurzel(-42d, 2, 0.00000001d);
		assertEquals("\"Kubikwurzel aus -42 auf sieben Nachkommastellen genau\" ist zu ungenau.", expected, actual, 0.00000001);
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