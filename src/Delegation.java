
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
		int a1=1;
		int a2=2;
		int f();
		int p(int m);
		int q(int m);
		int get_a1();
		int get_a2();
	}
	interface IB extends IA{
		int b1=10;
		int b2=20;
		int g();
		int p(int m);
		int q(int m);
		int get_b1();
		int get_b2();
	}
	interface IC extends IB{
		int r();
		int p(int m);
		int q(int m);
	}
	interface ID extends IB{
		int p(int m);
		int r();
	}
	
	class A2 implements IA{	
		IA this1;
		public A2(IA ia){
			this1 = ia;
		}
		public int f() {
			return get_a1() + p(100) + q(100);
		}
		public int p(int m){
			return this1.p(m);
		}
		public int q(int m){
			return this1.q(m);
		}
		public int get_a1(){
			return IA.a1;
		}
		public int get_a2(){
			return IA.a2;
		}
		
	}
	class B2 implements IB{
		IA this1;
		public B2(IA b){
			this1 = b;
		}
		@Override
		public int get_a1() {
			// TODO Auto-generated method stub
			return 0;
		}
		@Override
		public int get_a2() {
			// TODO Auto-generated method stub
			return 0;
		}
		@Override
		public int g() {
			// TODO Auto-generated method stub
			return 0;
		}
		@Override
		public int p(int m) {
			// TODO Auto-generated method stub
			return 0;
		}
		@Override
		public int q(int m) {
			// TODO Auto-generated method stub
			return 0;
		}
		@Override
		public int get_b1() {
			// TODO Auto-generated method stub
			return 0;
		}
		@Override
		public int get_b2() {
			// TODO Auto-generated method stub
			return 0;
		}
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

 