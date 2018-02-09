
public class StarTrekLift {
	public String name;
	private TurboShaft[] turboShafts;
	private Control control;

	public StarTrekLift(String name, TurboShaft[] turboShafts, Control control) {
		this.name = name;
		this.turboShafts = turboShafts;
		this.control = control;
	}
}

class TurboShaft {
	public String name;
	private Deck deck;
	protected Deck[] decks;
	private Motor motor;
	private Cabine cabine;

	public TurboShaft(String name, Deck[] decks) {
		this.name = name;
		this.decks = decks;

	}

	public void goTo(Deck destination) {

	}

	public Deck currentPosition() {
		return this.deck;
	}

	public Deck[] getReachableDecks() {
		return new Deck[] {};
	}
}

class Motor {
	public static final String MOTOR_TYPE = "Linear induction";
	private TurboShaft turboShaft;

	public void start(int direction, float speed) {

	}

	public void stop() {

	}
}

class Control {
	protected TurboShaft[] turboShafts;
	private StarTrekLift starTrekLift;

	public Control(TurboShaft[] turboShafts) {
		this.turboShafts = turboShafts;

	}
}

class Deck {
	public String name;
	public boolean request;

	public Deck(String name) {
		this.name = name;

	}
}

class Cabine {
	public boolean doorState;
	public Deck position;
	public Deck[] destinations;
	private TurboShaft turboShaft;

	public void changeDoorState(boolean doorState) {
	}
}
