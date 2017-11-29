public class Bin2Long {

	static int check(String s) {
		if (s == null) {
			return -3;
		}
		if (s == "") {
			return -2;
		}

		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) != '0' && s.charAt(i) != '1') {
				return i;
			}
		}

		return -1;
	}

	
	
	
	
	static long convertBit(char b) {
		if (b == '1') {
			return 2L;
		} else {
			return 0L;
		}
	}

	static long convert(String s) {
		if (check(s) != -1 || s.length() > 64) {
			return 0L;
		}
		long tmp = 0;
		for (int i = 1; i < s.length() - 1; i++) {
			tmp += Math.pow(convertBit(s.charAt(i)), s.length() - 1 - i);
		}
		if (s.charAt(s.length() - 1) == '1') {
			tmp += 1;
		}
		if (s.charAt(0) == '1') {
			return tmp * -1;
		}
		return tmp;
	}

	public static void main(String[] args) {
	}

}
