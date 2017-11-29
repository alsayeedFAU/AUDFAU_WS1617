public abstract class Nat {
	// ===== OPS: PRIMARY CONS =============================================================
	public static Nat NaN() {
		return NaN;
	}

	public static Nat zero() {
		return zero;
	}

	public static Nat succ(Nat n) {
		assert n != null;
		return n == NaN ? NaN : new Nat(n) {
		};
	}

	// ===== OPS: PROJECTIONS =============================================================
	public static Nat eq(Nat x, Nat y) {
		assert x != null && y != null;
		if (x == NaN || y == NaN)
			return NaN;
		else if (x == zero && y == zero)
			return succ(zero);
		else if (x == zero || y == zero)
			return zero;
		return eq(sub(x, succ(zero)), sub(y, succ(zero)));
	}

	// ===== OPS: SECONDARY CONS =============================================================
	public static Nat add(Nat x, Nat y) {
		assert x != null && y != null;
		return (x == NaN || y == NaN) ? NaN : x == zero ? y : succ(add(x.p, y));
	}

	public static Nat sub(Nat x, Nat y) {
		assert x != null && y != null;
		return (x == NaN || y == NaN) ? NaN : x == zero ? zero : y == zero ? x : sub(x.p, y.p);
	}

	public static Nat mul(Nat x, Nat y) {
		assert x != null && y != null;
		return (x == NaN || y == NaN) ? NaN : x == zero ? zero : add(y, mul(x.p, y));
	}

	public static Nat div(Nat x, Nat y) {
		assert x != null && y != null;
		return (x == NaN || y == NaN) ? NaN : sub(y, x) != zero ? zero : add(succ(zero), div(sub(x, y), y));
	}

	public static Nat mod(Nat x, Nat y) {
		assert x != null && y != null;
		return (x == NaN || y == NaN) ? NaN : Nat.sub(y, x) != Nat.zero() ? x : Nat.sub(x, Nat.mul(y, Nat.div(x, y)));
	}

	// ===== HMI ==============================================================
	@Override
	public String toString() {
		return this == NaN ? "NaN" : this == zero ? "z" : "S" + this.p;
	}

	// ===== INTERNALS ========================================================
	private static final Nat NaN = new Nat(null) {
	}; // internal marker object for NaN
	private static final Nat zero = new Nat(null) {
	}; // internal marker object for zero

	private final Nat p; // internal predecessor

	private Nat(Nat n) { // internal constructor
		this.p = n;
	}
}