package unittesting;
import GameSquares.GameSquare;
import GameSquares.Land;
import GameSquares.Land.state;
import GameSquares.Cards.ChanceCards.Hurricane;
import Main.Player;
import Main.Main;
import static org.junit.Assert.*;

import org.junit.Test;

public class HurricaneCardTest {

	@Test
	public void testUnimprovedLands() {
		Main.initializeGameSquares();
		GameSquare [] squares = Main.gameSquares;
		Player temp = new Player(0,"abdullah",squares);
		
		Land testerLand1 = (Land) squares[1];
		Land testerLand2 = (Land) squares[3];
		
		temp.buySquare(testerLand1);
		temp.buySquare(testerLand2);
		
		Hurricane card = new Hurricane();
		card.testing = true;
		card.onDraw(temp);

		assertEquals("testing if the card works when player has majority ownership but no houses",state.unImproved,testerLand1.currentState);
		
	}
	
	@Test
	public void testIfLandHasHouse() {
		Main.initializeGameSquares();
		GameSquare [] squares = Main.gameSquares;
		Player temp = new Player(0,"abdullah",squares);
		
		Land testerLand1 = (Land) squares[1];
		Land testerLand2 = (Land) squares[3];
		
		temp.buySquare(testerLand1);
		temp.buySquare(testerLand2);
		
		testerLand1.setState(state.house);
		
		Hurricane card = new Hurricane();
		card.testing = true;
		card.onDraw(temp);

		assertEquals("testing if works for a house built land",state.unImproved,testerLand1.currentState);
		
	}
	
	@Test
	public void testIfLandHasHotel() {
		Main.initializeGameSquares();
		GameSquare [] squares = Main.gameSquares;
		Player temp = new Player(0,"abdullah",squares);
		
		Land testerLand1 = (Land) squares[1];
		Land testerLand2 = (Land) squares[3];
		
		temp.buySquare(testerLand1);
		temp.buySquare(testerLand2);
		
		testerLand1.setState(state.hotel);
		
		Hurricane card = new Hurricane();
		card.testing = true;
		card.onDraw(temp);

		assertEquals("testing if works for a hotel built land",state.fourHouse,testerLand1.currentState);
		
	}
	
	@Test
	public void testIfLandHasSkyscraper() {
		Main.initializeGameSquares();
		GameSquare [] squares = Main.gameSquares;
		Player temp = new Player(0,"abdullah",squares);
		
		Land testerLand1 = (Land) squares[1];
		Land testerLand2 = (Land) squares[3];
		
		temp.buySquare(testerLand1);
		temp.buySquare(testerLand2);
		
		testerLand1.setState(state.skyscraper);
		
		Hurricane card = new Hurricane();
		card.testing = true;
		card.onDraw(temp);

		assertEquals("testing if works for a skyscraper built land",state.hotel,testerLand1.currentState);
		
	}
	
	@Test
	public void testIfPlayerDoesNotHaveMajorityOwnership() {
		Main.initializeGameSquares();
		GameSquare [] squares = Main.gameSquares;
		Player temp = new Player(0,"abdullah",squares);
		
		Land testerLand1 = (Land) squares[1];
		
		temp.buySquare(testerLand1);
		
		Hurricane card = new Hurricane();
		card.testing = true;
		card.onDraw(temp);

		assertEquals("testing if works for a non-majority ownership land",state.unImproved,testerLand1.currentState);
		
	}
	

}
