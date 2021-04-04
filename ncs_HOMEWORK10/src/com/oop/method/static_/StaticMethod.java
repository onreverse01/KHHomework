package com.oop.method.static_;

public class StaticMethod {
	
	//1. 전달한 문자열을 모두 대문자로 바꾸는 static 메소드
	public static String toUpper(String s) {
		/*
		String s2 = " ";
		for(int i =0; i<s.length(); i++) {
			char alp= s.charAt(i);
			if(alp>='a' && alp<='z') {
				alp-=32;
			}
			s2+=alp;
		}
		return s2;
		*/
		s=s.toUpperCase();
		return s;
	}
	
	//2. 첫번째 문자열의 전달받은 인덱스의 문자를 전달받은 문자로 변경하는 static 메소드
	public static String setChar(String s, int index, char ch) {
		char[] arr = s.toCharArray();
		arr[index] = ch;
		return new String(arr);
	}
	
	//3. 전달한 문자열에서 영문자의 개수를 리턴하는 static 메소드
	public static int getAlphabetLength(String s) {
		int count = 0;
		for(int i =0; i<s.length(); i++) {
			char alp = s.charAt(i);
			if((alp>='a'&&alp<='z') || (alp>='A'&&alp<='Z')) {
				count++;
			}
		}
		return count;
	}
	
	//4. 전달한 문자열값을 하나로 합쳐서 리턴 
	public String concat(String a, String b) {
		return a+b;
	}
}
