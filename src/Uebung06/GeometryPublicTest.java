import static org.junit.Assert.*;
import org.junit.*;
import java.lang.reflect.*;

public class GeometryPublicTest {

	private static final double PRECISION = 1e-7;

	// -------------------- Vec2D --------------------
	@Test(timeout = 1000)
	public void pubTest_Vec2D_intestines__THIS_TEST_IS_QUITE_IMPORTANT_IF_IT_FAILS_THEN_YOU_WILL_PROBABLY_GET_NO_POINTS_AT_ALL() {
		try {
			Class<?> clazz = Class.forName("Vec2D");
			assertFalse("Ist keine Klasse sondern eine Annotation.", clazz.isAnnotation());
			assertFalse("Ist keine Klasse sondern ein Enum.", clazz.isEnum());
			assertFalse("Ist keine Klasse sondern ein Interface.", clazz.isInterface());
			assertFalse("Ist faelschlicherweise >abstract<", Modifier.isAbstract(clazz.getModifiers()));
			assertEquals("Darf keine Schnittstelle implementieren.", 0, clazz.getInterfaces().length);
			assertEquals("Muss direkt von Object erben.", Object.class, clazz.getSuperclass());
			assertEquals("Hat falsche Anzahl an Attributen.", 2, clazz.getDeclaredFields().length);
			assertEquals("Hat falsche Anzahl an Konstruktoren.", 1, clazz.getDeclaredConstructors().length);
			assertEquals("Hat falsche Anzahl an Methoden.", 2, clazz.getDeclaredMethods().length);
			for (String varName : new String[] { "x", "y" }) {
				Field field = clazz.getDeclaredField(varName);
				assertTrue(varName + " - Feld: Sichtbarkeit ist nicht >private<", Modifier.isPrivate(field.getModifiers()));
				assertFalse(varName + " - Feld: Ist kein Instanzattribut", Modifier.isStatic(field.getModifiers()));
				assertSame(varName + " - Feld: Typ ist nicht >double<", double.class, field.getType());
			}
			Constructor<?> constructor = clazz.getDeclaredConstructor(double.class, double.class);
			assertNotNull("Konstruktor inkl. -parameter: inkorrekt", constructor);
			assertTrue("Konstruktor inkl. -parameter: Sichtbarkeit ist nicht >public<", Modifier.isPublic(constructor.getModifiers()));
			for (String methodName : new String[] { "getX", "getY" }) {
				Method method = clazz.getDeclaredMethod(methodName);
				assertNotNull(methodName + " - Methode inkl. -parameter: inkorrekt", method);
				assertTrue(methodName + " - Methode: Sichtbarkeit ist nicht >public<", Modifier.isPublic(method.getModifiers()));
				assertFalse(methodName + " - Methode: Ist keine Instanzmethode", Modifier.isStatic(method.getModifiers()));
				assertFalse(methodName + " - Methode: Ist faelschlicherweise >abstract<", Modifier.isAbstract(method.getModifiers()));
				assertEquals(methodName + " - Methode: Rueckgabetyp ist nicht >double<", double.class, method.getReturnType());
			}
		} catch (ClassNotFoundException e) {
			fail("Klasse nicht gefunden: " + e.getMessage());
		} catch (NoSuchFieldException e) {
			fail("Attribute nicht gefunden oder inkorrekt: " + e.getMessage());
		} catch (NoSuchMethodException e) {
			fail("Konstruktor oder Methoden nicht gefunden oder inkorrekt: " + e.getMessage());
		}
	}

	@Test(timeout = 1000)
	public void pubTest_Vec2D_functionality__THIS_TEST_IS_QUITE_IMPORTANT_IF_IT_FAILS_THEN_YOU_WILL_PROBABLY_GET_NO_POINTS_AT_ALL() {
		for (double[] xy : new double[][] { { 47, 11 }, { 0.815, 42.666 } }) {
			Vec2D v = new Vec2D(xy[0], xy[1]);
			assertEquals("Vec2D.getX ist kaputt.", xy[0], v.getX(), PRECISION);
			assertEquals("Vec2D.getY ist kaputt.", xy[1], v.getY(), PRECISION);
		}
	}

