/* This program fetches number of stones to remove and stores Player information. 
 * Yunqiu Weng, 8228509, 23/04/2018
 */
public class NimGame {
	void game (int numberOfStones, int upperBoundOfStone, String userName1, String userName2) {
		
		NimPlayer player1 = new NimPlayer();
		NimPlayer player2 = new NimPlayer();

		boolean flag = true; // flag is used to control whether to start a new game

		while (flag) {
			System.out.println("Initial stone count: ", numberOfStones);
			System.out.println("Maximum stone removal: ", upperBoundOfStone);
			System.out.println("Player 1: ", userName1);
			System.out.println("Player 2: ", userName2);

			int isPlayer1; // this variable is to control whether it's player 1's turn.
			for (isPlayer1 = 1; numberOfStones > 0; isPlayer1++) {
				System.out.print(numberOfStones + " stones left:");
				printStones(numberOfStones); // calling printStones method

				if (isPlayer1 % 2 != 0) {
					numberOfStones -= player1.removeStone(player1.name, numberOfStones, upperBoundOfStone, keyboard);
				} else {
					numberOfStones -= player1.removeStone(player2.name, numberOfStones, upperBoundOfStone, keyboard);
				}			
			}

			System.out.println("Game Over");
			System.out.println( ((isPlayer1 % 2 == 0)? player2.name : player1.name) +" wins!\n");
			System.out.print("Do you want to play again (Y/N):");

			if (!keyboard.next().equals("Y")) // All input except Y will end this game
				flag = false;

			private static void printStones(int numberOfStones) {
				int i;
				for (i = 1; i <= numberOfStones; i++) {
					System.out.print(" *");
				}
				System.out.println();

			}
		}