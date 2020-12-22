package com.oop.coffee.controller;

import java.util.Scanner;

import com.oop.coffee.model.dto.Coffee;


public class CoffeeManager {
	private Coffee[] arr = new Coffee[3];
	private Scanner sc = new Scanner(System.in);
	
	public void inputCoffee() {
		for(int i = 0; i<arr.length; i++) {
			System.out.println((i+1)+"번");
			System.out.print("원산지:");
			String origin = sc.nextLine();
			System.out.print("지역:");
			String location = sc.nextLine();
			Coffee p = new Coffee(origin, location);
			arr[i]=p;
		}

		System.out.println("<세계3대커피>\n" + 
				"---------------------------\n" + 
				"    원산지           지역\n" + 
				"---------------------------");
	}
	
	public void printCoffee() {
		for(Coffee c:arr) {
			c.printCoffee();
			
		}
		System.out.println("----------------------");
	}
}
