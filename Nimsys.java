
/* NimSys and NimPlayer files provide a two player game, and the rules are as follows:
 * The game begins with a number of objects (e.g., stones placed on a table).
 * Each player takes turns removing stones from the table.
 * On each turn, a player must remove at least one stone, at most the upper bound on the number of removal stones.
 * The game ends when there are no more stones remaining. The player who removes the last stone, loses. 
 * The initial number of stones and the upper bound number of removal are chosen before a game commences. 
 * This class file manages the game playing process. 
 * Author: Yunqiu Weng, Student Id: 8228509, 26/03/2018
 */
import java.util.Scanner;
import java.util.Arrays;
//import java.text.DecimalFormat;

public class Nimsys {
	public static Scanner keyboard;
	public static final int MAX_PLAYERS = 100;

	public NimPlayer[] players = new NimPlayer[MAX_PLAYERS];
	private int numOfPlayer;

	// public Nimsys() {
	// int numOfPlayer = 0;
	// }

	public static void main(String[] args) {

		keyboard = new Scanner(System.in);
		Nimsys controller = new Nimsys();

		System.out.println("Welcome to Nim");
		System.out.print("\n$");
		String command = keyboard.nextLine();

		while (!command.equals("exit")) {
			// The entered command consists of two parts. The first one indicates the type of the command, 
			// include addplayer, removeplayer,editplayer,resetstats,displayplayer,rankings,startgame,exit.
			String[] splitCommand = command.split(",| ");
			String commandType = splitCommand[0];

			if (commandType.equals("addplayer")) {
				controller.addPlayer(splitCommand[1], splitCommand[2], splitCommand[3]);
			}

			else if (commandType.equals("removeplayer")) {
				if (splitCommand.length == 1) {
					System.out.println("Are you sure you want to remove all players? (y/n)");
					if (keyboard.nextLine().equals("y")) {
						controller.numOfPlayer = 0;
						Arrays.fill(controller.players, null);
					}
				} else
					controller.removePlayer(splitCommand[1]);
			}

			else if (commandType.equals("editplayer")) {
				controller.editPlayer(splitCommand[1], splitCommand[2], splitCommand[3]);

			}

			else if (commandType.equals("resetstats")) {
				if (splitCommand.length == 1) {
					System.out.println("Are you sure you want to reset all player statistics? (y/n)");
					if (keyboard.nextLine().equals("y"))
						controller.resetStats();
				} else
					controller.resetStats(splitCommand[1]);

			}

			else if (commandType.equals("displayplayer")) {
				if (splitCommand.length == 1)
					controller.displayPlayer();
				else {
					int i = controller.isExist(splitCommand[1]);
					if (i > -1)
						controller.displayPlayer(i);
					else
						System.out.println("The player does not exist.");
				}
			}

			else if (commandType.equals("rankings")) {
				controller.rank();
			} else if (commandType.equals("startgame")) {
				controller.startGame(splitCommand[1], splitCommand[2], splitCommand[3], splitCommand[4]);
			}
			// For debug.
			// for (int i = 0; i < controller.numOfPlayer; i++)
			// System.out.println(controller.players[i].getGivenName());
			// System.out.println(controller.numOfPlayer);

			System.out.print("\n$");
			command = keyboard.nextLine();
		}
		System.out.println();
		System.exit(0);
	}

	// Rank in descending order by default. Ties are sorted on usernames
	// alphabetically. Only first 10 will be displayed.
	void rank() {
		
		// Create a new array to store statistics of the player.
//		DecimalFormat percent = new DecimalFormat("0%");
	
		for (int i = 0; i < numOfPlayer; i++) {
			if (players[i].getNumOfPlayedGame() != 0)
				players[i].setRatio((double) (players[i].getNumOfWonGame()) / players[i].getNumOfPlayedGame());
		}	
		Arrays.sort(players);
		displayPlayer();
//		for (double i : ratio)
//			System.out.printf("%-5s|\n", percent.format(i));
	}

	void addPlayer(String userName, String givenName, String familyName) {

		if (isExist(userName) < 0) {
			NimPlayer newPlayer = new NimPlayer(userName, givenName, familyName);
			players[numOfPlayer] = newPlayer;
			numOfPlayer++;
		} else
			System.out.println("The player already exists.");
	}

	// Remove specific player
	void removePlayer(String userName) {
		if (isExist(userName) > -1) {
			// move elements forward to fill the removed one
			for (int i = isExist(userName); i < numOfPlayer; i++)
				players[i] = players[i + 1];
			numOfPlayer--;
		} else
			System.out.println("The player does not exist.");
	}

	// When the argument is null, remove all players.
	void removePlayer() {
		players = null;
	}

	void editPlayer(String userName, String givenName, String familyName) {
		if (isExist(userName) > -1) {
			players[isExist(userName)].setGivenName(givenName);
			players[isExist(userName)].setFamilyName(familyName);
		} else
			System.out.println("The player does not exist.");
	}

	// Reset statistics of specific user
	void resetStats(String userName) {
		if (isExist(userName) > -1) {
			players[isExist(userName)].setNumOfPlayedGame(0);
			players[isExist(userName)].setNumOfWonGame(0);
		} else
			System.out.println("The player does not exist.");
	}

	// Reset all users' statistics
	void resetStats() {
		for (int i = 0; i < numOfPlayer; i++) {
			players[i].setNumOfPlayedGame(0);
			players[i].setNumOfWonGame(0);
		}
	}

	// Display information of specific player
	void displayPlayer(int i) {
		System.out.println(players[i].getUserName() + "," + players[i].getGivenName() + "," + players[i].getFamilyName()
				+ "," + players[i].getNumOfPlayedGame() + " games," + players[i].getNumOfWonGame() + " wins");
	}

	// Display all users' statistics
	void displayPlayer() {
		for (int i = 0; i < numOfPlayer; i++)
			displayPlayer(i);
	}

	void startGame(String numberOfStones, String upperBoundOfStone, String userName1, String userName2) {
		int index1 = isExist(userName1);
		int index2 = isExist(userName2);
		System.out.println(index1);
		System.out.println(index2);
		if (index1 < 0 || index2 < 0)
			System.out.println("One of the players does not exist.");
		else {

			NimGame newGame = new NimGame();
			int winner = newGame.game(Integer.parseInt(numberOfStones), Integer.parseInt(upperBoundOfStone),
					players[index1], players[index2], keyboard);

			// Update game statistics of two players
			players[index1].setNumOfPlayedGame(players[index1].getNumOfPlayedGame() + 1);
			players[index2].setNumOfPlayedGame(players[index2].getNumOfPlayedGame() + 1);

			if (winner == 1)
				players[index1].setNumOfWonGame(players[index1].getNumOfWonGame() + 1);
			else
				players[index2].setNumOfWonGame(players[index2].getNumOfWonGame() + 1);

		}
	}

	// Check whether the given user name exists. If it exists, this method will return the index of the player in the array.
	// Otherwise, return -1, to be used in other methods.
	public int isExist(String name) {
		int i = 0;
		while (i < numOfPlayer) {
			if (players[i].getUserName().equals(name))
				return i;
			else
				i++;
		}
		return -1;
	}
}