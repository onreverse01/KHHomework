package workshop4;

public class Test05 {

	public static void main(String[] args) {
		int num = Integer.parseInt(args[0]);
		int sum = 0;
		if(num>=1&&num<=5) {
			for(int i=num; i<10; i++) {
				if(i%3==0) {
					continue;
				}
				else if(i%5==0) {
					continue;
				}
				System.out.print(i);
				System.out.print(i!= 8? "+":" ");
				sum+=i;
			}
			System.out.println();		
			System.out.println("결과: "+sum);
		}
	}
}
