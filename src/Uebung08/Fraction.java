public class Fraction {

	private long numerator;
	private long denominator;

	private static long ggT(long a, long b) {
		if (b == 0)
			return a;
		return ggT(b, a % b);
	}

	public Fraction(long numerator, long denominator) {
		this.numerator = numerator;
		this.denominator = denominator;
	}

	public long getNumerator() {
		return numerator;
	}

	public long getDenominator() {
		return denominator;
	}

	@Override
	public String toString() {
		return "[ " + numerator + " / " + denominator + " ]";
	}

	public void simplify() {
		// TODO
	}

	public Fraction mul(Fraction other) {
		// TODO
		return null;
	}

	public Fraction div(Fraction other) {
		// TODO
		return null;
	}

	public Fraction add(Fraction other) {
		// TODO
		return null;
	}

	public Fraction sub(Fraction other) {
		// TODO
		return null;
	}

	public int compareTo(Fraction other) {
		// TODO
		return -1;
	}

	public boolean isNonNegative() {
		// TODO
		return false;
	}
}
