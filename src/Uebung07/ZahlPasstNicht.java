public class ZahlPasstNicht extends IndexOutOfBoundsException {
	private static final long serialVersionUID = 1L;
	private final int min;
	private final int max; 
	private final int ist;
	public ZahlPasstNicht(int min,int max,int ist) {
		this.min = min;
		this.max = max;
		this.ist = ist;
		
	}

	public int untereGrenze() {
		// TODO Auto-generated method stub
		return min;
	}

	public int obereGrenze() {
		// TODO Auto-generated method stub
		return max;
	}

	public int istWert() {
		// TODO Auto-generated method stub
		return ist;
	}
	
	public String getMessage(){
		return "ZahlPasstNicht: Expected [" + min + "..." + max + "] but was <" + ist + ">";
	}
}
