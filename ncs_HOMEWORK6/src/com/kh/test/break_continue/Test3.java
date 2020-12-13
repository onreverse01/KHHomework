package com.kh.test.break_continue;

import java.util.Scanner;

public class Test3 {
	public static void main(String[] args) {
		Test3 t3 = new Test3();
		t3.test();
	}
	
	public void test() {
		Scanner sc = new Scanner(System.in);
		int num = 0;
		int i = 2;

		do {
			System.out.print("2보다 큰 정수를 입력하세요.");
			num = sc.nextInt();
			if(num<2) {
				System.out.println("잘못 입력하셨습니다.");
				continue;
			}
			for(i = 2; i<num; i++) {
				if(num%i==0) {
					System.out.println("소수가 아니다.");
					return;
				}
			}
			System.out.println("소수다");
		}while(num<2);

	}
}
