import static org.junit.Assert.*;
import org.junit.*;

public class PersonPublicTest {

	@Test(timeout = 1000)
	public void pubTest_smokeTest() {
		Person p = null;
		GeschlechtsZugehoerigkeit gz = null;
		Maennlich gzm = null;
		Weiblich gzw = null;
		Unbestimmt gzu = null;
		Beziehung r = null;
		Partnerschaft e = null;
		Ehe m = null;
		Affaere a = null;
		assertTrue("Just use the vars to avoid warnings...", p == null & gz == null & gzm == null & gzw == null & gzu == null & r == null & e == null & m == null & a == null);
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