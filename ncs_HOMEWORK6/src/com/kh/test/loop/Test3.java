package com.kh.test.loop;

import java.util.Scanner;

public class Test3 {
	public static void main(String[] args) {
		Test3 t3 = new Test3();
		t3.test();
	}
	public void test() {
		Scanner sc = new Scanner(System.in);
		System.out.print("정수입력");
		int num = sc.nextInt();
		int i=1;
		while(i<=9) {
			System.out.println(num+"X"+i+"="+(num*i));
			i++;
		}
	}
}
