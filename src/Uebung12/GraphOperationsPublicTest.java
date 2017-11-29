import static org.junit.Assert.*;
import java.util.*;
import org.junit.*;

public class GraphOperationsPublicTest {
	// ========== SYSTEM ==========
	private static final java.util.Random random = new java.util.Random(4711_0815_666L);

	// ========== TESTS ==========
	@Test(timeout = 6666)
	public void pubTest__example_from_exercise_sheet() {
		for (int pass = 0; pass < 7; pass++) {
			Node A = new Node("A");
			Node B = new Node("B");
			Node C = new Node("C");
			Node D = new Node("D");
			Node E = new Node("E");
			Node F = new Node("F");
			Node G = new Node("G");
			Node H = new Node("H");
			List<Node> graph = new ArrayList<>();
			graph.add(random.nextInt(graph.size() + 1), A);
			graph.add(random.nextInt(graph.size() + 1), B);
			graph.add(random.nextInt(graph.size() + 1), C);
			graph.add(random.nextInt(graph.size() + 1), D);
			graph.add(random.nextInt(graph.size() + 1), E);
			graph.add(random.nextInt(graph.size() + 1), F);
			graph.add(random.nextInt(graph.size() + 1), G);
			graph.add(random.nextInt(graph.size() + 1), H);
			List<Node> graphClone = new ArrayList<>(graph);
			check("Example from exercise sheet (no edges at all):", graph, 8, "=A==B==C==D==E==F==G==H=");
			assertEquals("DO NOT MODIFY THE INPUT!", graphClone, graph);
			A.addEdge(B, 47);
			B.addEdge(G, 11);
			check("Example from exercise sheet (two edges):", graph, 6, "=ABG==C==D==E==F==H=");
			assertEquals("DO NOT MODIFY THE INPUT!", graphClone, graph);
			D.addEdge(C, 0);
			D.addEdge(E, 8);
			D.addEdge(F, 15);
			check("Example from exercise sheet (two edges):", graph, 3, "=ABG==CDEF==H=");
			assertEquals("DO NOT MODIFY THE INPUT!", graphClone, graph);
			C.addEdge(E, 666);
			C.addEdge(F, 42);
			E.addEdge(F, 999);
			check("Example from exercise sheet (EXACTLY THE EXAMPLE):", graph, 3, "=ABG==CDEF==H=");
			assertEquals("DO NOT MODIFY THE INPUT!", graphClone, graph);
			H.addEdge(A, 0x815);
			H.addEdge(G, 4711);
			H.addEdge(C, 666);
			H.addEdge(F, 42);
			check("Example from exercise sheet (fully connected):", graph, 1, "=ABCDEFGH=");
			assertEquals("DO NOT MODIFY THE INPUT!", graphClone, graph);
		}
	}

	protected static final void check(String message, List<Node> graph, int expectedNumberOfComponents, String expectedComponentRepresentation) {
		List<List<Node>> cs = GraphOperations.getComponents(graph);
		int actualNumberOfComponents = 0;
		List<String> csString = new ArrayList<>();
		for (List<Node> c : cs) {
			List<String> cString = new ArrayList<>();
			for (Node n : c) {
				cString.add(n.getLabel());
			}
			Collections.sort(cString);
			String r = "";
			for (String s : cString) {
				r += s;
			}
			csString.add(r);
			actualNumberOfComponents++;
		}
		Collections.sort(csString);
		String actualComponentRepresentation = "";
		for (String s : csString) {
			actualComponentRepresentation += "=" + s + "=";
		}
		assertEquals(message + " Number of found components is wrong.", expectedNumberOfComponents, actualNumberOfComponents);
		assertEquals(message + " Components are wrong.", expectedComponentRepresentation, actualComponentRepresentation);
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