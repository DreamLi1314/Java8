package com.inetsoft.stream;

public class Goddess {
	private String name;

	public Goddess() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Goddess(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Goddess [name=" + name + "]";
	}
	
}
