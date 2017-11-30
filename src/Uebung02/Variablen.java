import java.util.Arrays;

public class Variablen {
	// TODO: declare a constant named "FOO_BAR" with a value of -123.456 * 10^(-89)


	// TODO: declare an enumeration named "Months" containing the
	// english names in CAPITAL_LETTERS (!) of all the 12 months of the year in the correct order


	public static char[] someCharacters() {
		// TODO: declare, fill and return an 1-dimensional
		// array containing the ASCII-letters A to Z (capital letters)

		return null;
	}

	public static char[][] someMoreCharacters() {
		// TODO: declare, fill and return a 2-dimensional array containing
		// 1) the values (!) 0 to 25 in the first row,
		// 2) the ASCII-letters A to Z (capital letters) in the second row,
		// 3) the ASCII-characters (!) 0 to 9, than 0 to 9 again and finally 0 to 5 in the third row
		// 4) the ASCII-letters a to z (non-capital letters) in the fourth row,

		return null;
	}

	public static int[][][] theCube() {
		// TODO: declare, fill and return a 3-dimensional array
		// of size 3x3x3 (try to imagine a http://en.wikipedia.org/wiki/Rubik's_Cube)
		// containing only integer numbers such that
		// the value at [x][y][z] == (x+1)*100+(y+1)*10+(z+1)
		// (e.g. cube[2][1][0] == 321)

		return null;
	}

	// after creating the constant and the  enum, uncomment the code by
	// removing the two indicated lines
	// DON'T change anything else
	public static void main(String[] args) {
/* TODO: delete this line ...
		System.out.println("-----");
		System.out.println(Variablen.FOO_BAR);
		System.out.println("-----");
		System.out.println(Months.OCTOBER);
		System.out.println("-----");
... and this line */
		System.out.println(Arrays.toString(someCharacters()));
		System.out.println("-----");
		// The output of this part will look broken/wrong
		for (char[] r : someMoreCharacters()) {
			System.out.println(Arrays.toString(r));
		}
		System.out.println("-----");
		for (int[][] plane : theCube()) {
			for (int[] row : plane) {
				System.out.print(Arrays.toString(row));
			}
			System.out.println();
		}
	}
}