	// -------------------- Quadrilateral --------------------
	@Test(timeout = 1000)
	public void pubTest_Quadrilateral_functionality__THIS_TEST_IS_QUITE_IMPORTANT_IF_IT_FAILS_THEN_YOU_WILL_PROBABLY_GET_NO_POINTS_AT_ALL() {
		Vec2D[] pX = { new Vec2D(2, 1), new Vec2D(5, 0), new Vec2D(6, 4), new Vec2D(1, 3) };
		Quadrilateral q = new Quadrilateral(pX[0], pX[1], pX[2], pX[3]) {
			@Override
			public double perimeter() {
				return 42;
			}

			@Override
			public double area() {
				return 666;
			}
		};
		assertEquals("Quadrilateral.getA.getX ist kaputt.", pX[0].getX(), q.getA().getX(), PRECISION);
		assertEquals("Quadrilateral.getA.getY ist kaputt.", pX[0].getY(), q.getA().getY(), PRECISION);
		assertEquals("Quadrilateral.getB.getX ist kaputt.", pX[1].getX(), q.getB().getX(), PRECISION);
		assertEquals("Quadrilateral.getB.getY ist kaputt.", pX[1].getY(), q.getB().getY(), PRECISION);
		assertEquals("Quadrilateral.getC.getX ist kaputt.", pX[2].getX(), q.getC().getX(), PRECISION);
		assertEquals("Quadrilateral.getC.getY ist kaputt.", pX[2].getY(), q.getC().getY(), PRECISION);
		assertEquals("Quadrilateral.getD.getX ist kaputt.", pX[3].getX(), q.getD().getX(), PRECISION);
		assertEquals("Quadrilateral.getD.getY ist kaputt.", pX[3].getY(), q.getD().getY(), PRECISION);
		assertEquals("Quadrilateral.getAB ist kaputt.", Math.sqrt(10), q.getAB(), PRECISION);
		assertEquals("Quadrilateral.getBC ist kaputt.", Math.sqrt(17), q.getBC(), PRECISION);
		assertEquals("Quadrilateral.getCD ist kaputt.", Math.sqrt(26), q.getCD(), PRECISION);
		assertEquals("Quadrilateral.getDA ist kaputt.", Math.sqrt(5), q.getDA(), PRECISION);
	}

	// -------------------- Square --------------------
	@Test(timeout = 1000)
	public void pubTest_Square_intestines() {
		try {
			Class<?> clazz = Class.forName("Square");
			assertFalse("Ist keine Klasse sondern eine Annotation.", clazz.isAnnotation());
			assertFalse("Ist keine Klasse sondern ein Enum.", clazz.isEnum());
			assertFalse("Ist keine Klasse sondern ein Interface.", clazz.isInterface());
			assertFalse("Ist faelschlicherweise >abstract<", Modifier.isAbstract(clazz.getModifiers()));
			assertEquals("Darf keine Schnittstelle implementieren.", 0, clazz.getInterfaces().length);
			assertEquals("Muss direkt von Rectangle erben.", Rectangle.class, clazz.getSuperclass());
			assertEquals("Hat falsche Anzahl an Attributen.", 0, clazz.getDeclaredFields().length);
			assertEquals("Hat falsche Anzahl an Konstruktoren.", 1, clazz.getDeclaredConstructors().length);
			assertEquals("Hat falsche Anzahl an Methoden.", 0, clazz.getDeclaredMethods().length);
			Constructor<?> constructor = clazz.getDeclaredConstructor(Vec2D.class, double.class);
			assertNotNull("Konstruktor inkl. -parameter: inkorrekt", constructor);
			assertTrue("Konstruktor inkl. -parameter: Sichtbarkeit ist nicht >public<", Modifier.isPublic(constructor.getModifiers()));
		} catch (ClassNotFoundException e) {
			fail("Klasse nicht gefunden: " + e.getMessage());
		} catch (NoSuchMethodException e) {
			fail("Konstruktor oder Methoden nicht gefunden oder inkorrekt: " + e.getMessage());
		}
	}

