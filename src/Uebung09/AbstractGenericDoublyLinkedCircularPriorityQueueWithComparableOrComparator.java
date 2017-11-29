import java.util.Comparator;
import java.util.NoSuchElementException;

/** Inhaltliches: **/
// Prioritaetswarteschlangen in Anlehnung an Aufgabe 7 (insbesondere c und d) der AuD-Klausur vom 30.03.2015
// https://www2.cs.fau.de/aud/organisation/oldexams/secure/15-03-30_klausur.pdf
// Das Attribut head verweist auf den Knoten mit dem gemaess "Comparator<E> comparator" wichtigsten/"hoechsten"/"groessten" Wert,
// bzw. auf null, wenn die Schlange leer ist.
// Der Nachfolger von head verweist entsprechend auf den zweit-wichtigsten usw., bis der Ring wieder bei head schliesst.
// Der Vorgaenger von head verweist analog in Gegenrichtung auf den unwichtigsten, danach auf den zweit-unwichtigsten Wert usw.,
// bis auch hier der Ring wieder bei head schliesst.

/** Organisatorisches: **/
// Ihre Unterklasse darf KEINE weiteren Attribute, Klassen oder Annotationen deklarieren (sie soll nur die von hier geerbten nutzen)!
// Ihre Unterklasse darf KEINE anderen Klassen oder Methoden aus der Java-API verwenden, ausser die hier oben importierten!
// Sie duerfen und sollten eine oder mehrere private Hilfsmethode(n) deklarieren...

/** Plagiatorisches: **/
// Um Plagiate zu verhindern, wurden alle Klassen und Methoden umbenannt,
// die Hauptklasse zerlegt, Comparatoren statt Comparables verwendet und einiges mehr unternommen...
// Schreibt den Code allein von Grund auf selbst!
// PS: die Seite https://fsi.cs.fau.de/dw/pruefungen/bachelor/aud/loesungws14#aufgabe_7_-_doppelverkettung_29
// ist dem AuD-Team durchaus bekannt... aber zum Zeitpunkt der Erstellung dieser Aufgabe war die vermeintliche Loesung
// fehlerhaft (und ist es ziemlich sicher noch immer) - ausserdem passt sie hier hinten und vorne nicht ;)...

public abstract class AbstractGenericDoublyLinkedCircularPriorityQueueWithComparableOrComparator<E> {
	protected DoublyLinkedListNode<E> head; // Knoten mit hoechster Prioritaet (null falls Schlange leer)
	protected final Comparator<E> comparator; // stellt die Prioritaetsreihenfolge dar

	protected AbstractGenericDoublyLinkedCircularPriorityQueueWithComparableOrComparator(Comparator<E> comparator) {
		this.comparator = comparator;
	}

	// Fuegt den Wert gemaess "Sortierung" nach "Comparator<E> comparator" in den Ring ein.
	// Bei gleicher Prioritaet wird das neue Element "hinter" dem vorhandenen eingefuegt.
	public abstract void insert(E e);

	// Entfernt den gemaess "Comparator<E> comparator" wichtigsten Wert aus dem Ring und gibt ihn zurueck.
	// Ist die Schlange vor dem Aufruf leer, muss diese Methode eine NoSuchElementException werfen.
	// Diese Operation muss in O(1) erfolgen!
	public abstract E removeMostSignificant() throws NoSuchElementException;

	// Entfernt den gemaess "Comparator<E> comparator" *UN*wichtigsten Wert aus dem Ring und gibt ihn zurueck
	// Ist die Schlange vor dem Aufruf leer, muss diese Methode eine NoSuchElementException werfen.
	// Diese Operation muss in O(1) erfolgen!
	public abstract E removeLeastSignificant() throws NoSuchElementException;

	// Entfernt den gemaess "Comparator<E> otherComparator" wichtigsten Wert aus dem Ring und gibt ihn zurueck.
	// Ist die Schlange vor dem Aufruf leer, muss diese Methode eine NoSuchElementException werfen.
	// Diese Operation darf in O(Schlangenlaenge) erfolgen!
	public abstract E removeMostSignificant(Comparator<E> otherComparator) throws NoSuchElementException;

	// Entfernt den gemaess "Comparator<E> otherComparator" *UN*wichtigsten Wert aus dem Ring und gibt ihn zurueck.
	// Ist die Schlange vor dem Aufruf leer, muss diese Methode eine NoSuchElementException werfen.
	// Diese Operation darf in O(Schlangenlaenge) erfolgen!
	public abstract E removeLeastSignificant(Comparator<E> otherComparator) throws NoSuchElementException;

	@Override
	public final String toString() {
		String s = "#>";
		if (head != null) {
			DoublyLinkedListNode<E> n = head;
			do {
				s += " " + n.get() + " >";
				n = n.getSuccessor();
			} while (n != head);
		}
		return s + "|";
	}
}