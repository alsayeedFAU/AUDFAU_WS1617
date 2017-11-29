public class DoublyLinkedListNode<T> {
	private DoublyLinkedListNode<T> predecessor;
	private final T t;
	private DoublyLinkedListNode<T> successor;

	public DoublyLinkedListNode(T t) {
		this.predecessor = this;
		this.t = t;
		this.successor = this;
	}

	public DoublyLinkedListNode<T> getPredecessor() {
		return predecessor;
	}

	public DoublyLinkedListNode<T> replacePredecessor(DoublyLinkedListNode<T> newPredecessor) {
		return this.predecessor = newPredecessor;
	}

	T get() {
		return t;
	}

	DoublyLinkedListNode<T> getSuccessor() {
		return successor;
	}

	DoublyLinkedListNode<T> replaceSuccessor(DoublyLinkedListNode<T> newSuccessor) {
		return this.successor = newSuccessor;
	}
}