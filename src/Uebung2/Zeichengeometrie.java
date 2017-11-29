public class Zeichengeometrie {
	public static char[][] erzeugeNeueZeichenflaeche(int breite, int hoehe) {
		// TODO
		char[][] tmp = new char[hoehe][breite];
		for (int i = 0; i < tmp.length; i++) {
			for (int j = 0; j < tmp[i].length; j++) {
				tmp[i][j] = ' ';
			}
		}
		return tmp;
	}

	public static char[][] zeichnePunkt(char[][] zeichenflaeche, int x, int y, char zeichen) {
		// TODO
		if (x < 0 || y < 0 || x > zeichenflaeche[0].length || y > zeichenflaeche.length - 1) {
			return zeichenflaeche;
		} else {
			zeichenflaeche[zeichenflaeche.length - y - 1][x] = zeichen;
		}
		//System.out.println("(" + (zeichenflaeche.length - y - 1) + "|" + x + ")");

		return zeichenflaeche;
	}

	public static char[][] zeichneLinie(char[][] zeichenflaeche, int startX, int startY, int endeX, int endeY,
			char zeichen) {
		// TODO

		zeichnePunkt(zeichenflaeche, startX, startY, zeichen);
		zeichnePunkt(zeichenflaeche, endeX, endeY, zeichen);

		if (startY == endeY) {
			for (int i = startX < endeX ? startX : endeX; i < (startX > endeX ? startX : endeX); i++) {
				zeichnePunkt(zeichenflaeche, i, startY, zeichen);
			}
			return zeichenflaeche;
		} else if (startX == endeX) {
			for (int i = startY < endeY ? startY : endeY; i < (startY > endeY ? startY : endeY); i++) {
				zeichnePunkt(zeichenflaeche, startX, i, zeichen);
			}
			return zeichenflaeche;
		}
		if (startX < endeX) {
			for (int i = startX; i < endeX; i++) {
				zeichnePunkt(zeichenflaeche, i,
						(int) Math.round((double) (endeY - startY) / (double) (endeX - startX) * (i - startX) + startY),
						zeichen);
			}
		} else {
			for (int i = endeX; i < startX; i++) {
				zeichnePunkt(zeichenflaeche, i,
						(int) Math.round((double) (startY - endeY) / (double) (startX - endeX) * (i - endeX) + endeY),
						zeichen);
			}
		}
		/*
		 * if(startX < endeX){ double y = startY; for(int i = startX; i < endeX;
		 * i++){ zeichnePunkt(zeichenflaeche, i,(int) Math.round(y), zeichen); y
		 * += ((double)(endeY - startY)/(double)(endeX -startX)); } }else{
		 * double y = endeY; for(int i = endeX; i < startX; i++){
		 * zeichnePunkt(zeichenflaeche, i, (int) Math.round(y), zeichen); y +=
		 * ((double)(endeY - startY)/(double)(endeX -startX)); } }
		 */

		return zeichenflaeche;
	}

	public static char[][] zeichnePolygon(char[][] zeichenflaeche, int[] koordinaten, char zeichen) {
		// TODO
		for (int i = 0; i < koordinaten.length - 3; i = i + 2) {
			zeichneLinie(zeichenflaeche, koordinaten[i], koordinaten[i + 1], koordinaten[i + 2], koordinaten[i + 3],
					zeichen);
		}
		return zeichenflaeche;
	}

	public static char[][] zeichneRechteck(char[][] zeichenflaeche, int startX, int startY, int endeX, int endeY,
			char zeichen, boolean ausgefuellt) {
		// TODO
		zeichneLinie(zeichenflaeche, startX, startY, startX, endeY, zeichen);
		zeichneLinie(zeichenflaeche, startX, startY, endeX, startY, zeichen);
		zeichneLinie(zeichenflaeche, endeX, endeY, startX, endeY, zeichen);
		zeichneLinie(zeichenflaeche, endeX, endeY, endeX, startY, zeichen);
		if (ausgefuellt) {
			for (int i = startX < endeX ? startX : endeX; i < (startX > endeX ? startX : endeX); i++) {
				for (int j = startY < endeY ? startY : endeY; j < (startY > endeY ? startY : endeY); j++) {
					zeichnePunkt(zeichenflaeche, i, j, zeichen);
				}
			}
		}
		return zeichenflaeche;
	}

	public static char[][] zeichneKreis(char[][] zeichenflaeche, int mittelpunktX, int mittelpunktY, int radius,
			char zeichen) {
		// TODO
		for (int i = mittelpunktX - radius; i <= mittelpunktX + radius; i++) {
			for (int j = mittelpunktY - radius; j <= mittelpunktY + radius; j++) {
				int tmp = (int) Math.round(Math
						.sqrt((Math.pow((double) (i - mittelpunktX), 2) + Math.pow((double) (j - mittelpunktY), 2))));
				if (tmp == radius) {
					zeichnePunkt(zeichenflaeche, i, j, zeichen);
				}
			}
		}

		return zeichenflaeche;

	}

	public static void aufBildschirmAusgeben(char[][] zeichenflaeche) {
		System.out.println("\n===== NEUE AUSGABE: =====");
		for (int y = 0; y < zeichenflaeche.length; y++) {
			for (int x = 0; x < zeichenflaeche[y].length; x++) {
				System.out.print(zeichenflaeche[y][x]);
			}
			System.out.println();
		}
	}
}