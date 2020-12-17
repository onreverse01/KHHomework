package com.oop.emp.model;

import java.util.Scanner;

public class Employee {

	private int empNo;
	private String empName;
	private String dept;
	private String job;
	private int age;
	private char gender;
	private int salary;
	private double bonusPoint;
	private String phone;
	private String address;
	
	public void setEmpNo(int empNo) {
		this.empNo = empNo;
	}
	
	public int getEmpNo() {
		return this.empNo;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}
	
	public String getEmpName() {
		return empName;
	}
	
	public void setDept(String dept) {
		this.dept = dept;
	}

	public String getDept() {
		return dept;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getJob() {
		return job;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	public int getAge() {
		return age;
	}
	
	public void setGender(char gender) {
		this.gender = gender;
	}

	public char getGender() {
		return gender;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public int getSalary() {
		return salary;
	}

	public void setBonusPoint(double bonusPoint) {
		this.bonusPoint = bonusPoint;
	}

	public double getBonusPoint() {
		return bonusPoint;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getPhone() {
		return phone;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddress() {
		return address;
	}

	//키보드 입력 메소드
	public void empInput() {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("사번: ");
		empNo = sc.nextInt();
		
		sc.nextLine();
		
		System.out.print("사원이름: ");
		empName = sc.nextLine();
		System.out.print("소속부서: ");
		dept = sc.nextLine();
		System.out.print("직급: ");
		job = sc.nextLine();
		System.out.print("나이: ");
		age = sc.nextInt();
		System.out.print("성별: ");
		gender = sc.next().charAt(0);
		System.out.print("급여: ");
		salary = sc.nextInt();
		System.out.print("보너스포인트: ");
		bonusPoint = sc.nextDouble();
		
		sc.nextLine();
		
		System.out.print("핸드폰: ");
		phone = sc.nextLine();
		System.out.print("주소: ");
		address = sc.nextLine();
	}
	
//	private int empNo;
//	private String empName;
//	private String dept;
//	private String job;
//	private int age;
//	private char gender;
//	private int salary;
//	private double bonusPoint;
//	private String phone;
//	private String address;
	
	//출력 메소드
	public void empOutput() {
		System.out.println(getEmpNo());
		System.out.println(getEmpName());
		System.out.println(getDept());
		System.out.println(getJob());
		System.out.println(getAge());
		System.out.println(getGender());
		System.out.println(getSalary());
		System.out.println(getBonusPoint());
		System.out.println(getPhone());
		System.out.println(getAddress());
	}

}
