package com.kh.test.dimentional.array.test;

import java.util.Random;
import java.util.Scanner;

public class Sample {
	public static void main(String[] args) {
		Sample s = new Sample();
		s.exercise1();
	}

	public void exercise1() {
		Scanner sc = new Scanner(System.in);
		Random rnd = new Random();

		System.out.print("양수입력 : ");
		int num = sc.nextInt();
		if (num < 1) {
			System.out.println("양수가 아닙니다.");
			return;
		}
		int rnum = 0;
		int total = 0;

		int[][] arr = new int[3][num];
		
		//현재 열은 다 보여주지만, 열의 합계가 5의 배수만 X 되어있음.
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				rnum = rnd.nextInt(100) + 1;
				arr[i][j] = rnum;
				total+=arr[i][j];
				System.out.print(arr[i][j]+" ");
			}
			System.out.print(total % 5 == 0 ? total + " " : "X ");
			System.out.println();
		}
	}
}
