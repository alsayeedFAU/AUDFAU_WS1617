import java.lang.reflect.Array;
import java.util.Arrays;

import javax.swing.text.AbstractDocument.LeafElement;

public class Algebra {
	static long ausgabe[][];
	static long ausgabeBei1[][] = { {}, {} };
	static int maxFactor;
	static int indexArray = 0;
	static int indexArrayFactor = 1;

	public static long[][] primfaktorzerlegung(long n) {
		if (n == 1) {
			ausgabe = ausgabeBei1;
			return ausgabe;
		} else {
			maxFactor = (int) Math.ceil(Math.log10(n)/Math.log10(2));
			int test = maxFactor;
			long tmp[] = new long[maxFactor];

			for (int a = 0; a < tmp.length; a++) {
				tmp[a] = 0;
			}

			for (long i = 2; i <= n; i++) {
				if (n % i == 0) {
					tmp[indexArray++] = i;
					n = n / i;
					i = 1;
				}
			}
			for (int b = 0; b < tmp.length - 1; b++) {
				if ((tmp[b] != tmp[b + 1]) && (tmp[b + 1] != 0))
					indexArrayFactor += 1;
				int test1 = indexArrayFactor;
			}
			long ausgabe[][] = new long[2][indexArrayFactor];

/*			for (int c = 0; c < indexArrayFactor; c++) {
				ausgabe[0][0] = 0;
				ausgabe[c][1] = 0;
			}*/

			ausgabe[0][0] = tmp[0];
			for (int x = 0, y = 0; x < tmp.length; x++) {
				if (tmp[x] == 0) {

				} else if (tmp[x] == ausgabe[0][y]) {
					ausgabe[1][y] += 1;
				} else if (tmp[x] != ausgabe[0][y]) {
					ausgabe[0][y + 1] = tmp[x];
					ausgabe[1][y + 1] += 1;
					y++;
				}

			}
			return ausgabe;
		}



	}

	public static long ggT(long[][] aPFZ, long[][] bPFZ) {
		long tmp = 1;
		for (int i = 0; i < aPFZ[1].length; i++)
			for (int j = 0; j < bPFZ[1].length; j++)
				if (aPFZ[0][i] == bPFZ[0][j]) {
					if (aPFZ[1][i] > bPFZ[1][j])
						tmp *= Math.pow(bPFZ[0][j], bPFZ[1][j]);
					else if (aPFZ[1][i] < bPFZ[1][j])
						tmp *= Math.pow(aPFZ[0][i], aPFZ[1][i]);
					else
						tmp *= Math.pow(aPFZ[0][i], aPFZ[1][i]);
				}
		return tmp;
	}

	public static long kgV(long a, long b) {
		long a1 = a;
		long b1 = b;
	if ((a != 0 && b!=0)){
		while (a1!=b1) {
			if (Math.abs(a1)<Math.abs(b1))
				a1 +=a;
			else if (Math.abs(a1)>Math.abs(b1))
				b1 +=b;
		}	
	}
	else{
		a1 = 0;
	}
		return a1;
	}

}