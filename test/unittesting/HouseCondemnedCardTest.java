package unittesting;

import static org.junit.Assert.*;
import GameSquares.GameSquare;
import GameSquares.Land;
import GameSquares.Land.state;
import GameSquares.Cards.CommunityChestCards.HouseCondemned;
import Main.Player;
import Main.Main;

import org.junit.Test;

public class HouseCondemnedCardTest {

	@Test
	public void testWithNoHouses() {

		Main.initializeGameSquares();
		GameSquare [] squares = Main.gameSquares;
		Player temp = new Player(0,"abdullah",squares);
		
		Land testerLand1 = (Land) squares[1];
		Land testerLand2 = (Land) squares[3];
		
		temp.buySquare(testerLand1);
		temp.buySquare(testerLand2);
		
		HouseCondemned houseCard = new HouseCondemned();
		houseCard.testing=true;
		
		houseCard.onDraw(temp);
		
		assertEquals("testing if it works when player has no house",state.unImproved,testerLand1.currentState);
		
	}
	
	@Test
	public void testWhenLandHasHouses() {

		Main.initializeGameSquares();
		GameSquare [] squares = Main.gameSquares;
		Player temp = new Player(0,"abdullah",squares);
		
		Land testerLand1 = (Land) squares[1];
		Land testerLand2 = (Land) squares[3];
		
		temp.buySquare(testerLand1);
		temp.buySquare(testerLand2);
		testerLand1.setState(state.house);
		
		HouseCondemned houseCard = new HouseCondemned();
		houseCard.testing=true;
		
		int InitialPlayerMoney = temp.getMoney();
		houseCard.onDraw(temp);
		int PlayerMoneyFinal = temp.getMoney();
		
		assertTrue("testing if it works when player has no house",PlayerMoneyFinal > InitialPlayerMoney);
		assertEquals("testing if it works when player has no house",state.unImproved,testerLand1.currentState);
		
	}

}
