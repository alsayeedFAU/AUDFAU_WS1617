
public class NatStackOp {

	public static NatStack put(Nat n, NatStack s) {
		if (s == NatStack.empty()) {
			return NatStack.push(s, n);
		}
		return NatStack.push(put(n, NatStack.pop(s)), NatStack.peek(s));
	}

	public static Nat get(NatStack s) {
		if (s == NatStack.empty()) {
			return Nat.NaN();
		}
		Nat head = NatStack.peek(s);
		NatStack tail = NatStack.pop(s);

		if (tail == NatStack.empty()) {
			return head;
		}

		return get(NatStack.pop(s));
	}

	public static NatStack poll(NatStack s) {
		if (s == NatStack.empty()) {
			return NatStack.empty();
		}

		Nat head = NatStack.peek(s);
		NatStack tail = NatStack.pop(s);

		if (tail == NatStack.empty()) {
			return NatStack.empty();
		}
		return NatStack.push(poll(NatStack.pop(s)), head);
	}

	public static Nat stackMul(NatStack s) {
		Nat head = NatStack.peek(s);
		NatStack tail = NatStack.pop(s);

		if (tail == NatStack.empty()) {
			return head;
		}

		return Nat.mul(head, stackMul(NatStack.pop(s)));
	}

	public static NatStack stackPairOp(NatStack s) {
		if (s == NatStack.empty()) {
			return NatStack.empty();
		}
		Nat head = NatStack.peek(s);
		NatStack tail = NatStack.pop(s);

		if (tail == NatStack.empty()) {
			return NatStack.push(NatStack.empty(), head);
		}

		if (head == NatStack.peek(tail)) {
			return NatStack.push(stackPairOp(NatStack.pop(tail)), Nat.mul(head, head));
		}

		return NatStack.push(stackPairOp(NatStack.pop(tail)), Nat.add(head, NatStack.peek(tail)));

	}

	public static Nat bin2nat(NatStack ns) {
		Nat head = NatStackOp.get(ns);
		NatStack tail = NatStackOp.poll(ns);
		
		if(tail == NatStack.empty() && head != Nat.zero()){
			return Nat.NaN();
		}
		
		if(tail == NatStack.empty()){
			return head;
		}
		
		return Nat.add(head, Nat.mul(Nat.succ(Nat.succ(Nat.zero())), bin2nat(tail)));
		
	}

	

	public static NatStack nat2bin(Nat n) {
		if (n == Nat.NaN()) {
			return NatStack.empty();
		}

		if (n == Nat.zero()) {
			return NatStack.push(NatStack.empty(), n);
		}

		return NatStack.push(NatStackOp.nat2bin(Nat.div(n, Nat.succ(Nat.succ(Nat.zero())))),
				Nat.mod(n, Nat.succ(Nat.succ(Nat.zero()))));
	}
}
