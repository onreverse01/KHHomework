package com.thread.alphabet;

public class UpperAlphabetThread extends Thread {

	private char ch;
	
	public UpperAlphabetThread(char ch) {
		this.ch = ch;
	}

	@Override
	public void run() {
		for(char a = 'A'; a<='Z'; a++) {
			System.out.print(a+" ");
		}
		
	}
}
