
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
		int c1 = 100;
		int c2 = 200;
		int r();
		int p(int m);
		int q(int m);
		int get_c1();
		int get_c2();
	}
	interface ID extends IB{
		int d1 = 300;
		int d2 = 400;
		int p(int m);
		int r();
		int get_d1();
		int get_d2();
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
		IA super1;
		public B2(IB ib){
			super1 = new A2(ib);
		}
		@Override
		public int get_a1() {
			// TODO Auto-generated method stub
			return super1.get_a1();
		}
		@Override
		public int get_a2() {
			// TODO Auto-generated method stub
			return super1.get_a2();
		}
		@Override
		public int g() {
			// TODO Auto-generated method stub
			return f() + q(200);
		}
		@Override
		public int p(int m) {
			// TODO Auto-generated method stub
			return m + IB.b1;
		}
		@Override
		public int q(int m) {
			// TODO Auto-generated method stub
			return m + IB.b2;
		}
		@Override
		public int get_b1() {
			// TODO Auto-generated method stub
			return IB.b1;
		}
		@Override
		public int get_b2() {
			// TODO Auto-generated method stub
			return IB.b2;
		}
		@Override
		public int f() {
			// TODO Auto-generated method stub
			return super1.f();
		}
	}
	class C2 implements IC{
		
		IB super2;
		public C2(){
			super2 = new B2(this);
		}
		public int r(){
			return f()+ g() + IC.c1;
		}

		@Override
		public int g() {
			// TODO Auto-generated method stub
			return super2.g();
		}

		@Override
		public int get_b1() {
			// TODO Auto-generated method stub
			return super2.get_b1();
		}

		@Override
		public int get_b2() {
			// TODO Auto-generated method stub
			return super2.get_b2();
		}

		@Override
		public int f() {
			// TODO Auto-generated method stub
			return super2.f();
		}

		@Override
		public int get_a1() {
			// TODO Auto-generated method stub
			return super2.get_a1();
		}

		@Override
		public int get_a2() {
			// TODO Auto-generated method stub
			return super2.get_a2();
		}

		@Override
		public int p(int m) {
			// TODO Auto-generated method stub
			return super2.p(m) + super2.q(m) + IC.c2;
		}

		@Override
		public int q(int m) {
			// TODO Auto-generated method stub
			return m + get_a2() + get_b2() + get_c2();
		}

		@Override
		public int get_c1() {
			// TODO Auto-generated method stub
			return IC.c1;
		}

		@Override
		public int get_c2() {
			// TODO Auto-generated method stub
			return IC.c2;
		}
	}
	class D2 implements ID{
		IB super2;
		public D2() {
			super2 = new B2(this);
		}
		public int r(){
			return f() + g() + ID.d2;
		}

		@Override
		public int g() {
			// TODO Auto-generated method stub
			return super2.g();
		}

		@Override
		public int q(int m) {
			// TODO Auto-generated method stub
			return super2.q(m);
		}

		@Override
		public int get_b1() {
			// TODO Auto-generated method stub
			return super2.get_b1();
		}

		@Override
		public int get_b2() {
			// TODO Auto-generated method stub
			return super2.get_b2();
		}

		@Override
		public int f() {
			// TODO Auto-generated method stub
			return super2.f();
		}

		@Override
		public int get_a1() {
			// TODO Auto-generated method stub
			return super2.get_a1();
		}

		@Override
		public int get_a2() {
			// TODO Auto-generated method stub
			return super2.get_a2();
		}

		@Override
		public int p(int m) {
			// TODO Auto-generated method stub
			return m + get_a1() + get_b1() + ID.d1;
		}

		@Override
		public int get_d1() {
			// TODO Auto-generated method stub
			return ID.d1;
		}

		@Override
		public int get_d2() {
			// TODO Auto-generated method stub
			return ID.d2;
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

 