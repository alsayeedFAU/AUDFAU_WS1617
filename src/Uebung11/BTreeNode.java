import java.util.ArrayList;

public class BTreeNode {
	private final ArrayList<Integer> keys;
	private final ArrayList<BTreeNode> children;
	private final int degree;

	public int getDegree() {
		return degree;
	}

	public BTreeNode(int degree) {
		keys = new ArrayList<Integer>();
		children = new ArrayList<BTreeNode>();
		this.degree = degree;
	}

	public ArrayList<Integer> getKeys() {
		return keys;
	}

	public ArrayList<BTreeNode> getChildren() {
		return children;
	}

	public void addKey(int key) {
		keys.add(key);
	}

	public void addChild(BTreeNode child) {
		children.add(child);
	}

	private boolean isLeaf() {
		return (children.size() == 0);
	}

	private int identifyIndex(int key) {
		for (int i = 0; i < keys.size(); i++) {
			if (Integer.compare(key, keys.get(i)) < 0) {
				return i;
			}
		}
		return keys.size();
	}

	public boolean hasKey(int key) {
		// TODO
		if(!keys.contains(key)){
			if(!isLeaf()){
				return children.get(identifyIndex(key)).hasKey(key);
			}
			return false;
		}
		return true;
		
	}

	public OverflowNode insert(int key) {
		// TODO
		if(isLeaf()){
			keys.add(identifyIndex(key), key);
			
			if(keys.size() > getDegree()*2){
				OverflowNode o = split();
				return o;
			}
		}else{
			OverflowNode o = children.get(identifyIndex(key)).insert(key);
			if(o != null){
				keys.add(identifyIndex(o.getValue()), o.getValue());
				children.add(identifyIndex(o.getValue()), o.getRightChild());
			}
			
		}
		return null;

	}

	public OverflowNode split() {
		// TODO
		BTreeNode test = new BTreeNode(getDegree());
		for (int i = getDegree() + 1; i < keys.size(); i++) {
			test.addKey(keys.get(i));
		}
		if (children.size() > 0) {
			for (int i = getDegree(); i < keys.size(); i++) {
				test.addChild(children.get(i));
			}
		}
		OverflowNode tmp = new OverflowNode(keys.get(getDegree()), test);

		while (keys.size() > getDegree()) {
			keys.remove(keys.size() - 1);
		}

		if (children.size() > 0) {
			for (int i = getDegree(); i < keys.size(); i++) {
				children.remove(i);
			}
		}

		return tmp;
	}

	public String toJson() {
		// TODO
			if(isLeaf()){
				return "{node:" + getKeys() + "}";
			}
			return "1";
		
	}

	public void print(String pre) {
		System.out.print(pre + "--> ");
		for (Integer i : keys) {
			System.out.print(i.toString() + " ");
		}
		System.out.println();
		for (BTreeNode child : children) {
			child.print(pre + "\t");
		}
	}
}
