package com.thread.alphabet;

public class AlphabetTest {
	public static void main(String[] args) {
		
		
		Thread alp1 = new UpperAlphabetThread('A');
		Thread alp2 = new LowerAlphabetThread('a');
		
		alp1.start();
		alp2.start();
	}
}
