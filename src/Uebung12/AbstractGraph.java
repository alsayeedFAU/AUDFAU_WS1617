public abstract class AbstractGraph {
	private final int numberOfNodes;

	public AbstractGraph(int numberOfNodes) {
		if (numberOfNodes <= 0) {
			throw new IllegalArgumentException("Number of nodes must be greater than 0.");
		}
		this.numberOfNodes = numberOfNodes;
	}

	public final int getNumberOfNodes() {
		return numberOfNodes;
	}

	public abstract void addEdge(int fromNode, int toNode);

	public abstract boolean hasEdge(int fromNode, int toNode);

	public abstract boolean isUndirected();

	public abstract int isTree();

	public abstract java.util.List<Integer> dfsPreOrder(int startNode);

	public abstract java.util.List<Integer> bfsPreOrder(int startNode);
}