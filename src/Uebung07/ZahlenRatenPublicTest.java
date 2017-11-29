import static org.junit.Assert.*;
import java.util.*;
import java.lang.reflect.*;
import java.util.concurrent.atomic.AtomicInteger;
import org.junit.*;

public class ZahlenRatenPublicTest {
	// ========== The "_intestines_" cases... ==========
	@Test(timeout = 666)
	public void test_Throwables_intestines__PUBLIC_TEST__THIS_TEST_IS_VERY_IMPORTANT_IF_IT_FAILS_THEN_YOU_WILL_GET_NO_POINTS_AT_ALL() {
		InterruptedException zahlPasst = new ZahlPasst();
		IndexOutOfBoundsException zahlPasstNicht = new ZahlPasstNicht(666, 999, 4711);
		ZahlPasst zahlZuGross = new ZahlZuGross();
		ZahlPasst zahlZuKlein = new ZahlZuKlein();
		assertTrue("Dummy-Assert um Compiler-Warnung zu verhindern...", zahlPasst != null && zahlPasstNicht != null && zahlZuGross != null && zahlZuKlein != null);
		for (Class<?>[] superSub : new Class[][] { //
				{ InterruptedException.class, ZahlPasst.class }, //
				{ IndexOutOfBoundsException.class, ZahlPasstNicht.class }, //
				{ ZahlPasst.class, ZahlZuGross.class }, //
				{ ZahlPasst.class, ZahlZuKlein.class }, //
		}) {
			Class<?> clazz = superSub[1];
			assertFalse(clazz.getName() + " ist faelschlicherweise eine Annotation.", clazz.isAnnotation());
			assertFalse(clazz.getName() + " ist faelschlicherweise ein Enum.", clazz.isEnum());
			assertFalse(clazz.getName() + " ist faelschlicherweise ein Interface.", clazz.isInterface());
			assertFalse(clazz.getName() + " ist faelschlicherweise >abstract<", Modifier.isAbstract(clazz.getModifiers()));
			assertEquals(clazz.getName() + " hat falsche Anzahl an anderen Innereien.", 0, clazz.getDeclaredAnnotations().length);
			assertEquals(clazz.getName() + " hat falsche Anzahl an anderen Innereien.", 0, clazz.getDeclaredClasses().length);
			assertEquals(clazz.getName() + " darf keine Schnittstelle implementieren.", 0, clazz.getInterfaces().length);
			assertSame(clazz.getName() + " ist nicht von der richtigen Superklasse abgeleitet.", superSub[0], clazz.getSuperclass());
			Field[] fields = clazz.getDeclaredFields();
			boolean hasAssertionsDisabled = false, hasSerialVersionUID = false;
			for (Field field : fields) {
				switch (field.getName()) {
				case "$assertionsDisabled":
					hasAssertionsDisabled = true;
					break;
				case "serialVersionUID":
					hasSerialVersionUID = true;
					assertTrue(field.getName() + " ist faelschlicherweise nicht >private<", Modifier.isPrivate(field.getModifiers()));
					assertTrue(field.getName() + " ist faelschlicherweise nicht >static<", Modifier.isStatic(field.getModifiers()));
					assertTrue(field.getName() + " ist faelschlicherweise nicht >final<", Modifier.isFinal(field.getModifiers()));
					assertSame(field.getName() + " hat falschen Datentyp.", long.class, field.getType());
					break;
				default:
					assertTrue(field.getName() + " ist faelschlicherweise nicht >private<", Modifier.isPrivate(field.getModifiers()));
					assertFalse(field.getName() + " ist faelschlicherweise >static<", Modifier.isStatic(field.getModifiers()));
					assertTrue(field.getName() + " ist faelschlicherweise nicht >final<", Modifier.isFinal(field.getModifiers()));
					assertSame(field.getName() + " hat falschen Datentyp.", int.class, field.getType());
				}
			}
			assertTrue(clazz.getName() + " hat faelschlicherweise keine serialVersionUID.", hasSerialVersionUID);
			assertEquals(clazz.getName() + " hat falsche Anzahl an Konstruktoren.", 1, clazz.getDeclaredConstructors().length);
			if (clazz == ZahlPasstNicht.class) {
				assertEquals("Hat falsche Anzahl an Attributen.", hasAssertionsDisabled ? 5 : 4, clazz.getDeclaredFields().length);
				assertEquals(clazz.getName() + " hat einen fehlerhaften Konstruktor.", 3, clazz.getDeclaredConstructors()[0].getParameterTypes().length);
				for (Class<?> paraType : clazz.getDeclaredConstructors()[0].getParameterTypes()) {
					assertSame(clazz.getName() + " hat einen fehlerhaften Konstruktor.", int.class, paraType);
				}
				assertEquals(clazz.getName() + " hat falsche Anzahl an Methoden.", 4, clazz.getDeclaredMethods().length);
			} else {
				assertEquals("Hat falsche Anzahl an Attributen.", hasAssertionsDisabled ? 2 : 1, clazz.getDeclaredFields().length);
				assertEquals(clazz.getName() + " hat faelschlicherweise keinen Default-Konstruktor.", 0, clazz.getDeclaredConstructors()[0].getParameterTypes().length);
				assertEquals(clazz.getName() + " hat falsche Anzahl an Methoden.", 0, clazz.getDeclaredMethods().length);
			}
		}
	}

