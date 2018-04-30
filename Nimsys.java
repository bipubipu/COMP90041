
/* NimSys, NimPlayer and NimGame files enable a two-player game. 
 * This class file manages the game playing process, including processing the commands. 
 * Author: Yunqiu Weng, Student Id: 8228509, 29/04/2018
 */
import java.util.Scanner;
import java.util.Arrays;
import java.text.DecimalFormat;
import java.util.Comparator;

public class Nimsys {
	public static Scanner keyboard;
	public static final int MAX_PLAYERS = 100;

	public NimPlayer[] players = new NimPlayer[MAX_PLAYERS];
	private int numOfPlayer;

	public static void main(String[] args) {

		keyboard = new Scanner(System.in);
		Nimsys controller = new Nimsys();

		System.out.println("Welcome to Nim");
		System.out.print("\n$");
		String command = keyboard.nextLine();

		while (!command.equals("exit")) {
			// The entered command consists of two parts. The first one indicates the type of the command, including
			// addplayer, removeplayer, editplayer, resetstats, displayplayer, rankings, startgame, exit.
			String[] splitCommand = command.split(",| ");
			String commandType = splitCommand[0];

			if (commandType.equals("addplayer"))
				controller.addPlayer(splitCommand[1], splitCommand[2], splitCommand[3]);

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

			else if (commandType.equals("editplayer"))
				controller.editPlayer(splitCommand[1], splitCommand[2], splitCommand[3]);

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
			// Ties are sorted on usernames alphabetically. Only first 10 will be displayed. Can be desc or asc order.
			else if (commandType.equals("rankings")) {
				if (splitCommand.length > 1 && splitCommand[1].equals("asc"))
					controller.rankByRatioAsc();
				else
					controller.rankByRatioDesc();
			}

			else if (commandType.equals("startgame"))
				controller.startGame(splitCommand[1], splitCommand[2], splitCommand[3], splitCommand[4]);

			System.out.print("\n$");
			command = keyboard.nextLine();
		}
		System.out.println();
		System.exit(0);
	}

	private void rankByRatioDesc() {

		Arrays.sort(players, 0, numOfPlayer, new Comparator<NimPlayer>() {
			public int compare(NimPlayer p1, NimPlayer p2) {
				if (p1.getRatio() == p2.getRatio()) {
					if (p1.getUserName().compareTo(p2.getUserName()) < 0)
						return -1;
					if (p1.getUserName().compareTo(p2.getUserName()) > 0)
						return 1;
					else
						return 0;
				} else
					return p1.getRatio() > p2.getRatio() ? -1 : 1;
			}
		});

		displayRank();
	}

	private void rankByRatioAsc() {

		Arrays.sort(players, 0, numOfPlayer, new Comparator<NimPlayer>() {
			public int compare(NimPlayer p1, NimPlayer p2) {
				if (p1.getRatio() == p2.getRatio()) {
					if (p1.getUserName().compareTo(p2.getUserName()) < 0)
						return -1;
					if (p1.getUserName().compareTo(p2.getUserName()) > 0)
						return 1;
					else
						return 0;
				} else
					return p1.getRatio() > p2.getRatio() ? 1 : -1;
			}
		});

		displayRank();
	}

	private void displayRank() {

		DecimalFormat percent = new DecimalFormat("0%");
		for (int i = 0; i < ((10 > numOfPlayer) ? numOfPlayer : 10); i++) {
			System.out.printf("%-5s| %02d games | %s %s", percent.format(players[i].getRatio()),
					players[i].getNumOfPlayedGame(), players[i].getGivenName(), players[i].getFamilyName());
			System.out.println();
		}
	}
	
	// Will tell player already exists when adding unsuccessfully.
	private void addPlayer(String userName, String familyName, String givenName) {

		if (isExist(userName) < 0) {
			NimPlayer newPlayer = new NimPlayer(userName, familyName, givenName);
			players[numOfPlayer] = newPlayer;
			numOfPlayer++;
		} else
			System.out.println("The player already exists.");
	}

	// Remove specific player
	private void removePlayer(String userName) {
		if (isExist(userName) > -1) {
			// move elements forward to fill the removed one
			for (int i = isExist(userName); i < numOfPlayer; i++)
				players[i] = players[i + 1];
			numOfPlayer--;
		} else
			System.out.println("The player does not exist.");
	}
	
	// Can only edit an existing player
	private void editPlayer(String userName, String familyName, String givenName) {
		if (isExist(userName) > -1) {
			players[isExist(userName)].setGivenName(givenName);
			players[isExist(userName)].setFamilyName(familyName);
		} else
			System.out.println("The player does not exist.");
	}

	// Reset statistics of specific user
	private void resetStats(String userName) {
		if (isExist(userName) > -1) {
			players[isExist(userName)].setNumOfPlayedGame(0);
			players[isExist(userName)].setNumOfWonGame(0);
		} else
			System.out.println("The player does not exist.");
	}

	// Reset all users' statistics
	private void resetStats() {
		for (int i = 0; i < numOfPlayer; i++) {
			players[i].setNumOfPlayedGame(0);
			players[i].setNumOfWonGame(0);
		}
	}

	// Display information of specific player
	private void displayPlayer(int i) {
		System.out.println(players[i].getUserName() + "," + players[i].getGivenName() + "," + players[i].getFamilyName()
				+ "," + players[i].getNumOfPlayedGame() + " games," + players[i].getNumOfWonGame() + " wins");
	}

	// Display all users' statistics
	private void displayPlayer() {
		Arrays.sort(players, 0, numOfPlayer, new Comparator<NimPlayer>() {
			public int compare(NimPlayer p1, NimPlayer p2) {
				return p1.getUserName().compareTo(p2.getUserName());
			}
		});
		for (int i = 0; i < numOfPlayer; i++)
			displayPlayer(i);
	}

	private void startGame(String numberOfStones, String upperBoundOfStone, String userName1, String userName2) {
		int index1 = isExist(userName1);
		int index2 = isExist(userName2);

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
			for (int i = 0; i < numOfPlayer; i++) {
				if (players[i].getNumOfPlayedGame() != 0)
					players[i].setRatio((double) (players[i].getNumOfWonGame()) / players[i].getNumOfPlayedGame());
			}
		}
	}

	// Check whether the given user name exists. If it exists, this method will
	// return the index of the player in the array.
	// Otherwise, return -1, to be used in other methods.
	private int isExist(String name) {
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