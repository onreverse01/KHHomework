package com.thread.alphabet;

public class LowerAlphabetThread extends Thread {

	private char ch;

	public LowerAlphabetThread(char ch) {
		this.ch = ch;
	}
	
	@Override
	public void run() {
		for(char a = 'a'; a<='z'; a++) {
			System.out.print(a+" ");
		}
	}
}
