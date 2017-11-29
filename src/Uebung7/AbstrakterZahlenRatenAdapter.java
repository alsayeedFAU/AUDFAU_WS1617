public abstract class AbstrakterZahlenRatenAdapter {
	protected final ZahlenRaten zahlenRaten; // das ist der "Dienst" oder "adaptee" (https://en.wikipedia.org/wiki/Adapter_pattern)

	public AbstrakterZahlenRatenAdapter(ZahlenRaten zahlenRaten) {
		this.zahlenRaten = zahlenRaten;
	}

	/**
	 * 
	 * @param min
	 *            kleinstmoegliche Zahl, die gerade noch vorkommen kann
	 * @param max
	 *            groesstmoegliche Zahl, die gerade noch vorkommen kann
	 * @return 1 wenn gerade ein laufendes Spiel im Gange ist<br/>
	 *         2 wenn der Wertebereich ungueltig ist<br/>
	 *         3 wenn etwas anderes Unvorhergesehenes mehr als 7x hintereinander eingetreten ist<br/>
	 *         4 wenn alles OK ist<br/>
	 */
	public abstract int starteNeuesSpiel(int min, int max);

	/**
	 * 
	 * @param z
	 *            ein Rateversuch...
	 * @return 1 wenn unvorhergesehene Ausnahmen mehr als 7x hintereinander eingetreten sind, d.h. keiner der folgenden Faelle vorher zutrifft:<br/>
	 *         2 wenn das Spiel noch nicht mittels {@link starteNeuesSpiel} eingerichtet wurde<br/>
	 *         3 wenn das Spiel schon erfolgreich beendet ist (die Zahl also bereits erraten wurde)<br/>
	 *         4 wenn die uebergebene Zeichenkette gar keine Zahl ist<br/>
	 *         5 wenn die uebergebene Zeichenkette zwar eine Zahl ist, aber ausserhalb des erlaubten Bereichs liegt<br/>
	 *         6 wenn die gesuchte Zahl richtig erraten wurde<br/>
	 *         7 wenn die uebergebene Zahl kleiner als die zu ratende Zahl ist, aber noch im erlaubten Wertebereich liegt<br/>
	 *         8 wenn die uebergebene Zahl groesseer als die zu ratende Zahl ist, aber noch im erlaubten Wertebereich liegt<br/>
	 *         9 wenn gar nichts bekannt ist, weil gar nichts passiert ist und gar nichts zurueckkommt<br/>
	 */
	public abstract int rate(String z);
}