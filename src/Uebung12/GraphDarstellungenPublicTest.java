import static org.junit.Assert.*;
import org.junit.*;
import java.lang.reflect.*;

public class GraphDarstellungenPublicTest {
	// -------------------- Intestines --------------------
	private static final Field[] getDeclaredFields(Class<?> clazz) {
		java.util.List<Field> declaredFields = new java.util.ArrayList<>();
		for (Field f : clazz.getDeclaredFields()) {
			if (!f.isSynthetic()) {
				declaredFields.add(f);
			}
		}
		return declaredFields.toArray(new Field[0]);
	}

	private static final Constructor<?>[] getDeclaredConstructors(Class<?> clazz) {
		java.util.List<Constructor<?>> declaredConstructors = new java.util.ArrayList<>();
		for (Constructor<?> c : clazz.getDeclaredConstructors()) {
			if (!c.isSynthetic()) {
				declaredConstructors.add(c);
			}
		}
		return declaredConstructors.toArray(new Constructor[0]);
	}

	private static final Method[] getDeclaredMethods(Class<?> clazz) {
		java.util.List<Method> declaredMethods = new java.util.ArrayList<>();
		for (Method m : clazz.getDeclaredMethods()) {
			if (!m.isBridge() && !m.isSynthetic()) {
				declaredMethods.add(m);
			}
		}
		return declaredMethods.toArray(new Method[0]);
	}

