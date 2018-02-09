public class Riddle {

	public static boolean implies(boolean a, boolean b) {
		// TODO: a => b
		if ((a == false && b == true))
			return false;
		else
			return true;
	}

	public static boolean a0(boolean stan, boolean cartman, boolean kyle) {
		// TODO: A0
		return implies(stan, kyle);
	}

	public static boolean a1(boolean stan, boolean cartman, boolean kyle) {
		// TODO: A1
		return implies(cartman, stan);
	}

	public static boolean a2(boolean stan, boolean cartman, boolean kyle) {
		// TODO: A2
		if ((stan ^ cartman) ^ kyle)
			return true;
		else
			return false;
	}

	public static boolean a3(boolean stan, boolean cartman, boolean kyle) {
		// TODO: A3
		if ((((stan == true) && ((cartman && kyle) == false)) ^ ((cartman == true) && (stan && kyle) == false))
				^ ((kyle == true) && (stan && cartman) == false))
			return true;
		else
			return false;
	}

	public static boolean eval(boolean stan, boolean cartman, boolean kyle) {
		// TODO: eval
		if ((a0(stan, cartman, kyle) && a1(stan, cartman, kyle)) && a2(stan, cartman, kyle)
				&& a3(stan, cartman, kyle) == true)
			return true;
		else
			return false;
		
		
	}

	public static int checkRiddle() {
		if (eval(true, false, false) == true)
			return 0;
		else if (eval(false, false, true) == true)
			return 2;
		else if (eval(false, true, false) == true)
			return 1;
		else
			return -1;

	}
}
