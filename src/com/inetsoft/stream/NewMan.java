package com.inetsoft.stream;

import java.util.Optional;

public class NewMan {

	private Optional<Goddess> goddess = Optional.empty();

	public NewMan(Optional<Goddess> goddess) {
		super();
		this.goddess = goddess;
	}

	public NewMan() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Optional<Goddess> getGoddess() {
		return goddess;
	}

	public void setGoddess(Optional<Goddess> goddess) {
		this.goddess = goddess;
	}
	
}
