package GameSquares;

import gui.AdditionalWindows.InputReaders.GetYesNoInput;
import gui.Board.RollingTheDice;
import Main.Player;
import Main.Properties;

public class TransitStation extends GameSquare implements Ownable {
    private static final long serialVersionUID = 1L;
    private int               price            = Properties.TRANSITSTATION_PRICE / 2;
    private int               depotCost        = Properties.TRANSITSTATION_DEPOT_COST;
    private int               rent             = Properties.TRANSITSTATION_RENT;
    private int               depotCount       = 0;
    private int               connectedTransit;
    private boolean           onlinePricing    = false;
    private String            name;
    
    public TransitStation(int id, int connectedTransitID, String name) {
        super(id, type.TransitStation);
        this.connectedTransit = connectedTransitID;
        this.name = name;
    }
    
    @Override
    public void onArrive(Player pl) {
        RollingTheDice.logAdd(pl.getName() + " moved to " + toString());
        if (this.owner == null) {
            boolean buy = new GetYesNoInput("For " + price + " dollars", "Would you like to buy " + name + "Railroad ?")
                .getValue();
            if (buy) {
                if (pl.getMoney() >= price) {
                    pl.buySquare(this);
                    RollingTheDice.logContinue(" and bought it.");
                }
                else
                    System.out.println("You don't have enough money!");
            } else {
                Player winner = Auction.auctionProcess(this.price);
                if (winner != null)
                    winner.buySquare(this);
                for (int k = 0; k < 4; k++) {
                    Main.Main.players[k].inAuction = false;
                }
            }
        } else if (this.owner != pl) {
            if (pl.hasOnlinePricing()) {
                if (onlinePricing = new GetYesNoInput("Online Pricing", "Do you want to use it ?")
                    .getValue())
                    pl.pay(this.getOwner(), ((rent * (int) Math.pow(2, depotCount)) / 2));
                pl.removeOnlinePricingCard();
            }
            if (!onlinePricing)
                pl.pay(this.getOwner(), rent * (int) Math.pow(2, depotCount));
        } else if (new GetYesNoInput("Build Train Depot", "Do you want to pay " + depotCost + "$ to build Cab Stand ?")
            .getValue()) {
            pl.reduceMoney(depotCost);
            this.depotCount++;
        }
    }
    
    @Override
    public void sell() {
        this.owner.sellSquare(this);
    }
    
    public int getConnectedTransit() {
        return connectedTransit;
    }
    
    @Override
    public int getPrice() {
        return price;
    }
    
    @Override
    public String getName() {
        return name + " Railroad";
    }
    
    @Override
    public void upgrade() {
        depotCount++;
    }
    
    @Override
    public void downgrade() {
        if (depotCount > 0)
            depotCount--;
        else
            System.out.println("No further downgrade possible");
    }
    
    @Override
    public String getUpgradeState() {
        return "The Transit station from " + id + " to " + connectedTransit + " now has " + depotCount + " Depots";
    }
    
    public boolean isOwned() {
        if (this.owner != null)
            return true;
        else
            return false;
    }
    
    public int getDepotCount() {
        return depotCount;
    }
    
    @Override
    public String toString() {
        return "Transit station from " + id + " to " + connectedTransit;
    }
    
    public void setDepotCount(int i) {
        depotCount = i;
    }
}
