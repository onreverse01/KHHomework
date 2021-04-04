package com.oop.movie.model.entity;

import java.util.Calendar;

public class Movie {
	private String movieTitle;
	private String director;
	private String[] casting = new String[5];
	private Calendar release;
	
	public String getMovieTitle() {
		return movieTitle;
	}



	public void setMovieTitle(String movieTitle) {
		this.movieTitle = movieTitle;
	}



	public String getDirector() {
		return director;
	}



	public void setDirector(String director) {
		this.director = director;
	}



	public String[] getCasting() {
		return casting;
	}



	public void setCasting(String[] casting) {
		this.casting = casting;
	}



	public Calendar getRelease() {
		return release;
	}



	public void setRelease(Calendar release) {
		this.release = release;
	}


	/*
	public String information() {
		String cast = "";
		for(int i=0; i<getCasting().length; i++) {
			cast = cast+getCasting()[i]+"/";
		}
		
		String temp = "제목 : "+getMovieTitle()+
				"\n감독 : "+getDirector()+
				"\n출연진 : "+cast;
		if(release!=null)
			temp=temp+"\n개봉일 : "+getRelease().get(Calendar.YEAR)+"년"+getRelease().get(Calendar.MONTH)+"월"+getRelease().get(Calendar.DAY_OF_MONTH);
		return temp;
	}
	*/
	
	public void information() {
		System.out.println("-----------------------");
		System.out.println("영화 : " + movieTitle);
		System.out.println("감독 : " + director);
		System.out.print("캐스팅 : ");
		if (casting == null) {
			System.out.print("null");
		} else {
			for (int i = 0; i < casting.length; i++) {
				System.out.print(casting[i]);
				if (i == casting.length - 1) {
					System.out.println();
				} else {
					System.out.print(", ");
				}
			}
		}
		System.out.println("개봉날짜 : " + release);
		System.out.println("-----------------------");
	}
	
	public Movie(String movieTitle) {
		this.movieTitle = movieTitle;
	}
	
	public Movie(String movieTitle, String director) {
		this.movieTitle = movieTitle;
		this.director = director;
	}
	
	public Movie(String movieTitle, String director, String[] casting) {
		this(movieTitle, director);
		this.casting = casting;
	}

	public Movie(String movieTitle, String director, String[] casting, Calendar release) {
		this(movieTitle, director, casting);
		this.release = release;
	}

	
}
