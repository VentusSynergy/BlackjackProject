package com.skilldistillery.blackjack;

import java.util.Scanner;

import com.skilldistillery.cards.Card;
import com.skilldistillery.cards.Deck;

public class BlackJackApp {
	private static Player player;
	private static Dealer dealer;
	private static BlackJackApp app = new BlackJackApp();
	private static Deck deck;

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

		System.out.println("Enter name to play or enter \"quit\" ");
		name = sc.nextLine();
		if (name.equalsIgnoreCase("quit")) {
			sc.close();
			app.endGame();
		} else {
			player.setName(name);

			dealCard(deck, player, dealer);
			if (dealer.getCardValue() <= 21) {
				playersChoice(deck, player, dealer);
			}
		}

	}

	public void dealCard(Deck deck, Player player, Dealer dealer) {
		Card dc = deck.dealCard();
		Card c = deck.dealCard();
		System.out.println("\n" + player.getName() + " has been given the card " + c);
		player.hand.add(c);

		player.setCardValue((player.getCardValue() + c.getValue()));
		if (player.getCardValue() > 21) {
			System.out.println("Players hand " + player.hand);
			System.out.println("Players value: " + player.getCardValue());
			System.out.println("You got a bust!!! You lose.");
			app.replay();
		}
		dealer.setCardValue((dealer.getCardValue() + dc.getValue()));

		dealer.hand.add(dc);
		if (player.getCardValue() <= 21) {
			if (dealer.getCardValue() > 21) {
				System.out.println("Dealer added " + dc + " to his hand\n");
				System.out.println("Dealers hand " + dealer.hand);
				System.out.println("Dealers value: " + dealer.getCardValue());
				System.out.println("Dealer got a bust. You win!");
				app.replay();
			}

			else if (dealer.hand.size() < 2) {
				System.out.println("\nDealer gives himself a facedown card\n");
			} else {
				System.out.println("Dealer added " + dc + " to his hand\n");
			}
		}

	}

	public void showPlayersHands(Player player) {
		System.out.println(player.getName() + "'s current hand: " + player.hand + "\nValue: " + player.getCardValue());

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
			if (player.getCardValue() <= 21) {
				app.showPlayersHands(player);
			}
			break;
		case 2:
			dealer.dealerAddsCards(dealer.hand, dealer, deck, player);
		}
	}

	public void checkWinner(Player player, Dealer dealer) {
		if (player.getCardValue() == 21 && dealer.getCardValue() == 21) {
			System.out.println("Dealer cards and your cards are both equal to 21...");
			app.replay();
		} else if (dealer.getCardValue() == 21) {
			System.out.println("You lose");
			app.replay();
		} else if (dealer.getCardValue() > player.getCardValue() && dealer.getCardValue() < 22) {
			System.out.println("You lose");
			app.replay();
		}

		else if (player.getCardValue() > dealer.getCardValue() && player.getCardValue() < 22) {
			System.out.println("You win!");
			app.replay();
		} else if (dealer.getCardValue() == player.getCardValue()) {
			System.out.println("It's a draw");
			app.replay();
		}

	}

	public void replay() {
		Scanner sc = new Scanner(System.in);
		String replay = "";
		while (!replay.equalsIgnoreCase("C")) {
			System.out.println("Press \"c\" to continue");
			replay = sc.next();

		}
		app.launch();

	}

	public void endGame() {
		System.out.println("Ending . . .");

	}
}
