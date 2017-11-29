import static org.junit.Assert.*;
import org.junit.*;
import java.util.*;

public class RekursiveKunstPublicTest {
	@Test(timeout = 666)
	public void main_checkForBrokenSkeleton_PUBLIC_TEST() {
		// first check that you didn't break anything
		RekursiveKunst.angleRandomness = 0;
		assertEquals("You broke given method getNextRandomAngle", 0, RekursiveKunst.getNextRandomAngle(), 0.00001);
		RekursiveKunst.angleRNG = new Random(23);
		RekursiveKunst.angleRandomness = Math.PI / 6.0;
		assertEquals("You broke given method getNextRandomAngle", 0.1215442066519879, RekursiveKunst.getNextRandomAngle(), 0.00001);

		RekursiveKunst.lenRandomness = 0;
		assertEquals("You broke given method getNextRandomLen", 0, RekursiveKunst.getNextRandomLen(), 0.00001);
		RekursiveKunst.lenRNG = new Random(4711);
		RekursiveKunst.lenRandomness = 0.35;
		assertEquals("You broke given method getNextRandomLen", -0.15989444495801644, RekursiveKunst.getNextRandomLen(), 0.00001);
	}

	@Test(timeout = 666)
	public void main_getNewX_PUBLIC_TEST() {
		int[] xe = new int[] { -5, -4, -31, -2, -1, 0, 1, 2, 3, 4 };
		double[] le = new double[] { 0.5, 1.0, 1.5, 20.0, 2.5, 3.0, 35, 4.0, 4.5, 5.0 };
		int[] expxe = new int[] { -5, -4, -32, -13, -1, 0, 11, 4, 6, 8 };
		for (int i = 0; i < xe.length; i++) {
			double a = i * Math.PI / 10. - Math.PI / 2.;
			int newX = RekursiveKunst.getNewX(xe[i], le[i], a);
			assertEquals("checking getNewX with " + xe[i] + ", " + le[i] + ", " + a, expxe[i], newX);
		}
	}

	@Test(timeout = 666)
	public void main_getNewX_checkCast_PUBLIC_TEST() {
		int x = 25;
		double len = 12;
		double angle = -0.9780132221044398;

		int expected = 15;
		int expectedWithWrongCast = 16;

		int newX = RekursiveKunst.getNewX(x, len, angle);
		assertNotEquals("you may cast too early", expectedWithWrongCast, newX);
		assertEquals("checking getNewX with " + x + ", " + len + ", " + angle, expected, newX);
	}

	@Test(timeout = 666)
	public void main_getNewY_PUBLIC_TEST() {
		int[] ye = new int[] { -5, -4, -31, -2, -1, 0, 1, 2, 3, 4 };
		double[] le = new double[] { 0.5, 1.0, 1.5, 20.0, 2.5, 3.0, 35, 4.0, 4.5, 5.0 };
		ye[4] = 23;
		int[] expye = new int[] { -5, -3, -29, 17, 24, 0, -19, -1, -1, 1 };
		for (int i = 0; i < ye.length; i++) {
			double a = i * Math.PI / 5. - Math.PI / 2.;
			int newY = RekursiveKunst.getNewY(ye[i], le[i], a);
			assertEquals("checking getNewY with " + ye[i] + ", " + le[i] + ", " + a, expye[i], newY);
		}
	}

	@Test(timeout = 666)
	public void main_getNewLen_PUBLIC_TEST() {
		RekursiveKunst.lenFactor = 0.6;
		RekursiveKunst.lenRandomness = 0.;
		RekursiveKunst.lenRNG = new Random(4711);
		RekursiveKunst.getNewLen(1.0);
	}

	@Test(timeout = 666)
	public void main_getNewAngle_PUBLIC_TEST() {
		RekursiveKunst.angleRNG = new Random(23);
		RekursiveKunst.angleRandomness = 0;
		assertEquals("checking getNewAngle", -1, RekursiveKunst.getNewAngle(-1, Math.PI / 10., 0), 0.00001);
		assertEquals("checking getNewAngle", -0.6858407346410207, RekursiveKunst.getNewAngle(-1, Math.PI / 10., 1), 0.00001);
		assertEquals("checking getNewAngle", -0.057522203923062065, RekursiveKunst.getNewAngle(-1, Math.PI / 10., 3), 0.00001);
	}

