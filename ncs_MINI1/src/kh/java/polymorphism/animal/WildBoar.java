package kh.java.polymorphism.animal;

public class WildBoar extends Animal{
	public void Headbutt() {
		System.out.println("몸통 박치기!");
	}

	@Override
	public void attack() {
		Headbutt();
	}
}
