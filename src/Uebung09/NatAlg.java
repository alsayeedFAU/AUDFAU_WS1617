public class NatAlg {

	public static Nat mod(Nat x, Nat y) {
		return x == Nat.zero() ? Nat.zero() : y == Nat.zero() ? Nat.zero() : Nat.sub(x, Nat.mul(Nat.div(x, y), y));
	}

	public static Nat lt(Nat x, Nat y) {
		return gt(x, y) == Nat.zero() ? Nat.succ(Nat.zero()) : Nat.zero();
	}

	public static Nat gt(Nat x, Nat y) { // >
		return Nat.sub(x, y) == Nat.zero() ? Nat.zero() : Nat.succ(Nat.zero());

	}

	public static Nat eq(Nat x, Nat y) {
		return mod(x, y) == Nat.zero() ? gt(x, y) == Nat.zero() ? Nat.succ(Nat.zero()) : Nat.zero() : Nat.zero();
	}

	public static Nat gcd(Nat x, Nat y) {
		return y == Nat.zero() ? x : gcd(y, mod(x, y));
	}

	public static Nat lcm(Nat x, Nat y) {
		return x == Nat.zero() ? Nat.zero()
				: y == Nat.zero() ? Nat.zero()
						: gcd(x, y) == Nat.zero() ? Nat.zero() : Nat.div(Nat.mul(x, y), gcd(x, y));
	}

	public static EleList<Nat> pfz(Nat x) {
		return pfzH(x, Nat.succ(Nat.succ(Nat.zero())));

	}

	private static EleList<Nat> pfzH(Nat zahl1, Nat zahl2) {
		if (gt(zahl1, zahl2) == Nat.zero()) {
			return EleList.add(new EleList<Nat>(), zahl2);
		} else if (mod(zahl1, zahl2) == Nat.zero()) {
			return EleList.add(pfzH(Nat.div(zahl1, zahl2), zahl2), zahl2);
		} else {
			return pfzH(zahl1, Nat.add(zahl2, Nat.succ(Nat.zero())));
		}
	}
}
