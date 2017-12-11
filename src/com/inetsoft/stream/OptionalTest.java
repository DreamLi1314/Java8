package com.inetsoft.stream;

import java.util.Optional;

public class OptionalTest {

	public static void main(String[] args) {
		Man man = new Man(new Goddess("hh"));
		String name = getGoddessName(man);//null
		System.out.println(name);
		
		NewMan man2 = new NewMan();
		String name2 = getGoddessName2(Optional.ofNullable(null));//man2
		System.out.println(name2);
		
		
	}

	public static String getGoddessName2(Optional<NewMan> man) {
		return man.orElse(new NewMan())
				  .getGoddess()
				  .orElse(new Goddess("Default Goddess"))
				  .getName();
	}
	
	
	public static String getGoddessName(Man man) {
		if(man != null) {
			Goddess goddess = man.getGoddess();
			if(goddess != null) {
				return goddess.getName();
			}
		}
		
		return "Goddess";
		
	}
}
