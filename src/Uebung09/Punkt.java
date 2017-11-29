import java.lang.reflect.Array;
import java.util.Arrays;

public class Punkt extends AbstrakterPunkt implements Comparable<Punkt> {

	public Punkt(int x, int y) {
		super(x, y);
	}

	@Override
	public double euklid(Punkt that) {
		if(that == null){
			return Double.NaN;
		}
		return Math.sqrt(Math.pow(this.getX()-that.getX(), 2)+Math.pow(this.getY()-that.getY(), 2));
	}

	@Override
	public int manhattan(Punkt that) {
		// TODO Auto-generated method stub
		return Math.abs(this.getX()-that.getX())+Math.abs(this.getY()-that.getY());
	}
	
	@Override
	public boolean equals(Object that) {
		Punkt that1 = (Punkt) that;
		return that1.getX() == this.getX() && that1.getY() == this.getY();
	}

	public int compareTo(Punkt that) {
		Punkt ursprung = new Punkt(0, 0); 
		if(ursprung.euklid(this)< ursprung.euklid(that)){
			return -1;
		}else if(ursprung.euklid(this)> ursprung.euklid(that)){
			return 1;
		}
		
		if(this.getX() == that.getX()){
			return this.getY() < that.getY()? this.getY() - that.getY() : Math.abs(that.getY() - this.getY());
		}
		return this.getX() < that.getX()? this.getX() - that.getX() : Math.abs(that.getX() - this.getX());
		
	}

	public static void sortiere(Punkt[] a) {
		Arrays.sort(a);
		
	}

	public static void sortiereZentrum(Punkt[] a, Punkt z) {
		Arrays.sort(a, new PunktVergleicher(z));
	}

}
