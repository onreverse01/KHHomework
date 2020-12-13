package com.kh.test.break_continue;

import java.util.Random;
import java.util.Scanner;

public class Test2 {
	public static void main(String[] args) {
		Test2 t2 = new Test2();
		t2.test();
	}
	
	public void test() {
		Random rnd = new Random();
		Scanner sc = new Scanner(System.in);
		
		int num = 0;
		int rnum = 0;
		int i = 0;
		rnum = rnd.nextInt(100)+1;
		do {
			System.out.print("정수입력1~100:");
			num = sc.nextInt();
			if(num<1)continue;
			if(num>100)continue;
			
			i++;
			
			if(num==rnum)break;
			
			else if(num>rnum) {
				System.out.println("입력하신 정수값보다 큽니다.");
			}
			else if(num<rnum) {
				System.out.println("입력하신 정수값보다 작습니다.");
			}
			
		}while(num!=rnum);
		System.out.println("정답입니다. "+i+"회 만에 정답을 맞추셨습니다.");
	}
}
