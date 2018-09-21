package Main;

import gui.Board.PlayerInfo;
import GameSquares.GameSquare;
import GameSquares.Land;
import GameSquares.Land.color;
import GameSquares.Ownable;
import GameSquares.Cards.ChanceDeck;
import GameSquares.Cards.CommunityChestDeck;

public class Admin extends Main {

	ChanceDeck          chanceDeck    = null;
	CommunityChestDeck  communityDeck = null;
	static GameSquare[] gameSquares   = null;
	Player[]            players       = null;


	/**************************/
	/** Player admin methods **/
	/**************************/
	// Move PLAYER by AMOUNT and CALL onArrive()
	public static <T> void movePlayerBy(T player, int amount) {
		System.out.println("ADMIN -> Player:" + findPlayer(player).getName()
				+ " is moved by " + amount);
		findPlayer(player).moveBy(amount);
		refreshUI();
	}
	// Move PLAYER to given LOCATION and CALL onArrive()
	public static <T> void movePlayerTo(T player, T gameSquare) {
		System.out.println("ADMIN -> Player:" + findPlayer(player).getName()
				+ " is moved to location with id " + findGameSquare(gameSquare).getID());
		findPlayer(player).moveTo(findGameSquare(gameSquare).getID());
		refreshUI();
	}
	// Move PLAYER to given LOCATION but WONT CALL location's onArrive()
	public static <T> void movePlayerToForced(T player, T location) {
		System.out.println("ADMIN -> Player:" + findPlayer(player).getName()
				+ "'s Location is set to location with id " + location);
		findPlayer(player).setLocation(findGameSquare(location));
		refreshUI();
	}


	// Increase PLAYER's MONEY by AMOUNT
	public static <T> void increaseMoneyBy(T player, int amount) {
		System.out.println("ADMIN -> Player:" + findPlayer(player).getName()
				+ "'s money is increased by " + amount + " and is now "
				+ (findPlayer(player).getMoney() + amount));
		findPlayer(player).addMoney(amount);
		refreshUI();
	}
	// Decrease PLAYER's MONEY by AMOUNT
	public static <T> void decreaseMoneyBy(T player, int amount) {
		System.out.println("ADMIN -> Player:" + findPlayer(player).getName()
				+ "'s money is decreased by " + amount + " and is now "
				+ (findPlayer(player).getMoney() - amount));
		findPlayer(player).reduceMoney(amount);
		refreshUI();
	}
	// Sets PLAYER's MONEY to given AMOUNT
	public static <T> void setMoney(T player, int amount) {
		System.out.println("ADMIN -> Player:" + findPlayer(player).getName()
				+ "'s money is set to " + amount);
		findPlayer(player).setMoney(amount);
		refreshUI();
	}

	// Give PLAYER the ownership of OWNABLE
	public static <T> void giveOwnership(T player, T ownable) {
		if(findOwnable(ownable) != null){	
			System.out.println("ADMIN -> Player:" + findPlayer(player).getName()
					+ " is given the ownership of "
					+ ((Ownable) findOwnable(ownable)).getName());
			findPlayer(player).getOwnership(findGameSquare(ownable));
			refreshUI();
		}
	}
	// Remove the ownership of OWNABLE from PLAYER
	public static <T> void removeOwnership(T player, T ownable) {
		if(findOwnable(ownable) != null){	
			System.out.println("ADMIN -> Player:" + findPlayer(player).getName() + "'s ownership is removed from "
					+ findOwnable(ownable).getName());
			findPlayer(player).removeOwnership((GameSquare) findOwnable(ownable));
			refreshUI();
		}
	}

	// Upgrage Ownable if possible
	public static <T> void upgradeOwnable(T ownable) {
		findOwnable(ownable).upgrade();
		System.out.println("ADMIN -> Ownable " + findOwnable(ownable).getName() + " is upgraded." + " "
				+ findOwnable(ownable).getUpgradeState());
	}
	// Downgrade Ownable if possible
	public static <T> void downgradeOwnable(T ownable) {
		findOwnable(ownable).downgrade();
		System.out.println("ADMIN -> Ownable " + findOwnable(ownable).getName() + " is upgraded." + " "
				+ findOwnable(ownable).getUpgradeState());
	}

