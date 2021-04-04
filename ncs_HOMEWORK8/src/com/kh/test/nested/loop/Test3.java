package com.kh.test.nested.loop;

import java.util.Scanner;

public class Test3 {
	public static void main(String[] args) {
		Test3 t3 = new Test3();
		t3.test();
	}

	public void test() {
		Scanner sc = new Scanner(System.in);
		System.out.print("양수입력:");
		int num = sc.nextInt();
		if (num < 1) {
			System.out.println("양수가 아닙니다.");
			return;
		}
		// 5->9, 6->11, 7->13
		for (int i = 0; i < num * 2 - 1; i++) {
			// 0 1 2 3 4
			if (i < num) {
				// j<=1 등호 주의!!
				for (int j = 0; j <= i; j++) {
					System.out.print("*");
				}
			}
			// 5 6 7 8
			else {
				for (int j = 0; j < num; j++) {
					if (j <= (i - num))
						System.out.print(" ");
					else
						System.out.print("*");
				}
			}
			System.out.println();
		}
		/*
		for (int i = 0; i < num; i++) {
			for (int j = 0; j <= i; j++) {
				System.out.print("*");
			}
			System.out.println();
		}
		for (int i = 0; i < num - 1; i++) {
			for (int j = 0; j <= i; j++) {
				System.out.print(" ");
			}
			for (int j = 0; j < num - 1 - i; j++) {
				System.out.print("*");
			}
			System.out.println();
		}
		*/
	}
}
