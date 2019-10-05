package com.skilldistillery.blackjack;

import java.util.List;

import com.skilldistillery.cards.Card;

public class Player extends Participants {
	private static String name;

	public Player() {
		
	}
	public Player(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name + " has a hand of " + hand;

	}
	
}