	@Test(timeout = 666)
	public void main_getNewLen2_PUBLIC_TEST() {
		RekursiveKunst.lenFactor = 0.6;
		RekursiveKunst.lenRandomness = 0.35;
		RekursiveKunst.lenRNG = new Random(4711);
		assertEquals("checking getNewLen", 0.44010555504198356, RekursiveKunst.getNewLen(1.0), 0.00001);
		assertEquals("checking getNewLen", 0.5574103903339103, RekursiveKunst.getNewLen(1.0), 0.00001);
		assertEquals("checking getNewLen", 0.47781624905004605, RekursiveKunst.getNewLen(1.0), 0.00001);
	}

	@Test(timeout = 666)
	public void main_getNewAngle2_PUBLIC_TEST() {
		RekursiveKunst.angleRNG = new Random(23);
		RekursiveKunst.angleRandomness = Math.PI / 6.;
		assertEquals("checking getNewAngle", -0.8784557933480122, RekursiveKunst.getNewAngle(-1, Math.PI / 10., 0), 0.00001);
		assertEquals("checking getNewAngle", -1.2427093586667994, RekursiveKunst.getNewAngle(-1, Math.PI / 10., 0), 0.00001);
		assertEquals("checking getNewAngle", -1.1447483797648634, RekursiveKunst.getNewAngle(-1, Math.PI / 10., 0), 0.00001);
	}

	@Test(timeout = 6666)
	public void main_draw_PUBLIC_TEST() {
		RekursiveKunst.branches = 4;
		RekursiveKunst.openAngle = 0.7 * Math.PI;
		RekursiveKunst.lenFactor = 0.6;
		RekursiveKunst.lenRandomness = 0.35;
		RekursiveKunst.angleRandomness = Math.PI / 6.;
		RekursiveKunst.angleRNG = new Random(23);
		RekursiveKunst.lenRNG = new Random(4711);
		int numSteps = 5;
		RekursiveKunst.canvas = new Canvas(50, 70, numSteps);
		RekursiveKunst.draw(25, 1, 27, 0, numSteps);
		// don't try to understand this, instead try to fix your recursion
		assertEquals("draw incorrect",
				"3331112622132121562143111511311411335311117355111915214941821111349811411648721422246112151111451136111621521124211123721136122211312336123481213214111353131515421140111141111053381212611152402111326122322213413414211221041351231521143661321115812113511613332146751317115431171331364248813213132121121812451302111121523161741130212122221121251932141262434113130212643117111305114121112812131421611327217131111121153471451331111422711771341112113711511125134152115229713413231613217413511251442521243627159252235271512411235261421212161221113615171131237118127151712311234121613416146231221633216127311617315102111142211313122542213611334111111141133242716323141231111132511129321212312225341301211167127",
				RekursiveKunst.canvas.toString());
	}

	public static void main(String args[]) {
		// to compile on command line: javac -cp .:/usr/share/java/junit4.jar *.java
		// to run on command line: java -cp .:/usr/share/java/junit4.jar <nameOfThisClass>

		if (args.length == 0) {
			// starts junit runner - don't try to understand!
			org.junit.runner.JUnitCore.main(new Object() {
			}.getClass().getEnclosingClass().getSimpleName());
		} else if (args.length == 1) {
			// left tree from exercise sheet: run this main with exactly one argument to see it (NO TESTING!!!)
			RekursiveKunst.branches = 5;
			RekursiveKunst.openAngle = 0.7 * Math.PI;
			RekursiveKunst.lenFactor = 0.6;
			RekursiveKunst.lenRandomness = 0.;
			RekursiveKunst.angleRandomness = 0.;
			RekursiveKunst.angleRNG = new Random(23);
			RekursiveKunst.lenRNG = new Random(4711);
			int numSteps = 7;
			RekursiveKunst.canvas = new Canvas(1000, 1000, numSteps);
			RekursiveKunst.draw(500, 10, 300, 0, numSteps);
		} else {
			// right tree from exercise sheet: run this main with more than one argument to see it (NO TESTING!!!)
			RekursiveKunst.branches = 4;
			RekursiveKunst.openAngle = 0.7 * Math.PI;
			RekursiveKunst.lenFactor = 0.6;
			RekursiveKunst.lenRandomness = 0.35;
			RekursiveKunst.angleRandomness = Math.PI / 6.;
			RekursiveKunst.angleRNG = new Random(23);
			RekursiveKunst.lenRNG = new Random(4711);
			int numSteps = 9;
			RekursiveKunst.canvas = new Canvas(1000, 1000, numSteps);
			RekursiveKunst.draw(500, 10, 270, 0, numSteps);
		}
	}
}