	@Test(timeout = 666)
	public void test_special_Throwable_intestines__PUBLIC_TEST() {
		Class<ZahlPasstNicht> clazz = ZahlPasstNicht.class;
		for (int min = -7; min <= 7; min++) {
			for (int max = -7; max <= 7; max++) {
				for (int ist = -7; ist <= 7; ist++) {
					ZahlPasstNicht zahlPasstNicht = new ZahlPasstNicht(min, max, ist);
					assertEquals(clazz.getName() + ".untereGrenze() gibt falsche Ergebnisse zurueck.", min, zahlPasstNicht.untereGrenze());
					assertEquals(clazz.getName() + ".obereGrenze() gibt falsche Ergebnisse zurueck.", max, zahlPasstNicht.obereGrenze());
					assertEquals(clazz.getName() + ".istWert() gibt falsche Ergebnisse zurueck.", ist, zahlPasstNicht.istWert());
					String message = zahlPasstNicht.getMessage();
					assertEquals(clazz.getName() + ".getMessage() gibt falsche Ergebnisse zurueck.", clazz.getName() + ": Expected [" + min + "..." + max + "] but was <" + ist + ">", message);
				}
			}
		}
	}

	@Test(timeout = 666)
	public void test_Adapter_intestines__PUBLIC_TEST__THIS_TEST_IS_VERY_IMPORTANT_IF_IT_FAILS_THEN_YOU_WILL_GET_NO_POINTS_AT_ALL() {
		Class<?> clazz = ZahlenRatenAdapter.class;
		assertFalse(clazz.getName() + " ist faelschlicherweise eine Annotation.", clazz.isAnnotation());
		assertFalse(clazz.getName() + " ist faelschlicherweise ein Enum.", clazz.isEnum());
		assertFalse(clazz.getName() + " ist faelschlicherweise ein Interface.", clazz.isInterface());
		assertFalse(clazz.getName() + " ist faelschlicherweise >abstract<", Modifier.isAbstract(clazz.getModifiers()));
		assertEquals(clazz.getName() + " darf keine Schnittstelle implementieren.", 0, clazz.getInterfaces().length);
		assertSame(clazz.getName() + " ist nicht von der richtigen Superklasse abgeleitet.", AbstrakterZahlenRatenAdapter.class, clazz.getSuperclass());
		if (clazz.getDeclaredFields().length == 1) {
			assertEquals("Hat unerwartete Attribute.", "$assertionsDisabled", clazz.getDeclaredFields()[0].getName());
		} else {
			assertEquals("Hat falsche Anzahl an Attributen.", 0, clazz.getDeclaredFields().length);
		}
		assertEquals(clazz.getName() + " hat falsche Anzahl an Konstruktoren.", 1, clazz.getDeclaredConstructors().length);
		assertEquals(clazz.getName() + " hat falsche Anzahl an Methoden.", 2, clazz.getDeclaredMethods().length);
		assertEquals(clazz.getName() + " hat falsche Anzahl an anderen Innereien.", 0, clazz.getDeclaredAnnotations().length);
		assertEquals(clazz.getName() + " hat falsche Anzahl an anderen Innereien.", 0, clazz.getDeclaredClasses().length);
	}

