
abstract class AbsPoint {
	public double distance(AbsPoint p2) {
		double deltax = xcoord() - p2.xcoord();
		double deltay = ycoord() - p2.ycoord();
		return Math.sqrt(deltax * deltax + deltay * deltay);
	}

	protected abstract double xcoord();
	protected abstract double ycoord();
}

class Cartesian extends AbsPoint {
	public Cartesian(double a, double b) {
		x = a;
		y = b;
	}

	public double xcoord() {
		return x;
	}

	public double ycoord() {
		return y;
	}

	double x, y;
}

class Polar extends AbsPoint {
	public Polar(double a, double b) {
		r = a;
		theta = b;
	}

	public double xcoord() {
		return r * Math.cos(theta);
	}

	public double ycoord() {
		return r * Math.sin(theta);
	}

	double r, theta;
}

// ---------SIMULATING INHERITANCE BY DELEGATION ------
// ----------- FOLLOWING A SYSTEMATIC APPROACH --------
// ------------ (Lecture 8, slides 28-30 --------------

interface I1 {
	double distance(I1 p);
	double xcoord();    // abstract 
	double ycoord();    // abstract
}

interface I11 extends I1 {
	double xcoord();
	double ycoord();
}

interface I12 extends I1 {
	double xcoord();
	double ycoord();
}


class AbsPoint2 implements I1 {
	public AbsPoint2(I1 p) {
		this2 = p;
	}

	public double distance(I1 p2) {
		double deltax = xcoord() - p2.xcoord();
		double deltay = ycoord() - p2.ycoord();
		return Math.sqrt(deltax * deltax + deltay * deltay);
	}
	
	public double xcoord() {
		return this2.xcoord();
	}

	public double ycoord() {
		return this2.ycoord();
	}
	
	I1 this2;
}

class Cartesian2 implements I11 {
	public Cartesian2(double a, double b) {
		x = a;
		y = b;
		super2 = new AbsPoint2(this);
	}

	public double xcoord() {
		return x;
	}

	public double ycoord() {
		return y;
	}

	public double distance(I1 p2) {
		return super2.distance(p2);
	}

	double x, y;
	I1 super2;
}

class Polar2 implements I12 {
	public Polar2(double a, double b) {
		r = a;
		theta = b;
		super2 = new AbsPoint2(this);
	}

	public double xcoord() {
		return r * Math.cos(theta);
	}

	public double ycoord() {
		return r * Math.sin(theta);
	}

	public double distance(I1 p2) {
		return super2.distance(p2);
	}

	double r, theta;
	I1 super2;
}

// -------------------------------

public class PointDriver {
	public static void main(String[] args) {

		
		 Cartesian p1 = new Cartesian(0.0, 0.0); 
		 Polar p2 = new Polar(1.0, 1.5707);
		 
		 double d1 = p1.distance(p2); 
		 System.out.println(d1);
		 
		 I11 q1 = new Cartesian2(0.0, 0.0);
		 I12 q2 = new Polar2(1.0, 1.5707);

		 double d2 = q1.distance(q2);
		 System.out.println(d2);

	}
}
