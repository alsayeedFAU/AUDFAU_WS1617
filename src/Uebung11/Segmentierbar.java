// Schnittstelle fuer Klassen mit einer natuerlichen Ordnung zwecks verallgemeinertem Sortieren durch Fachverteilen.
public interface Segmentierbar {
	// Zerlegt das Objekt in einzelne Segmente, auf die eine natuerliche Ordnung definiert ist.
	// Das hoeherwertige Segment befindet sich dabei am Anfang des Feldes (also bei Index 0).
	public Comparable<?>[] holeSegmente();
}