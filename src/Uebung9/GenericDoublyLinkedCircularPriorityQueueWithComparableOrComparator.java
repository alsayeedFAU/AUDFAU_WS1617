import java.util.Comparator;
import java.util.NoSuchElementException;

public class GenericDoublyLinkedCircularPriorityQueueWithComparableOrComparator<T>
		extends AbstractGenericDoublyLinkedCircularPriorityQueueWithComparableOrComparator<T> {

	protected GenericDoublyLinkedCircularPriorityQueueWithComparableOrComparator(Comparator<T> comparator) {
		super(comparator);
	}

	public void insert(T i) {
		if (super.head == null) {
			head = new DoublyLinkedListNode<T>(i);
			head.replacePredecessor(head);
			head.replaceSuccessor(head);
		} else {
			if (comparator.compare(i, head.get()) > 0) {
				DoublyLinkedListNode<T> tmp = head;
				head = new DoublyLinkedListNode<T>(i);
				head.replacePredecessor(tmp.getPredecessor());
				head.replaceSuccessor(tmp);
				tmp.replacePredecessor(head);
			} else {
				DoublyLinkedListNode<T> c = head.getSuccessor();
				while (comparator.compare(i, c.get()) > 0 && c.getSuccessor() != head) {
					c = c.getSuccessor();
				}
				DoublyLinkedListNode<T> tmp = new DoublyLinkedListNode<T>(i);
				tmp.replacePredecessor(c);
				tmp.replaceSuccessor(c.getSuccessor());
				c.replaceSuccessor(tmp);
			}
		}

	}

	public T removeMostSignificant() {
		if (head == null) {
			throw new NoSuchElementException();
		}
		DoublyLinkedListNode<T> tmp = head;

		if (head.getPredecessor() == head && head.getSuccessor() == head) {
			head = null;
		} else {

			head = tmp.getSuccessor();
			head.replacePredecessor(tmp.getPredecessor());
		}
		return tmp.get();
	}

	public T removeLeastSignificant() {
		if (head == null) {
			throw new NoSuchElementException();
		}
		DoublyLinkedListNode<T> tmp = head.getPredecessor();

		if (head.getPredecessor() == head && head.getSuccessor() == head) {
			head = null;
		} else {
			tmp.getPredecessor().replaceSuccessor(head);
			head.replacePredecessor(tmp.getPredecessor());
		}
		return tmp.get();
	}

	public T removeLeastSignificant(Comparator<T> c) {
		if (head == null) {
			throw new NoSuchElementException();
		}

		DoublyLinkedListNode<T> tmp = head;
		while (c.compare(tmp.get(), tmp.getSuccessor().get()) >= 0 && tmp.getSuccessor() != head) {
			tmp = tmp.getSuccessor();
		}
		if (head.getPredecessor() == head && head.getSuccessor() == head) {
			head = null;
		} else {

			tmp.getPredecessor().replaceSuccessor(tmp.getSuccessor());
			tmp.getSuccessor().replacePredecessor(tmp.getPredecessor());
		}
		return tmp.get();
	}

	public T removeMostSignificant(Comparator<T> c) {
		if (head == null) {
			throw new NoSuchElementException();
		}

		DoublyLinkedListNode<T> tmp = head;
		while (c.compare(tmp.get(), tmp.getSuccessor().get()) <= 0 && tmp.getSuccessor() != head) {
			tmp = tmp.getSuccessor();
		}
		if (head.getPredecessor() == head && head.getSuccessor() == head) {
			head = null;
		} else {

			tmp.getPredecessor().replaceSuccessor(tmp.getSuccessor());
			tmp.getSuccessor().replacePredecessor(tmp.getPredecessor());
		}
		return tmp.get();
	}

}
