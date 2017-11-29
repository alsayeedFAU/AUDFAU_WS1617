public class Queue<E> {
	private Stack<E> stack;

	public Queue<E> enqueue(E s) {
		if (this.stack == null) {
			this.stack = Stack.create();
			stack = Stack.push(this.stack, s);
			return this;
		}
		//this.stack =Stack.reverse(this.stack);
		this.stack = Stack.push(this.stack, s);
		//this.stack = Stack.reverse(this.stack);
		return this;
	}

	public Queue<E> dequeue() {
		this.stack = Stack.pop(this.stack);
		return this;
	}

	public boolean isEmpty() {
		if(stack == null){
			return true;
		}
		if(Stack.pop(stack) == null){
			return true;
		}
		return false;
	}

	public  E front() {
		return  Stack.top(stack);
	}

}
