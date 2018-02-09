import java.nio.channels.NetworkChannel;

public abstract class Merge {

	// Don't delete!! Tests will fail otherwise
	public Merge() {
	}

	static int merker = 1;
	static int j = 0;

	/**
	 * @param ns
	 *            Integer array being merged
	 * @param i
	 *            Start index
	 * @param r
	 *            Only for test purposes. Ignore it!
	 * @return String containing merged output
	 */
	public static String merge(int[] ns, int i, Merge r) {
		// Don't delete!! Tests will fail otherwise
		// Pass object r to other function calls of merge (e.g. for recursion).
		// Do not create other instances of object r
		// Function merge(...) must be recursive, don't implement other
		// recursive helper functions
		r.check();
		String tmp = "";
		/*
		 * if (j < i && i != 0) { tmp += ns[j]; j++; tmp += merge(ns, i, r); }
		 * else {
		 */
		if (i < 0) { //Abbruch wenn i negativ ist
			return tmp;
		} else if (i < ns.length - 1) {
			
			if (ns[i] == ns[i + 1] && merker == 1) { //ersten zwei gleiche Eintraege
				merker *= ns[i] * ns[i];			
			} else if (ns[i] == ns[i + 1] && merker != 1) { //jeder weitere Eintrag
				merker *= ns[i];
			} else if (ns[i] != ns[i + 1] && merker != 1) { //Ausgabe Merker nachdem i != i+1
				tmp += " " + merker;
				merker = 1;
				
			} else { //Ausgabe i bei i!=1+1
				tmp += " " + ns[i];
			}
			// j++;
			tmp += merge(ns, i + 1, r); //rekursiver Aufruf
			
		} else if (merker != 1 && i == ns.length-1){ //prueft ob am Ende des Arrays doppelte Zahlen sind
			tmp += " " + merker;
		}else {
			// j++;
			tmp += " " + ns[ns.length - 1]; //wenn i == ns.lenght-1, Abbruchbedingung
		}
		return tmp;

	}

	// Don't delete!! Tests will fail otherwise
	public abstract void check();
}