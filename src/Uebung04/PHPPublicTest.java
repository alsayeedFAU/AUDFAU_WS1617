import static org.junit.Assert.*;
import org.junit.*;

public class PHPPublicTest {

	@Test(timeout = 666)
	public void test__nextX_x0_d0__PUBLIC_TEST() {
		assertSame("DO NOT CHANGE THE GIVEN SKELETON!", KnotPoint.class, PHP.class.getSuperclass());
		long actual = PHP.nextX(0L, 0L);
		assertEquals("PHP.nextX(0L, 0L) failed", 0, actual);
	}

	@Test(timeout = 666)
	public void test__nextY_y0_d0__PUBLIC_TEST() {
		assertSame("DO NOT CHANGE THE GIVEN SKELETON!", KnotPoint.class, PHP.class.getSuperclass());
		long actual = PHP.nextY(0L, 0L);
		assertEquals("PHP.nextY(0L, 0L) failed", 1, actual);
	}

	@Test(timeout = 666)
	public void test__nextD_d0__PUBLIC_TEST() {
		assertSame("DO NOT CHANGE THE GIVEN SKELETON!", KnotPoint.class, PHP.class.getSuperclass());
		long actual = PHP.nextD(0L);
		assertEquals("PHP.nextD(0L) failed", 1, actual);
	}

	@Test(timeout = 666)
	public void test__markHoovered_x0_y0__PUBLIC_TEST() {
		assertSame("DO NOT CHANGE THE GIVEN SKELETON!", KnotPoint.class, PHP.class.getSuperclass());
		PHP.markHoovered(0L, 0L);
	}

	@Test(timeout = 666)
	public void test__isHoovered_x0_y0__PUBLIC_TEST() {
		assertSame("DO NOT CHANGE THE GIVEN SKELETON!", KnotPoint.class, PHP.class.getSuperclass());
		boolean actual = PHP.isHoovered(0L, 0L);
		assertTrue("Dummy (aka dumb) use of local variable to avoid def-undef-anomaly warnings.", actual || !actual);
	}

	@Test(timeout = 666)
	public void test__hoover__PUBLIC_TEST() {
		assertSame("DO NOT CHANGE THE GIVEN SKELETON!", KnotPoint.class, PHP.class.getSuperclass());
		int aDumbWayToEnforceInterfaceWithoutAnyInvocation = 42;
		if (aDumbWayToEnforceInterfaceWithoutAnyInvocation != 42) {
			PHP.hoover();
		}
	}
	
	@Test(timeout = 666)
	public void test__Hoovered(){
		PHP.hoover();
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