package GameSquares;

import java.io.Serializable;
import Main.Player;

public abstract class GameSquare implements Serializable {
    private static final long serialVersionUID = 1L;
    
    protected int             id;
    protected type            cardType;
    protected Player          owner;

    /**
     * @param id Each game square is associated with a unique id
     * @param type Specifies the square type, 
     */
    protected GameSquare(int id, type type) {
        this.id = id;
        this.cardType = type;
    }
    
    public enum type {
        Chance, CommunityChest, FreePark, Land, RollOnce, StartSquare, SqueezePlay, Cab,
        LuxuryTax, TransitStation, Jail, ElectricCompany, WaterWorks, Subway, BusTicket,
        CableCompany, Tunnel, Auction, InternetServiceProvider, Payday, TrashCollector,
        StockExchange, TaxRefund, GasCompany, HollandTunnel, ReverseDirection, IncomeTax,
        GotoJail, Bus, SewegeSystem, BirthdayGift, TelephoneCompany, Bonus
    }
    
    /**
     * @param pl Player that have moved
     * @requires pl!=null and player moves to another square
     * @modifies player pl (all of its fields may be modified).
     * @effects Effects depend according to the specific game square 
     */
    public abstract void onArrive(Player pl);
    
    @Override
    /**
     * @requires Game square fields are not null
     * @effects Returns information about the squares current information
     */
    public abstract String toString();
    
    /**
     * @param pl
     * @requires pl!=null
     * @modifies Player pl
     * @effects pl now owns this game square
     */
    public void setOwner(Player pl) {
        owner = pl;
    }
    
    /**
     * @return The player who owns this game square
     */
    public Player getOwner() {
        return owner;
    }
    
    /**
     * @return The specific characteristic of the square according to effect
     */
    public String getType() {
        return cardType.toString();
    }
    
    /**
     * @return The id of the game square
     */
    public int getID() {
        return id;
    }
    
    /**
     * @return Checks the representation invariants
     */
    public boolean repOK(){
    	return (120>id)&&(id>=0);
    }
}
