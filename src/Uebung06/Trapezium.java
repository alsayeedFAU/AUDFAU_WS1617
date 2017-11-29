public class Trapezium extends Quadrilateral {
	protected double a;
	protected double c;
	protected double h;
	public Trapezium(Vec2D pP, double a, double c, double h) {
		super(pP, new Vec2D(pP.getX()+a, pP.getY()), new Vec2D((pP.getX()+a-((a-c))/2), pP.getY()+h), new Vec2D((pP.getX()+((a-c))/2), pP.getY()+h));
		this.a = a;
		this.c = c;
		this.h = h;

	}

	@Override
	public double area() {
		// TODO Auto-generated method stub
		return ((a+c)*h)/2;
	}

	@Override
	public double perimeter() {
		// TODO Auto-generated method stub
		return a+c+getBC()*2;
	}


}
