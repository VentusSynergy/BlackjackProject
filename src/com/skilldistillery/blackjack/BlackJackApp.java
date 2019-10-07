package com.skilldistillery.blackjack;

import java.util.Scanner;

import com.skilldistillery.cards.Card;
import com.skilldistillery.cards.Deck;

public class BlackJackApp {
	private Scanner sc;
	private Player player;
	private Dealer dealer;
	private static BlackJackApp app = new BlackJackApp();
	private Deck deck;

	public static void main(String[] args) {
		app.launch();

	}

	public void launch() {
		sc = new Scanner(System.in);
		String name;

		System.out.println("Enter name to play or enter \"quit\" ");
		name = sc.nextLine();
		if (name.equalsIgnoreCase("quit")) {
			System.out.println("Ending . . .");
		} else {
			player = new Player();
			dealer = new Dealer();
			deck = new Deck();
			deck.shuffle();
			player.setName(name);
			System.out.println("\nDealer gives himself a facedown card");

			dealCard(deck, player, dealer);
			if (dealer.getCardValue() <= 21) {
				playersChoice(deck, player, dealer);
			}
		}

	}

	public void dealCard(Deck deck, Player player, Dealer dealer) {
		if (dealer.getCardValue() >= 17) {
			System.out.println("The dealer decided to stay");
		} else if (dealer.getCardValue() < 17) {
			Card dc = deck.dealCard();
			dealer.setHand(dc);
			dealer.setCardValue((dealer.getCardValue() + dc.getValue()));

			if (dealer.currentHand().size() > 1) {
				dealer.setShownHand(dc);
				System.out.println("Dealer added " + dc + " to his hand");
			}
		}
		if (dealer.getCardValue() > 21) {
			System.out.println("Dealer got a bust ! ! !");
			System.out.println("Dealers hand: " + dealer.getHand());
			System.out.println(dealer.getCardValue());
			System.out.println("\n You win ! ! !");
		} 
		
		
		else {
			Card c = deck.dealCard();
			player.setHand(c);
			player.setCardValue((player.getCardValue() + c.getValue()));
			System.out.println("\n" + player.getName() + " was given " + c);
			if (player.getCardValue() > 21) {
				System.out.println("You got a bust ! ! !");
				System.out.println(player.getName() + "'s current hand: " + player.getHand());
				System.out.println("Value: " + player.getCardValue());
			} else{
				showPlayersHands(player);
			}

		}
		launch();



	}

	public void showPlayersHands(Player player) {
		System.out.println(
				player.getName() + "'s current hand: " + player.getHand() + "\nValue: " + player.getCardValue() + "\n");
		if (dealer.getShownHand().size() > 0) {
			System.out.println("Dealer has a facedown card with " + dealer.getShownHand());
			System.out.println();
		}

		playersChoice(deck, player, dealer);

	}

	public void playersChoice(Deck deck, Player player, Dealer dealer) {
		sc = new Scanner(System.in);
		int playerChoice;
		System.out.println("\n1. Hit\n2. Stay");
		playerChoice = sc.nextInt();

		switch (playerChoice) {
		case 1:
			dealCard(deck, player, dealer);
			if (player.getCardValue() <= 21) {
				showPlayersHands(player);
			}
			break;
		case 2:
			dealer.dealerAddsCards(dealer, deck, player);
		}
	}

	public void checkWinner(Player player, Dealer dealer) {
		if (player.getCardValue() == 21 && dealer.getCardValue() == 21) {
			System.out.println("Dealer cards and your cards are both equal to 21...");
			replay();
		} else if (dealer.getCardValue() == 21) {
			System.out.println("You lose");
			replay();
		} else if (dealer.getCardValue() > player.getCardValue() && dealer.getCardValue() < 22) {
			System.out.println("You lose");
			replay();
		}

		else if (player.getCardValue() > dealer.getCardValue() && player.getCardValue() < 22) {
			System.out.println("You win!");
			replay();
		} else if (dealer.getCardValue() == player.getCardValue()) {
			System.out.println("It's a draw");
			replay();
		}

	}

	public void replay() {
		sc = new Scanner(System.in);
		String replay = "";
		while (!replay.equalsIgnoreCase("C")) {
			System.out.println("Press \"c\" to continue");
			replay = sc.next();

		}
		launch();

	}

	public void playAgain() {
		String choice;
		System.out.println("Play again? (Y/N)");
		choice = sc.nextLine();
		while (!choice.equalsIgnoreCase("Y") || !choice.equalsIgnoreCase("Y")) {
			switch (choice) {
			case "y":
			case "Y":
				launch();
				break;
			case "n":
			case "N":
				endGame();
			}
		}
	}

	public void endGame() {
		sc.close();

	}

}
