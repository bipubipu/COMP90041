
/* This program is to define the player and receives the number of stones to remove from players. 
 * A player of Nim game has user name, given name and family. 
 * Besides, the number of wins and games played are recorded by the system.  
 * Yunqiu Weng, 8228509, 29/04/2018
 */
import java.util.Scanner;

public class NimPlayer {
	private String userName, givenName, familyName;
	private int numOfPlayedGame, numOfWonGame;
	private double ratio;

	// For certain player, this method gets the input number from the player.
	// If the player enters invalid number, this method would give the notification.
	public int removeStone(String givenName, int numberOfStones, int upperBoundOfStone, Scanner keyboard) {
		
		int numberToRemove;
		// When the number of remained stones is smaller than upperBoundOfStone, then
		// the maximum number to be removed is the stones left. So the value of
		// validRemove depends on numberOfStones and upperBoundOfStone.
		int validRemove = (upperBoundOfStone > numberOfStones) ? numberOfStones : upperBoundOfStone;
		
		System.out.println(givenName + "\'s turn - remove how many?");
		numberToRemove = keyboard.nextInt();
		keyboard.nextLine();
		System.out.print("\n");
		
		if (numberToRemove > validRemove | numberToRemove < 1) {
			System.out.println("Invalid move. You must remove between 1 and " + validRemove + " stones.\n");
			return -1; // -1 means that the move is invalid which will be used in NimGame for input again.
		}
		return numberToRemove;
	}

	public NimPlayer() {

	}

	public NimPlayer(String userName,String familyName, String givenName) {
		this.userName = userName;
		this.givenName = givenName;
		this.familyName = familyName;
		numOfPlayedGame = 0;
		numOfWonGame = 0;
		ratio = 0.0;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getGivenName() {
		return givenName;
	}

	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}

	public String getFamilyName() {
		return familyName;
	}

	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	public int getNumOfPlayedGame() {
		return numOfPlayedGame;
	}

	public void setNumOfPlayedGame(int numOfPlayedGame) {
		this.numOfPlayedGame = numOfPlayedGame;
	}

	public int getNumOfWonGame() {
		return numOfWonGame;
	}

	public void setNumOfWonGame(int numOfWonGame) {
		this.numOfWonGame = numOfWonGame;
	}
	
	public double getRatio() {
		return ratio;
	}

	public void setRatio(double ratio) {
		this.ratio = ratio;
	}
}