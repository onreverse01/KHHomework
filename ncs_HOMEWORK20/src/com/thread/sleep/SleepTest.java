package com.thread.sleep;

public class SleepTest {
	
	public static void main(String[] args) {
		SleepTest s = new SleepTest();
		s.sendAphorism();
	}
	
	public void sendAphorism() {
		
		String arr[] = {"가는 말이 고아야 오는 말이 곱다.",
				"낮말은 새가 듣고 밤말은 쥐가 듣는다.",
				"사돈 남 말 한다.",
				"호랑이도 제 말 하면 온다",
				"싼게 비지 떡",
				"개구리 올챙이 적 생각을 못한다.",
				"고래 싸움에 새우 등 터진다.",
				"공든 탑이 무너지라",
				"그림의 떡",
				"금강산도 식후경"};
		int rand = 0;
		for(int i=0; i<arr.length; i++) {
			rand = (int) (Math.random()*arr.length);
				System.out.println(arr[rand]);
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}

}
