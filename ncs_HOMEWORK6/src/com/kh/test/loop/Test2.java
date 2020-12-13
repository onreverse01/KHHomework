package com.kh.test.loop;

import java.util.Scanner;

public class Test2 {
	public static void main(String[] args) {
		Test2 t2=new Test2();
		t2.test();
	}
	
	public void test() {
		Scanner sc = new Scanner(System.in);
		System.out.print("정수입력:");
		int num = sc.nextInt();
		int i = 1;
		while(i<=num) {
			if(i%2==1) {
				System.out.print("수");
			}
			else if(i%2==0) {
				System.out.print("박");
			}
			i++;
		}
	}
}
