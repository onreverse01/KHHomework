package com.oop.body.model;

public class Head extends Body{
	private String hairColor;
	
	public Head() {}
	
	public Head(String hairColor) {
		super();
		this.hairColor=hairColor;
	}
	
	public String getHairColor() {
		return hairColor;
	}

	public void setHairColor(String hairColor) {
		this.hairColor = hairColor;
	}

	public void headBang() {
		System.out.println(hairColor+" 머리를 돌립니다.");
	}
	
	@Override
	public void action() {
		headBang();
	}
}
