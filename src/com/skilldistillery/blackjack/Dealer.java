package com.skilldistillery.blackjack;

import java.util.List;

import com.skilldistillery.cards.Card;
import com.skilldistillery.cards.Deck;

public class Dealer extends Participants {
	
	public Dealer() {
		
	}

	public Dealer(String name, List<Card> hand) {
		this.hand = hand;
	}
	public List<Card> currentHand(){
		
		return hand;
		
	}
	public void dealerAddsCards(List<Card> hand, Dealer dealer, Deck deck, Player player) {
		BlackJackApp app = new BlackJackApp();
		while(dealer.getCardValue() < 17) {
			Card c = deck.dealCard();
			dealer.hand.add(c);
			dealer.setCardValue((dealer.getCardValue() + c.getValue()));
			System.out.println("Dealer drew a " + c);
			if(dealer.getCardValue() > 21) {
				System.out.println("Dealer has a hand of " + dealer.hand);
				System.out.println("Dealers value: " + dealer.getCardValue());
				System.out.println("Dealer got a bust, you win!");
				app.replay();
			}
			
		}
		System.out.println("Dealer has a hand of " + dealer.hand);
		System.out.println(player.getName() + " has a hand of " + player.hand);
		System.out.println("Players value: " + player.getCardValue());
		System.out.println("Dealers value: " + dealer.getCardValue());
		app.checkWinner(player, dealer);

		
	}

}
