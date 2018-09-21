package Main;

import gui.Options;
import gui.Board.PlayerInfo;
import gui.Board.RollingTheDice;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import GameSquares.Cab;
import GameSquares.GameSquare;
import GameSquares.Land;
import GameSquares.Land.state;
import GameSquares.Ownable;
import GameSquares.TransitStation;
import GameSquares.Utility;
import GameSquares.Cards.Card.CardType;

public class SaveLoad {
	// 1 -> JSON
	// 2 -> Byte
	private static int saveType = 1;
	private static final String fileOne = "savegame.txt";
	private static final String fileTwo = "savegame2.txt";
	private static final String fileThree = "savegame3.txt";
	private static final String fileFour = "savegame4.txt";

	public static void save() {
		if (saveType == 1)
			saveJSON();
		else
			saveByte();

		RollingTheDice.logAdd("Game Saved");
	}
	public static void load() {
		if (saveType == 1)
			loadJSON();
		else
			loadByte();

		RollingTheDice.logAdd("Game Loaded");
	}

	private static String squareInfo(Ownable current) {
		String condition = "-----";
		if (current instanceof Land) {
			condition = "land " + current.getID() + " " + ((Land) current).getState().toString();
		}
		if (current instanceof TransitStation) {
			condition = "transitstation " + current.getID() + " " +
					((TransitStation) current).getDepotCount();
		}
		if (current instanceof Utility) {
			if (((Utility) current).isMortgaged())
				condition = "mortgaged";
			else
				condition = "unmortgaged";
			condition = "utility " + current.getID() + " " + condition;
		}
		if (current instanceof Cab) {
			condition = "cab " + current.getID() + " " + ((Cab) current).isMortgaged()
					+ " " + ((Cab) current).standed();
		}
		return condition;
	}

	private static CardType stringToType(String s) {
		for (int i = 0; i < CardType.values().length; i++) {
			if (s.equals(CardType.values()[i].toString())) return CardType.values()[i];
		}
		return null;
	}

	private static state stringToState(String s) {
		for (int i = 0; i < state.values().length; i++) {
			if (s.equals(state.values()[i].toString())) return state.values()[i];
		}
		return null;
	}

