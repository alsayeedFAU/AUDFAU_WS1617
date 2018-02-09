public class TreeDetector {

	public boolean isHeap(Node node) {
		// TODO
		if (node == null) {
			return true;
		}
		Node right = node.getRightChild();
		Node left = node.getLeftChild();

		if (right == null && left == null) {
			return true;
		}

		if (right != null && left != null) {
			if (right.getValue() < node.getValue() && left.getValue() < node.getValue()) {
				return isHeap(right) && isHeap(left);
			}
		} else if (right != null && left == null) {
			if (right.getValue() < node.getValue()) {
				return isHeap(right);
			}
		} else if (left != null && right == null) {
			if (left.getValue() < node.getValue()) {
				return isHeap(left);
			}
		}
		return false;
	}

	public boolean isBinarySearchTree(Node node) {
		// TODO
		if(node == null){
			return true;
		}
		return helper(node, -1, Integer.MAX_VALUE);
	}

	public boolean helper(Node node, int min, int max) {

		if (node == null) {
			return true;
		}
		Node right = node.getRightChild();
		Node left = node.getLeftChild();
		
		if (right == null && left == null) {
			return true;
		}

		if (right != null && left != null) {
			if (right.getValue() > node.getValue() && left.getValue() < node.getValue() && left.getValue() > min && right.getValue() < max) {
				return helper(right, node.getValue(), max) && helper(left, min, node.getValue());
			}
		} else if (right != null && left == null && right.getValue() < max) {
			if (right.getValue() > node.getValue()) {
				return helper(right, node.getValue(), max);
			}
		} else if (left != null && right == null && left.getValue() > min) {
			if (left.getValue() < node.getValue()) {
				return helper(left, min, node.getValue());
			}
		}
		//System.out.println(node.getValue());
		return false;

	}

}
