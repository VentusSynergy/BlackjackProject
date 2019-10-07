package com.skilldistillery.blackjack;

public class Player extends Participants {
	private String name;

	public Player() {
		
	}
	public Player(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
}
