import java.util.*;

public class WebOfTrustNode {
	public ArrayList<WebOfTrustNode> trustees;

	/**
	 * Adds a directed edge from <code>this</code> to <code>other</code> to the
	 * graph.
	 */
	public void trusts(WebOfTrustNode whom) {
		if (trustees == null) {
			trustees = new ArrayList<>();
		}

		if (!trustees.contains(whom)) {
			trustees.add(whom);
		}
	}

	/**
	 * Traverses the Graph in Breadth-First order starting from this node until
	 * <code>other</code> is found. Afterwards reconstructs the path to other
	 * and returns it to the caller.
	 */
	public ArrayList<WebOfTrustNode> findTrustPath(WebOfTrustNode other) {

		ArrayList<WebOfTrustNode> visited = new ArrayList<WebOfTrustNode>();
		Queue<WebOfTrustNode> toVisit = new ArrayDeque<>();
		toVisit.add(this);

		while (!toVisit.isEmpty()) {
			WebOfTrustNode next = toVisit.remove();
			visited.add(next);
			if (next.equals(other)) {
				return visited;
			}
			if (next.trustees != null) {
				for (WebOfTrustNode node : next.trustees) {
					if (!visited.contains(node)) {
						toVisit.add(node);
					}
				}
			} else {
				visited.remove(this);
			}
		}

		return null;
	}

	/**
	 * Calculates the set of all nodes that can be reached from this node.
	 */
	public HashSet<WebOfTrustNode> calculateReachableSubset() {

		HashSet<WebOfTrustNode> visited = new HashSet<WebOfTrustNode>();
		Queue<WebOfTrustNode> toVisit = new ArrayDeque<>();
		toVisit.add(this);

		
		while (!toVisit.isEmpty()) {
			WebOfTrustNode next = toVisit.remove();
			visited.add(next);
			if (next.trustees != null) {
				for (WebOfTrustNode node : next.trustees) {
					if (!visited.contains(node)) {
						toVisit.add(node);
					}
				}
			}

		}

		return visited;
	}

	/**
	 * Calculates the strong set that contains the current node.
	 */
	public HashSet<WebOfTrustNode> calculateStrongSubset() {
		HashSet<WebOfTrustNode> ereichbar = calculateReachableSubset();
		HashSet<WebOfTrustNode> tmp = new HashSet<>();

		for (WebOfTrustNode node : ereichbar) {
			if (node.calculateReachableSubset().contains(this)) {
				tmp.add(node);
			}
		}
		return tmp;
	}
}
