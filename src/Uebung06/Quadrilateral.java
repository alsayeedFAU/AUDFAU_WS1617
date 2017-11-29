import java.util.ArrayList;

public abstract class Quadrilateral {

	protected Vec2D pA;
	protected Vec2D pB;
	protected Vec2D pC;
	protected Vec2D pD;

	public Quadrilateral(Vec2D pA, Vec2D pB, Vec2D pC, Vec2D pD) {
		this.pA = pA;
		this.pB = pB;
		this.pC = pC;
		this.pD = pD;
	}

	public static double area(Quadrilateral[] vs) {
		if (vs == null) {
			return 0;
		}
		double tmp = 0;
		for (int i = 0; i < vs.length; i++) {
			if (vs[i] != null) {
				tmp += vs[i].area();
			}
		}

		return tmp;
	}

	public static Trapezium[] parFilter(Quadrilateral[] vs) {
		if (vs == null) {
			return new Trapezium[0];
		}
		ArrayList<Quadrilateral> tmp = new ArrayList<>();
		for (int i = 0; i < vs.length; i++) {
			if (vs[i].getAB() == vs[i].getBC() || vs[i].getAB() == vs[i].getCD() || vs[i].getAB() == vs[i].getDA()) {
				tmp.add(vs[i]);
			}
		}
		return tmp.toArray(new Trapezium[tmp.size()]);
	}

	public abstract double area();

	public abstract double perimeter();

	public Vec2D getA() {
		return pA;
	}

	public Vec2D getB() {
		return pB;
	}

	public Vec2D getC() {
		return pC;
	}

	public Vec2D getD() {
		return pD;
	}

	public double getAB() {
		return Math.sqrt(Math.pow(pA.getX() - pB.getX(), 2) + Math.pow(pA.getY() - pB.getY(), 2));
	}

	public double getBC() {
		return Math.sqrt(Math.pow(pB.getX() - pC.getX(), 2) + Math.pow(pB.getY() - pC.getY(), 2));
	}

	public double getCD() {
		return Math.sqrt(Math.pow(pC.getX() - pD.getX(), 2) + Math.pow(pC.getY() - pD.getY(), 2));
	}

	public double getDA() {
		return Math.sqrt(Math.pow(pD.getX() - pA.getX(), 2) + Math.pow(pD.getY() - pA.getY(), 2));
	}
}
