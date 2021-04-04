package com.kh.test.nested.loop;

import java.util.Arrays;
import java.util.Scanner;

public class Test4 {
	public static void main(String[] args) {
		Test4 t4 = new Test4();
		t4.test1();
		t4.test2();
	}
	

	public void test1() {

		String temp;
		char[] inputArr = new char[11];
		char[] outputArr;
		Scanner sc = new Scanner(System.in);

		System.out.println("전화번호 11자리를 입력하세요 : ");
		temp = sc.nextLine();

		for (int i = 0; i < temp.length(); i++) {
			inputArr[i] = temp.charAt(i);
		}

		outputArr = inputArr.clone();

		for (int i = 3; i < 7; i++) {
			outputArr[i] = '*';
		}
		System.out.println("원본:" + Arrays.toString(inputArr));
		System.out.println("출력용:" + Arrays.toString(outputArr));
	}

	public void test2() {
		String temp;
		Scanner sc = new Scanner(System.in);

		System.out.println("전화번호11자리를 입력하세요:");
		temp = sc.nextLine();

		System.out.println(temp.substring(0, 3) + "****" + temp.substring(7));
	}
}
