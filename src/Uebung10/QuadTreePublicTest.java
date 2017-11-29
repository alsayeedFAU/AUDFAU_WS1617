import static org.junit.Assert.*;
import java.util.*;
import org.junit.*;

public class QuadTreePublicTest {
	private static final java.util.Random random = new java.util.Random(4711_0815_666L);

	@Test(timeout = 666, expected = IndexOutOfBoundsException.class)
	public void pubTest__String__insert__String__check_bounds_x_too_small() {
		AbstractQuadTree<String> qt = QuadTreePublicTest.<String> createAndCheckRandomGoodQuadTree();
		String old = qt.insert(qt.minX - 1, qt.minY + 7, "JohnDoe was here.");
		assertSame("Method should not have returned anything at all (not even null), but thrown an IndexOutOfBoundsException instead!", old, old);
	}

	@Test(timeout = 666)
	public void pubTest__Long__insert__Long__and_replace_root_remains_leaf() {
		// insert and check result
		AbstractQuadTree<Long> qt = QuadTreePublicTest.<Long> createAndCheckRandomGoodQuadTree();
		long insX = qt.minX + random.nextInt((int) qt.delta), insY = qt.minY + random.nextInt((int) qt.delta), insV1 = random.nextLong(), insV2 = random.nextLong();
		Long old1 = qt.insert(insX, insY, insV1);
		assertNull("There should be no old entry here!", old1);
		// check data structure
		assertNotNull("data: Fields must be instantiated lazy - and now we must be eager...", qt.data);
		assertTrue("children: Fields must be instantiated lazy!", qt.northWest == null && qt.northEast == null && qt.southWest == null && qt.southEast == null);
		// check content
		assertEquals("data: Field has wrong size.", 1, qt.data.size());
		assertTrue("data: Field has wrong entry.", insX == qt.data.get(0).getX() && insY == qt.data.get(0).getY() && Long.valueOf(insV1).equals(qt.data.get(0).getValue()));
		// overwrite and check result
		for (int pass = 0; pass < 10; pass++) {
			Long old2 = qt.insert(insX, insY, insV2);
			assertNotNull("There should be an old entry here!", old2);
			assertEquals("data: Field has wrong size.", Long.valueOf(insV1), old2);
			insV1 = insV2; // for next pass
			// check data structure
			assertNotNull("data: Fields must be instantiated lazy - and now we must be eager...", qt.data);
			assertTrue("children: Fields must be instantiated lazy!", qt.northWest == null && qt.northEast == null && qt.southWest == null && qt.southEast == null);
			// check content
			assertEquals("data: Field has wrong size.", 1, qt.data.size());
			assertTrue("data: Field has wrong entry.", insX == qt.data.get(0).getX() && insY == qt.data.get(0).getY() && Long.valueOf(insV2).equals(qt.data.get(0).getValue()));
		}
	}

	@Test(timeout = 666)
	public void pubTest__Long__insert__Long__until_root_splits() {
		AbstractQuadTree<Long> qt = QuadTreePublicTest.<Long> createAndCheckRandomGoodQuadTree();
		HashSet<Point2D<Long>> ps = new HashSet<>();
		// fill root (southWest only)
		for (int i = 1; i <= qt.capacity; i++) {
			Long old = qt.insert(qt.minX + i, qt.minY + i, random.nextLong());
			assertNull("There should be no old entry here!", old);
			assertNotNull("data: Fields must be instantiated lazy - and now we must be eager...", qt.data);
			assertEquals("data: Field has wrong size.", i, qt.data.size());
		}
		// remember the contents of southWest
		ps.addAll(qt.data);
		// make root split (into southWest and northEast)
		long insX = qt.minX + qt.delta - random.nextInt((int) (qt.delta / 2 - 2)), insY = qt.minY + qt.delta - random.nextInt((int) (qt.delta / 2 - 2)), insV = random.nextLong();
		Long old = qt.insert(insX, insY, insV);
		assertNull("There should be no old entry here!", old);
		assertNull("data: Fields must be cleared eager!", qt.data);
		assertTrue("children: Fields must be instantiated lazy!", qt.northWest == null && qt.southEast == null);
		assertTrue("children: Fields must be instantiated lazy - and now we must be eager...", qt.northEast != null && qt.southWest != null);
		assertTrue("data: Fields must be instantiated lazy - and now we must be eager...", qt.northEast.data != null && qt.southWest.data != null);
		// check content
		assertEquals("data@southWest: Field has wrong size.", qt.capacity, qt.southWest.data.size());
		assertTrue("data@southWest: Field has wrong content!", ps.containsAll(qt.southWest.data));
		assertEquals("data@northEast: Field has wrong size.", 1, qt.northEast.data.size());
		assertTrue("data@northEast: Field has wrong content!", insX == qt.northEast.data.get(0).getX() && insY == qt.northEast.data.get(0).getY() && Long.valueOf(insV).equals(qt.northEast.data.get(0).getValue()));
	}

