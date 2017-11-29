
public class Polymorphie {
	public static void main(String[] args) {
		Bar rq = new Qux();
		Foo oq = new Qux();
		Baz zq = new Qux();
		Qux xq = new Qux();
		Bar rx = new Xyzzy();
		/* (01) */ print(rq.foo);
		/* (02) */ print(oq.garply(666));
		/* (03) */ print(oq.foo);
		/* (04) */ print(zq.garply(4711));
		/* (05) */ print(zq.foo);
		/* (06) */ print(zq.grault(Integer.MAX_VALUE * 42));
		/* (07) */ print(zq.grault("grault"));
		/* (08) */ print(xq.foo);
		/* (09) */ print(rx.garply(1));
		/* (10) */ print(rq.garply(2));
	}

	public static void print(Object o) {
		System.out.println(o.toString());
	}
}

interface Bar {
	long foo = 999L;

	String garply(long Foo);
}

interface Foo {
	String foo = "foorz";

	String garply(int foo);
}

abstract class Baz implements Foo {
	int bar = 0x815_4711;

	public String garply(int wibble) {
		return Integer.toString(bar);
	}

	public static String grault(int wobble) {
		return "BazGraultInt";
	}

	public String grault(String wubble) {
		return "BazGraultString";
	}
}

class Qux extends Baz implements Bar{
	static String foo = "QuxFoo";
	String bar = "StringBarBar";
	private boolean waldo = false;
	
	public String garply(long wibble){
		return Long.toString(wibble);
	}
	
	public static String grault(long wobble){
		return "QuxGraultLong";
	}
	
	public String grault(String wubble){
		return "QuxGraultString";
	}
}

class Xyzzy implements Bar{
	String foo = "FooxFoox";
	
	public String garply(int blubb){
		return "maeh";
	}
	public String garply(long bla){
		return "blablubb";
	}
	
}
