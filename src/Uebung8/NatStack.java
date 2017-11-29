public abstract class NatStack {
	// ===== OPS: PRIMARY CONS =============================================================
	public static NatStack empty() {
		return empty;
	}

	public static NatStack push(NatStack s, Nat n) {
		return new NatStack(s, n) {
		};
	}

	public static Nat peek(NatStack s) {
		return s == empty ? Nat.NaN() : s.n;
	}

	public static NatStack pop(NatStack s) {
		return s == empty ? empty : s.s;
	}

	// ===== HMI ==============================================================
	@Override
	public String toString() {
		return this == empty() ? "[]" : "[" + n + "," + s + "]";
	}

	// ===== INTERNALS ========================================================
	private static final NatStack empty = new NatStack(null, null) {
	}; // internal marker object for empty NatStack

	private final Nat n; // internal: top/last Nat
	private final NatStack s; // internal: tail, i.e. all but top/last Nat

	private NatStack(NatStack s, Nat n) { // internal constructor
		this.s = s;
		this.n = n;
	}
}