public class QueueElement {
	private final Object object;
	private final int priority;
	private QueueElement next;

	public QueueElement(Object object, int priority) {
		this.object = object;
		this.priority = priority;
	}

	public QueueElement getNext() {
		return next;
	}

	public void setNext(QueueElement next) {
		this.next = next;
	}

	public int getPriority() {
		return priority;
	}

	public Object getValue() {
		return object;
	}

	@Override
	public String toString() {
		return "(" + String.valueOf(object) + "," + priority + ")";
	}
}