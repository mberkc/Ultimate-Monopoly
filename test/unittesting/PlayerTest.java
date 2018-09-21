package unittesting;
import static org.junit.Assert.*;

import org.junit.Test;
import Main.Main;
import GameSquares.GameSquare;
import Main.Player;

public class PlayerTest{
	
	@Test
	public void testTransitPassOdd(){	 
		Main.initializeGameSquares();
		GameSquare [] squares = Main.gameSquares;
		Player temp = new Player(0,"kerem",squares);
		temp.setLocation(1);
		temp.testing = true;
		temp.moveBy(5);
		int locationOfPlayer = temp.getLocation();
		assertTrue("repOk?",temp.repOK());
		assertEquals("testing of passing from railroad with rolled dice with odd value", 6, locationOfPlayer);
		
	}
	
	@Test
	public void testTransitPassEven(){
		Main.initializeGameSquares();
		GameSquare [] squares = Main.gameSquares;
		Player temp = new Player(0,"kerem",squares);
		temp.setLocation(1);
		temp.testing = true;
		temp.moveBy(6);
		int locationOfPlayer = temp.getLocation();
		
		assertTrue("repOk?",temp.repOK());
		assertEquals("testing of passing from railroad with rolled dice with even value", 49, locationOfPlayer);
		
	}

	
	@Test
	public void testNOTransitPassPayDayEven(){
		
		Main.initializeGameSquares();
		GameSquare [] squares = Main.gameSquares;
		Player temp = new Player(0,"kerem",squares);
		temp.setLocation(67);
		temp.testing = true;
		
		int InitialMoneyOfPlayer = temp.getMoney();
		System.out.println(InitialMoneyOfPlayer);
		temp.moveBy(4);
		int FinalMoneyOfPlayer = temp.getMoney();
		System.out.println(FinalMoneyOfPlayer);
		
		int differenceBetweenMoney = FinalMoneyOfPlayer - InitialMoneyOfPlayer;
		
		assertTrue("repOk?",temp.repOK());
		assertEquals("testing of passing from Pay Day square with rolled dice with even value", 400,differenceBetweenMoney);
		
	}
	
	@Test
	public void testNOTransitPassPayDayOdd(){
		
		Main.initializeGameSquares();
		GameSquare [] squares = Main.gameSquares;
		Player temp = new Player(0,"kerem",squares);
		temp.setLocation(67);
		temp.testing = true;
		
		int InitialMoneyOfPlayer = temp.getMoney();
		temp.moveBy(3);
		int FinalMoneyOfPlayer = temp.getMoney();
		
		int differenceBetweenMoney = FinalMoneyOfPlayer - InitialMoneyOfPlayer;
		
		assertTrue("repOk?",temp.repOK());
		assertEquals("testing of passing from Pay Day square with rolled dice with odd value",300,differenceBetweenMoney);
		
	}

	@Test
	public void testNOTransitPassBonus(){
		
		Main.initializeGameSquares();
		GameSquare [] squares = Main.gameSquares;
		Player temp = new Player(0,"kerem",squares);
		temp.setLocation(100);
		temp.testing = true;
		
		int InitialMoneyOfPlayer = temp.getMoney();
		temp.moveBy(4);
		int FinalMoneyOfPlayer = temp.getMoney();
		
		int differenceBetweenMoney = FinalMoneyOfPlayer - InitialMoneyOfPlayer;
		
		assertTrue("repOk?",temp.repOK());
		assertEquals("testing of passing from Bonus square", 250, differenceBetweenMoney);
		
	}
	
	@Test
	public void testNOTransitPassStartSquare(){
		
		Main.initializeGameSquares();
		GameSquare [] squares = Main.gameSquares;
		Player temp = new Player(0,"kerem",squares);
		temp.setLocation(39);
		temp.testing = true;
		
		int InitialMoneyOfPlayer = temp.getMoney();
		temp.moveBy(2);
		int FinalMoneyOfPlayer = temp.getMoney();
		
		int differenceBetweenMoney = FinalMoneyOfPlayer - InitialMoneyOfPlayer;
		
		assertTrue("repOk?",temp.repOK());
		assertEquals("testing of passing from the start square", 200, differenceBetweenMoney);
		
	}
	