	@Test(timeout = 555)
	public void pubTest__AbstractGraphWithOps__check_intestines__THIS_TEST_IS_VERY_IMPORTANT_IF_IT_FAILS_THEN_YOU_WILL_GET_NO_POINTS_AT_ALL() {
		Class<?> clazz = AbstractGraphWithOps.class;
		assertTrue(clazz.getName() + " ist faelschlicherweise nicht >public<", Modifier.isPublic(clazz.getModifiers()));
		assertTrue(clazz.getName() + " ist faelschlicherweise nicht >abstract<", Modifier.isAbstract(clazz.getModifiers()));
		assertTrue(clazz.getName() + " ist faelschlicherweise Annotation/Enum/Interface.", !clazz.isAnnotation() && !clazz.isEnum() && !clazz.isInterface());
		assertEquals(clazz.getName() + " hat faelschlicherweise innere Annotationen.", 0, clazz.getDeclaredAnnotations().length);
		assertEquals(clazz.getName() + " hat faelschlicherweise innere Klassen.", 0, clazz.getDeclaredClasses().length);
		assertEquals(clazz.getName() + " darf keine Schnittstelle implementieren.", 0, clazz.getInterfaces().length);
		assertSame(clazz.getName() + " ist nicht von der richtigen Superklasse abgeleitet.", AbstractGraph.class, clazz.getSuperclass());
		Field[] fields = getDeclaredFields(clazz);
		assertEquals("Hat falsche Anzahl an Attributen.", 0, fields.length);
		Constructor<?>[] constructors = getDeclaredConstructors(clazz);
		assertEquals(clazz.getName() + "hat falsche Anzahl an Konstruktoren.", 1, constructors.length);
		for (Constructor<?> constructor : constructors) {
			assertTrue("Konstruktor inkl. -parameter: Sichtbarkeit ist faelschlicherweise nicht >public<", Modifier.isPublic(constructor.getModifiers()));
		}
		Method[] methods = getDeclaredMethods(clazz);
		assertTrue(clazz.getName() + "hat falsche Anzahl an Methoden.", methods.length >= 4);
		for (Method method : methods) {
			if (method.getName().equals("isUndirected")) {
				assertTrue(method.getName() + " - Methode: Sichtbarkeit ist nicht >public<", Modifier.isPublic(method.getModifiers()));
				assertFalse(method.getName() + " - Methode: Ist faelschlicherweise Klassenmethode", Modifier.isStatic(method.getModifiers()));
				assertFalse(method.getName() + " - Methode: Ist faelschlicherweise >abstract<", Modifier.isAbstract(method.getModifiers()));
				assertEquals(method.getName() + " - Methode: hat falsche Anzahl an Argumenten", 0, method.getParameterTypes().length);
				assertSame(method.getName() + " - Methode: Rueckgabetyp ist falsch", boolean.class, method.getReturnType());
			} else if (method.getName().equals("isTree")) {
				assertTrue(method.getName() + " - Methode: Sichtbarkeit ist nicht >public<", Modifier.isPublic(method.getModifiers()));
				assertFalse(method.getName() + " - Methode: Ist faelschlicherweise Klassenmethode", Modifier.isStatic(method.getModifiers()));
				assertFalse(method.getName() + " - Methode: Ist faelschlicherweise >abstract<", Modifier.isAbstract(method.getModifiers()));
				assertEquals(method.getName() + " - Methode: hat falsche Anzahl an Argumenten", 0, method.getParameterTypes().length);
				assertSame(method.getName() + " - Methode: Rueckgabetyp ist falsch", int.class, method.getReturnType());
			} else if (method.getName().equals("dfsPreOrder")) {
				assertTrue(method.getName() + " - Methode: Sichtbarkeit ist nicht >public<", Modifier.isPublic(method.getModifiers()));
				assertFalse(method.getName() + " - Methode: Ist faelschlicherweise Klassenmethode", Modifier.isStatic(method.getModifiers()));
				assertFalse(method.getName() + " - Methode: Ist faelschlicherweise >abstract<", Modifier.isAbstract(method.getModifiers()));
				assertEquals(method.getName() + " - Methode: hat falsche Anzahl an Argumenten", 1, method.getParameterTypes().length);
				assertSame(method.getName() + " - Methode: hat falsche Argumenttypen", int.class, method.getParameterTypes()[0]);
				assertSame(method.getName() + " - Methode: Rueckgabetyp ist falsch", java.util.List.class, method.getReturnType());
			} else if (method.getName().equals("bfsPreOrder")) {
				assertTrue(method.getName() + " - Methode: Sichtbarkeit ist nicht >public<", Modifier.isPublic(method.getModifiers()));
				assertFalse(method.getName() + " - Methode: Ist faelschlicherweise Klassenmethode", Modifier.isStatic(method.getModifiers()));
				assertFalse(method.getName() + " - Methode: Ist faelschlicherweise >abstract<", Modifier.isAbstract(method.getModifiers()));
				assertEquals(method.getName() + " - Methode: hat falsche Anzahl an Argumenten", 1, method.getParameterTypes().length);
				assertSame(method.getName() + " - Methode: hat falsche Argumenttypen", int.class, method.getParameterTypes()[0]);
				assertSame(method.getName() + " - Methode: Rueckgabetyp ist falsch", java.util.List.class, method.getReturnType());
			} else {
				assertTrue(method.getName() + " - Methode: Sichtbarkeit ist nicht >private<", Modifier.isPrivate(method.getModifiers()));
			}
		}
	}

