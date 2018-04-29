
/* This program handles the core game play mechanism. It is a two player game, and the rules are as follows:
 * The game begins with a number of objects (e.g., stones placed on a table).
 * Each player takes turns removing stones from the table.
 * On each turn, a player must remove at least one stone, at most the upper bound on the number of stones or the number of stones. 
 * The game ends when there are no more stones remaining. The player who removes the last stone, loses. 
 * Both the initial number of stones, and the upper bound removal number, can be varied and must be chosen before a game commences.
 * Yunqiu Weng, 8228509, 29/04/2018
 */
import java.util.Scanner;

public class NimGame {
	
	//This method would receive the input from Nimsys.  
	public int game(int numberOfStones, int upperBoundOfStone, NimPlayer player1, NimPlayer player2, Scanner keyboard) {
		
		// playerA, playerB receive two players' input using the method from NimPlayer class, corresponding to player1 and player2.
		NimPlayer playerA = new NimPlayer();
		NimPlayer playerB = new NimPlayer();
		
		System.out.println();		
		System.out.println("Initial stone count: " + numberOfStones);
		System.out.println("Maximum stone removal: " + upperBoundOfStone);
		System.out.println("Player 1: " + player1.getGivenName() + " " + player1.getFamilyName());
		System.out.println("Player 2: " + player2.getGivenName() + " " + player2.getFamilyName());
		System.out.println();	
		
		int isPlayer1; // This variable controls the turn of players using isPlayer1 % 2. E.g. if isPlayer1 % 2 = 1, it's player 1.
		for (isPlayer1 = 1; numberOfStones > 0; isPlayer1++) {
			
			System.out.print(numberOfStones + " stones left:");
			printStones(numberOfStones); 
			
			// When result is smaller than 0, the player needs to enter the number of removal stones again.
			// Otherwise, the input is valid, and result is the number of stones to be removed.
			int result; 
			if (isPlayer1 % 2 != 0) {
				result = playerA.removeStone(player1.getGivenName(), numberOfStones, upperBoundOfStone, keyboard);
				if (result <0) 
					isPlayer1--;
				else numberOfStones -= result;
			} else {
				result = playerB.removeStone(player2.getGivenName(), numberOfStones, upperBoundOfStone, keyboard);
				if (result <0) 
					isPlayer1--;
				else numberOfStones -= result;
			}
		}

		System.out.println("Game Over");
		
		// The return value 1 or 2 represents player1 or player 2 wins.
		if (isPlayer1 % 2 == 0) {
			System.out.println(player2.getGivenName() + " wins!");
			return 2;
		}
		else {
			System.out.println(player1.getGivenName() + " wins!");
			return 1;
		}
	}
	
	private void printStones(int numberOfStones) {
		int i;
		for (i = 1; i <= numberOfStones; i++)
			System.out.print(" *");
		System.out.println();

	}
}