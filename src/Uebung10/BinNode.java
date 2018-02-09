import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

public class BinNode<T extends Comparable<T>> extends AbstractBinNode<T> {

	public BinNode(T value, BinNode<T> sibling, BinNode<T> child) {
		super(value, sibling, child);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isTree() {
		// TODO Auto-generated method stub
		if (sibling != null) {
			return false;
		}

		Set<BinNode<T>> visited = new HashSet<>();
		Queue<BinNode<T>> toVisit = new ArrayDeque<>();
		toVisit.add(this);

		while (!toVisit.isEmpty()) {
			BinNode<T> next = toVisit.poll();
			if (!visited.add(next)) {
				return false;
			}
			if (next.child != null) {
				toVisit.add(next.child);
			}
			if (next.sibling != null) {
				toVisit.add(next.sibling);
			}
		}
		return true;

	}

	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
		if (!isTree()) {
			return -1;
		}
		return getHeightH();

	}

	private int getHeightH() {
		if (child != null) {
			if (child.sibling != null) {
				return child.getHeightH() != -1 && child.sibling.getHeightH() != -1
						? Math.max(child.getHeightH() + 1, child.sibling.getHeightH() + 1) : -1;
			}
			return child.getHeightH() + 1;
		}
		return 0;
	}

	@Override
	public int getBranchingFactor() {
		// TODO Auto-generated method stub
		if (!isTree()) {
			return -1;
		}
		return getBranchingFactorH();
	}

	private int getBranchingFactorH() {
		if (sibling != null) {
			return sibling.getBranchingFactorH() + 1;
		}
		if (child != null) {
			if (child.sibling != null) {
				return child.getBranchingFactorH() != -1 && child.sibling.getBranchingFactorH() != -1
						? Math.max(child.getBranchingFactorH(), child.sibling.getBranchingFactorH()) : -1;
			}
			return child.getBranchingFactorH();
		}
		return 0;
	}

	@Override
	public boolean isBinaryTree() {
		// TODO Auto-generated method stub
		return getBranchingFactor() < 3 && getBranchingFactor() != -1 && isTree() ? true : false;
	}

	@Override
	public boolean isBinarySearchTree() {
		// TODO Auto-generated method stub
		if (!isBinaryTree()) {
			return false;
		}
		return isBinarySearchTreeH();
	}

	private boolean isBinarySearchTreeH() {

		if (child != null) {
			if (child.sibling != null) {
				if (child.value.compareTo(this.value) > 0 || child.sibling.value.compareTo(this.value) < 0) {
					return false;
				}
				return child.isBinarySearchTreeH() && child.isBinarySearchTreeH();
			}
			if (child.value.compareTo(this.value) > 0) {
				return false;
			}
			return child.isBinarySearchTree();
		}
		return true;
	}

	@Override
	public boolean isAVLTree() {
		// TODO Auto-generated method stub
		if (!isBinarySearchTree()) {
			return false;
		}
		return isAVLTreeH();
	}

	public boolean isAVLTreeH() {
		if (child != null) {
			if (child.sibling != null) {
				if (Math.abs(child.getHeight() - child.sibling.getHeight()) > 2) {
					return false;
				}
				return child.isAVLTreeH() && child.sibling.isAVLTreeH();
			}
			if(child.getHeight()> 1){
				return false;
			}
			return child.isAVLTreeH();
		}
		return true;

	}

	@Override
	public boolean isMinHeap() {
		// TODO Auto-generated method stub
		if (!isBinaryTree()) {
			return false;
		}
		return isMinHeapH();
	}

	private boolean isMinHeapH() {
		// TODO Auto-generated method stub
		if (child != null) {
			if (child.sibling != null) {
				if (child.value.compareTo(this.value) <= 0 || child.sibling.value.compareTo(this.value) <= 0) {
					return false;
				}
				return child.isMinHeapH() && child.sibling.isMinHeapH();
			} else {
				if (child.value.compareTo(this.value) <= 0) {
					return false;
				}
				return child.isMinHeapH();
			}
		}
		return true;
	}

	@Override
	public boolean isMaxHeap() {
		// TODO Auto-generated method stub
		if (!isBinaryTree()) {
			return false;
		}
		return isMaxHeapH();
	}

	private boolean isMaxHeapH() {
		if (child != null) {
			if (child.sibling != null) {
				if (child.value.compareTo(this.value) >= 0 || child.sibling.value.compareTo(this.value) >= 0) {
					return false;
				}
				return child.isMaxHeapH() && child.sibling.isMaxHeapH();
			} else {
				if (child.value.compareTo(this.value) >= 0) {
					return false;
				}
				return child.isMaxHeapH();
			}
		}
		return true;
	}
}
