package kh.java.polymorphism.animal;

public class Dog extends Animal implements Runnable, Bitable{

	public void kick() {
		System.out.println("견 킥 날림!");
	}
	
	@Override
	public void attack() {
		kick();
	}
	
	@Override
	public void say() {
		System.out.println("왈왈컹컹멍멍!");
	}
	
	@Override
	public void run() {
		System.out.println("달린다멍!");
	}
	
	@Override
	public void bite(String sound) {
		System.out.println("미스터 견은 물어요~ "+sound);
	}

}
