package kh.java.polymorphism.animal;

public class AnimalMain {
	
	public static void main(String[] args) {
		AnimalMain am = new AnimalMain();
//		am.test1();
//		am.test2();
//		am.test3();
		
		//다형성의 활용
		//1.매개변수 선언부
//		am.test4();
		
		//2. 리턴값 처리
//		am.test5();
		
		//동적바인딩
//		am.test6();
		
		//추상메소드
//		am.test7();
		
		//인터페이스
//		am.test8();
		am.test8_5();
	}
	
	public void test8_5() {
		Flyable f = new Eagle();
		
		f.fly();
		((Eagle)f).attack();
	}
	
	/**
	 * 인터페이스는 객체화 할 수 없다.
	 * 인터페이스 역시 부모타입으로 다형성을 적용할 수 있다.
	 * 
	 */
	public void test8() {
//		Runnable r = new Runnable(); //Cannot instantiate the type Runnable
		
		Runnable r1 = new Dog();
		Runnable r2 = new Cat();
		
		r1.run();
		r2.run();
		
//		r1.say(); // Animal 재작성메소드 say
//		r1.attack(); // Animal의 재작성메소드 attack
//		r1.kick(); // Dog의 kick
		
		((Dog)r1).say();
		((Dog)r1).attack();
		((Dog)r1).kick();
		
		//상수
		System.out.println(Runnable.LEGS);
		
		Bitable b1 = new Dog();
		Bitable b2 = new Cat();
		b1.bite("왈왈컹컹!");
		b2.bite("왜애애앵~!");
		
		System.out.println("-----------------");
		
		Dog d1 = (Dog)b1;
		d1.attack();
		d1.bite("왈왈!");
		d1.kick();
		d1.attack();
		d1.say();
		
		System.out.println("-----------------");
		
		Animal a1 = d1;
		a1.attack();
		a1.say();

	}
	
	/**
	 * 추상클래스 Animal
	 */
	public void test7() {
//		Animal a = new Animal(); //Cannot instantiate the type Animal (오른쪽이 문제)
		Animal a = new Cat();
		Animal b = new Dog();
		Animal c = new WildBoar();
		
		//추상클래스 Animal을 상속한 자식클래스는
		//무조건 추상메소드 attack구현을 보장받는다.
		a.attack();
		b.attack();
		c.attack();
		
	}
	
	/**
	 * 동적바인딩
	 * 1. 상속 & 부모클래스의 메소드 오버라이딩
	 * 2. 자식객체를 부모타입으로써 제어, 해당 메소드 호출
	 * 자동으로 자식타입의 재작성한 메소드가 호출된다.
	 * 다운캐스팅 없이 자식메소드 사용 가능
	 * 
	 * 정적바인딩
	 * 동일한 타입의 메소드를 호출한다.
	 * 
	 */
	public void test6() {
		Animal a1 = new Cat();
		Animal a2 = new Dog();
		
		a1.say();
		a2.say();
		
		//다형성을 이용한 attack
		Animal[] arr = new Animal[100];
		for(int i =0; i<arr.length; i++) {
			if(i % 2 ==0) {
				arr[i]= makeDog();
			}
			else
				arr[i]=makeCat();
		}
		
		//실행
		for(Animal anm : arr)
			anm.attack();//동적바인딩 자식크래스의 오버라이드한 메소드가 호출됨. (animal의 메소드 atttack이다.)
	}
	
	
	/**
	 * 리턴값을 다형성 이용해서 처리하기
	 */
	public void test5() {
		Animal Cat = makeCat();
		Animal Dog = makeDog();
//		makeCat();
		Animal[] arr = new Animal[100];
		for(int i =0; i<arr.length; i++) {
			if(i % 2 ==0) {
				arr[i]= makeDog();
			}
			else
				arr[i]=makeCat();
		}
		
		for(Animal anm : arr)
			attack(anm);
		
	}
	
	public Dog makeDog() {
		return new Dog();
	}
	
	public Cat makeCat() {
		return new Cat();
	}
	
	/**
	 * 매개변수 선언부에서 다형성 활용하기
	 */
	public void test4() {
		Cat cat = new Cat();
		Dog dog = new Dog();
		
		attack(cat);//메소드 호출 오버로딩
		attack(dog);
	}
	
	public void attack(Animal animal) {
		if(animal instanceof Dog) {
			((Dog)animal).kick();
		}
		else if(animal instanceof Cat) {
			((Cat)animal).punch();
		}
	}
	
//	public void attack(Cat cat) {
//		cat.punch();
//	}
//	
//	public void attack(Dog dog) {
//		dog.kick();
//	}
	
	/**
	 * 부모타입의 배열에 자식객체를 담을 수 있다.
	 * 
	 */
	public void test3() {
		Animal[] arr = new Animal[3];
		arr[0] = new Cat();
		arr[1] = new Dog();
//		arr[2] = new Animal(); //instantiate객체화할 수 없다.
		arr[2] = new Cat();
		
		System.out.println(arr[0] instanceof Cat); //true
		System.out.println(arr[0] instanceof Dog); //false
		System.out.println(arr[0] instanceof Animal); //true
		System.out.println(arr[0] instanceof Object); //true
		
		//Animal anml = new Dog();
		for(Animal anml : arr) {
			anml.say();
			
			//instanceof 해당타입의 객체인 경우 true 리턴
			//true가 리턴된 경우, 해당타입으로의 안전한 형변환 보장
			//Cat객체인 경우
			if(anml instanceof Cat) {
				((Cat)anml).punch();
			}
			//Dog객체인 경우
			else if(anml instanceof Dog) {
				((Dog)anml).kick();
			}
//			((Cat)anml).punch();//형변환
//			((Dog)anml).kick();// cat 객체를 dog로 억지로 변환하려다가 오류가 발생함
		}
	}
	
	/**
	 * 형변환
	 * - upcasting 부모타입으로 형변환. 자동(암묵적)형변환
	 * - downcasting 자식타입으로 형변환. 수동(명시적)형변환
	 */
	public void test2() {
		Animal animal = new Dog(); //업캐스팅 upcasting
		Dog dog = (Dog)animal; //다운캐스팅 downcasting
		
		dog.kick();
	}
	
	/**
	 * 자식객체를 부모타입의 참조변수에 담아 제어할 수 있다.
	 * 단, 자식클래스에 선언된 자원은 사용할 수 없다.
	 * 
	 */
	public void test1() {
		Cat cat = new Cat();
		cat.say();
		cat.punch();
		
		Animal animal1 = cat;
		System.out.println(cat == animal1); //주소값 비교 (담겨있는 변수명만 다르다.)
		animal1.say();
//		animal1.punch();//부모타입 변수에 담으면 자식클래스의 선언된 자원(필드,메소드)를 사용할 수 없다.
		
		Object obj1 = cat;
//		obj1.say();
//		obj1.punch();
		System.out.println(obj1.hashCode());
		
		System.out.println("---------------");
		//다시 자식타입의 변수에 담으면 자식타입의 자원 사용 가능
		Animal animal2 = (Animal)obj1;
		animal2.say();
		((Animal)obj1).say(); //괄호를 넣아야 하는 이유는 .연산자가 우선순위가 높기 때문이다.
		
		Cat cat2 = (Cat)animal2;
		cat2.punch();
		((Cat)obj1).punch();
//		((Dog)obj1).kick(); //형변환 오류 서로가 남남
		
	}
	
}