	@Test(timeout = 1000)
	public void pubTest_Square_functionality() {
		for (double[] xya : new double[][] { { 47, 11, 666 }, { 0.815, 42.666, 42 } }) {
			Vec2D pP = new Vec2D(xya[0], xya[1]);
			Quadrilateral q = new Square(pP, xya[2]);
			assertEquals("Square.getA.getX ist kaputt.", xya[0], q.getA().getX(), PRECISION);
			assertEquals("Square.getA.getY ist kaputt.", xya[1], q.getA().getY(), PRECISION);
			assertEquals("Square.getB.getX ist kaputt.", xya[0] + xya[2], q.getB().getX(), PRECISION);
			assertEquals("Square.getB.getY ist kaputt.", xya[1], q.getB().getY(), PRECISION);
			assertEquals("Square.getC.getX ist kaputt.", xya[0] + xya[2], q.getC().getX(), PRECISION);
			assertEquals("Square.getC.getY ist kaputt.", xya[1] + xya[2], q.getC().getY(), PRECISION);
			assertEquals("Square.getD.getX ist kaputt.", xya[0], q.getD().getX(), PRECISION);
			assertEquals("Square.getD.getY ist kaputt.", xya[1] + xya[2], q.getD().getY(), PRECISION);
			assertEquals("Square.getAB ist kaputt.", xya[2], q.getAB(), PRECISION);
			assertEquals("Square.getBC ist kaputt.", xya[2], q.getBC(), PRECISION);
			assertEquals("Square.getCD ist kaputt.", xya[2], q.getCD(), PRECISION);
			assertEquals("Square.getDA ist kaputt.", xya[2], q.getDA(), PRECISION);
			assertEquals("Square.area ist kaputt.", xya[2] * xya[2], q.area(), PRECISION);
			assertEquals("Square.perimeter ist kaputt.", 4 * xya[2], q.perimeter(), PRECISION);
		}
	}

	// -------------------- Quadrilateral.parFilter --------------------
	@Test(timeout = 1000)
	public void pubTest_Quadrilateral_functionality_Quadrilateral_parFilter_null() {
		Trapezium[] trapeze = Quadrilateral.parFilter(null);
		assertNotNull("Quadrilateral.parFilter darf niemals nie nicht null zurueckgeben.", trapeze);
		assertEquals("Quadrilateral.parFilter hat etwas gefunden, was nicht da ist.", 0, trapeze.length);
	}

	@Test(timeout = 1000)
	public void pubTest_Quadrilateral_functionality_Quadrilateral_parFilter_vieleQuadrilateraleAberKeinTrapezium() {
		Vec2D[] pX = { new Vec2D(2, 1), new Vec2D(5, 0), new Vec2D(6, 4), new Vec2D(1, 3) };
		Quadrilateral[] vieleQuadrilateraleAberKeinTrapezium = new Quadrilateral[42];
		for (int i = 0; i < vieleQuadrilateraleAberKeinTrapezium.length; i++) {
			vieleQuadrilateraleAberKeinTrapezium[i] = new Quadrilateral(pX[0], pX[1], pX[2], pX[3]) {
				@Override
				public double perimeter() {
					return 42;
				}

				@Override
				public double area() {
					return 666;
				}
			};
		}
		Trapezium[] trapeze = Quadrilateral.parFilter(vieleQuadrilateraleAberKeinTrapezium);
		assertNotNull("Quadrilateral.parFilter darf niemals nie nicht null zurueckgeben.", trapeze);
		assertEquals("Quadrilateral.parFilter hat etwas gefunden, was nicht da ist.", 0, trapeze.length);
	}

	// -------------------- Quadrilateral.area --------------------
	@Test(timeout = 1000)
	public void pubTest_Quadrilateral_functionality_Quadrilateral_area_null() {
		assertEquals("Quadrilateral.area(null) ist kaputt.", 0, Quadrilateral.area(null), PRECISION);
	}

	@Test(timeout = 1000)
	public void pubTest_Quadrilateral_functionality_Quadrilateral_area_einSquare() {
		assertEquals("Quadrilateral.area(aSquare, null) ist kaputt.", 42 * 42, Quadrilateral.area(new Quadrilateral[] { new Square(new Vec2D(47, 11), 42), null }), PRECISION);
	}

	// -------------------- SMOKE TEST FOR THE REST --------------------
	@Test(timeout = 1000)
	public void pubTest_smokeTestForTheRest() {
		Vec2D pP = new Vec2D(47, 11);
		double a = 42, h = 666, b = 08.15, alpha = 45;
		Quadrilateral parallelogram = new Parallelogram(pP, a, h, alpha);
		assertEquals("Parallelogram.perimeter ist kaputt.", 1967.7324650809626, parallelogram.perimeter(), PRECISION);
		Trapezium rectangle = new Rectangle(pP, a, b);
		assertEquals("Rectangle.area ist kaputt.", a * b, rectangle.area(), PRECISION);
		Kite rhombus = new Rhombus(pP, a, h);
		assertEquals("Rhombus.area ist kaputt.", (a * h) / 2, rhombus.area(), PRECISION);
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