	@Test(timeout = 555)
	public void pubTest__AdjacencyXXXGraph__check_intestines__THIS_TEST_IS_VERY_IMPORTANT_IF_IT_FAILS_THEN_YOU_WILL_GET_NO_POINTS_AT_ALL() {
		for (Class<?> clazz : new Class[] { AdjacencyMatrixGraph.class, AdjacencyListGraph.class, AdjacencyArrayGraph.class }) {
			assertTrue(clazz.getName() + " ist faelschlicherweise nicht >public<", Modifier.isPublic(clazz.getModifiers()));
			assertFalse(clazz.getName() + " ist faelschlicherweise >abstract<", Modifier.isAbstract(clazz.getModifiers()));
			assertTrue(clazz.getName() + " ist faelschlicherweise Annotation/Enum/Interface.", !clazz.isAnnotation() && !clazz.isEnum() && !clazz.isInterface());
			assertEquals(clazz.getName() + " hat faelschlicherweise innere Annotationen.", 0, clazz.getDeclaredAnnotations().length);
			assertEquals(clazz.getName() + " hat faelschlicherweise innere Klassen.", 0, clazz.getDeclaredClasses().length);
			assertEquals(clazz.getName() + " darf keine Schnittstelle implementieren.", 0, clazz.getInterfaces().length);
			assertSame(clazz.getName() + " ist nicht von der richtigen Superklasse abgeleitet.", AbstractGraphWithOps.class, clazz.getSuperclass());
			Field[] fields = getDeclaredFields(clazz);
			assertEquals("Hat falsche Anzahl an Attributen.", 1, fields.length);
			for (Field field : fields) {
				assertTrue(field + " - Feld: Sichtbarkeit ist nicht >private<", Modifier.isPrivate(field.getModifiers()));
				assertFalse(field + " - Feld: Ist kein Instanzattribut", Modifier.isStatic(field.getModifiers()));
				if (clazz == AdjacencyMatrixGraph.class) {
					assertTrue(field + " - Feld: Hat falschen Typ.", field.getType().isArray());
					assertTrue(field + " - Feld: Hat falschen Typ.", field.getType().getComponentType().isArray());
					assertSame(field + " - Feld: Hat falschen Typ.", boolean.class, field.getType().getComponentType().getComponentType());
				} else if (clazz == AdjacencyListGraph.class) {
					assertSame(field + " - Feld: Hat falschen Typ.", java.util.List.class, field.getType());
					assertTrue(field + " - Feld: Hat falschen Typ.", field.toGenericString().startsWith("private java.util.List<java.util.List<java.lang.Integer>>"));
				} else if (clazz == AdjacencyArrayGraph.class) {
					assertSame(field + " - Feld: Hat falschen Typ.", java.util.List.class, field.getType());
					assertTrue(field + " - Feld: Hat falschen Typ.", field.toGenericString().startsWith("private java.util.List<java.lang.Integer>"));
				}
			}
			Constructor<?>[] constructors = getDeclaredConstructors(clazz);
			assertEquals(clazz.getName() + "hat falsche Anzahl an Konstruktoren.", 1, constructors.length);
			for (Constructor<?> constructor : constructors) {
				assertTrue("Konstruktor inkl. -parameter: Sichtbarkeit ist faelschlicherweise nicht >public<", Modifier.isPublic(constructor.getModifiers()));
				assertEquals("Konstruktor inkl. -parameter: Parametertypen sind falsch", 1, constructor.getParameterTypes().length);
				assertSame("Konstruktor inkl. -parameter: Parametertypen sind falsch", int.class, constructor.getParameterTypes()[0]);
			}
			Method[] methods = getDeclaredMethods(clazz);
			assertEquals(clazz.getName() + "hat falsche Anzahl an Methoden.", 2, methods.length);
			for (Method method : methods) {
				if (method.getName().equals("addEdge")) {
					assertSame(method.getName() + " - Methode: Rueckgabetyp ist falsch", void.class, method.getReturnType());
				} else if (method.getName().equals("hasEdge")) {
					assertSame(method.getName() + " - Methode: Rueckgabetyp ist falsch", boolean.class, method.getReturnType());
				} else {
					fail("There should be no other method here. Found: " + method.toString());
				}
				assertTrue(method.getName() + " - Methode: Sichtbarkeit ist nicht >public<", Modifier.isPublic(method.getModifiers()));
				assertFalse(method.getName() + " - Methode: Ist faelschlicherweise Klassenmethode", Modifier.isStatic(method.getModifiers()));
				assertFalse(method.getName() + " - Methode: Ist faelschlicherweise >abstract<", Modifier.isAbstract(method.getModifiers()));
				assertEquals(method.getName() + " - Methode: hat falsche Anzahl an Argumenten", 2, method.getParameterTypes().length);
				assertSame(method.getName() + " - Methode: hat falsche Argumenttypen", int.class, method.getParameterTypes()[0]);
				assertSame(method.getName() + " - Methode: hat falsche Argumenttypen", int.class, method.getParameterTypes()[1]);
			}
		}
	}