	// Return PLAYER's ID
	public static <T> int getPlayerID(T player) {
		return findPlayer(player).getID();
	}

	// Return PLAYER's LOCATION
	public static <T> int getPlayerLocation(T player) {
		return findPlayer(player).getLocation();
	}

	// Return PLAYER's MONEY
	public static <T> int getPlayerMoney(T player) {
		return findPlayer(player).getMoney();
	}

	// Return PLAYER's NAME
	public static <T> String getPlayerName(T player) {
		return findPlayer(player).getName();
	}

	// Return NEXT PLAYER's NAME
	public static <T> String getNextPlayerName(T player) {
		return Main.players[(findPlayer(player).getID() + 1) % Main.players.length].getName();
	}

	// Returns IF the PLAYER HAS any LAND
	public static <T> boolean playerHasLand(T player) {
		return !findPlayer(player).getOwnedLands().isEmpty();
	}

	// Moves PLAYER to NEXT NOT OWNED LAND
	public static void movePlayerToNextNeutralLand(Player player) {
		for (int i = 0; i < Main.gameSquares.length; i++) {
			GameSquare nextGameSquare = Main.gameSquares[(player.getLocation() + i) % Main.gameSquares.length];
			if (nextGameSquare instanceof Land && !((Land) nextGameSquare).isOwned()) {
				movePlayerTo(player, nextGameSquare);
				refreshUI();
				break;
			}
		}
	}

	// Moves PLAYER to NEXT NOT OWNED LAND
	public static void movePlayerToNextNeutralLand(int playerID, boolean even) {
		int start = Main.players[playerID].getLocation();
		int currentCheck = start + 1;
		while (currentCheck != start) {
			System.out.print("now at " + currentCheck);
			if (even) {
				if (currentCheck == 5)
					currentCheck = 47;
				else if (currentCheck == 15)
					currentCheck = 105;
				else if (currentCheck == 25)
					currentCheck = 75;
				else if (currentCheck == 35)
					currentCheck = 117;
				else if (currentCheck == 47)
					currentCheck = 5;
				else if (currentCheck == 105)
					currentCheck = 15;
				else if (currentCheck == 75)
					currentCheck = 25;
				else if (currentCheck == 117) currentCheck = 35;
			}
			if (currentCheck == 39)
				currentCheck = 0;
			else if (currentCheck == 120)
				currentCheck = 97;
			else if (currentCheck == 96) currentCheck = 40;

			GameSquare nextGameSquare = Main.gameSquares[currentCheck % Main.gameSquares.length];
			if (nextGameSquare instanceof Ownable && !((Ownable) nextGameSquare).isOwned()) {
				movePlayerTo(Main.players[playerID], nextGameSquare);
				refreshUI();
				break;
			}
			currentCheck++;
		}
	}

	// Moves PLAYER to NEXT LAND
	public static void movePlayerToNextLand(Player player) {
		for (int i = 1; i < Main.gameSquares.length; i++) {
			GameSquare nextGameSquare = Main.gameSquares[(player.getLocation() + i) % Main.gameSquares.length];
			if (nextGameSquare instanceof Land) {
				movePlayerTo(player, nextGameSquare);
				refreshUI();
				break;
			}
		}
	}

	// Moves PLAYER to NEXT LAND
	public static void movePlayerToNextLand(int playerID, boolean even) {
		int start = Main.players[playerID].getLocation();
		int currentCheck = start + 1;
		while (currentCheck != start) {
			System.out.print("now at " + currentCheck);
			if (even) {
				if (currentCheck == 5)
					currentCheck = 47;
				else if (currentCheck == 15)
					currentCheck = 105;
				else if (currentCheck == 25)
					currentCheck = 75;
				else if (currentCheck == 35)
					currentCheck = 117;
				else if (currentCheck == 47)
					currentCheck = 5;
				else if (currentCheck == 105)
					currentCheck = 15;
				else if (currentCheck == 75)
					currentCheck = 25;
				else if (currentCheck == 117) currentCheck = 35;
			}

			if (currentCheck == 39)
				currentCheck = 0;
			else if (currentCheck == 120)
				currentCheck = 97;
			else if (currentCheck == 96) currentCheck = 40;

			GameSquare nextGameSquare = Main.gameSquares[currentCheck % Main.gameSquares.length];
			if (nextGameSquare instanceof Ownable) {
				movePlayerTo(Main.players[playerID], nextGameSquare);
				refreshUI();
				break;
			}
			currentCheck++;
		}
	}

