public class Rowland {
	private static int[] merker;

	public static int rowlandNaive(GCD gcd, int n) {
		if (n <= 1) {
			return 7;
		}
		return rowlandNaive(gcd, n - 1) + gcd.gcd(n, rowlandNaive(gcd, n - 1));
	}

	public static int omitNaive(GCD gcd, int n) {
		int erg = 0;
		int z = 0;
		for (int i = 0; z < n; i++) {
			erg = (rowlandNaive(gcd, i) - rowlandNaive(gcd, i - 1));
			if (erg > 1) {
				z++;
			}
		}
		return erg;

	}

	public static int rowlandDP(GCD gcd, int n) {
		if (merker != null && merker.length <= n) {
			int[] newMerker = new int[n + 1];
			System.arraycopy(merker, 0, newMerker, 0, merker.length);
			merker = newMerker;
		}
		if (merker == null) {
			int[] newMerker = new int[n + 1];
			newMerker[1] = 7;
			merker = newMerker;
		}

		if (merker[n] != 0) {
			return merker[n];
		}

		merker[n] = rowlandDP(gcd, n - 1) + gcd.gcd(n, rowlandDP(gcd, n - 1));
		return merker[n];
		
	}

	public static int omitDP(GCD gcd, int n) {
		int erg = 0;
		int z = 0;
		for (int i = 2; z < n; i++) {
			erg = (rowlandDP(gcd, i) - rowlandDP(gcd, i - 1));
			if (erg > 1) {
				z++;
			}
		}
		return erg;
	}

	public static void resetDP() {
		merker = null;
	}
}