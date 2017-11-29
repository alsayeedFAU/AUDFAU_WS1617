import java.util.Comparator;

public class PunktVergleicher implements Comparator<Punkt> {

	Punkt z = new Punkt(0, 0);
	
	public PunktVergleicher(Punkt z) {
		if(z != null){
			this.z = z;
		}
	}
	
	@Override
	public int compare(Punkt arg0, Punkt arg1) {
		if(arg0.getX() == arg1.getX()){
			return 0;
		}else{
			return (Math.abs(arg0.getX()-z.getX())) - Math.abs((arg1.getX()-z.getX()));
		}
	}
	
	public boolean equals(Object obj){
		return this == obj;
	}

}
