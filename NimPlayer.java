
/* This program fetches number of stones to remove and stores Player information. 
 * Yunqiu Weng, 8228509, 23/04/2018
 */
//import java.util.Scanner;

public class NimPlayer {
	private String userName, givenName, familyName;
	// private int numOfPlayedGame, numOfWonGame;
	
	/*
	 * public int removeStone (String userame, int numberOfStones, int
	 * upperBoundOfStone, Scanner keyboard) { int numberToRemove;
	 * System.out.println(name + "\'s turn - remove how many?"); numberToRemove =
	 * keyboard.nextInt(); System.out.print("\n"); if (numberToRemove >
	 * upperBoundOfStone) System.out.print("Wrong Input"); return numberToRemove; }
	 */

	public NimPlayer(String userName, String givenName, String familyName) {
		this.userName = userName;
		this.givenName = givenName;
		this.familyName = familyName;
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
	
}