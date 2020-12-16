package kh.java.test.array;

import java.util.Scanner;

public class Test3 {
	public static void main(String[] args) {
		Test3 t3 = new Test3();
		t3.test();
	}
	
	public void test() {
		Scanner sc = new Scanner(System.in);
		System.out.print("입력값");
		String s =sc.nextLine();
		System.out.print("검색값");
		char ch = sc.next().charAt(0);
		
		int num =0;
		
		char [] sarr= new char[s.length()];
		
		for (int i = 0; i<s.length(); i++) {
			sarr[i]=s.charAt(i);
		}
		for (int i = 0; i<sarr.length; i++) {
			if(ch==sarr[i]) {
				num++;
			}
		}
		System.out.println(num);
	}
}
