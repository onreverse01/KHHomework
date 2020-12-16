package com.kh.test.nested.loop;

import java.util.Scanner;

public class Test1 {
	public static void main(String[] args) {
		Test1 t1 = new Test1();
		t1.test();
	}
	
	public void test() {
		Scanner sc = new Scanner(System.in);
		System.out.print("정수 하나 입력:");
		int su = sc.nextInt();
		if(su<1) {
			System.out.println("양수가 아닙니다.");
			return;
		}
		for(int i = 1; i<=su; i++) {
			for(int j = 1; j<=i; j++) {
				System.out.print(j);
			}
			System.out.println();
		}
		
		//양수가 아니면 반복
//		int su = 0;
//		do {
//			System.out.print("정수 하나 입력:");
//			su = sc.nextInt();
//			if(su<1) {
//				System.out.println("양수가 아닙니다.");
//			}
//			for(int i = 1; i<=su; i++) {
//				for(int j = 1; j<=i; j++) {
//					System.out.print(j);
//				}
//				System.out.println();
//			}
//		}while(su<1);
	}
}