	// ========== some normal test cases... ==========
	@Test(timeout = 666)
	public void test_etwasFunktionalitaet__PUBLIC_TEST() {
		int actual;
		AtomicInteger exhibitSpuriousProblem = new AtomicInteger(-1);
		AtomicInteger zahl = new AtomicInteger(0x815); // 2069 ;)
		AtomicInteger[] callCount = { new AtomicInteger(0), new AtomicInteger(0) };
		AtomicInteger[] maxFailCalls = { new AtomicInteger(42), new AtomicInteger(42) };
		EineMoeglicheImplementierungVonZahlenRaten zr = new EineMoeglicheImplementierungVonZahlenRaten(exhibitSpuriousProblem, zahl, callCount, maxFailCalls);
		AbstrakterZahlenRatenAdapter zra = new ZahlenRatenAdapter(zr);
		actual = zra.starteNeuesSpiel(666, 4711);
		assertEquals("Der Rueckgabewert fuer starteNeuesSpiel ist falsch.", 4, actual);
		assertEquals("Anzahl der Aufrufe an starteNeuesSpiel ist falsch.", 1, callCount[0].get());
		assertEquals("Anzahl der Aufrufe an rate ist falsch.", 0, callCount[1].get());
		actual = zra.starteNeuesSpiel(666, 4711);
		assertEquals("Der Rueckgabewert fuer starteNeuesSpiel ist falsch.", 1, actual);
		assertEquals("Anzahl der Aufrufe an starteNeuesSpiel ist falsch.", 2, callCount[0].get());
		assertEquals("Anzahl der Aufrufe an rate ist falsch.", 0, callCount[1].get());
		exhibitSpuriousProblem.set(-666);
		actual = zra.rate("AuD");
		assertEquals("Der Rueckgabewert fuer rate ist falsch.", 9, actual);
		assertEquals("Anzahl der Aufrufe an starteNeuesSpiel ist falsch.", 2, callCount[0].get());
		assertEquals("Anzahl der Aufrufe an rate ist falsch.", 1, callCount[1].get());
		exhibitSpuriousProblem.set(-1);
		actual = zra.rate("AuD");
		assertEquals("Der Rueckgabewert fuer rate ist falsch.", 4, actual);
		assertEquals("Anzahl der Aufrufe an starteNeuesSpiel ist falsch.", 2, callCount[0].get());
		assertEquals("Anzahl der Aufrufe an rate ist falsch.", 2, callCount[1].get());
		actual = zra.rate("2069");
		assertEquals("Der Rueckgabewert fuer rate ist falsch.", 6, actual);
		assertEquals("Anzahl der Aufrufe an starteNeuesSpiel ist falsch.", 2, callCount[0].get());
		assertEquals("Anzahl der Aufrufe an rate ist falsch.", 3, callCount[1].get());
	}

	@Test(timeout = 666)
	public void test_starteNeuesSpiel_spackt_ewig__PUBLIC_TEST() {
		int actual;
		AtomicInteger exhibitSpuriousProblem = new AtomicInteger(random.nextInt(8));
		AtomicInteger zahl = new AtomicInteger(-1);
		AtomicInteger[] callCount = { new AtomicInteger(0), new AtomicInteger(0) };
		AtomicInteger[] maxFailCalls = { new AtomicInteger(42), new AtomicInteger(42) };
		EineMoeglicheImplementierungVonZahlenRaten zr = new EineMoeglicheImplementierungVonZahlenRaten(exhibitSpuriousProblem, zahl, callCount, maxFailCalls);
		AbstrakterZahlenRatenAdapter zra = new ZahlenRatenAdapter(zr);
		actual = zra.starteNeuesSpiel(666, 4711);
		assertEquals("Der Rueckgabewert fuer starteNeuesSpiel ist falsch.", 3, actual);
		assertEquals("Anzahl der Aufrufe an starteNeuesSpiel ist falsch.", 7, callCount[0].get());
		assertEquals("Anzahl der Aufrufe an rate ist falsch.", 0, callCount[1].get());
	}

	@Test(timeout = 666)
	public void test_rate_spackt_ewig__PUBLIC_TEST() {
		int actual;
		AtomicInteger exhibitSpuriousProblem = new AtomicInteger(-1);
		AtomicInteger zahl = new AtomicInteger(0x815); // 2069 ;)
		AtomicInteger[] callCount = { new AtomicInteger(0), new AtomicInteger(0) };
		AtomicInteger[] maxFailCalls = { new AtomicInteger(42), new AtomicInteger(42) };
		EineMoeglicheImplementierungVonZahlenRaten zr = new EineMoeglicheImplementierungVonZahlenRaten(exhibitSpuriousProblem, zahl, callCount, maxFailCalls);
		AbstrakterZahlenRatenAdapter zra = new ZahlenRatenAdapter(zr);
		actual = zra.starteNeuesSpiel(666, 4711);
		assertEquals("Der Rueckgabewert fuer starteNeuesSpiel ist falsch.", 4, actual);
		assertEquals("Anzahl der Aufrufe an starteNeuesSpiel ist falsch.", 1, callCount[0].get());
		assertEquals("Anzahl der Aufrufe an rate ist falsch.", 0, callCount[1].get());
		exhibitSpuriousProblem.set(random.nextInt(8));
		actual = zra.rate("" + zahl.get());
		assertEquals("Der Rueckgabewert fuer rate ist falsch.", 1, actual);
		assertEquals("Anzahl der Aufrufe an starteNeuesSpiel ist falsch.", 1, callCount[0].get());
		assertEquals("Anzahl der Aufrufe an rate ist falsch.", 7, callCount[1].get());
	}

