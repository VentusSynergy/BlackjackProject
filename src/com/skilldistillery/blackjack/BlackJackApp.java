package com.skilldistillery.blackjack;

import java.util.Scanner;

import com.skilldistillery.cards.Card;
import com.skilldistillery.cards.Deck;

public class BlackJackApp {
	private static Player player;
	private static Dealer dealer;
	private static BlackJackApp app = new BlackJackApp();
	private static Deck deck;
	private Card c;

	public static void main(String[] args) {
		app.launch();

	}

	public void launch() {
		Scanner sc = new Scanner(System.in);
		deck = new Deck();
		player = new Player();
		dealer = new Dealer();
		deck.shuffle();
		String name;

		System.out.println("Enter name: ");
		name = sc.nextLine();
		player.setName(name);

		dealCard(deck, player, dealer);
		playersChoice(deck, player, dealer);

	}

	public void dealCard(Deck deck, Player player, Dealer dealer) {
		Card dc = deck.dealCard();
		Card c = deck.dealCard();
		System.out.println("\n" + player.getName() + " has been given the card " + c);
		
		player.setCardValue((player.getCardValue() + c.getValue()));
		if(player.getCardValue() > 21) {
			System.out.println("You got a bust!!! You lose.");
			app.replay();
		}
		player.hand.add(c);
		dealer.setCardValue((dealer.getCardValue() + dc.getValue()));

		dealer.hand.add(dc);
		if (dealer.hand.size() < 2) {
			System.out.println("\nDealer gives himself a facedown card\n");
		} else {
			System.out.println("\nDealer added " + dc + " to his hand");
		}

	}

	public void showAllHands(Player player, Dealer dealer) {
		for (Card card : player.hand) {
			System.out.println(player.getName() + "'s current hand: " + player.hand + "\nValue: " + player.getCardValue());

		}
		for (int i = 0; i < dealer.hand.size(); i++) {
			if (dealer.hand.size() == 1) {
				System.out.println("\nDealer has a face down card ...");
			} else {
				System.out.println("\nDealer's current hand: " + dealer.hand);
			}
		}
		app.playersChoice(deck, player, dealer);

	}

	public void playersChoice(Deck deck, Player player, Dealer dealer) {
		Scanner sc = new Scanner(System.in);
		int playerChoice;
		System.out.println("1. Hit\n2. Stay");
		playerChoice = sc.nextInt();

		switch (playerChoice) {
		case 1:
			app.dealCard(deck, player, dealer);
			app.showAllHands(player, dealer);
			break;
		case 2:
			dealer.dealerAddsCards(dealer.hand, dealer, deck, player);
		}
	}

	public void checkWinner(Player player, Dealer dealer) {
		if (player.getCardValue() == 21 && dealer.getCardValue() == 21) {
			System.out.println("Dealer cards and your cards are both equal to 21...");
			app.replay();
		}
		else if (dealer.getCardValue() == 21) {
			System.out.println("You lose");
			app.replay();
		}
		else if (dealer.getCardValue() > player.getCardValue() && dealer.getCardValue() < 22) {
			System.out.println("You lose");
			app.replay();
		}
			
		else if(player.getCardValue() > dealer.getCardValue() && player.getCardValue() < 22) {
			System.out.println("You win!");
			app.replay();
		}

	}

	public void replay() {
		Scanner sc = new Scanner(System.in);
		String replay;
		System.out.println("Play again? (Y/N)");
		replay = sc.next();
		switch (replay) {
		case "Y":
		case "y":
			app.launch();
			break;
		case "N":
		case "n":
			sc.close();
			break;
		}

	}
}
