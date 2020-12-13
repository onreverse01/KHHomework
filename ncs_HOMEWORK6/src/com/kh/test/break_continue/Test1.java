package com.kh.test.break_continue;

import java.util.Scanner;

public class Test1 {
	public static void main(String[] args) {
		Test1 t1 = new Test1();
//		t1.test1();
		t1.test2();
	}
	public void test1() {
		Scanner sc = new Scanner(System.in);
		System.out.print("정수입력:");
		int num = sc.nextInt();
		int mul = 1;
		
		//while문
		int i = 1;
		while(i<=num) {
			mul*=i;
			i+=2;
		}
		System.out.println(mul);
		
		//for문
//		for(i =1; i<=num; i+=2) {
//			mul*=i;
//		}
//		System.out.println(mul);
		
	}
	public void test2() {
		Scanner sc = new Scanner(System.in);
		System.out.print("정수를 하나 입력하세요.=>");
		int num1 = sc.nextInt();
		System.out.print("다른 정수를 하나 입력하세요.=>");
		int num2 = sc.nextInt();
		int sum = 0;
		
		//while문
		if(num1<=num2) {
			System.out.println("====================");
			System.out.print(num1+"부터 "+num2+"까지의 홀수의 합은 ");
			while(num1<=num2) {
				if(num1%2==1) {
					sum+=num1;
				}
				num1++;
			}
			System.out.println(sum+"입니다.");
		}
		else if(num2<=num1) {
			System.out.println("====================");
			System.out.print(num2+"부터 "+num1+"까지의 홀수의 합은 ");
			while(num2<=num1) {
				if(num2%2==1) {
					sum+=num2;
				}
				num2++;
			}
			System.out.println(sum+"입니다.");
		}
	}
}
