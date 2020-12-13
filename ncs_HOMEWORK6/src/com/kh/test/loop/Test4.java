package com.kh.test.loop;

import java.util.Scanner;

public class Test4 {
	public static void main(String[] args) {
		Test4 t4 = new Test4();
		t4.test();
	}
	
	public void test() {
		Scanner sc = new Scanner(System.in);
		System.out.print("문자열 입력:");
		String name = sc.nextLine();
		System.out.print("검색할 문자 입력:");
		char search = sc.next().charAt(0);
		//개수 보관할 변수
		int count = 0;
		int i = 0;
		if((search>='A'&&search<='Z')||(search>='a'&&search<='z')){
			//문자열의 길이만큼 반복문
			while(i<name.length()) {
				if(name.charAt(i)==search) {
					count++;
				}
				i++;
			}
			System.out.println(count);
		}
		else {
			System.out.println("영문자가 아닙니다.");
		}
		
	}
}
