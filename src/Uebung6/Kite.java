public class Kite extends Quadrilateral {
	protected double e1;
	protected double e2;
	protected double f;

	public Kite(Vec2D pP, double e1, double e2, double f) {
		super(pP, null, null, null);
		this.e1 = e1;
		this.e2 = e2;
		this.f = f;

	}

	@Override
	public double area() {
		return (f*(e1+e2))/2;
	}

	@Override
	public double perimeter() {
		// TODO Auto-generated method stub
		double a1 = Math.pow(f/2, 2);
		double b1 = Math.pow(e1, 2);
		double b2 = Math.pow(e2, 2);
		return 2*(Math.sqrt(a1+b1)+Math.sqrt(a1+b2));
	}



}