	// ========== real TESTS ==========
	// Some "interesting" graphs
	private final String tinyGraphIsTree = "graph 1 0";
	private final String tinyGraphIsTree_bdfs[][] = { { "[0]" }, { "[0]" } };
	private final int tinyGraphIsTree_isTree = 0;
	// -----
	private final String smallGraphIsTree = "graph 2 1 1 0";
	private final String smallGraphIsTree_bdfs[][] = { { "[0, 1]", "[1, 0]" }, { "[0, 1]", "[1, 0]" } };
	private final int smallGraphIsTree_isTree = 0;
	// -----
	private final String smallDiGraphIsTree = "digraph 2 1 1 0";
	private final String smallDiGraphIsTree_bdfs[][] = { { "[0]", "[1, 0]" }, { "[0]", "[1, 0]" } };
	private final int smallDiGraphIsTree_isTree = 1;
	// -----
	private final String exampleFromExerciseSheet = "digraph 4 6 0 1 0 2 1 2 2 1 1 3 2 3";
	private final String exampleFromExerciseSheet_bdfs[][] = { { "[0, 1, 2, 3]", "[1, 2, 3]", "[2, 1, 3]", "[3]" }, { "[0, 1, 2, 3]", "[1, 2, 3]", "[2, 1, 3]", "[3]" } };
	private final int exampleFromExerciseSheet_isTree = -1;
	// -----
	private final String exampleFromLecture4DFSandBFS = "digraph 5 8 0 1 0 3 1 0 1 3 3 4 4 2 2 4 2 2"; // off-by-one, i.e. nodes numbered 0-4 here (lecture: 1-5)!
	private final String exampleFromLecture4DFSandBFS_bdfs[][] = { { "[0, 1, 3, 4, 2]", "[1, 0, 3, 4, 2]", "[2, 4]", "[3, 4, 2]", "[4, 2]" }, { "[0, 1, 3, 4, 2]", "[1, 0, 3, 4, 2]", "[2, 4]", "[3, 4, 2]", "[4, 2]" } };
	private final int exampleFromLecture4DFSandBFS_isTree = -1;
	// -----
	private final String exampleFromLecture4Dijkstra = "digraph 6 9 0 1 0 2 0 5 1 3 1 4 2 5 3 0 3 2 4 5"; // off-by-one, i.e. nodes numbered 0-5 here (lecture: 1-6)!
	private final String exampleFromLecture4Dijkstra_bdfs[][] = { { "[0, 1, 3, 2, 5, 4]", "[1, 3, 0, 2, 5, 4]", "[2, 5]", "[3, 0, 1, 4, 5, 2]", "[4, 5]", "[5]" }, { "[0, 1, 2, 5, 3, 4]", "[1, 3, 4, 0, 2, 5]", "[2, 5]", "[3, 0, 2, 1, 5, 4]", "[4, 5]", "[5]" } };
	private final int exampleFromLecture4Dijkstra_isTree = -1;
	// -----

