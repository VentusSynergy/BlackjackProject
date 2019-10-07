package com.skilldistillery.blackjack;

import java.util.ArrayList;
import java.util.List;

import com.skilldistillery.cards.Card;

public class Participants {
	private List<Card> hand = new ArrayList<>();
	private int cardValue;
	
	public Participants() {
		
	}

	public List<Card> getHand() {
		return hand;
	}

	public void setHand(Card c) {
		this.hand.add(c);
	}


	public int getCardValue() {
		return cardValue;
	}

	public void setCardValue(int cardValue) {
		this.cardValue = cardValue;
	}
	
	
	
}