	@Test(timeout = 666)
	public void pubTest__String__insert__String__until_level_4_reached() {
		AbstractQuadTree<String> qt = new QuadTree<>(0, 0, 8000, 4);
		// fill all but southWest
		int[][] mask = { //
				{ 1, 1, 1, 1, 1, 1, 1, 1 }, //
				{ 0, 1, 0, 1, 0, 1, 0, 1 }, //
				{ 0, 0, 1, 1, 0, 0, 1, 1 }, //
				{ 0, 0, 0, 1, 0, 0, 0, 1 }, //
				{ 0, 0, 0, 0, 1, 1, 1, 1 }, //
				{ 0, 0, 0, 0, 0, 1, 0, 1 }, //
				{ 0, 0, 0, 0, 0, 0, 1, 1 }, //
				{ 0, 0, 0, 0, 0, 0, 0, 1 }, //
		};
		for (int y = 0; y < mask.length; y++) {
			for (int x = 0; x < mask[y].length; x++) {
				if (mask[y][x] != 0) {
					for (long[] ins : new long[][] { //
							{ x * 1000 + 1, (7 - y) * 1000 + 1 }, // SW-corner
							{ x * 1000 + 1000, (7 - y) * 1000 + 1 }, // NW-corner
							{ x * 1000 + 1, (7 - y) * 1000 + 1000 }, // SE-corner
							{ x * 1000 + 1000, (7 - y) * 1000 + 1000 } // NE-corner
					}) {
						long insX = ins[0], insY = ins[1];
						assertNull("There should be no old entry here!", qt.insert(insX, insY, "JohnDoe was at (" + insX + "," + insY + ")"));
					}
				}
			}
		}
		// check level 0 to 2: nothing
		for (AbstractQuadTree<?> checkQt : new AbstractQuadTree<?>[] { //
				qt, // level 0 (root)
				qt.northWest, qt.northEast, qt.southEast, // level 1
				qt.northWest.northWest, qt.northWest.northEast, qt.northWest.southEast, // level 2a
				qt.northEast.northWest, qt.northEast.northEast, qt.northEast.southEast, // level 2b
				qt.southEast.northWest, qt.southEast.northEast, qt.southEast.southEast // level 2c
		}) {
			assertTrue("children: Fields must be instantiated lazy!", checkQt.southWest == null);
			assertTrue("children: Fields must be instantiated lazy - and now we must be eager...", checkQt.northWest != null && checkQt.northEast != null && checkQt.southEast != null);
			assertNull("data: Fields must be cleared eager!", checkQt.data);
		}
		// check level 2: some of the "not so nothing"
		assertNotNull("data: Fields must be instantiated lazy - and now we must be eager...", qt.northWest.northWest.northWest.data);
		assertEquals("data: Field has wrong size.", 4, qt.northWest.northWest.northWest.data.size());
		for (Point2D<String> p : qt.northWest.northWest.northWest.data) {
			assertTrue("data@northWest.northWest.northWest: Field has wrong content!", "JohnDoe was at (1,7001)".equals(p.getValue()) || "JohnDoe was at (1000,7001)".equals(p.getValue()) || "JohnDoe was at (1,8000)".equals(p.getValue()) || "JohnDoe was at (1000,8000)".equals(p.getValue()));
		}
		assertNotNull("data: Fields must be instantiated lazy - and now we must be eager...", qt.southEast.northWest.northEast.data);
		assertEquals("data: Field has wrong size.", 4, qt.southEast.northWest.northEast.data.size());
		for (Point2D<String> p : qt.southEast.northWest.northEast.data) {
			assertTrue("data@southEast.northWest.northEast: Field has wrong content!", "JohnDoe was at (5001,3001)".equals(p.getValue()) || "JohnDoe was at (6000,3001)".equals(p.getValue()) || "JohnDoe was at (5001,4000)".equals(p.getValue()) || "JohnDoe was at (6000,4000)".equals(p.getValue()));
		}
	}

