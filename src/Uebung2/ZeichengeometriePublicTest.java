import static org.junit.Assert.*;
import org.junit.*;

public class ZeichengeometriePublicTest {
	// ========== erzeugeNeueZeichenflaeche ==========
	@Test(timeout = 666)
	public void testErzeugeNeueZeichenflaeche__80x40__check() {
		char[][] zeichenflaeche = Zeichengeometrie.erzeugeNeueZeichenflaeche(80, 40);
		assertNotNull(zeichenflaeche);
		assertEquals(40, zeichenflaeche.length);
		for (int zeile = 0; zeile < 40; zeile++) {
			assertNotNull(zeichenflaeche[zeile]);
			assertEquals(80, zeichenflaeche[zeile].length);
		}
		assertEquals(' ', zeichenflaeche[28][47]);
		assertEquals(32, zeichenflaeche[28][47]);
	}

	// ========== zeichnePunkt ==========
	@Test(timeout = 666)
	public void testZeichnePunkt__47x11xStern__check() {
		char[][] zeichenflaeche = Zeichengeometrie.erzeugeNeueZeichenflaeche(80, 40);
		zeichenflaeche = Zeichengeometrie.zeichnePunkt(zeichenflaeche, 47, 11, '*');
		assertEquals('*', zeichenflaeche[28][47]);
	}

	// ========== zeichneLinie ==========
	@Test(timeout = 666)
	public void testZeichneLinie__x_Achse__check() {
		char[][] zeichenflaeche = Zeichengeometrie.erzeugeNeueZeichenflaeche(80, 40);
		zeichenflaeche = Zeichengeometrie.zeichneLinie(zeichenflaeche, 0, 0, 79, 0, '_');
		Zeichengeometrie.aufBildschirmAusgeben(zeichenflaeche);
		assertEquals('_', zeichenflaeche[39][0]);
		assertEquals('_', zeichenflaeche[39][42]);
		assertEquals('_', zeichenflaeche[39][79]);
	}

	@Test(timeout = 666)
	public void testZeichneLinie__y_Achse__check() {
		char[][] zeichenflaeche = Zeichengeometrie.erzeugeNeueZeichenflaeche(80, 40);
		zeichenflaeche = Zeichengeometrie.zeichneLinie(zeichenflaeche, 0, 0, 0, 39, '|');
		Zeichengeometrie.aufBildschirmAusgeben(zeichenflaeche);
		assertEquals('|', zeichenflaeche[0][0]);
		assertEquals('|', zeichenflaeche[21][0]);
		assertEquals('|', zeichenflaeche[39][0]);
	}

	@Test(timeout = 666)
	public void testZeichneLinie__Diagonale__check() {
		char[][] zeichenflaeche = Zeichengeometrie.erzeugeNeueZeichenflaeche(80, 40);
		zeichenflaeche = Zeichengeometrie.zeichneLinie(zeichenflaeche, 40, 20, 80, -80, '\\');
		Zeichengeometrie.aufBildschirmAusgeben(zeichenflaeche);
		assertEquals('\\', zeichenflaeche[19][40]);
		assertEquals('\\', zeichenflaeche[31][45]);
		assertEquals('\\', zeichenflaeche[39][48]);
		String linie = "" + //
				"                                                                                " + //
				"                                                                                " + //
				"                                                                                " + //
				"                                                                                " + //
				"                                                                                " + //
				"                                                                                " + //
				"                                                                                " + //
				"                                                                                " + //
				"                                                                                " + //
				"                                                                                " + //
				"                                                                                " + //
				"                                                                                " + //
				"                                                                                " + //
				"                                                                                " + //
				"                                                                                " + //
				"                                                                                " + //
				"                                                                                " + //
				"                                                                                " + //
				"                                                                                " + //
				"                                        \\                                       " + //
				"                                                                                " + //
				"                                         \\                                      " + //
				"                                                                                " + //
				"                                                                                " + //
				"                                          \\                                     " + //
				"                                                                                " + //
				"                                           \\                                    " + //
				"                                                                                " + //
				"                                                                                " + //
				"                                            \\                                   " + //
				"                                                                                " + //
				"                                             \\                                  " + //
				"                                                                                " + //
				"                                                                                " + //
				"                                              \\                                 " + //
				"                                                                                " + //
				"                                               \\                                " + //
				"                                                                                " + //
				"                                                                                " + //
				"                                                \\                               " + //
				"";
		for (int zeile = 0; zeile < 40; zeile++) {
			for (int spalte = 0; spalte < 80; spalte++) {
				assertEquals(linie.charAt(80 * zeile + spalte), zeichenflaeche[zeile][spalte]);
			}
		}
	}

