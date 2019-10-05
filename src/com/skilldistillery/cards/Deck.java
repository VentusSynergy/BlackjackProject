package com.skilldistillery.cards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
	private List<Card> cards;
	
	public Deck() {
		cards = inputDeck();
	}
	
	private List<Card> inputDeck() {
		List<Card> deck = new ArrayList<>(52);
		for (Suit suit : Suit.values()) {
			for (Rank rank : Rank.values()) {
				deck.add(new Card(suit, rank));
				
			}
			
		}
		return deck;
	}
	
	public int checkDeckSize() {
		return cards.size();
		
	}
	public Card dealCard() {
		return cards.remove(0);
	}
	@Override
	public String toString() {
		return "Deck [cards=" + cards + "]";
	}

	public void shuffle() {
		Collections.shuffle(cards);
	}

}
