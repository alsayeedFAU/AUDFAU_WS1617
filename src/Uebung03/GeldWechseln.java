import java.util.Arrays;

public class GeldWechseln {

	public static void wechseln(Wechsel w, int[] m, int b) {
		if (w == null || m == null || b <= 0) {

		} else {
			int[] wm = new int[b];
			Arrays.sort(m);
			System.out.println(Arrays.toString(m));
			wechselnH(w, m, b, 0, wm);
		}
	}

	private static void wechselnH(Wechsel w, int[] m, int b, int pos, int[] wm) {
		//System.out.println(Arrays.toString(wm));
		if (m.length > 0 && b >= m[m.length-1]) {
			//System.out.println("b: "+ b + "Muenze: " + m[m.length-1]);
				wm[pos] = m[m.length-1];
				// System.out.println(Arrays.toString(wm));
				if(b - m[m.length-1] == 0){
					System.out.println(Arrays.toString(wm));
					w.merke(wm);
				}
				wechselnH(w, m, b - m[m.length-1], pos + 1, wm);
			
			System.out.println("1");
			wechselnH(w, Arrays.copyOfRange(m, 0, m.length - 1), b, pos, wm);
		}

	}

	public GeldWechseln() {
		// TODO Auto-generated constructor stub
	}

}
