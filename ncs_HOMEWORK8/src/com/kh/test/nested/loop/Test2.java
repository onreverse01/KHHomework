package com.kh.test.nested.loop;

import java.util.Scanner;

public class Test2 {
	public static void main(String[] args) {
		Test2 t2 = new Test2();
		t2.test();
	}
	
	public void test() {
		//1. 사용자 입력
		Scanner sc = new Scanner(System.in);
		System.out.print("정수입력:");
		int num = sc.nextInt();
		
		//2. 유효성 검사
		if(num<1) {
			System.out.println("양수가 아닙니다.");
			return;
		}
		
		//3. 출력
		for(int i = 0; i<num; i++) {
			for(int j = 0; j< (num-i); j++) {
				System.out.print("*");
			}
			System.out.println();
		}
		/**
		 * 1234
		 * 123
		 * 12
		 * 1
		 */
		
//		for(int i = 0; i<num; i++) {
//			for(int j = num; j>i; j--) {
//				System.out.print("*");
//			}
//			System.out.println();
//		}
		/**
		 * 54321
		 * 5432
		 * 543
		 * 54
		 * 5
		 */
	}
}
