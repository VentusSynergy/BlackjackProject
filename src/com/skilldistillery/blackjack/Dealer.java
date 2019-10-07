package com.skilldistillery.blackjack;

import java.util.ArrayList;
import java.util.List;

import com.skilldistillery.cards.Card;
import com.skilldistillery.cards.Deck;

public class Dealer extends Participants {
	private List<Card> shownHand = new ArrayList<>();

	public Dealer() {

	}

	public List<Card> visDeck() {
		return shownHand;

	}

	public Dealer(String name, Card dc, List<Card> shownHand) {
		this.setHand(dc);
		this.shownHand = shownHand;
	}

	public List<Card> getShownHand() {
		return shownHand;
	}

	public void setShownHand(Card dc) {
		this.shownHand.add(dc);
	}

	public List<Card> currentHand() {

		return getHand();

	}

	public void dealerAddsCards(Dealer dealer, Deck deck, Player player) {
		BlackJackApp app = new BlackJackApp();
		while (dealer.getCardValue() < 17) {
			Card c = deck.dealCard();
			dealer.setHand(c);
			dealer.setCardValue((dealer.getCardValue() + c.getValue()));
			System.out.println("Dealer drew a " + c);
			if (dealer.getCardValue() > 21) {
				System.out.println("Dealer has a hand of " + dealer.getHand());
				System.out.println("Dealers value: " + dealer.getCardValue());
				System.out.println("Dealer got a bust, you win!");
				app.replay();
			} else {

			System.out.println("Dealer has a hand of " + dealer.getHand());
			System.out.println(player.getName() + " has a hand of " + player.getHand());
			System.out.println("Players value: " + player.getCardValue());
			System.out.println("Dealers value: " + dealer.getCardValue());
			app.checkWinner(player, dealer);
			}
		}

	}

}
