package kh.java.polymorphism.animal;

public class Cat extends Animal implements Runnable, Bitable{

	public void punch() {
		System.out.println("냥냥 펀치!");
	}
	
	@Override
	public void attack() {
		punch();
	}
	
	@Override
	public void say() {
		System.out.println("안녕한다냥!");
	}
	
	@Override
	public void run() {
		System.out.println("달린다냥!");
	}
	
	@Override
	public void bite(String sound) {
		System.out.println("미스묘가 뭅니다~ "+sound);
	}

}
