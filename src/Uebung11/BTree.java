public class BTree {
	private BTreeNode root = null;
	private final int degree;

	public BTree(int degree) {
		this.degree = degree;
	}

	public int getDegree() {
		return degree;
	}

	public void setRoot(BTreeNode root) {
		this.root = root;
	}

	public BTreeNode getRoot() {
		return root;
	}

	public boolean hasKey(int key) {
		// TODO
		return root == null ? false : root.hasKey(key);
	}

	public void insert(int key) {
		// TODO
		if (root == null) {
			root = new BTreeNode(getDegree());
		}
		
		OverflowNode tmp = root.insert(key);
		if(tmp != null){
			//System.out.println("RootMethode");
			BTreeNode newRoot = new BTreeNode(getDegree());
			newRoot.addChild(root);
			newRoot.addChild(tmp.getRightChild());
			newRoot.addKey(tmp.getValue());
			setRoot(newRoot);
		}

	}

	public String toJson() {
		// TODO
		String tmp = "";
		if(root == null){
			return tmp;
		}else if(root.getChildren().size() == 0){
			return "{node:" + root.getKeys() + ",children:[]}";
		}else{
			tmp += "{node:" + root.getKeys() + ",children:[";
			for(int i = 0; i < root.getChildren().size()-1; i++){
				tmp += root.getChildren().get(i).toJson() +",";
			}
			tmp += root.getChildren().get(root.getChildren().size()-1).toJson();
			tmp += "]}";
		}
		return tmp;
	}

	public void printTree() {
		if (root != null) {
			root.print("");
		}
	}
}
