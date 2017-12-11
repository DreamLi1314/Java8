package com.inetsoft.stream;

public class Man {
	private Goddess goddess;

	public Man(Goddess goddess) {
		super();
		this.goddess = goddess;
	}

	public Goddess getGoddess() {
		return goddess;
	}

	public void setGoddess(Goddess goddess) {
		this.goddess = goddess;
	}

	@Override
	public String toString() {
		return "Man [goddess=" + goddess + "]";
	}
	
}