	// ========== zeichnePolygon ==========
	@Test(timeout = 666)
	public void testZeichnePolygon__Das_Haus_vom_Nikolaus__check() {
		char[][] zeichenflaeche = Zeichengeometrie.erzeugeNeueZeichenflaeche(80, 40);
		int[] dasHausVomNikolaus = { 28, 10, 10, 28, 19, 37, 28, 28, 28, 10, 10, 10, 28, 28, 10, 28, 10, 10 };
		zeichenflaeche = Zeichengeometrie.zeichnePolygon(zeichenflaeche, dasHausVomNikolaus, '*');
		Zeichengeometrie.aufBildschirmAusgeben(zeichenflaeche);
		String dasHausVomNikolausErgebnis = "" + //
				"                                                                                " + //
				"                                                                                " + //
				"                   *                                                            " + //
				"                  * *                                                           " + //
				"                 *   *                                                          " + //
				"                *     *                                                         " + //
				"               *       *                                                        " + //
				"              *         *                                                       " + //
				"             *           *                                                      " + //
				"            *             *                                                     " + //
				"           *               *                                                    " + //
				"          *******************                                                   " + //
				"          **               **                                                   " + //
				"          * *             * *                                                   " + //
				"          *  *           *  *                                                   " + //
				"          *   *         *   *                                                   " + //
				"          *    *       *    *                                                   " + //
				"          *     *     *     *                                                   " + //
				"          *      *   *      *                                                   " + //
				"          *       * *       *                                                   " + //
				"          *        *        *                                                   " + //
				"          *       * *       *                                                   " + //
				"          *      *   *      *                                                   " + //
				"          *     *     *     *                                                   " + //
				"          *    *       *    *                                                   " + //
				"          *   *         *   *                                                   " + //
				"          *  *           *  *                                                   " + //
				"          * *             * *                                                   " + //
				"          **               **                                                   " + //
				"          *******************                                                   " + //
				"                                                                                " + //
				"                                                                                " + //
				"                                                                                " + //
				"                                                                                " + //
				"                                                                                " + //
				"                                                                                " + //
				"                                                                                " + //
				"                                                                                " + //
				"                                                                                " + //
				"                                                                                " + //
				"";
		for (int zeile = 0; zeile < 40; zeile++) {
			for (int spalte = 0; spalte < 80; spalte++) {
				assertEquals(dasHausVomNikolausErgebnis.charAt(80 * zeile + spalte), zeichenflaeche[zeile][spalte]);
			}
		}
	}

	// ========== zeichneRechteck ==========
	@Test(timeout = 666)
	public void testZeichneRechteck__RechteckRahmen__check() {
		char[][] zeichenflaeche = Zeichengeometrie.erzeugeNeueZeichenflaeche(80, 40);
		zeichenflaeche = Zeichengeometrie.zeichneRechteck(zeichenflaeche, 0, 39, 79, 0, '+', false);
		Zeichengeometrie.aufBildschirmAusgeben(zeichenflaeche);
		assertEquals('+', zeichenflaeche[0][0]);
		assertEquals('+', zeichenflaeche[0][42]);
		assertEquals('+', zeichenflaeche[0][79]);
		assertEquals('+', zeichenflaeche[21][0]);
		assertEquals('+', zeichenflaeche[21][79]);
		assertEquals('+', zeichenflaeche[39][0]);
		assertEquals('+', zeichenflaeche[39][42]);
		assertEquals('+', zeichenflaeche[39][79]);
	}

	@Test(timeout = 666)
	public void testZeichneRechteck__RechteckFlaeche__check() {
		char[][] zeichenflaeche = Zeichengeometrie.erzeugeNeueZeichenflaeche(80, 40);
		zeichenflaeche = Zeichengeometrie.zeichneRechteck(zeichenflaeche, -5, -5, 15, 15, '#', true);
		Zeichengeometrie.aufBildschirmAusgeben(zeichenflaeche);
		assertEquals('#', zeichenflaeche[24][0]);
		assertEquals('#', zeichenflaeche[24][15]);
		assertEquals('#', zeichenflaeche[39][15]);
		assertEquals(' ', zeichenflaeche[24][16]);
		assertEquals(' ', zeichenflaeche[23][14]);
		assertEquals(' ', zeichenflaeche[23][16]);
		assertEquals('#', zeichenflaeche[39][0]);
		assertEquals('#', zeichenflaeche[29][10]);
	}

	// ========== zeichneKreis ==========
	@Test(timeout = 666)
	public void testZeichneKreis__check() {
		char[][] zeichenflaeche = Zeichengeometrie.erzeugeNeueZeichenflaeche(80, 40);
		zeichenflaeche = Zeichengeometrie.zeichneKreis(zeichenflaeche, 39, 19, 15, 'o');
		Zeichengeometrie.aufBildschirmAusgeben(zeichenflaeche);
		String kreis = "" + //
				"                                                                                " + //
				"                                                                                " + //
				"                                                                                " + //
				"                                                                                " + //
				"                                                                                " + //
				"                                    ooooooo                                     " + //
				"                                 ooo       ooo                                  " + //
				"                               oo             oo                                " + //
				"                              o                 o                               " + //
				"                             o                   o                              " + //
				"                            o                     o                             " + //
				"                           o                       o                            " + //
				"                          o                         o                           " + //
				"                          o                         o                           " + //
				"                         o                           o                          " + //
				"                         o                           o                          " + //
				"                         o                           o                          " + //
				"                        o                             o                         " + //
				"                        o                             o                         " + //
				"                        o                             o                         " + //
				"                        o                             o                         " + //
				"                        o                             o                         " + //
				"                        o                             o                         " + //
				"                        o                             o                         " + //
				"                         o                           o                          " + //
				"                         o                           o                          " + //
				"                         o                           o                          " + //
				"                          o                         o                           " + //
				"                          o                         o                           " + //
				"                           o                       o                            " + //
				"                            o                     o                             " + //
				"                             o                   o                              " + //
				"                              o                 o                               " + //
				"                               oo             oo                                " + //
				"                                 ooo       ooo                                  " + //
				"                                    ooooooo                                     " + //
				"                                                                                " + //
				"                                                                                " + //
				"                                                                                " + //
				"                                                                                " + //
				"";
		for (int zeile = 0; zeile < 40; zeile++) {
			for (int spalte = 0; spalte < 80; spalte++) {
				assertEquals(kreis.charAt(80 * zeile + spalte), zeichenflaeche[zeile][spalte]);
			}
		}
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