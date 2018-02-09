public class AwesomAvatar {
	// TODO: Hier die noetigen Datenstrukturen anlegen
	private int strength;
	private int intelligence;
	private int experience;
	private int lifePoints;
	private int mana;
	private AwesomWeapon weapon;

	public AwesomAvatar(int strength, int intelligence, int experience, AwesomWeapon weapon) {
		// TODO
		setStrength(strength);
		setIntelligence(intelligence);
		setExperience(experience);
		setWeapon(weapon);
		setLifePoints(getMaxLifePoints());
		setMana(getMaxMana());
	}

	public int getIntelligence() {
		// TODO
		// Gibt den aktuellen Wert von intelligence zurueck.
		return this.intelligence;
	}

	public void setIntelligence(int intelligence) {
		// TODO
		// Setzt intelligence auf den uebergebenen Wert. Positive wie negative
		// Werte sind moeglich.
		this.intelligence = intelligence;
	}

	public int getStrength() {
		// TODO
		// Gibt den aktuellen Wert von strength zurueck.
		return this.strength;
	}

	public void setStrength(int strength) {
		// TODO
		// Setzt strength auf den uebergebenen Wert. Positive wie negative Werte
		// sind moeglich.
		this.strength = strength;
	}

	public int getExperience() {
		// TODO
		// Gibt den aktuellen Wert von experience zurueck.
		return this.experience;
	}

	public void setExperience(int experience) {
		// TODO
		// Setzt experience auf den uebergebenen Wert. Positive wie negative
		// Werte sind moeglich.
		this.experience = experience;
	}
	

	public int getLifePoints() {
		// TODO
		// Gibt den aktuellen Wert von lifepoints zurueck.
		return this.lifePoints;
	}

	public void setLifePoints(int lifePoints) {
		// TODO
		// Setzt lifePoints auf den uebergebenen Wert, jedoch nicht groesser als
		// den Maximalwert. Falls der uebergebene Wert groesser ist als der
		// Maximalwert, so wird lifePoints auf den Maximalwert gesetzt.
		// Negative lifePoints hingegen sind moeglich.
		if ( getLifePoints() > getMaxLifePoints()){
			this.lifePoints = getMaxLifePoints();
		} else {
			this.lifePoints = lifePoints;
		}
	}

	public int getMana() {
		// TODO
		// Gibt den aktuellen Wert von mana zurueck.
		return this.mana;
	}

	public void setMana(int mana) {
		// TODO
		// Setzt mana auf den uebergebenen Wert, jedoch nicht groesser als den
		// Maximalwert. Falls der uebergebene Wert groesser ist als der
		// Maximalwert, so wird mana auf den Maximalwert gesetzt.
		// Negative mana hingegen sind moeglich.
		if ( mana+this.mana > getMaxMana()){
		this.mana = getMaxMana();
		} else {
			this.mana = mana;
		}
	}

	public int getMaxLifePoints() {
		// TODO
		// Gibt die maximale Anzahl an lifePoints zurueck.
		// Diese berechnen sich aus dem Produkt von experience und strength.
		return this.experience*this.strength;
	}

	public int getMaxMana() {
		// TODO
		// Gibt die maximal Anzahl an mana zurueck.
		// Diese berechnen sich aus dem Produkt von experience und intelligence.
		return this.experience*this.intelligence;
	}

	public void addLifePoints(int lifePoints) {
		// TODO
		// Fuegt den uebergebenen Wert zu den aktuellen lifePoints hinzu, jedoch
		// nicht groesser als den Maximalwert der lifePoints.
		// Bei negativem Uebergabewert: Nichts tun.
		if (lifePoints > 0){
			if (this.lifePoints+lifePoints > getMaxLifePoints()){
				this.lifePoints = getMaxLifePoints();
			}else {
				this.lifePoints += lifePoints;
			}
		}
	}

	public void removeLifePoints(int lifePoints) {
		// TODO
		// Zieht den uebergebenen Wert von den aktuellen lifePoints ab.
		// Bei negativem Uebergabewert: Nichts tun.
		if ( lifePoints > 0){
			this.lifePoints -= lifePoints;
		}
	}

	public void rest() {
		// TODO
		// Setzt lifePoints und mana auf den Maximalwert.
		this.lifePoints = getMaxLifePoints();
		this.mana = getMaxMana();
	}

	public AwesomWeapon getWeapon() {
		// TODO
		// gibt die aktuelle Weapon zurueck.
		return this.weapon;
	}

	public void setWeapon(AwesomWeapon weapon) {
		// TODO
		// setzt die Weapon des Avatars auf die uebergebene weapon
		this.weapon = weapon;
	}

	public void attack(AwesomAvatar avatar) {
		// TODO
		// Diese fuehrt eine Attacke mit der Waffe des Spielers auf den
		// uebergebenen avatar aus.
		// Sollten nach der Attacke die lifePoints des Angegriffenen auf 0 oder
		// darunter liegen, so
		// wird dessen experience zur experience des Angreifers hinzuaddiert...
		// ...und falls die Waffe des Gegners sowohlmehr Schaden anrichten kann,
		// als auch weniger abgenutzt ist,
		// wird die eigene Waffe durch diese ausgetauscht und die Leiche erhaelt
		// die schwaechere Waffe (Leichenfleddern mit Zuruecklegen).
		avatar.removeLifePoints(this.getWeapon().attack());
		if (avatar.getLifePoints() <= 0) {
			this.setExperience(getExperience() + avatar.getExperience());
			if (avatar.getWeapon().getDamage() > this.getWeapon().getDamage() && avatar.getWeapon().getWear() > this.getWeapon().getWear()) {
				AwesomWeapon tmp = this.getWeapon();
				this.setWeapon(avatar.getWeapon());
				avatar.setWeapon(tmp);
			}
			this.levelUp();

		}
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
		if (spell.getCost()<=this.mana){
			this.setMana(this.getMana()-spell.getCost());
			avatar.getWeapon().setWear(avatar.getWeapon().getWear()-spell.getDamage());
		}
	}

	public void levelUp() {
		// TODO
		// Erhoeht die aktuellen Werte von strength und intelligence um jeweils
		// einen Punkt.
		setStrength(getStrength()+1);
		setIntelligence(getIntelligence()+1);
	}

}
