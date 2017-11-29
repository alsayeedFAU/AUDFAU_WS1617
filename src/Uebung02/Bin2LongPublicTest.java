import static org.junit.Assert.*;
import org.junit.*;

public class Bin2LongPublicTest {

	// ========== pruefeEingabe ==========
	@Test(timeout = 666)
	public void test_check__valid_input__PUBLIC_TEST() {
		int returned = Bin2Long.check("010101");
		assertEquals(-1, returned);
	}

	@Test(timeout = 666)
	public void test_check__invalid_input_at_2__PUBLIC_TEST() {
		int returned = Bin2Long.check("01OI01");
		assertEquals(2, returned);
	}

	// ========== charBit2Long ==========
	@Test(timeout = 666)
	public void test_convertBit__value_0__PUBLIC_TEST() {
		long returned = Bin2Long.convertBit('0');
		assertEquals(0, returned);
	}

	// ========== bin2Long ==========
	@Test(timeout = 666)
	public void test_convert__value_42__PUBLIC_TEST() {
		long returned = Bin2Long.convert("0101011");
		assertEquals(43, returned);
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
