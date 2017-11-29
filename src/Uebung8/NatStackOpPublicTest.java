import static org.junit.Assert.*;
import org.junit.*;
import java.lang.reflect.*;

public class NatStackOpPublicTest {

	// ========== define some Nats... ==========
	private static final Nat zero = Nat.zero();
	private static final Nat one = Nat.succ(zero);
	private static final Nat two = Nat.succ(one);
	private static final Nat three = Nat.succ(two);
	private static final Nat four = Nat.succ(three);
	private static final Nat five = Nat.succ(four);
	private static final Nat six = Nat.succ(five);
	private static final Nat seven = Nat.succ(six);
	private static final Nat eight = Nat.succ(seven);
	private static final Nat nine = Nat.succ(eight);
	private static final Nat ten = Nat.succ(nine);
	private static final Nat eleven = Nat.succ(ten);
	private static final Nat twelve = Nat.succ(eleven);
	private static final Nat n11 = eleven;
	private static final Nat n42 = Nat.add(Nat.mul(four, ten), two);
	private static final Nat n47 = Nat.add(Nat.mul(four, ten), seven);
	private static final Nat n58 = Nat.add(Nat.mul(five, ten), eight);
	private static final Nat n121 = Nat.add(Nat.mul(Nat.add(Nat.mul(one, ten), two), ten), one);
	private static final Nat n655 = Nat.add(Nat.mul(Nat.add(Nat.mul(six, ten), five), ten), five);
	private static final Nat n666 = Nat.add(Nat.mul(Nat.add(Nat.mul(six, ten), six), ten), six);

	// ========== The "_intestines_" cases... ==========
	@Test(timeout = 666)
	public void pubTest_NatStackOp_intestines__THIS_TEST_IS_VERY_IMPORTANT_IF_IT_FAILS_THEN_YOU_WILL_GET_NO_POINTS_AT_ALL() {
		Class<?> clazz = NatStackOp.class;
		assertFalse(clazz.getName() + " ist faelschlicherweise eine Annotation.", clazz.isAnnotation());
		assertFalse(clazz.getName() + " ist faelschlicherweise ein Enum.", clazz.isEnum());
		assertFalse(clazz.getName() + " ist faelschlicherweise ein Interface.", clazz.isInterface());
		assertFalse(clazz.getName() + " ist faelschlicherweise >abstract<", Modifier.isAbstract(clazz.getModifiers()));
		assertTrue(clazz.getName() + " ist faelschlicherweise nicht >public<", Modifier.isPublic(clazz.getModifiers()));
		assertEquals(clazz.getName() + " hat faelschlicherweise innere Annotationen.", 0, clazz.getDeclaredAnnotations().length);
		assertEquals(clazz.getName() + " hat faelschlicherweise innere Kalssen.", 0, clazz.getDeclaredClasses().length);
		assertEquals(clazz.getName() + " darf keine Schnittstelle implementieren.", 0, clazz.getInterfaces().length);
		assertSame(clazz.getName() + " ist nicht von der richtigen Superklasse abgeleitet.", Object.class, clazz.getSuperclass());
		if (clazz.getDeclaredFields().length == 1) {
			assertEquals("Hat unerwartete Attribute.", "$assertionsDisabled", clazz.getDeclaredFields()[0].getName());
		} else {
			assertEquals("Hat falsche Anzahl an Attributen.", 0, clazz.getDeclaredFields().length);
		}
		assertEquals(clazz.getName() + " hat falsche Anzahl an Konstruktoren.", 1, clazz.getDeclaredConstructors().length); // default cons only!
		assertEquals(clazz.getName() + " hat falsche Anzahl an Methoden.", 7, clazz.getDeclaredMethods().length);
	}

	// ========== NatStackOp_put ==========
	@Test(timeout = 666)
	public void pubTest_NatStackOp_put() {
		Nat[] someNats = { one, two, three, four, five };
		NatStack s = createNatStack(someNats);
		Nat[] someSpecialNats = { n42, n47, n121, n666 };
		for (Nat n : someSpecialNats) {
			s = NatStackOp.put(n, s);
		}
		for (Nat expected : someNats) {
			assertSame("NatStackOp.put failed.", expected, NatStack.peek(s));
			s = NatStack.pop(s);
		}
		for (Nat expected : someSpecialNats) {
			assertSame("NatStackOp.put failed.", expected, NatStack.peek(s));
			s = NatStack.pop(s);
		}
		assertSame("NatStackOp.put failed.", NatStack.empty(), s);
	}