	private static JSONObject createPlayerJSON(Player player) {
		JSONObject jp = new JSONObject();

		try {
			jp.put("name", player.getName());
			jp.put("id", player.getID());
			jp.put("money", player.getMoney());
			jp.put("location", player.getLocation());
			jp.put("jailTime", player.getJailTime());
			jp.put("doublesRolled", player.getDoublesRolled());
			jp.put("jailed", player.isJailed());

			ArrayList<String> cards = new ArrayList<String>();
			for (int i = 0; i < player.getCardsInventory().size(); i++) {
				cards.add(player.getCardsInventory().get(i).name());
			}

			ArrayList<String> sqs = new ArrayList<String>();
			for (int i = 0; i < player.getOwnedSquares().size(); i++) {
				sqs.add(squareInfo(player.getOwnedSquares().get(i)));
			}

			ArrayList<Integer> sts = new ArrayList<Integer>();
			for (int i = 0; i < player.getStocks().length; i++) {
				sts.add(player.getStocks()[i]);
			}

			jp.put("cardInventory", cards);
			jp.put("ownedSquares", sqs);
			jp.put("stocks", sts);
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return jp;
	}

	private static void saveJSON() {
		Date d = new Date();
		String time = d.toString().substring(11, 20);

		try {
			JSONArray j = new JSONArray();
			JSONArray players = new JSONArray();

			for (int i = 0; i < Main.players.length; i++) {
				players.put(createPlayerJSON(Main.players[i]));
			}

			JSONObject currentPlayer = new JSONObject();
			currentPlayer.put("currentPlayer", Main.CurrentPlayer.getName());

			JSONObject music = new JSONObject();
			music.put("musicId", Main.musicID);

			JSONObject turn = new JSONObject();
			turn.put("turn", RollingTheDice.getTurn());

			JSONObject log = new JSONObject();
			log.put("log", RollingTheDice.getLog());

			JSONObject enabledButtons = new JSONObject();
			boolean[] buttonStata = Main.board.round.getButtonEnableds();
			enabledButtons.put("rollButton", buttonStata[0]);
			enabledButtons.put("endButton", buttonStata[1]);
			enabledButtons.put("sellButton", buttonStata[2]);

			j.put(players);
			j.put(currentPlayer);
			j.put(enabledButtons);
			j.put(music);
			j.put(turn);
			j.put(log);

			System.out.println(j);

			String fileName = selectFile();
			JButton button = selectButton();
			if(button.equals(Options.fileOne))
				Options.fileOneName = time;
			else if(button.equals(Options.fileTwo))
				Options.fileTwoName = time;
			else if(button.equals(Options.fileThree))
				Options.fileThreeName = time;
			else if(button.equals(Options.fileFour))
				Options.fileFourName = time;

			FileWriter fw = new FileWriter(fileName);
			button.setText(time);
			fw.write(j.toString());
			fw.close();

			Main.stopTurnLoop();
		} catch (IOException e) {} catch (JSONException je) {}
	}

	private static void saveByte() {
		try {
			Object[] objArry = { Main.players, Main.gameSquares, Main.CurrentPlayer,
					Main.board.round.getButtonEnableds() };

			FileOutputStream f_out = new FileOutputStream("savegame.data");
			ObjectOutputStream obj_out = new ObjectOutputStream(f_out);

			// Write object to disk
			obj_out.writeObject(objArry);

			// Close OutputStreams
			f_out.close();
			obj_out.close();

		} catch (IOException e) {}
	}




	private static void loadJSON() {
		try {
			BufferedReader b_in = new BufferedReader(new FileReader(selectFile()));
			String jsonString = b_in.readLine();
			JSONArray j = new JSONArray(jsonString);

			JSONArray players = j.getJSONArray(0);
			String currentPlayerName = j.getJSONObject(1).getString("currentPlayer");
			JSONObject enabledButtons = j.getJSONObject(2);
			int musicId = j.getJSONObject(3).getInt("musicId");
			int turn = j.getJSONObject(4).getInt("turn");
			String log = j.getJSONObject(5).getString("log");

			Main.stopTurnLoop();

			Player[] gamePlayers = new Player[players.length()];
			for (int i = 0; i < players.length(); i++) {
				gamePlayers[i] = setPlayerAttr(i, players.getJSONObject(i));
				if (gamePlayers[i].getName().equals(currentPlayerName))
					Main.CurrentPlayer = gamePlayers[i];
			}

			Main.players = gamePlayers;

			boolean[] buttons = { enabledButtons.getBoolean("rollButton"),
					enabledButtons.getBoolean("endButton"),
					enabledButtons.getBoolean("sellButton") };
			Main.board.round.setButtonEnableds(buttons);

			Main.musicID = musicId;
			RollingTheDice.setTurn(turn);
			RollingTheDice.setLog(log);

			PlayerInfo.refreshData();
			Main.board.initiateLoadProtection();

			b_in.close();

		} catch (IOException e) {
			e.printStackTrace(System.out);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	private static void loadByte() {
		try {
			FileInputStream f_in = new FileInputStream("savegame.data");
			ObjectInputStream obj_in = new ObjectInputStream(f_in);

			// Read Data
			Object[] obj = (Object[]) obj_in.readObject();

			// Load Game
			Main.stopTurnLoop();
			Main.players = (Player[]) obj[0];
			Main.gameSquares = (GameSquare[]) obj[1];
			Main.CurrentPlayer = (Player) obj[2];
			Main.board.round.setButtonEnableds((boolean[]) obj[3]);

			// Check for error
			if (Main.players == null || Main.gameSquares == null)
				System.out.println("!!! Error on loading objects !!!");

			// Save Protection
			PlayerInfo.refreshData();
			Main.board.initiateLoadProtection();

			// Close InputStreams
			f_in.close();
			obj_in.close();

		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace(System.out);
		}
	}


	private static Player setPlayerAttr(int playerID, JSONObject o) {
		Player p = null;
		try {
			p = new Player(playerID, o.getString("name"), o.getInt("money"), o.getInt("location"), Main.gameSquares);
			p.setId(o.getInt("id"));
			p.setJailTime(o.getInt("jailTime"));
			p.setJailed(o.getBoolean("jailed"));

			JSONArray cards = o.getJSONArray("cardInventory");
			for (int i = 0; i < cards.length(); i++) {
				p.addToCardInventory(stringToType(cards.getString(i)));
			}

			JSONArray stocks = o.getJSONArray("stocks");
			for(int i=0; i<stocks.length();i++){
				int stockCount = stocks.getInt(i);
				for(int j=0; j<stockCount;i++) p.giveStock(i);
			}


			JSONArray squares = o.getJSONArray("ownedSquares");
			for (int i = 0; i < squares.length(); i++) {
				String[] currentSquareInfo = squares.getString(i).split(" ");
				String type = currentSquareInfo[0];
				int id = Integer.parseInt(currentSquareInfo[1]);

				if(Main.gameSquares[id].getOwner()!= p)
					p.getOwnership(Main.gameSquares[id]);

				switch (type) {
				case "land":
					((Land) Main.gameSquares[id]).setState(stringToState(currentSquareInfo[2]));
					break;
				case "transtation":
					((TransitStation) Main.gameSquares[id]).setDepotCount(Integer.parseInt(currentSquareInfo[2]));
					break;
				case "utility":
					if (currentSquareInfo[2].equals("morgaged"))
						((Utility) Main.gameSquares[id]).setMortgage(true);
					break;
				case "cab":
					((Cab) Main.gameSquares[id]).setStanded(Boolean.getBoolean(currentSquareInfo[2]));
					((Cab) Main.gameSquares[id]).setMortgage(Boolean.getBoolean(currentSquareInfo[3]));
					break;
				}

			}

			o.getJSONArray("stocks");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return p;
	}

	private static String selectFile(){
		int fileSelection = Options.getFile();
		switch(fileSelection){
		case 0:
			return fileOne;
		case 1:
			return fileTwo;
		case 2:
			return fileThree;
		case 3:
			return fileFour;
		default:
			return fileOne;
		}
	}

	private static JButton selectButton(){
		int fileSelection = Options.getFile();
		switch(fileSelection){
		case 0:
			return Options.fileOne;
		case 1:
			return Options.fileTwo;
		case 2:
			return Options.fileThree;
		case 3:
			return Options.fileFour;
		default:
			return Options.fileOne;
		}
	}
}
