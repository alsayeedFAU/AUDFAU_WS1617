public class ZahlenRatenAdapter extends AbstrakterZahlenRatenAdapter {

	public ZahlenRatenAdapter(ZahlenRaten zahlenRaten) {
		super(zahlenRaten);
	}

	@Override
	public int starteNeuesSpiel(int min, int max) {
		boolean lock = true;
		for (int i = 0; i < 7; i++) {
			if (lock) {
				lock = false;
			}
			try {
				zahlenRaten.starteNeuesSpiel(min, max);

			} catch (IllegalStateException iE) {
				return 1;
			} catch (IllegalArgumentException iA) {
				return 2;
			} catch (Exception e) {
				lock = true;
			} catch (Throwable t) {
				lock = true;
			}
			if (!lock) {
				return 4;
			}
		}

		return lock ? 3 : 4;
	}

	@Override
	public int rate(String z) {

		boolean lock = true;

		for (int i = 0; i < 7; i++) {
			if (lock) {
				lock = false;
			}
			try {
				zahlenRaten.rate(z);

			} catch (IllegalStateException iE) {
				return 2;
			} catch (NumberFormatException iA) {
				return 4;
			} catch (IllegalArgumentException iA) {
				return 3;
			} catch (ZahlPasstNicht e) {
				return 5;
			} catch (ZahlZuKlein e) {
				return 7;
			} catch (ZahlZuGross e) {
				return 8;
			} catch (ZahlPasst e) {
				return 6;
			} catch (Exception e) {
				lock = true;
			} catch (Throwable t) {
				lock = true;
			}
			if (!lock) {
				return 9;
			}
		}

		return lock ? 1 : 9;
	}

}