	// ========== NatStackOp_get ==========
	@Test(timeout = 666)
	public void pubTest_NatStackOp_get() {
		Nat[] someSpecialNats = { n42, n47, n121, n666 };
		for (Nat expected : someSpecialNats) {
			NatStack s = createNatStack(new Nat[] { one, two, three, four, five, expected });
			Nat actual = NatStackOp.get(s);
			assertSame("NatStackOp.get(" + s + ") failed.", expected, actual);
		}
	}

	// ========== NatStackOp_poll ==========
	@Test(timeout = 666)
	public void pubTest_NatStackOp_poll() {
		Nat[] someNats = { one, two, three, four, five, n42, n47, n121, n666 };
		NatStack s = createNatStack(someNats);
		for (int i = someNats.length - 1; i >= 0; i--) {
			Nat[] expectedNatArray = java.util.Arrays.copyOfRange(someNats, 0, i);
			NatStack actualNatStack = NatStackOp.poll(s);
			checkNatStack("NatStackOp.poll(" + s + ") failed.", expectedNatArray, actualNatStack);
			s = actualNatStack;
		}
	}

	// ========== NatStackOp_stackMul ==========
	@Test(timeout = 666)
	public void pubTest_NatStackOp_stackMul() {
		NatStack s = createNatStack(new Nat[] { two, three, two });
		Nat actual = NatStackOp.stackMul(s);
		assertNatEquals("NatStackOp.stackMul(" + s + ")", twelve, actual);
	}

	// ========== NatStackOp_stackPairOp ==========
	@Test(timeout = 666)
	public void pubTest_NatStackOp_stackPairOp() {
		NatStack s = createNatStack(new Nat[] { n47, n11, n11, n11, n11, n655, n42 });
		NatStack actualNatStack = NatStackOp.stackPairOp(s);
		checkNatStack("NatStackOp.stackPairOp(" + s + ")", new Nat[] { n58, n121, n666, n42 }, actualNatStack);
	}

	// ========== NatStackOp_nat2bin ==========
	@Test(timeout = 666)
	public void pubTest_NatStackOp_nat2bin() {
		check_nat2bin(n42, new Nat[] { zero, one, zero, one, zero, one, zero });
	}

	// ========== NatStackOp_bin2nat ==========
	@Test(timeout = 666)
	public void pubTest_NatStackOp_bin2nat() {
		NatStack s = createNatStack(new Nat[] { zero, one, zero, one, zero, one, zero });
		//Nat actual = Nat.div(NatStackOp.bin2nat(s),Nat.succ(Nat.succ(Nat.zero())));
		Nat actual = NatStackOp.bin2nat(s);
		assertNatEquals("NatStackOp.bin2nat(" + s + ")", n42, actual);
	}

	// ========== NatStackOp_checkerHelper ==========
	private static final boolean natEquals(Nat expected, Nat actual) {
		boolean failed = false;
		if ((expected == null && actual != null) || (expected != null && actual == null)) {
			failed |= true;
		} else if ((expected == Nat.NaN() && actual != Nat.NaN()) || (expected != Nat.NaN() && actual == Nat.NaN())) {
			failed |= true;
		} else if (expected != null && actual != null && expected != Nat.NaN() && actual != Nat.NaN()) {
			failed |= Nat.sub(expected, actual) != Nat.zero();
			failed |= Nat.sub(actual, expected) != Nat.zero();
		}
		return !failed;
	}

	protected static final void assertNatEquals(String message, Nat expected, Nat actual) {
		assertTrue(message + ": expected Nat(" + expected + ") but was Nat(" + actual + ").", natEquals(expected, actual));
	}

	protected static final NatStack createNatStack(Nat[] ns) {
		NatStack s = NatStack.empty();
		for (int i = ns.length - 1; i >= 0; i--) {
			s = NatStack.push(s, ns[i]);
		}
		return s;
	}

	protected static final void checkNatStack(String message, Nat[] expectedNatArray, NatStack actualNatStack) {
		String expectedString = java.util.Arrays.toString(expectedNatArray);
		String actualString = actualNatStack.toString();
		for (Nat expectedNat : expectedNatArray) {
			Nat actualNat = NatStack.peek(actualNatStack);
			assertTrue(message + ": expected " + expectedString + " but was " + actualString, natEquals(expectedNat, actualNat));
			actualNatStack = NatStack.pop(actualNatStack);
		}
		assertSame(message + ": expected " + expectedString + " but was " + actualString, NatStack.empty(), actualNatStack);
	}

	protected static final void check_nat2bin(Nat in, Nat[] expectedNatArray) {
		NatStack actualNatStack = NatStackOp.nat2bin(in);
		checkNatStack("nat2bin(" + in + ")", expectedNatArray, actualNatStack);
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