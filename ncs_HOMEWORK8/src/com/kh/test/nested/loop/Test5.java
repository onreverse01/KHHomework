package com.kh.test.nested.loop;

public class Test5 {
	public static void main(String[] args) {
		Test5 t5 = new Test5();
		t5.test();
//		new Test5().test();
	}

	public void test() {
		char[] chArr = { 'y', 'y', 'm', 'm', 'd', 'd', '-', '1', '2', '3', '4', '5', '6', '7' };
		char[] tempArr = chArr.clone();

		for (int i = tempArr.length - 6; i < tempArr.length; i++) {
			tempArr[i] = '*';
		}
		System.out.println(chArr);
		System.out.println(tempArr);
	}
}