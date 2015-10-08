
	public class Delegation {
		public static void main(String args[]) {
			C c = new C();
			System.out.println(c.r());
			D d = new D();
			System.out.println(d.r());
			
			C2 c2 = new C2();
			System.out.println(c2.r());
			D2 d2 = new D2();
			System.out.println(d2.r());	
		}
	}

	interface IA{
	}
	interface IB extends IA{
	}
	interface IC extends IB{
	}
	interface ID extends IB{
	}
	
	class A2 implements IA{
		
	}
	class B2 implements IB{
		
	}
	class C2 implements IC{
		public int r(){
			return 0;
		}
	}
	class D2 implements ID{
		public int r(){
			return 0;
		}
	}
	
	 abstract class A {
		int a1 = 1;
		int a2 = 2;

		public int f() {
			return a1 + p(100) + q(100);
		}

        protected abstract int p(int m);
        protected abstract int q(int m);
	 }
	
	 
	 class B extends A {
		int b1 = 10;
		int b2 = 20;

		public int g() {
			return f() + this.q(200);
		}

		public int p(int m) {
			return m + b1;
		}

		public int q(int m) {
			return m + b2;
		}
	}
	 

	class C extends B {
		int c1 = 100;
		int c2 = 200;

		public int r() {
			return f() + g() + c1;
			}
		
		public int p(int m) {
			return super.p(m) + super.q(m) + c2;
		}
		
		public int q(int m) {
			return m + a2 + b2 + c2;
		}
	}

	class D extends B {
		int d1 = 300;
		int d2 = 400;
		
		public int p(int m) {
			return m + a1 + b1 + d1;
			
		}
		public int r() {
			return f() + g() + d2;
		}

	}

 