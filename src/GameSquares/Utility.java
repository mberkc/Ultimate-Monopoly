package GameSquares;

import gui.AdditionalWindows.MessageDisplayer;
import gui.AdditionalWindows.InputReaders.GetYesNoInput;
import gui.Board.RollingTheDice;
import Main.Player;
import Main.Properties;

public class Utility extends GameSquare implements Ownable {
    private static final long serialVersionUID = 1L;
    private String            name;
    private boolean           mortgaged;
    
    public Utility(int id, String name, type type) {
        super(id, type);
        this.name = name;
        mortgaged = false;
    }
    
    public void onArrive(Player pl, int amount) {
        RollingTheDice.logAdd(pl.getName() + " moved to " + toString());
        if (this.owner == null) {
            boolean buy = new GetYesNoInput("For " + Properties.UTILITY_PRICE + " dollars", "Would you like to buy "
                + name + " ?")
                .getValue();
            
            if (buy) {
                if (pl.getMoney() >= Properties.UTILITY_PRICE) {
                    pl.buySquare(this);
                    RollingTheDice.logContinue(" and bought it.");
                } else
                    System.out.println("You don't have enough money!");
            } else {
                Player winner = Auction.auctionProcess(Properties.UTILITY_PRICE);
                if (winner != null)
                    winner.buySquare(this);
                for (int k = 0; k < 4; k++) {
                    Main.Main.players[k].inAuction = false;
                }
            }
        } else {
            if (this.owner != pl) {
                int totalRent = amount;
                switch (this.owner.numOfOwnedUtilities()) {
                    case 1:
                        totalRent *= 4;
                        break;
                    case 2:
                        totalRent *= 10;
                        break;
                    case 3:
                        totalRent *= 20;
                        break;
                    case 4:
                        totalRent *= 40;
                        break;
                    case 5:
                        totalRent *= 80;
                        break;
                    case 6:
                        totalRent *= 100;
                        break;
                    case 7:
                        totalRent *= 120;
                        break;
                    case 8:
                        totalRent *= 150;
                        break;
                }
                System.out.println("This Utility is owned by " + owner.getName());
                
                pl.pay(owner, totalRent);
            }
            else
                System.out.println("Player already owns this land!");
        }
    }
    @Override
    public void onArrive(Player pl) {
        // Use other onArrive for this GameSquare
    }
    
    public void mortgage() {
        this.owner.addMoney(Properties.UTILITY_PRICE / 2);
        mortgaged = true;
    }
    
    public void leaveMortgage() {
        int mortgageAmount = (int) (1.1 * Properties.UTILITY_PRICE);
        if (this.owner.getMoney() >= mortgageAmount) {
            this.owner.reduceMoney(mortgageAmount);
            mortgaged = false;
        } else
            new MessageDisplayer("You do not have enough money to leave mortgage");
    }
    
    public boolean isMortgaged() {
        return mortgaged;
    }
    
    @Override
    public void sell() {
        this.owner.sellSquare(this);
    }
    
    @Override
    public int getPrice() {
        return Properties.UTILITY_PRICE;
    }
    
    @Override
    public String getName() {
        return name;
    }
    
    public boolean isOwned() {
        if (this.owner != null)
            return true;
        return false;
    }
    
    @Override
    public void upgrade() {
        System.out.println("No further upgrade possible");
    }
    
    @Override
    public void downgrade() {
        System.out.println("No further downgrade possible");
    }
    
    @Override
    public String getUpgradeState() {
        return "Utility " + name + " has no Upgrade on it.";
    }
    
    @Override
    public String toString() {
        return name;
    }
    
    public void setMortgage(boolean val) {
        mortgaged = val;
    }
}