	// ========== DATA ==========
	private static final Random random = new Random(4711_0815_666_42L);

	protected static final class EineMoeglicheImplementierungVonZahlenRaten implements ZahlenRaten {
		protected final AtomicInteger exhibitSpuriousProblem, zahl;
		protected final AtomicInteger[] callCount, maxFailCalls;
		protected int min = 0, max = 0;
		protected int state = 0;

		public EineMoeglicheImplementierungVonZahlenRaten(AtomicInteger exhibitSpuriousProblem, AtomicInteger zahl, AtomicInteger[] callCount, AtomicInteger[] maxFailCalls) {
			this.exhibitSpuriousProblem = exhibitSpuriousProblem;
			this.zahl = zahl;
			this.callCount = callCount;
			this.maxFailCalls = maxFailCalls;
		}

		@Override
		public void starteNeuesSpiel(int min, int max) {
			callCount[0].incrementAndGet();
			if (exhibitSpuriousProblem.get() >= 0 && callCount[0].get() <= maxFailCalls[0].get()) {
				throw getAnyProblemAtRandom(exhibitSpuriousProblem.get());
			} else if (state == 1) {
				throw new IllegalStateException("Du kannst nicht einfach mitten im Spiel aufhoeren!");
			} else if (min < -4711_0815 || min >= max || max > 4711_0815) {
				throw new IllegalArgumentException("Das ist doch alles Quatsch! So kann ich nicht spielen!");
			}
			this.min = min;
			this.max = max;
			this.state = 1;
		}

		@Override
		public void rate(String z) throws InterruptedException {
			callCount[1].incrementAndGet();
			if (exhibitSpuriousProblem.get() == -666) {
			} else if (exhibitSpuriousProblem.get() >= 0 && callCount[1].get() <= maxFailCalls[1].get()) {
				if (random.nextDouble() > 0.666) {
					throw getAnyRandomProblem(exhibitSpuriousProblem.get());
				} else {
					throw getAnyProblemAtRandom(exhibitSpuriousProblem.get());
				}
			} else if (state == 0) {
				throw new ServiceConfigurationError("Wie willst du denn ueberhaupt spielen?");
			} else if (state == 2) {
				throw new IllegalStateException("Du verwirrst mich! Was willst du denn noch hier?");
			} else if (Integer.parseInt(z) < min || max < Integer.parseInt(z)) {
				throw new ZahlPasstNicht(min, max, Integer.parseInt(z));
			} else if (Integer.parseInt(z) < zahl.get()) {
				throw new ZahlZuKlein();
			} else if (Integer.parseInt(z) == zahl.get()) {
				state = 2;
				throw new ZahlPasst();
			} else if (Integer.parseInt(z) > zahl.get()) {
				throw new ZahlZuGross();
			}
		}
	}

	private static final RuntimeException getAnyRandomProblem(int problemId) {
		switch (problemId) {
		case 0:
			return new NullPointerException();
		case 1:
			return new ArithmeticException();
		case 2:
			return new java.nio.BufferOverflowException();
		case 3:
			return new java.util.EmptyStackException();
		case 4:
			return new NegativeArraySizeException();
		case 5:
			return new java.util.concurrent.RejectedExecutionException();
		case 6:
			return new NoSuchElementException();
		case 7:
			return new UnsupportedOperationException();
		default:
			return new IllegalArgumentException();
		// the secret tests may give you some more here - muhahaha...
		}
	}

	@SuppressWarnings("serial")
	private static final Error getAnyProblemAtRandom(int problemId) {
		switch (problemId) {
		case 0:
			return new java.lang.annotation.AnnotationFormatError("You cannot catch that in EST - muhahaha...");
		case 1:
			return new AssertionError();
		case 2:
			return new java.awt.AWTError("You cannot catch that in EST - muhahaha...");
		case 3:
			return new java.nio.charset.CoderMalfunctionError(new NullPointerException());
		case 4:
			return new javax.xml.parsers.FactoryConfigurationError();
		case 5:
			return new javax.xml.stream.FactoryConfigurationError();
		case 6:
			return new java.io.IOError(new NullPointerException());
		case 7:
			return new java.lang.LinkageError();
		default:
			return new VirtualMachineError() {
			};
		// the secret tests may give you some more here - muhahaha...
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