	@Test(timeout = 3333)
	public void pubTest__Long__insert__Long__really_many__Long__get__one_existing() {
		AbstractQuadTree<Long> qt = QuadTreePublicTest.<Long> createAndCheckRandomGoodQuadTree();
		// insert a few
		HashMap<Long, HashMap<Long, Long>> trace = new HashMap<>();
		for (int i = 0; i <= 42_000; i++) {
			long insX = qt.minX + random.nextInt((int) (qt.delta));
			long insY = qt.minY + random.nextInt((int) (qt.delta));
			long insV = random.nextLong();
			if (!trace.containsKey(insX)) {
				trace.put(insX, new HashMap<Long, Long>());
			}
			Long old = qt.insert(insX, insY, insV);
			if (!trace.get(insX).containsKey(insY)) {
				assertNull("There should be no old entry here!", old);
			} else {
				assertNotNull("There should be an old entry here!", old);
				assertEquals("There should be an old entry here!", trace.get(insX).get(insY), old);
			}
			trace.get(insX).put(insY, insV);
		}
		// overwrite each one
		for (Long insX : trace.keySet()) {
			for (Long insY : trace.get(insX).keySet()) {
				long insV = random.nextLong();
				Long old = qt.insert(insX, insY, insV);
				assertNotNull("Overwrite: There should be an old entry here!", old);
				assertEquals("Overwrite: There should be an old entry here!", trace.get(insX).get(insY), old);
				trace.get(insX).put(insY, insV);
			}
		}
		// recall each one
		for (Long insX : trace.keySet()) {
			for (Long insY : trace.get(insX).keySet()) {
				Long old = qt.get(insX, insY);
				assertNotNull("Get: There should be an old entry here!", old);
				assertEquals("Get: There should be an old entry here!", trace.get(insX).get(insY), old);
			}
		}
	}

	@Test(timeout = 666, expected = NoSuchElementException.class)
	public void pubTest__String__insert__String__and_than__String__get__one_not_existing() {
		AbstractQuadTree<String> qt = QuadTreePublicTest.<String> createAndCheckRandomGoodQuadTree();
		long insX = qt.minX + random.nextInt((int) qt.delta - 1), insY = qt.minY + random.nextInt((int) qt.delta);
		qt.insert(insX, insY, "JohnDoe was here.");
		String old = qt.get(insX + 1, insY); // but not here ;)...
		assertSame("Method should not have returned anything at all (not even null), but thrown a NoSuchElementException instead!", old, old);
	}

	@Test(timeout = 6666)
	public void pubTest__Long__insert__Long__really_many__ListLong__get_radius() {
		AbstractQuadTree<Long> qt = QuadTreePublicTest.<Long> createAndCheckRandomGoodQuadTree();
		long radius = qt.delta / 8 + random.nextInt((int) (qt.delta / 8));
		long cX = qt.minX + qt.delta / 4 + random.nextInt((int) (qt.delta / 2));
		long cY = qt.minY + qt.delta / 4 + random.nextInt((int) (qt.delta / 2));
		HashMap<Long, HashMap<Long, Long>> traceInside = new HashMap<>(), traceOutside = new HashMap<>();
		for (int i = 0; i <= 42_000; i++) {
			long insX = qt.minX + random.nextInt((int) (qt.delta));
			long insY = qt.minY + random.nextInt((int) (qt.delta));
			long insV = random.nextLong();
			qt.insert(insX, insY, insV);
			if (Math.sqrt((insX - cX) * (insX - cX) + (insY - cY) * (insY - cY)) <= radius) {
				if (!traceInside.containsKey(insX)) {
					traceInside.put(insX, new HashMap<Long, Long>());
				}
				traceInside.get(insX).put(insY, insV);
			} else {
				if (!traceOutside.containsKey(insX)) {
					traceOutside.put(insX, new HashMap<Long, Long>());
				}
				traceOutside.get(insX).put(insY, insV);
			}
		}
		java.util.LinkedList<Long> result = qt.get(cX, cY, radius);
		assertNotNull("You should NEVER EVER return null here!", result);
		for (Long insX : traceInside.keySet()) {
			for (Long insY : traceInside.get(insX).keySet()) {
				Long expected = traceInside.get(insX).get(insY);
				assertTrue("Missing value inside: " + expected + "@(" + insX + "," + insY + ")" + " for (cX, cY, radius) = (" + cX + "," + cY + "," + radius + ")", result.contains(expected));
			}
		}
		for (Long insX : traceOutside.keySet()) {
			for (Long insY : traceOutside.get(insX).keySet()) {
				Long expectedNot = traceOutside.get(insX).get(insY);
				assertFalse("Found illegal value inside: " + expectedNot + "@(" + insX + "," + insY + ")" + " for (cX, cY, radius) = (" + cX + "," + cY + "," + radius + ")", result.contains(expectedNot));
			}
		}
	}

