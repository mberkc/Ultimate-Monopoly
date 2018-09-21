package GameSquares;

import java.util.Arrays;
import java.util.Random;
import Main.Player;
import gui.AdditionalWindows.InputReaders.GetTextInput;
import gui.Board.RollingTheDice;

public class Auction extends GameSquare {
    private static final long serialVersionUID = 1L;
    
    public Auction(int id) {
        super(id, type.Auction);
    }
    
    @Override
    public void onArrive(Player pl) {
        RollingTheDice.logAdd(pl.getName() + " moved to " + toString());
        GameSquare sq = null;
        int id = 0;
        if (unOwnedPropertyLeft()) {
            while (sq == null || !(sq.getOwner() == null && (sq instanceof Ownable))) {
                id = new GetTextInput("Enter a square Number (0-119) to enter the auction for that property ").getInt();
                sq = Main.Main.gameSquares[id];
            }
            
            Player winner = Auction.auctionProcess(((Ownable) sq).getPrice());
            if (winner != null)
                winner.buySquare(sq);
            
            for (int k = 0; k < 4; k++) {
                Main.Main.players[k].inAuction = false;
            }
        }
    }
    
    public static Player auctionProcess(int priceOfProperty) {
        
        int limit = priceOfProperty / 2;
        int input1 = Integer.MAX_VALUE, input2 = Integer.MAX_VALUE, input3 = Integer.MAX_VALUE, input4 = Integer.MAX_VALUE;
        Player[] players = Main.Main.players;
        
        while (input1 > players[0].getMoney()) {
            input1 = new GetTextInput("Enter your bid player :" + players[0].getName() + " property price is : "
                + priceOfProperty).getInt();
        }
        
        while (input2 > players[1].getMoney()) {
            input2 = new GetTextInput("Enter your bid player :" + players[1].getName() + " property price is : "
                + priceOfProperty).getInt();
        }
        
        while (input3 > players[2].getMoney()) {
            input3 = new GetTextInput("Enter your bid player :" + players[2].getName() + " property price is : "
                + priceOfProperty).getInt();
        }
        
        while (input4 > players[3].getMoney()) {
            input4 = new GetTextInput("Enter your bid player :" + players[3].getName() + " property price is : "
                + priceOfProperty).getInt();
        }
        
        if ((input1 < limit) && (input2 < limit) && (input3 < limit) && (input4 < limit)) {
            // tüm bidler yarisindan az, hicbir sey yapma
            System.out.println("Property is not sold");
            return null;
        }
        else {
            players[0].setBid(input1);
            players[1].setBid(input2);
            players[2].setBid(input3);
            players[3].setBid(input4);
            for (int i = 0; i < 4; i++) {
                players[i].inAuction = true;
            }
            
            // highest bid okay ve bir tane burda, property'i ver
            if (input1 > input2 && input1 > input3 && input1 > input4) {
                System.out.println(players[0].getName() + " is the winner");
                RollingTheDice.logAdd(players[0].getName() + " is the winner");
                return players[0];
            }
            else if (input2 > input1 && input2 > input3 && input2 > input4) {
                System.out.println(players[1].getName() + " is the winner");
                RollingTheDice.logAdd(players[1].getName() + " is the winner");
                return players[1];
            }
            else if (input3 > input2 && input3 > input4 && input3 > input1) {
                System.out.println(players[2].getName() + " is the winner");
                RollingTheDice.logAdd(players[2].getName() + " is the winner");
                return players[2];
            }
            else if (input4 > input2 && input4 > input3 && input4 > input1) {
                System.out.println(players[3].getName() + " is the winner");
                RollingTheDice.logAdd(players[3].getName() + " is the winner");
                return players[3];
            }
            else {
                // ayni bidden birden cok var, random olarak en yuksek bidlerin arasindan sec ona property/stock
                // verilcek
                int[] a = new int[4];
                a[0] = input1;
                a[1] = input2;
                a[2] = input3;
                a[3] = input4;
                
                Arrays.sort(a);
                int highestbid = a[3];
                System.out.println("highest bid: " + highestbid);
                int esitler = 0;
                
                for (int i = 0; i < 4; i++) {
                    if (highestbid == a[i])
                        esitler++;
                }
                
                Random rand = new Random();
                int potentialWinner = rand.nextInt(esitler);
                System.out.println("potential winner is : " + potentialWinner);
                int winner = 0;
                
                if (input1 == highestbid && input1 >= limit) {
                    if (winner == potentialWinner) {
                        System.out.println(players[0].getName() + " is the winner");
                        RollingTheDice.logAdd(players[0].getName() + " is the winner");
                        return players[0];
                    }
                    winner++;
                }
                if (input2 == highestbid && input2 >= limit) {
                    if (winner == potentialWinner) {
                        System.out.println(players[1].getName() + " is the winner");
                        RollingTheDice.logAdd(players[1].getName() + " is the winner");
                        return players[1];
                    }
                    winner++;
                }
                if (input3 == highestbid && input3 >= limit) {
                    if (winner == potentialWinner) {
                        System.out.println(players[2].getName() + " is the winner");
                        RollingTheDice.logAdd(players[2].getName() + " is the winner");
                        return players[2];
                    }
                    winner++;
                }
                if (input4 == highestbid && input4 >= limit) {
                    if (winner == potentialWinner) {
                        System.out.println(players[3].getName() + " is the winner");
                        RollingTheDice.logAdd(players[3].getName() + " is the winner");
                        return players[3];
                    }
                    winner++;
                }
            }
        }
        return null;
        
        
    }
    
    public boolean unOwnedPropertyLeft() {
        
        for (int i = 0; i < 120; i++) {
            if (Main.Main.gameSquares[i] instanceof Ownable)
                if (Main.Main.gameSquares[i].getOwner() == null)
                    return true;
        }
        return false;
    }
    
    @Override
    public String toString() {
        return "Auction";
    }
    
}
