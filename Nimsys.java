
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

public class Nimsys {
	public static Scanner keyboard;
	public static final int MAX_PLAYERS = 100;
	int numOfPlayer;
	NimPlayer[] players = new NimPlayer[MAX_PLAYERS];

	public Nimsys() {
		int numOfPlayer = 0;
	}
	
	public static void main(String[] args) {

		keyboard = new Scanner(System.in);
		Nimsys controller = new Nimsys();

		System.out.println("Welcome to Nim");
		System.out.print("\n$");
		String command = keyboard.nextLine();

		while (!command.equals("exit")) {
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
						controller.players = null;
					}
				} else {
					System.out.println(splitCommand[1]);
					controller.removePlayer(splitCommand[1]);
				}
			} 
			
			else if (commandType.equals("editplayer")) {
				controller.editPlayer(splitCommand[1], splitCommand[2], splitCommand[3]);

			} else if (commandType.equals("resetstats")) {

			} else if (commandType.equals("displayplayer")) {

			} else if (commandType.equals("rankings")) {

			} else if (commandType.equals("startgame")) {

			}
			for (int i=0; i< controller.numOfPlayer; i++)
			    System.out.println(controller.players[i].getGivenName());
			System.out.print("\n$");
			command = keyboard.nextLine();
		}
		System.out.println();
		System.exit(0);
	}

	void addPlayer(String userName, String givenName, String familyName) {
		if (isExist(userName) <0) {
			NimPlayer newPlayer = new NimPlayer(userName, givenName, familyName);
			players[numOfPlayer] = newPlayer;
			numOfPlayer++;
			System.out.println(numOfPlayer);
		} else
			System.out.println("The player already exists.");
	}
	void removePlayer(String userName) {
		if (isExist(userName) > -1) {
			System.out.println(isExist(userName));
			players[isExist(userName)] = null;
//			System.out.println(players[numOfPlayer].getUserName());
			numOfPlayer--;
		} else
			System.out.println("The player does not exist.");
	}
	void editPlayer(String userName, String givenName, String familyName) {
		if (isExist(userName) > -1) {
			players[isExist(userName)].setGivenName(givenName);
			players[isExist(userName)].setFamilyName(familyName); 
		} else
			System.out.println("The player does not exist.");
	}
	
	
	
	int isExist(String name) {
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