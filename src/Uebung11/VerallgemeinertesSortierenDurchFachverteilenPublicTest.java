import static org.junit.Assert.*;
import java.util.*;
import org.junit.*;

public class VerallgemeinertesSortierenDurchFachverteilenPublicTest {
	@Test(timeout = 666)
	public void pubTest_sortiere() {
		System.out.println("=============== Zahlen (natuerlich) ===============");
		Zahl[] zahlA = { new Zahl(47110815), new Zahl(4711), new Zahl(91058), new Zahl(666) };
		LinkedList<Segmentierbar> zahlLL = new LinkedList<>();
		System.out.println("\t----- unsortiert: -----");
		for (Segmentierbar s : zahlA) {
			System.out.println("\t" + s);
			zahlLL.addLast(s);
		}
		VerallgemeinertesSortierenDurchFachverteilen.sortiere(zahlLL);
		System.out.println("\t----- sortiert: -----");
		for (Segmentierbar s : zahlLL) {
			System.out.println("\t" + s);
		}
		assertEquals("Zahlen (natuerlich) - Entry 0 is wrong.", "ZAHL: 666", zahlLL.get(0).toString());
		assertEquals("Zahlen (natuerlich) - Entry 1 is wrong.", "ZAHL: 4711", zahlLL.get(1).toString());
		assertEquals("Zahlen (natuerlich) - Entry 2 is wrong.", "ZAHL: 91058", zahlLL.get(2).toString());
		assertEquals("Zahlen (natuerlich) - Entry 3 is wrong.", "ZAHL: 47110815", zahlLL.get(3).toString());
		System.out.println("=============== Zahlen (lexikographisch) ===============");
		Zahl[] zahlLexA = { new ZahlLex(47110815), new ZahlLex(4711), new ZahlLex(91058), new ZahlLex(666) };
		LinkedList<Segmentierbar> zahlLexLL = new LinkedList<>();
		System.out.println("\t----- unsortiert: -----");
		for (Segmentierbar s : zahlLexA) {
			System.out.println("\t" + s);
			zahlLexLL.addLast(s);
		}
		VerallgemeinertesSortierenDurchFachverteilen.sortiere(zahlLexLL);
		System.out.println("\t----- sortiert: -----");
		for (Segmentierbar s : zahlLexLL) {
			System.out.println("\t" + s);
		}
		assertEquals("Zahlen (lexikographisch) - Entry 0 is wrong.", "ZAHL: 4711", zahlLexLL.get(0).toString());
		assertEquals("Zahlen (lexikographisch) - Entry 1 is wrong.", "ZAHL: 47110815", zahlLexLL.get(1).toString());
		assertEquals("Zahlen (lexikographisch) - Entry 2 is wrong.", "ZAHL: 666", zahlLexLL.get(2).toString());
		assertEquals("Zahlen (lexikographisch) - Entry 3 is wrong.", "ZAHL: 91058", zahlLexLL.get(3).toString());
		System.out.println("==================== Namen ====================");
		Name[] nameA = { new Name("Baby", "Doll"), new Name("Johnny", "Doe"), new Name("AuD", "AuD"), new Name("John", "Doe"), new Name("AuD", "FAU"), new Name("Jane", "Doe"), new Name("FAU", "AuD"), new Name("AuD", "AuD") };
		LinkedList<Segmentierbar> nameLL = new LinkedList<>();
		System.out.println("\t----- unsortiert: -----");
		for (Segmentierbar s : nameA) {
			System.out.println("\t" + s);
			nameLL.addLast(s);
		}
		VerallgemeinertesSortierenDurchFachverteilen.sortiere(nameLL);
		System.out.println("\t----- sortiert: -----");
		for (Segmentierbar s : nameLL) {
			System.out.println("\t" + s);
		}
		assertEquals("Namen - Entry 0 is wrong.", "NAME:      AuD AuD :aka: AuD, AuD", nameLL.get(0).toString().substring(0, "NAME:      AuD AuD :aka: AuD, AuD".length()));
		assertEquals("Namen - Entry 1 is wrong.", "NAME:      AuD AuD :aka: AuD, AuD", nameLL.get(1).toString().substring(0, "NAME:      AuD AuD :aka: AuD, AuD".length()));
		assertEquals("Namen - Entry 2 is wrong.", "NAME:      FAU AuD :aka: AuD, FAU", nameLL.get(2).toString().substring(0, "NAME:      FAU AuD :aka: AuD, FAU".length()));
		assertEquals("Namen - Entry 3 is wrong.", "NAME:     Jane Doe :aka: Doe, Jane", nameLL.get(3).toString().substring(0, "NAME:     Jane Doe :aka: Doe, Jane".length()));
		assertEquals("Namen - Entry 4 is wrong.", "NAME:     John Doe :aka: Doe, John", nameLL.get(4).toString().substring(0, "NAME:     John Doe :aka: Doe, John".length()));
		assertEquals("Namen - Entry 5 is wrong.", "NAME:   Johnny Doe :aka: Doe, Johnny", nameLL.get(5).toString().substring(0, "NAME:   Johnny Doe :aka: Doe, Johnny".length()));
		assertEquals("Namen - Entry 6 is wrong.", "NAME:    Baby Doll :aka: Doll, Baby", nameLL.get(6).toString().substring(0, "NAME:    Baby Doll :aka: Doll, Baby".length()));
		assertEquals("Namen - Entry 7 is wrong.", "NAME:      AuD FAU :aka: FAU, AuD", nameLL.get(7).toString().substring(0, "NAME:      AuD FAU :aka: FAU, AuD".length()));
	}

	// ================================================================================

	class Zahl implements Segmentierbar {
		protected final int zahl;

		public Zahl(int zahl) {
			this.zahl = zahl >= 0 ? zahl : -zahl;
		}

		@Override
		public Integer[] holeSegmente() {
			return new Integer[] { zahl };
		}

		@Override
		public String toString() {
			return "ZAHL: " + zahl;
		}
	}

	class ZahlLex extends Zahl {
		public ZahlLex(int zahl) {
			super(zahl);
		}

		@Override
		public Integer[] holeSegmente() {
			LinkedList<Integer> segmente = new LinkedList<>();
			int zahlTmp = zahl;
			while (zahlTmp > 0) {
				segmente.addFirst(zahlTmp % 10);
				zahlTmp = zahlTmp / 10;
			}
			return segmente.toArray(new Integer[0]);
		}
	}

	class Name implements Segmentierbar {
		private final String vorname, nachname;

		public Name(String vorname, String nachname) {
			if (nachname == null || nachname.length() == 0 || vorname == null || vorname.length() == 0) {
				throw new IllegalArgumentException("Nix Name: " + nachname + ", " + vorname);
			}
			this.nachname = nachname;
			this.vorname = vorname;
		}

		@Override
		public String[] holeSegmente() {
			String name = nachname + vorname;
			String[] segmente = new String[name.length()];
			for (int c = 0; c < name.length(); c++) {
				segmente[c] = String.valueOf(name.charAt(c));
			}
			return segmente;
		}

		@Override
		public String toString() {
			return String.format("NAME: %1$12s :aka: %2$-12s ;) [id: %3$s]", vorname + " " + nachname, nachname + ", " + vorname, super.toString());
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