	protected static final void setUpWithGraph(int type, String g, String[] dfsStrings, String[] bfsStrings, int expectedIsTree) {
		// ----- generate -----
		java.util.Scanner s = new java.util.Scanner(g);
		boolean directed = false;
		switch (s.next()) {
		case "graph":
			directed = false;
			break;
		case "digraph":
			directed = true;
			break;
		default:
			fail("Unknown graph type!");
		}
		int numberOfNodes = s.nextInt();
		int numberOfEdges = s.nextInt();
		AbstractGraph actualGraph;
		String expectedGraph = "";
		switch (type) {
		case 0:
			actualGraph = new AdjacencyMatrixGraph(numberOfNodes);
			break;
		case 1:
			actualGraph = new AdjacencyListGraph(numberOfNodes);
			break;
		default:
			actualGraph = new AdjacencyArrayGraph(numberOfNodes);
		}
		while (numberOfEdges-- > 0) {
			int fromNode = s.nextInt();
			int toNode = s.nextInt();
			actualGraph.addEdge(fromNode, toNode);
			expectedGraph += "(" + fromNode + "," + toNode + ")";
			if (!directed) {
				actualGraph.addEdge(toNode, fromNode);
				expectedGraph += "(" + toNode + "," + fromNode + ")";
			}
		}
		s.close();
		// ----- check -----
		for (int fromNode = 0; fromNode < numberOfNodes; fromNode++) {
			for (int toNode = 0; toNode < numberOfNodes; toNode++) {
				assertEquals("Check addEdge vs. hasEdge failed.", expectedGraph.contains("(" + fromNode + "," + toNode + ")"), actualGraph.hasEdge(fromNode, toNode));
			}
		}
		assertEquals("Check isUndirected failed.", !directed, actualGraph.isUndirected());
		for (int i = 0; i < actualGraph.getNumberOfNodes(); i++) {
			assertEquals("Check dfs failed.", dfsStrings[i], actualGraph.dfsPreOrder(i).toString());
		}
		for (int i = 0; i < actualGraph.getNumberOfNodes(); i++) {
			assertEquals("Check bfs failed.", bfsStrings[i], actualGraph.bfsPreOrder(i).toString());
		}
		assertEquals("Check isTree failed.", expectedIsTree, actualGraph.isTree());
	}

	// ---------- tinyGraphIsTree ----------
	@Test(timeout = 666)
	public void pubTest__AdjacencyMatrixGraph__tinyGraphIsTree() {
		setUpWithGraph(0, tinyGraphIsTree, tinyGraphIsTree_bdfs[0], tinyGraphIsTree_bdfs[1], tinyGraphIsTree_isTree);
	}

	@Test(timeout = 666)
	public void pubTest__AdjacencyListGraph__tinyGraphIsTree() {
		setUpWithGraph(1, tinyGraphIsTree, tinyGraphIsTree_bdfs[0], tinyGraphIsTree_bdfs[1], tinyGraphIsTree_isTree);
	}

	@Test(timeout = 666)
	public void pubTest__AdjacencyArrayGraph__tinyGraphIsTree() {
		setUpWithGraph(2, tinyGraphIsTree, tinyGraphIsTree_bdfs[0], tinyGraphIsTree_bdfs[1], tinyGraphIsTree_isTree);
	}

	// ---------- smallGraphIsTree ----------
	@Test(timeout = 666)
	public void pubTest__AdjacencyMatrixGraph__smallGraphIsTree() {
		setUpWithGraph(0, smallGraphIsTree, smallGraphIsTree_bdfs[0], smallGraphIsTree_bdfs[1], smallGraphIsTree_isTree);
	}

	@Test(timeout = 666)
	public void pubTest__AdjacencyListGraph__smallGraphIsTree() {
		setUpWithGraph(1, smallGraphIsTree, smallGraphIsTree_bdfs[0], smallGraphIsTree_bdfs[1], smallGraphIsTree_isTree);
	}

	@Test(timeout = 666)
	public void pubTest__AdjacencyArrayGraph__smallGraphIsTree() {
		setUpWithGraph(2, smallGraphIsTree, smallGraphIsTree_bdfs[0], smallGraphIsTree_bdfs[1], smallGraphIsTree_isTree);
	}

	// ---------- smallDiGraphIsTree ----------
	@Test(timeout = 666)
	public void pubTest__AdjacencyMatrixGraph__smallDiGraphIsTree() {
		setUpWithGraph(0, smallDiGraphIsTree, smallDiGraphIsTree_bdfs[0], smallDiGraphIsTree_bdfs[1], smallDiGraphIsTree_isTree);
	}

	@Test(timeout = 666)
	public void pubTest__AdjacencyListGraph__smallDiGraphIsTree() {
		setUpWithGraph(1, smallDiGraphIsTree, smallDiGraphIsTree_bdfs[0], smallDiGraphIsTree_bdfs[1], smallDiGraphIsTree_isTree);
	}

