public class AwesomAvatar {
	// TODO: Hier die noetigen Datenstrukturen anlegen

	public AwesomAvatar(int strength, int intelligence, int experience, AwesomWeapon weapon) {
		// TODO
	}

	public int getIntelligence() {
		// TODO
		// Gibt den aktuellen Wert von intelligence zurueck.
		return 0;
	}

	public void setIntelligence(int intelligence) {
		// TODO
		// Setzt intelligence auf den uebergebenen Wert. Positive wie negative
		// Werte sind moeglich.
	}

	public int getStrength() {
		// TODO
		// Gibt den aktuellen Wert von strength zurueck.
		return 0;
	}

	public void setStrength(int strength) {
		// TODO
		// Setzt strength auf den uebergebenen Wert. Positive wie negative Werte
		// sind moeglich.
	}

	public int getExperience() {
		// TODO
		// Gibt den aktuellen Wert von experience zurueck.
		return 0;
	}

	public void setExperience(int experience) {
		// TODO
		// Setzt experience auf den uebergebenen Wert. Positive wie negative
		// Werte sind moeglich.
	}

	public int getLifePoints() {
		// TODO
		// Gibt den aktuellen Wert von lifepoints zurueck.
		return 0;
	}

	public void setLifePoints(int lifePoints) {
		// TODO
		// Setzt lifePoints auf den uebergebenen Wert, jedoch nicht groesser als
		// den Maximalwert. Falls der uebergebene Wert groesser ist als der
		// Maximalwert, so wird lifePoints auf den Maximalwert gesetzt.
		// Negative lifePoints hingegen sind moeglich.
	}

	public int getMana() {
		// TODO
		// Gibt den aktuellen Wert von mana zurueck.
		return 0;
	}

	public void setMana(int mana) {
		// TODO
		// Setzt mana auf den uebergebenen Wert, jedoch nicht groesser als den
		// Maximalwert. Falls der uebergebene Wert groesser ist als der
		// Maximalwert, so wird mana auf den Maximalwert gesetzt.
		// Negative mana hingegen sind moeglich.
	}

	public int getMaxLifePoints() {
		// TODO
		// Gibt die maximale Anzahl an lifePoints zurueck.
		// Diese berechnen sich aus dem Produkt von experience und strength.
		return 0;
	}

	public int getMaxMana() {
		// TODO
		// Gibt die maximal Anzahl an mana zurueck.
		// Diese berechnen sich aus dem Produkt von experience und intelligence.
		return 0;
	}

	public void addLifePoints(int lifePoints) {
		// TODO
		// Fuegt den uebergebenen Wert zu den aktuellen lifePoints hinzu, jedoch
		// nicht groesser als den Maximalwert der lifePoints.
		// Bei negativem Uebergabewert: Nichts tun.
	}

	public void removeLifePoints(int lifePoints) {
		// TODO
		// Zieht den uebergebenen Wert von den aktuellen lifePoints ab.
		// Bei negativem Uebergabewert: Nichts tun.
	}

	public void rest() {
		// TODO
		// Setzt lifePoints und mana auf den Maximalwert.
	}

	public AwesomWeapon getWeapon() {
		// TODO
		// gibt die aktuelle Weapon zurueck.
		return null;
	}

	public void setWeapon(AwesomWeapon weapon) {
		// TODO
		// setzt die Weapon des Avatars auf die uebergebene weapon
	}

	public void attack(AwesomAvatar avatar) {
		// TODO
		// Diese fuehrt eine Attacke mit der Waffe des Spielers auf den
		// uebergebenen avatar aus.
		// Sollten nach der Attacke die lifePoints des Angegriffenen auf 0 oder darunter liegen, so
		// wird dessen experience zur experience des Angreifers hinzuaddiert...
		// ...und falls die Waffe des Gegners sowohlmehr Schaden anrichten kann,
		// als auch weniger abgenutzt ist,
		// wird die eigene Waffe durch diese ausgetauscht und die Leiche erhaelt
		// die schwaechere Waffe (Leichenfleddern mit Zuruecklegen).
	}

	public void castSpell(AwesomAvatar avatar, AwesomSpell spell) {
		// TODO
		// Der uebergebene avatar ist der Avatar, auf den der Spell ziehlt.
		// Ein spell kostet mana. Ist nicht genuegend mana vorhanden, so wird
		// der spell nicht ausgefuehrt bzw. abgebrochen.
		// Wenn genuegend mana vorhanden ist, wird die benoetigte Menge mana vom
		// aktuellen mana des ausfuehrenden Avatars subtrahiert.
		// Zaubersprueche treffen die Weapon des Gegners und reduzieren deren
		// wear um die vom spell angerichtete damage.
	}

	public void levelUp() {
		// TODO
		// Erhoeht die aktuellen Werte von strength und intelligence um jeweils
		// einen Punkt.
	}

}