	@Test(timeout = 9999)
	public void pubTest__String__insert__really_many__ListString__get_radius() {
		AbstractQuadTree<String> qt = QuadTreePublicTest.<String> createAndCheckRandomGoodQuadTree();
		long radius = qt.delta / 8 + random.nextInt((int) (qt.delta / 8));
		long cX = qt.minX + qt.delta / 4 + random.nextInt((int) (qt.delta / 2));
		long cY = qt.minY + qt.delta / 4 + random.nextInt((int) (qt.delta / 2));
		HashMap<Long, HashMap<Long, String>> traceInside = new HashMap<>(), traceOutside = new HashMap<>();
		for (int i = 0; i <= 42_000; i++) {
			long insX = qt.minX + random.nextInt((int) (qt.delta));
			long insY = qt.minY + random.nextInt((int) (qt.delta));
			String insV = "JohnDoe was at (" + insX + "," + insY + ") with " + random.nextLong();
			qt.insert(insX, insY, insV);
			if (Math.sqrt((insX - cX) * (insX - cX) + (insY - cY) * (insY - cY)) <= radius) {
				if (!traceInside.containsKey(insX)) {
					traceInside.put(insX, new HashMap<Long, String>());
				}
				traceInside.get(insX).put(insY, insV);
			} else {
				if (!traceOutside.containsKey(insX)) {
					traceOutside.put(insX, new HashMap<Long, String>());
				}
				traceOutside.get(insX).put(insY, insV);
			}
		}
		java.util.LinkedList<String> result = qt.get(cX, cY, radius);
		assertNotNull("You should NEVER EVER return null here!", result);
		for (Long insX : traceInside.keySet()) {
			for (Long insY : traceInside.get(insX).keySet()) {
				String expected = traceInside.get(insX).get(insY);
				assertTrue("Missing value inside: " + expected + "@(" + insX + "," + insY + ")" + " for (cX, cY, radius) = (" + cX + "," + cY + "," + radius + ")", result.contains(expected));
			}
		}
		for (Long insX : traceOutside.keySet()) {
			for (Long insY : traceOutside.get(insX).keySet()) {
				String expectedNot = traceOutside.get(insX).get(insY);
				assertFalse("Found illegal value inside: " + expectedNot + "@(" + insX + "," + insY + ")" + " for (cX, cY, radius) = (" + cX + "," + cY + "," + radius + ")", result.contains(expectedNot));
			}
		}
	}

