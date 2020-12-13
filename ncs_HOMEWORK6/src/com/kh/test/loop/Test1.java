package com.kh.test.loop;

import java.util.Scanner;

public class Test1 {
	public static void main(String[] args) {
		Test1 t1 = new Test1();
		t1.test();
	}
	public void test() {
		Scanner sc = new Scanner(System.in);
		System.out.print("수를 입력하라:");
		int num = sc.nextInt();
		int sum = 0; 
		int i = 0;
		while(i<=num) {
			if(i%2==0)
				sum+=i;
			i++;
		}
		System.out.println(sum);
	}
		
}
