package pgdp.casino;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Casino {

	public static void main(String[] args) {
		penguBlackJack();
	}

	public static void penguBlackJack() {

		// Here is a card deck for your games :)
		// Remember for testing you can use seeds:
		// CardDeck deck = CardDeck.getDeck(420);
		CardDeck deck = CardDeck.getDeck();
		// TODO
		System.out.println("Welcome to Pengu-BlackJack!");
		int tokens = 1000; //Startkapital
		while (tokens > 0) {
			System.out.println("(1) Start a game or (2) exit");
			int input = readInt();
			while (input != 2 && input != 1) { //bei fehlerhafter Eingabe Wiederholung
				System.out.println("What?!");
				input = readInt();
			}
			if (input == 1) { //Starte Spiel
				System.out.println("Your current balance: " + tokens);
				System.out.println("How much do you want to bet?");
				int einsatz = readInt();
				while (einsatz > tokens || einsatz < 1) { // Man darf nur so viel einsetzen wie man tokens zu verfügung hat
					System.out.println("How much do you want to bet?");
					einsatz = readInt();
				}
				System.out.println("Player cards:"); // Du (der Spieler) bist an der Reihe
				int KartenNr = 1;
				int sumOfCards = 0;
				int i = 0;
				for (i = 0; i < 2; i++) { // Am Anfang zieht der Spieler zwei Karten hintereinander
					int card = deck.drawCard();
					System.out.println(KartenNr + " : " + card);
					sumOfCards += card;
					KartenNr++;
				}
				System.out.println("Current standing: " + sumOfCards);
				if (sumOfCards < 21) {
					System.out.println("(1) draw another card or (2) stay");
					input = readInt();
					while (input != 2 && input != 1) {
						System.out.println("What?!");
						input = readInt();
					}
					if (input == 1) {
						while (sumOfCards < 21 || input == 1) {
							for (i = 0; i < 1; i++) {
								int card = deck.drawCard();
								System.out.println(KartenNr + " : " + card);
								sumOfCards = sumOfCards + card;
								KartenNr++;
							}
							System.out.println("Current standing: " + sumOfCards);
							if (sumOfCards >= 21) {
								break;
							}
							System.out.println("(1) draw another card or (2) stay");
							input = readInt();
							if (input == 2) { // Beendet den Zug
								break;
							} else {
								while (input != 2 && input != 1) {
									System.out.println("What?!");
									input = readInt();
								}
							}
						}
						if (sumOfCards > 21) {
							tokens = tokens - einsatz; //Verfügbare tokens mit Einsatz abziehen
							System.out.println("You lost " + einsatz + " tokens.");
						} else if (sumOfCards == 21) { //Jackpot
							tokens = tokens + (einsatz * 2);
							System.out.println("Blackjack! You won " + (einsatz * 2) + " tokens.");
						} else { //Dealer spielt mit, wenn Summe der Werte des Spielers unter 21 und den ZUg beendet
							int dealerKartenNr = 1;
							int dealerSumOfCards = 0;
							System.out.println("Dealer cards:");
							while (dealerSumOfCards < sumOfCards && dealerSumOfCards < 21) {
								for (i = 0; i < 2; i++) {
									int card = deck.drawCard();
									System.out.println(dealerKartenNr + " : " + card);
									dealerSumOfCards = dealerSumOfCards + card;
									dealerKartenNr++;
								}
							}
							System.out.println("Dealer: " + dealerSumOfCards);
							if (dealerSumOfCards > 21) {
								tokens = tokens + einsatz;
								System.out.println("You won " + einsatz + " tokens.");
							} else {
								tokens = tokens - einsatz;
								System.out.println("Dealer wins. You lost " + einsatz + " tokens.");
							}
						}
					} else {// Wenn input 2
						int dealerKartenNr = 1;
						int dealerSumOfCards = 0;
						System.out.println("Dealer cards:");
						while (dealerSumOfCards < sumOfCards && dealerSumOfCards < 21) {
							for (i = 0; i < 2; i++) {
								int card = deck.drawCard();
								System.out.println(dealerKartenNr + " : " + card);
								dealerSumOfCards = dealerSumOfCards + card;
								dealerKartenNr++;
							}
						}
						System.out.println("Dealer: " + dealerSumOfCards);
						if (dealerSumOfCards > 21) {
							tokens = tokens + einsatz;
							System.out.println("You won " + einsatz + " tokens.");
						} else {
							tokens = tokens - einsatz;
							System.out.println("Dealer wins. You lost " + einsatz + " tokens.");

						/*System.out.println("Your final balance: " + tokens);
						if (tokens > 1000) { // echt positiver Gewinn
							System.out.println("Wohooo! Ez profit! :D");
						}
						else { //kein Gewinn
							System.out.println("That's very very sad :(");
						}
						System.out.println("Thank you for playing. See you next time.");*/
						}
					}

				}
			else { // Wenn input 2 od. Schlusssequenz
				System.out.println("Your final balance: " + tokens);
				if (tokens > 1000) { // echt positiver Gewinn
					System.out.println("Wohooo! Ez profit! :D");
				}
				else { //kein Gewinn
					System.out.println("That's very very sad :(");
				}
				System.out.println("Thank you for playing. See you next time.");
				break;
			}
			if (tokens <= 0) {
				System.out.println("Sorry, you are broke. Better Luck next time.");
				System.out.println("Thank you for playing. See you next time.");
				break;
			}
			}
			else if (input == 2) {
				System.out.println("Your final balance: " + tokens);
				if (tokens > 1000) { // echt positiver Gewinn
					System.out.println("Wohooo! Ez profit! :D");
				}
				else { //kein Gewinn
					System.out.println("That's very very sad :(");
				}
				System.out.println("Thank you for playing. See you next time.");
				break;
			}
			else {
				while (input != 2 && input != 1) { //bei fehlerhafter Eingabe Wiederholung
					System.out.println("What?!");
					input = readInt();
				}
			}

		}

	}


	public static String readString() {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			return br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static int readInt() {
		while (true) {
			String input = readString();
			try {
				return Integer.parseInt(input);
			} catch (Exception e) {
				System.out.println("This was not a valid number, please try again.");
			}
		}
	}



}
