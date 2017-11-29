public class Stack<E> {
	private Stack stack;
	private E e;

	private Stack(Stack<E> stack, E e) {
		this.stack = stack;
		this.e = e;
	}

	public static <E> Stack<E> create() {
		return new Stack<E>(null, null);
	}

	public static <E> Stack<E> push(Stack<E> s, E e) {
		if (s == null) {
			return new Stack<E>(Stack.<E>create(), e);
		}
		return new Stack<E>(s, e);
	}

	public static <E> Stack<E> pop(Stack<E> s) {

		if (s == null || s.stack == null) {
			return create();
		}

		return s.stack;
	}

	public static <E> Stack<E> reverse(Stack<E> s) {
		Stack<E> erg = new Stack<>(null, null);
		Stack<E> tmp = s;
		while (!Stack.isEmpty(tmp)) {
			if(!top(tmp).equals(null)){
				erg = Stack.push(erg, Stack.top(tmp));
			}
			tmp = Stack.pop(tmp);
		}
		return erg;
	}

	public static <E> boolean isEmpty(Stack<E> s) {
		if (s == null) {
			return true;
		}

		if (s.e == null && s.stack == null) {
			return true;
		}
		return false;
	}

	public static <E> E top(Stack<E> s) {
		if (s == null) {
			return null;
		}
		return s.e;
	}

}
