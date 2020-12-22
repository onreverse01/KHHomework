package com.oop.movie.model.entity;

public class MovieRun {

	public static void main(String[] args) {
		Movie m1 = new Movie("매트릭스");
		Movie m2 = new Movie("매트릭스","워쇼스키");
		Movie m3 = new Movie("매트릭스","워쇼스키",new String[] {"키아누 리브스", "로렌스 피시번","캐리 앤 모스","휴고 위빙","글로리아 포스터"});
		Movie m4 = new Movie("매트릭스","워쇼스키",new String[] {"키아누 리브스", "로렌스 피시번","캐리 앤 모스","휴고 위빙","글로리아 포스터"});
		m1.information();
		m2.information();
		m3.information();
		m4.information();
		
		System.out.println(m1.information());
		System.out.println(m2.information());
		System.out.println(m3.information());
		System.out.println(m4.information());
	}

}
