package workshop4;

public class Test06 {

	public static void main(String[] args) {
		if(args.length != 2) {
			System.out.println("다시 입력 하세요");
			return;
		}
		
		int num = Integer.parseInt(args[0]);
		int num2 = Integer.parseInt(args[1]);
		int numR = 0;
		int[][] array = new int [2][2];
		double sum = 0, cnt = 0;
		double avg = 0;
		if(num>=1&&num<=5&&num2>=1&&num2<=5) {
			for(int i = 0; i<array.length; i++) {
				for(int j=0; j<array[i].length; j++) {
					if(i==0) {
						if(j==0) {
							array[i][j]=num;
							System.out.print(num+" ");
							sum+=num;
							cnt++;
						}
						if(j==1) {
							array[i][j]=num2;
							System.out.println(num2);
							sum+=num2;
							cnt++;
						}
					}
					else {
						numR = (int)(Math.random()*5)+1;
						array[i][j]=numR;
						System.out.print(numR+" ");
						sum+=numR;
						cnt++;
					}
				}
			}
			avg = sum/cnt;
			System.out.println("\n");
			System.out.println("sum="+ sum);
			System.out.printf("avg=%.1f",avg);
			
		}
		else {
			System.out.println("숫자를 확인 하세요");
		}
		
	}

}
