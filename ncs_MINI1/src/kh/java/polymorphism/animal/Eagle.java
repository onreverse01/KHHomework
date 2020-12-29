package kh.java.polymorphism.animal;

public class Eagle extends Animal implements Flyable{

	@Override
	public void fly() {
		System.out.println("날아라 꽈악!");
		
	}

	@Override
	public void attack() {
		System.out.println("대머리 독수리의 부리공격!");
	}

}
