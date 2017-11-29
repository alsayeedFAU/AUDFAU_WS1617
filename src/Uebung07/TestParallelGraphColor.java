import java.util.ArrayList;
import java.util.Random;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestParallelGraphColor {
	ParallelGraphColor graphColor = new ParallelGraphColor();

	private final ArrayList<Node> getColoredNodes(final int[] colors) {
		final ArrayList<Node> result = new ArrayList<>(colors.length);
		for (int i = 0; i < colors.length; ++i) {
			result.add(new Node(i));
			result.get(i).color = colors[i];
		}
		return result;
	}

	@Test
	public void testGetFreeColorExamples() {
		// Examples from the exercise sheet
		assertEquals(0, graphColor.getFreeColor(getColoredNodes(new int[] { -1, 2, 3 })));
		assertEquals(3, graphColor.getFreeColor(getColoredNodes(new int[] { 0, 1, 2 })));
		assertEquals(1, graphColor.getFreeColor(getColoredNodes(new int[] { 0, 0, 0 })));
		assertEquals(2, graphColor.getFreeColor(getColoredNodes(new int[] { 0, 1, 7 })));
	}

	@Test
	public void testGetFreeColor() {
		ArrayList<Node> array = new ArrayList<Node>();
		Node[] nodes = new Node[8];

		for (int i = 0; i < nodes.length; i++) {
			Node n = new Node(i);
			nodes[i] = n;
			array.add(n);
		}
		// System.out.println(graphColor.getFreeColor(array));

		assertEquals(0, graphColor.getFreeColor(array));

		nodes[0].color = 7;

		assertEquals(0, graphColor.getFreeColor(array));

		nodes[1].color = 0;

		assertEquals(1, graphColor.getFreeColor(array));

		nodes[2].color = 8;

		assertEquals(1, graphColor.getFreeColor(array));

		nodes[3].color = 1;

		assertEquals(2, graphColor.getFreeColor(array));

		nodes[4].color = 2;
		nodes[5].color = 3;
		nodes[6].color = 4;
		nodes[7].color = 5;

		assertEquals(6, graphColor.getFreeColor(array));

		// System.out.println("getFreeColor seems to be ok");
	}

	private boolean isValidColoring(Node[] graph) {

		for (int i = 0; i < graph.length; i++) {
			if (graph[i].color == -1) {
				System.err.println("uncolored node");
				return false;
			}

			for (Node n : graph[i].neighbours) {
				if (graph[i].color == n.color) {
					System.err.println("neighbours with equal colors");
					return false;
				}
			}
		}

		return true;
	}

	@Test
	public void testColorGraphExample() {
		// Example from the exercise sheet
		final Node[] graph = new Node[5];
		for (int i = 0; i < graph.length; ++i) {
			graph[i] = new Node(i);
		}
		graph[0].neighbours.add(graph[1]);
		graph[0].neighbours.add(graph[2]);
		graph[1].neighbours.add(graph[0]);
		graph[1].neighbours.add(graph[3]);
		graph[1].neighbours.add(graph[4]);
		graph[2].neighbours.add(graph[0]);
		graph[2].neighbours.add(graph[3]);
		graph[2].neighbours.add(graph[4]);
		graph[3].neighbours.add(graph[1]);
		graph[3].neighbours.add(graph[2]);
		graph[3].neighbours.add(graph[4]);
		graph[4].neighbours.add(graph[1]);
		graph[4].neighbours.add(graph[2]);
		graph[4].neighbours.add(graph[3]);

		graphColor.colorGraph(graph, 4);

		//assertTrue(isValidColoring(graph));
	}
	

	@Test
	public void testIsValidColoring() {
		int nodes = 2 * 1024 * 1024;
		int edges = nodes * 4;
		Node[] graph = graphColor.generateRandomGraph(nodes, edges, new Random(42));

		long count = 0;
		for (int i = 1; i < 8; i *= 2) {

			System.out.println(i);

			long start = System.nanoTime();
			graphColor.colorGraph(graph, i);
			assertTrue(isValidColoring(graph));
			long end = System.nanoTime();

			count += (end - start);
			for (int j = 0; j < graph.length; j++) {
				graph[j].color = -1;
			}
		}

		System.out.println("colorGraph seems to be ok");
		System.out.println("total time for coloring: " + count + "ns");
	}

}