	@Test(timeout = 666)
	public void pubTest__Long__insert__Long__really_many__Long__remove() {
		AbstractQuadTree<Long> qt = QuadTreePublicTest.<Long> createAndCheckRandomGoodQuadTree();
		// insert a few
		HashMap<Long, HashMap<Long, Long>> trace = new HashMap<>();
		for (int i = 0; i <= 50; i++) {
			long insX = qt.minX + random.nextInt((int) (qt.delta));
			long insY = qt.minY + random.nextInt((int) (qt.delta));
			long insV = random.nextLong();
			if (!trace.containsKey(insX)) {
				trace.put(insX, new HashMap<Long, Long>());
			}
			qt.insert(insX, insY, insV);
			trace.get(insX).put(insY, insV);
		}
		// remove each one of the old ones
		for (Long remX : trace.keySet()) {
			for (Long remY : trace.get(remX).keySet()) {
				Long old = qt.remove(remX, remY);
				assertNotNull("Remove: There should be an old entry here!", old);
				assertEquals("Remove: There should be an old entry here!", trace.get(remX).get(remY), old);
			}
		}
		assertNull("data: Fields must be cleaned up eager!", qt.data);
		assertTrue("children: Fields must be cleaned up eager!", qt.northWest == null && qt.northEast == null && qt.southWest == null && qt.southEast == null);
		// remove at random
		for (int i = 0; i <= 50; i++) {
			long remX = qt.minX + random.nextInt((int) (qt.delta));
			long remY = qt.minY + random.nextInt((int) (qt.delta));
			Long old = qt.remove(remX, remY);
			assertNull("Remove: There should be no old entry here (anymore)!", old);
		}
	}

	@Test(timeout = 666)
	public void pubTest__String__insert__String__really_many__String__remove() {
		AbstractQuadTree<String> qt = QuadTreePublicTest.<String> createAndCheckRandomGoodQuadTree();
		// insert a few
		HashMap<Long, HashMap<Long, String>> trace = new HashMap<>();
		for (int i = 0; i <= 50; i++) {
			long insX = qt.minX + random.nextInt((int) (qt.delta));
			long insY = qt.minY + random.nextInt((int) (qt.delta));
			String insV = "JohnDoe was at (" + insX + "," + insY + ") with " + random.nextLong();
			if (!trace.containsKey(insX)) {
				trace.put(insX, new HashMap<Long, String>());
			}
			qt.insert(insX, insY, insV);
			trace.get(insX).put(insY, insV);
		}
		// remove each one of the old ones
		for (Long remX : trace.keySet()) {
			for (Long remY : trace.get(remX).keySet()) {
				String old = qt.remove(remX, remY);
				assertNotNull("Remove: There should be an old entry here!", old);
				assertEquals("Remove: There should be an old entry here!", trace.get(remX).get(remY), old);
			}
		}
		assertNull("data: Fields must be cleaned up eager!", qt.data);
		assertTrue("children: Fields must be cleaned up eager!", qt.northWest == null && qt.northEast == null && qt.southWest == null && qt.southEast == null);
		// remove at random
		for (int i = 0; i <= 50; i++) {
			long remX = qt.minX + random.nextInt((int) (qt.delta));
			long remY = qt.minY + random.nextInt((int) (qt.delta));
			String old = qt.remove(remX, remY);
			assertNull("Remove: There should be no old entry here (anymore)!", old);
		}
	}

	// ========== HELPER ==========
	protected static <V> AbstractQuadTree<V> createAndCheckRandomGoodQuadTree() {
		return QuadTreePublicTest.<V> createAndCheckRandomGoodQuadTree(-666, 4711, -666, 4711, 666, 4711, 7, 42);
	}

	protected static <V> AbstractQuadTree<V> createAndCheckRandomGoodQuadTree(long minXmin, int minXrange, long minYmin, int minYrange, long deltaMin, int deltaRange, int capacityMin, int capacityRange) {
		long minX = minXmin + random.nextInt(minXrange), minY = minYmin + random.nextInt(minYrange), delta = deltaMin + random.nextInt(deltaRange);
		int capacity = capacityMin + (capacityRange == 0 ? 0 : random.nextInt(capacityRange));
		AbstractQuadTree<V> qt = new QuadTree<>(minX, minY, delta, capacity);
		assertEquals("minX: Call the super-cons and use the super-attributes!", minX, qt.minX);
		assertEquals("minY: Call the super-cons and use the super-attributes!", minY, qt.minY);
		assertEquals("minX: Call the super-cons and use the super-attributes!", delta, qt.delta);
		assertEquals("minY: Call the super-cons and use the super-attributes!", capacity, qt.capacity);
		assertNull("data: Fields must be instantiated lazy!", qt.data);
		assertNull("northWest: Fields must be instantiated lazy!", qt.northWest);
		assertNull("northEast: Fields must be instantiated lazy!", qt.northEast);
		assertNull("southWest: Fields must be instantiated lazy!", qt.southWest);
		assertNull("southEast: Fields must be instantiated lazy!", qt.southEast);
		return qt;
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