	@Test(timeout = 666)
	public void pubTest__AdjacencyArrayGraph__smallDiGraphIsTree() {
		setUpWithGraph(2, smallDiGraphIsTree, smallDiGraphIsTree_bdfs[0], smallDiGraphIsTree_bdfs[1], smallDiGraphIsTree_isTree);
	}

	// ---------- exampleFromExerciseSheet ----------
	@Test(timeout = 666)
	public void pubTest__AdjacencyMatrixGraph__exampleFromExerciseSheet() {
		setUpWithGraph(0, exampleFromExerciseSheet, exampleFromExerciseSheet_bdfs[0], exampleFromExerciseSheet_bdfs[1], exampleFromExerciseSheet_isTree);
	}

	@Test(timeout = 666)
	public void pubTest__AdjacencyListGraph__exampleFromExerciseSheet() {
		setUpWithGraph(1, exampleFromExerciseSheet, exampleFromExerciseSheet_bdfs[0], exampleFromExerciseSheet_bdfs[1], exampleFromExerciseSheet_isTree);
	}

	@Test(timeout = 666)
	public void pubTest__AdjacencyArrayGraph__exampleFromExerciseSheet() {
		setUpWithGraph(2, exampleFromExerciseSheet, exampleFromExerciseSheet_bdfs[0], exampleFromExerciseSheet_bdfs[1], exampleFromExerciseSheet_isTree);
	}

	// ---------- exampleFromLecture4DFSandBFS ----------
	@Test(timeout = 666)
	public void pubTest__AdjacencyMatrixGraph__exampleFromLecture4DFSandBFS() {
		setUpWithGraph(0, exampleFromLecture4DFSandBFS, exampleFromLecture4DFSandBFS_bdfs[0], exampleFromLecture4DFSandBFS_bdfs[1], exampleFromLecture4DFSandBFS_isTree);
	}

	@Test(timeout = 666)
	public void pubTest__AdjacencyListGraph__exampleFromLecture4DFSandBFS() {
		setUpWithGraph(1, exampleFromLecture4DFSandBFS, exampleFromLecture4DFSandBFS_bdfs[0], exampleFromLecture4DFSandBFS_bdfs[1], exampleFromLecture4DFSandBFS_isTree);
	}

	@Test(timeout = 666)
	public void pubTest__AdjacencyArrayGraph__exampleFromLecture4DFSandBFS() {
		setUpWithGraph(2, exampleFromLecture4DFSandBFS, exampleFromLecture4DFSandBFS_bdfs[0], exampleFromLecture4DFSandBFS_bdfs[1], exampleFromLecture4DFSandBFS_isTree);
	}

	// ---------- exampleFromLecture4Dijkstra ----------
	@Test(timeout = 666)
	public void pubTest__AdjacencyMatrixGraph__exampleFromLecture4Dijkstra() {
		setUpWithGraph(0, exampleFromLecture4Dijkstra, exampleFromLecture4Dijkstra_bdfs[0], exampleFromLecture4Dijkstra_bdfs[1], exampleFromLecture4Dijkstra_isTree);
	}

	@Test(timeout = 666)
	public void pubTest__AdjacencyListGraph__exampleFromLecture4Dijkstra() {
		setUpWithGraph(1, exampleFromLecture4Dijkstra, exampleFromLecture4Dijkstra_bdfs[0], exampleFromLecture4Dijkstra_bdfs[1], exampleFromLecture4Dijkstra_isTree);
	}

	@Test(timeout = 666)
	public void pubTest__AdjacencyArrayGraph__exampleFromLecture4Dijkstra() {
		setUpWithGraph(2, exampleFromLecture4Dijkstra, exampleFromLecture4Dijkstra_bdfs[0], exampleFromLecture4Dijkstra_bdfs[1], exampleFromLecture4Dijkstra_isTree);
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