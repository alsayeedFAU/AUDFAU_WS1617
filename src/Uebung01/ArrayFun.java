
public class ArrayFun {

	static String[][][] allocate(int x, int y, int z) {

		if (x >= 0 && y >= 0 && z >= 0) {
			String array[][][] = new String[x][y][z];

			for (int i = 0; array != null && i < array.length; i++) {
				for (int j = 0; array[i] != null && j < array[i].length; j++) {
					for (int k = 0; array[i][j] != null && k < array[i][j].length; k++) {
						array[i][j][k] = "(" + i + "," + j + "," + k + ")";
					}
				}
			}
			return array;
		} else {
			throw new IllegalArgumentException("Keine negativen Zahlen!");
		}
	}

	
	
	//b
	static String[][][][] stuff(String[][][][] array) {
		for (int i = 0; array != null && i < array.length; i++) {
			for (int j = 0; array[i] != null && j < array[i].length; j++) {
				for (int k = 0; array[i][j] != null && k < array[i][j].length; k++) {
					for (int l = 0; array[i][j][k] != null && l < array[i][j][k].length; l++) {
						array[i][j][k][l] = "(" + i + "," + j + "," + k + "," + l + ")";
					}
				}
			}
		}
		return array;
	}
	//c
	static String[][] convolve(String[][][] array) {
		String tmp[][] = { { null, null, null }, {} };

		String s = null;
		for (int i = 0; array != null && i < array.length; i++) {
			for (int j = 0; array[i] != null && j < array[i].length; j++) {
				for (int k = 0; array[i][j] != null && k < array[i][j].length; k++) {
					if (array[i][j][k] != null && s == null) {
						s = array[i][j][k];
					} else if (s != null && array[i][j][k] != null) {
						s += array[i][j][k];
					}
				}

				if (s != null) {
					tmp[i][j] = s;

					s = null;
				}

			}
		}
		return tmp;
	}

	public ArrayFun() {

	}

}
