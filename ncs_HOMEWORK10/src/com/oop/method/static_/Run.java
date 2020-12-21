package com.oop.method.static_;

public class Run {
	public static void main(String[] args) {
		String a = "asdfghj";
		System.out.println("기존값: "+a);
		
		a=StaticMethod.toUpper(a);
		System.out.println(a);
		
		int b=StaticMethod.getAlphabetLength(a);
		System.out.println("알파벳수: "+b);
		
		System.out.println();
	}
}
