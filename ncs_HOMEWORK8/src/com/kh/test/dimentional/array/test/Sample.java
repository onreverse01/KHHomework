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
		int cnt = 0;

		int[][] arr = new int[3][num];
		
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				rnum = rnd.nextInt(100) + 1;
				arr[i][j] = rnum;
				total+=arr[i][j];
			}
			if(total % 5 == 0) {
				cnt++;
				for(int k = 0; k < arr[i].length; k++) {
					System.out.print(arr[i][k] + " ");
				}
			}
			System.out.println("---------");
		}
		if (cnt==0) {
			System.out.println("5의 배수의 합이 없습니다.");
		}
		
		
	}
}
