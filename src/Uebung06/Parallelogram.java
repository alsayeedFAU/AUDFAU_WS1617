public class Parallelogram extends Quadrilateral {
	protected double a;
	protected double h;
	protected double alpha;

	public Parallelogram(Vec2D pP, double a, double h, double alpha) {
		super(pP,null,null,null);
		this.a = a;
		this.h = h;
		this.alpha = alpha;
	}

	@Override
	public double area() {
		// TODO Auto-generated method stub
		return a * h;
	}

	@Override
	public double perimeter() {

		double b = area()/(Math.sin(Math.toRadians(180-alpha))*a);		
		return 2*(a+b);
	}

}