	/************************/
	/** Land admin methods **/
	/************************/
	// Change LAND's name to NAME
	public void setName(GameSquare land, String name) {
		System.out.println("ADMIN -> LAND:" + ((Land) land).getName()
				+ "'s name is changed to " + name);
		((Land) land).setName(name);
	}

	// Change LANDS's color to COLOR
	public void setColor(GameSquare land, color color) {
		System.out.println("ADMIN -> LAND:" + ((Land) land).getName()
				+ "'s color is changed to " + color.toString());
		((Land) land).setColor(color);
	}

	// Change LAND's price to AMOUNT
	public void setPrice(GameSquare land, int amount) {
		System.out.println("ADMIN -> LAND:" + ((Land) land).getName()
				+ "'s price is changed to " + amount);
		((Land) land).setPrice(amount);
	}

	// Change LAND's rent to AMOUNT
	public void setRent(GameSquare land, int amount) {
		System.out.println("ADMIN -> LAND:" + ((Land) land).getName()
				+ "'s rent is changed to " + amount);
		((Land) land).setRent(amount);
	}

	// Checks if all LAND's are OWNED
	public static boolean allLandsOwned() {
		for (GameSquare currentLand : Main.gameSquares)
			if (currentLand instanceof Ownable && !((Ownable) currentLand).isOwned()) return false;
		return true;
	}

	/*********************/
	/** General methods **/
	/*********************/
	private static void refreshUI() {
		PlayerInfo.refreshData();
	}

	private static <T> Player findPlayer(T player) {
		Player pl = null;
		if (player instanceof Player)
			pl = (Player) player;
		else if (player instanceof Integer && Main.players.length - 1 >= (Integer) player)
			pl = Main.players[(int) player];

		return pl;
	}

	private static <T> GameSquare findGameSquare(T gameSquare) {
		GameSquare gsq = null;
		if (gameSquare instanceof GameSquare)
			gsq = (GameSquare) gameSquare;
		else if (gameSquare instanceof Integer && Main.gameSquares.length - 1 >= (Integer) gameSquare)
			gsq = Main.gameSquares[(int) gameSquare];
		else if (gameSquare instanceof String) {
			for (GameSquare tempgsq : Main.gameSquares)
				if (tempgsq instanceof Ownable && ((Ownable) tempgsq).getName().equals(gameSquare))
					return (GameSquare) tempgsq;
				else if (((String) gameSquare).length() > 7 && ((String) gameSquare).substring(0, 6).equals("Chance"))
					return Main.gameSquares[Integer.parseInt(((String) gameSquare).substring(7, ((String) gameSquare).length()))];
				else if (((String) gameSquare).length() > 15 && ((String) gameSquare).substring(0, 14).equals("CommunityChest"))
					return Main.gameSquares[Integer.parseInt(((String) gameSquare).substring(15, ((String) gameSquare).length()))];
				else if (((String) gameSquare).length() < 9 && ((String) gameSquare).substring(0, 3).equals("Cab"))
					return Main.gameSquares[Integer.parseInt(((String) gameSquare).substring(4, ((String) gameSquare).length()))];
				else if (tempgsq.getType().equals(gameSquare))
					return tempgsq;
		}
		return gsq;
	}
	private static <T> Ownable findOwnable(T ownable) {
		Ownable ownbl = null;
		if (ownable instanceof Ownable)
			ownbl = (Ownable) ownable;
		else if (ownable instanceof Integer && Main.gameSquares.length - 1 >= (Integer) ownable)
			ownbl = (Ownable) Main.gameSquares[(int) ownable];
		else if (ownable instanceof String) {
			for (GameSquare gameSquare : Main.gameSquares)
				if (gameSquare instanceof Ownable && ((Ownable) gameSquare).getName().equals(ownable)) return (Ownable) gameSquare;
		}
		return ownbl;
	}
	public static int getPlayerCount() {
		return Main.players.length;
	}

	public static int getGameSquareCount() {
		return Main.gameSquares.length;
	}

	public static GameSquare[] getGameSquares() {
		return Main.gameSquares;
	}
}