	@Test
	public void testNOTPassingFromBonusTransitPayDay(){
		
		Main.initializeGameSquares();
		GameSquare [] squares = Main.gameSquares;
		Player temp = new Player(0,"kerem",squares);
		temp.setLocation(0);
		temp.testing = true;
		temp.moveBy(4);
		
		int locationOfPlayer = temp.getLocation();
		
		assertTrue("repOk?",temp.repOK());
		assertEquals("Test if player passed none of the squares above.", 4, locationOfPlayer);
		
	}
	
	@Test
	public void testPayMethod(){
		
		Player first = new Player(0,"kerem",null);
		Player second = new Player(1,"abdullah",null);
		
		int firstInitialMoney = first.getMoney();
		int secondInitialMoney = second.getMoney();
		
		first.pay(second, 50);
		
		int firstFinalMoney = first.getMoney();
		int secondFinalMoney = second.getMoney();
		
		assertTrue("repOk?",first.repOK());
		assertTrue("repOk?",second.repOK());
		assertTrue("Did the money of first player get lower?",firstInitialMoney > firstFinalMoney);
		assertTrue("Did the money of second player get higher?",secondInitialMoney < secondFinalMoney);
	
	}
	
	@Test
	public void testBuyMethodWithLand(){
		
		Main.initializeGameSquares();
		GameSquare [] squares = Main.gameSquares;
		Player temp = new Player(0,"kerem",squares);
		
		temp.buySquare(squares[1]);
		
		assertTrue("repOk?",temp.repOK());
		assertTrue("To test if player is the owner now",temp==squares[1].getOwner());

	}
	
	@Test
	public void testBuyMethodWithAnotherGameSquare(){
		
		Main.initializeGameSquares();
		GameSquare [] squares = Main.gameSquares;
		Player temp = new Player(0,"kerem",squares);
		
		temp.buySquare(squares[15]);
		temp.buySquare(squares[28]);
		temp.buySquare(squares[46]);
		
		assertTrue("repOk?",temp.repOK());
		assertTrue("To test if player is the owner of the Railroad",temp==squares[15].getOwner());
		assertTrue("To test if player is the owner of the Utility",temp==squares[28].getOwner());
		assertTrue("To test if player is the owner of the Cab",temp==squares[46].getOwner());
		
	}
	
	@Test
	public void testSellMethodWithLand(){
		
		Main.initializeGameSquares();
		GameSquare [] squares = Main.gameSquares;
		Player temp = new Player(0,"kerem",squares);
		
		temp.buySquare(squares[1]);
		temp.sellSquare(squares[1]);
		
		assertTrue("repOk?",temp.repOK());
		assertTrue("To test if player is the owner now",temp!=squares[1].getOwner());

	}
	
	@Test
	public void testSellMethodWithAnotherGameSquare(){
		
		Main.initializeGameSquares();
		GameSquare [] squares = Main.gameSquares;
		Player temp = new Player(0,"kerem",squares);
		
		temp.buySquare(squares[15]);
		temp.buySquare(squares[28]);
		temp.buySquare(squares[46]);
		
		temp.sellSquare(squares[15]);
		temp.sellSquare(squares[28]);
		temp.sellSquare(squares[46]);
		
		assertTrue("repOk?",temp.repOK());
		assertTrue("To test if player is the owner of the Railroad",temp!=squares[15].getOwner());
		assertTrue("To test if player is the owner of the Utility",temp!=squares[28].getOwner());
		assertTrue("To test if player is the owner of the Cab",temp!=squares[46].getOwner());
		
	}
	
}
