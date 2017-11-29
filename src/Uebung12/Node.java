import java.util.*;

/**
 * Node in an undirected graph.
 */
public class Node {
	private final String label;
	private final List<Edge> edges = new ArrayList<Edge>();

	/**
	 * Constructs a node with a label.
	 * 
	 * @param label
	 *            the label of the node
	 */
	public Node(String label) {
		this.label = label;
	}

	/**
	 * Returns the label of the node
	 * 
	 * @return the label of the node
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * Creates an undirected edge from this node to another node and connects both to each other
	 * 
	 * @param other
	 *            the other node
	 * @param cost
	 *            the cost of the edge
	 */
	public void addEdge(Node other, int cost) {
		Edge e = new Edge(this, other, cost);
		this.edges.add(e);
		other.edges.add(e);
	}

	/**
	 * Returns the list of all edges connected to this node
	 * 
	 * @Return the list of all edges connected to this node
	 */
	public List<Edge> getEdges() {
		return new ArrayList<>(edges);
	}

	/**
	 * Returns a string representation of the node
	 * 
	 * @return a string representation of the node
	 */
	public String toString() {
		return getLabel();
	}
}