public class PriorityQueue {
	private QueueElement theQueue = null;

	public void reset() {
		if (theQueue != null) {
			theQueue = null;
		}
	}

	public int length() {
		int count = 0;
		QueueElement current = theQueue;
		while (current != null) {
			current = current.getNext();
			count++;
		}
		return count;
	}

	public Object top() {
		Log.log("top");
		Log.log(1);
		Object result = null;
		Log.log(2);
		if (theQueue != null) {
			Log.log(3);
			result = theQueue.getValue();
		}
		Log.log(4);
		Log.log();
		return result;
	}

	public String toString() {
		String output = "";
		QueueElement current = theQueue;
		while (current != null) {
			output += current.toString();
			current = current.getNext();
		}
		return output;
	}

	public void addItem(Object object, int priority) {
		QueueElement current;
		QueueElement lookahead;
		QueueElement tmp = new QueueElement(object, priority);
		if (theQueue == null) {
			theQueue = tmp;
		} else {
			if (theQueue.getPriority() < tmp.getPriority() || theQueue.getNext() == null) {
				if (theQueue.getPriority() < tmp.getPriority()) {
					tmp.setNext(theQueue);
					theQueue = tmp;
				} else {
					theQueue.setNext(tmp);
				}
			} else {
				current = theQueue;
				lookahead = theQueue.getNext();
				while ((lookahead.getPriority() >= tmp.getPriority()) && (lookahead.getNext() != null)) {
					current = lookahead;
					lookahead = lookahead.getNext();
				}
				if (lookahead.getPriority() < tmp.getPriority()) {
					tmp.setNext(lookahead);
					current.setNext(tmp);
				} else {
					lookahead.setNext(tmp);
				}
			}
		}
	}
}