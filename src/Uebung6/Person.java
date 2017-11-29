public class Person {
	public String nachName;
	public String vorName;
	protected java.util.Date geburtsDatum;
	protected GeschlechtsZugehoerigkeit geschlechtsZugehoerigkeit;

	public Person(java.util.Date gebDat, GeschlechtsZugehoerigkeit geschlZuge) {

	}
}

interface GeschlechtsZugehoerigkeit {

}

abstract class Maennlich implements GeschlechtsZugehoerigkeit {

}

abstract class Weiblich implements GeschlechtsZugehoerigkeit {

}

abstract class Unbestimmt implements GeschlechtsZugehoerigkeit {

}

abstract class Beziehung {
	protected java.util.Date beginn;
	protected java.util.Date ende;
	protected Person partnerA;
	protected Person partnerB;
	private Person[] nachkommen;

	public void beenden(java.util.Date ende) {

	}
}

class Partnerschaft extends Beziehung {
	public Partnerschaft(Person partnerA, Person partnerB, java.util.Date beginn) {
	}
}

class Affaere extends Beziehung {
	private Beziehung beziehung;
}

class Ehe extends Partnerschaft {

	public Ehe(Person partnerA, Person partnerB, java.util.Date beginn) {
		super(partnerA, partnerB, beginn);
	}
}
