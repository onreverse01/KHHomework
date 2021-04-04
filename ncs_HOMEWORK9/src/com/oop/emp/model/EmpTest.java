package com.oop.emp.model;

import java.util.Scanner;

public class EmpTest {
	public static void main(String[] args) {
		String menu = "******* 사원 정보 관리 프로그램 ************\n" + 
				"    1. 새 사원 정보 입력  => empInput() 메소드 실행\n" + 
				"    2. 사원 정보 삭제 => 참조변수에 null대입할  것.\n" + 
				"    3. 사원정보 출력 =>  empOutput() 메소드 실행\n" + 
				"    9. 끝내기\n" + 
				"********************************************";

		int choice = 0;
		Scanner sc = new Scanner(System.in);
		Employee e = null; // 반복문 이전 선언
		while(true) {
			System.out.println(menu);
			choice = sc.nextInt();
			switch(choice) {
			case 1: e = new Employee(); e.empInput(); break;
			case 2: e=null;
					System.out.println("삭제완료!");
					break;
			case 3: 
				if(e==null) {
					System.out.println("먼저 입력하시오!");
				}
				else
					e.empOutput(); break;
			case 9: System.out.println("끝"); return;
			}
		}
		
	}
}
