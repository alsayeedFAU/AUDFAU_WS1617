

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Small golden section search test
 * 
 * @author Georg Dotzler<georg.dotzler@informatik.uni-erlangen.de>
 */
public class GoldenSearchTest {
	final double EPSILON = 0.000001;

	double[][] polynom = {{0,1},{0,0,1},{0,-1,0.5},{42,-2,-0.5,1.0/3.0},{128,-30,-0.5,2,0.25},{128,-30,-0.5,2,0.25}};
	double[] solution = {-1,0,1,2,2,-5};
	double[] bordersLeft = {-1,-1,-5,-1,-1,-10};
	double[] bordersRight = {1, 1, 1, 5, 4, -4};

	Function[] getFunctions(){
		Function[] list = new Function[polynom.length];
		for (int i = 0; i < polynom.length; i++){
			list[i] = new Polynomial(polynom[i]);
		}
		return list;
	}


	@Test
	public void testGoldenSearch() {
		GoldenSearch b = new GoldenSearchImpl();
		Function[] f = getFunctions();
		for (int i = 0; i < polynom.length;i++){
			Double result = b.findMinimum(f[i], bordersLeft[i], bordersRight[i]);
			assertTrue(result!=null);
			assertEquals(solution[i], result, EPSILON);
		}
	}

	@Test
	public void testThreadedSearch() {
		GoldenSearch ts = new GoldenSearchImpl();
		ParallelGoldenSearch b = new ThreadedGoldenSearch();
		Function[] f = getFunctions();
		Double[] result = b.findMinimum(f, bordersLeft, bordersRight,4, ts);
		for (int j = 0; j < f.length; j++) {
			assertTrue(result[j]!=null);
			assertEquals(solution[j], result[j], EPSILON);
		}
	}

	@Test
	public void testTaskedSearch() {
		GoldenSearch ts = new GoldenSearchImpl();
		ParallelGoldenSearch b = new TaskedGoldenSearch();
		Function[] f = getFunctions();
		Double[] result = b.findMinimum(f, bordersLeft, bordersRight,4, ts);
		for (int j = 0; j < f.length; j++) {
			assertTrue(result[j]!=null);
			assertEquals(solution[j], result[j], EPSILON);
		}
	}
	
	@Test
	public void testThreadedSearchOneThread() {
		GoldenSearch ts = new GoldenSearchImpl();
		ParallelGoldenSearch b = new ThreadedGoldenSearch();
		Function[] f = getFunctions();
		Double[] result = b.findMinimum(f, bordersLeft, bordersRight,1, ts);
		for (int j = 0; j < f.length; j++) {
			assertTrue(result[j]!=null);
			assertEquals(solution[j], result[j], EPSILON);
		}
	}

	@Test
	public void testTaskedSearchOneThread() {
		GoldenSearch ts = new GoldenSearchImpl();
		ParallelGoldenSearch b = new TaskedGoldenSearch();
		Function[] f = getFunctions();
		Double[] result = b.findMinimum(f, bordersLeft, bordersRight,1, ts);
		for (int j = 0; j < f.length; j++) {
			assertTrue(result[j]!=null);
			assertEquals(solution[j], result[j], EPSILON);
		}
	}
	
	@Test
	public void testGoldenThreadedLarge(){
		int anzahl = 50000;
		Function[] f = new Function[anzahl];
		double[] l = new double[anzahl];
		double[] r = new double[anzahl];
		for (int i = 0; i < anzahl; i++) {
			f[i] = Polynomial.randomPolynomial((int) (Math.random() * 4));
			l[i] = -Math.random()*1000;
			r[i] = Math.random()*1000;
		}
		Double[] nullstellen = new Double[anzahl];
		Double[] reference = new Double[anzahl];
		ThreadedGoldenSearch ts = new ThreadedGoldenSearch();
		GoldenSearch ref = new GoldenSearchImpl();
		nullstellen = ts.findMinimum(f, l, r,8,ref);
		for (int i = 0; i < anzahl; i++) {
			reference[i] = ref.findMinimum(f[i], l[i], r[i]);
			assertTrue(nullstellen[i]!=null);
			assertEquals(reference[i], nullstellen[i], EPSILON);
		}
	}

	@Test
	public void testGoldenTaskedLarge(){
		int anzahl = 50000;
		Function[] f = new Function[anzahl];
		double[] l = new double[anzahl];
		double[] r = new double[anzahl];
		for (int i = 0; i < anzahl; i++) {
			f[i] = Polynomial.randomPolynomial((int) (Math.random() * 4));
			l[i] = -Math.random()*1000;
			r[i] = Math.random()*1000;
		}
		Double[] nullstellen = new Double[anzahl];
		Double[] reference = new Double[anzahl];
		TaskedGoldenSearch ts = new TaskedGoldenSearch();
		GoldenSearch ref = new GoldenSearchImpl();
		nullstellen = ts.findMinimum(f, l, r,8,ref);
		for (int i = 0; i < anzahl; i++) {
			reference[i] = ref.findMinimum(f[i], l[i], r[i]);
			assertTrue(nullstellen[i]!=null);
			assertEquals(reference[i], nullstellen[i], EPSILON);
		}
	}
}
