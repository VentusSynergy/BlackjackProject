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
		Card c = deck.dealCard();
		while(dealer.getCardValue() < 17) {
			dealer.hand.add(c);
			dealer.setCardValue((dealer.getCardValue() + c.getValue()));
			if(dealer.getCardValue() > 21) {
				System.out.println("Dealer got a bust, you win!");
				app.replay();
			}
			
		}
		app.checkWinner(player, dealer);

		
	}

}
