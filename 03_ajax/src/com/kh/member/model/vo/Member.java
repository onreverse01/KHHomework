package com.kh.member.model.vo;

public class Member {
	private String id;
	private String name;
	private String profile; // 프로필이미지 파일명
	public Member() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Member(String id, String name, String profile) {
		super();
		this.id = id;
		this.name = name;
		this.profile = profile;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getProfile() {
		return profile;
	}
	public void setProfile(String profile) {
		this.profile = profile;
	}
	@Override
	public String toString() {
		return id + "," + name + "," + profile;
	}
	
	
	
}
