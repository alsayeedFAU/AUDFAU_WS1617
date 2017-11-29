/**
 * Weighted edge in an undirected graph.
 */
public class Edge {
	private final Node n[] = new Node[2];
	private final int cost;

	/**
	 * Creates an edge between node a and b with costs.
	 * 
	 * @param a
	 *            source node
	 * @param b
	 *            destination node
	 * @param cost
	 *            cost of the edge
	 */
	public Edge(Node a, Node b, int cost) {
		n[0] = a;
		n[1] = b;
		this.cost = cost;
	}

	/**
	 * Returns the other node the edge
	 * 
	 * @param m
	 *            a node
	 * @return the other node of the edge
	 */
	public Node getOther(Node m) {
		return n[0] == m ? n[1] : n[0];
	}

	/**
	 * Returns the cost of the edge
	 * 
	 * @return the cost of the edge
	 */
	public int getCost() {
		return cost;
	}

	/**
	 * Returns an array of both nodes of the edge
	 * 
	 * @return an array of both nodes of the edge
	 */
	public Node[] getNodes() {
		return new Node[] { n[0], n[1] };
	}

	/**
	 * Returns a string representing the edge
	 * 
	 * @eeturn a string representing the edge
	 */
	public String toString() {
		return n[0].getLabel() + " = " + n[1].getLabel() + " cost: " + cost;
	}
}
