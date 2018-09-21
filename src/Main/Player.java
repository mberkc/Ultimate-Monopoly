package Main;

import gui.AdditionalWindows.InputReaders.GetTextInput;
import gui.Board.Board;
import gui.Board.PlayerInfo;
import gui.Board.RollingTheDice;
import java.io.Serializable;
import java.util.ArrayList;
import GameSquares.GameSquare;
import GameSquares.Land;
import GameSquares.Land.color;
import GameSquares.Ownable;
import GameSquares.StockExchange.stockType;
import GameSquares.TransitStation;
import GameSquares.Utility;
import GameSquares.Cards.Card.CardType;

public class Player implements Serializable {
    private static final long   serialVersionUID = 1L;
    private int                 id, money, location, jailTime, doublesRolled, bid;
    private int                 jailID           = Properties.JAIL_ID;
    private String              name;
    private GameSquare[]        gameSquares;
    private boolean             jailed           = false, bonusPassAvailable, loseTurn;
    public boolean              inAuction        = false;
    private ArrayList<CardType> cardInventory    = new ArrayList<CardType>();
    private ArrayList<Ownable>  ownedSquares     = new ArrayList<Ownable>();
    private int[]               Stocks           = new int[6];
    public boolean              testing          = false;
    
    // Default Constructor
    /** @param id
     * @param name
     * @param gameSquares */
    public Player(int id, String name, GameSquare[] gameSquares) {
        this.id = id;
        this.location = 0;
        this.money = Properties.START_GOLD;
        this.name = name;
        this.gameSquares = gameSquares;
        System.out.println("Player " + name + " with " + money + " added.");
    }
    
    // Constructor for dummy player
    public Player(GameSquare[] gameSquares) {
        this.id = -1;
        this.location = -1;
        this.money = Properties.START_GOLD;
        this.name = ".";
        this.gameSquares = gameSquares;
    }
    
    // Constructor for load
    public Player(int id, String name, int money, int location, GameSquare[] gameSquares) {
        this.id = id;
        this.location = location;
        this.money = money;
        this.name = name;
        this.gameSquares = gameSquares;
        System.out.println("Player " + name + " with " + money + " added.");
    }
    /** @param amount
     * @requires amount is greater than 0
     * @modifies this
     * @effects this player moves according to the amount given */
    public void moveBy(int amount) {
        int nextLocation = location + 1;
        bonusPassAvailable = true;
        if (location == 102) {
            bonusPassAvailable = false;
        }
        for (int i = 0; i < amount; i++) {
            
            if (amount % 2 == 0) {// transit locations
                boolean passed = true;
                if (nextLocation == 5)
                    nextLocation = 47;
                else if (nextLocation == 15)
                    nextLocation = 105;
                else if (nextLocation == 25)
                    nextLocation = 75;
                else if (nextLocation == 35)
                    nextLocation = 117;
                else if (nextLocation == 47)
                    nextLocation = 5;
                else if (nextLocation == 105)
                    nextLocation = 15;
                else if (nextLocation == 75)
                    i = 25;
                else if (nextLocation == 117)
                    nextLocation = 35;
                else
                    passed = false;
                if (passed) {
                    System.out.println(name + " passed transit station");
                    RollingTheDice.logAdd(name + " passed transit station");
                }
            }
            
            if (nextLocation == 40)
                nextLocation = 0; // track endings
            else if (nextLocation == 120)
                nextLocation = 97;
            else if (nextLocation == 96)
                nextLocation = 40;
            
            if (nextLocation == 0) { // start square
                System.out.println(name + " passed Start Square.");
                addMoney(Properties.START_PASSING_MONEY);
                RollingTheDice.logAdd(name + " passed Start Square and got " + Properties.START_PASSING_MONEY);
            }
            
            if (nextLocation == 68) { // payday
                System.out.println(name + " passed Pay Day Square.");
                if (amount % 2 != 0) {
                    addMoney(Properties.PAYDAY_ODD);
                    RollingTheDice.logAdd(name + " passed Pay Day Square and got " + Properties.PAYDAY_ODD);
                } else {
                    addMoney(Properties.PAYDAY_EVEN);
                    RollingTheDice.logAdd(name + " passed Pay Day Square and got " + Properties.PAYDAY_EVEN);
                }
            }
            if (nextLocation == 103 && bonusPassAvailable) { // bonus
                System.out.println(name + " passed Bonus Square.");
                addMoney(Properties.BONUS_PASSING_MONEY);
                RollingTheDice.logAdd(name + " passed Bonus Square and got " + Properties.BONUS_PASSING_MONEY);
            }
            nextLocation++; // update location
            
        }
        location = nextLocation - 1;
        System.out.println(name + " moved " + amount + " squares and now is at " + gameSquares[location].toString()
            + "\n You have: " + money);
        if (!testing)
            if (gameSquares[location] instanceof Utility) {
                ((Utility) gameSquares[location]).onArrive(this, amount);
            }
            else
                gameSquares[location].onArrive(this);
    }
    /** @param id
     * @requires number of game squares is greater than id is greater than or equal to 0
     * @modifies this
     * @effects this player goes to the square with the given id */
    public void moveTo(int id) {
        System.out.println(name + " is at " + gameSquares[id].toString());
        if (id < 40 && location > id) {
            System.out.println(name + " passed Start Square.");
            addMoney(Properties.START_PASSING_MONEY);
        }
        location = id;
        
        if (gameSquares[location] instanceof Utility) {
            int amount = new GetTextInput("Total dice ?").getInt();
            ((Utility) gameSquares[location]).onArrive(this, amount);
        } else
            gameSquares[location].onArrive(this);
    }
    
    /** @param amount
     * @requires amount is greater than or equal to 0
     * @modifies this
     * @effects this players money is increased by amount */
    public void addMoney(int amount) {
        money += amount;
        System.out.println(name + "'s money increased by " + amount + " to " + money);
    }
    
    /** @param amount
     * @requires amount is greater than or equal to 0
     * @modifies this
     * @effects this players money is decreased by amount */
    public void reduceMoney(int amount) {
        if (money >= amount) {
            money -= amount;
            System.out.println(name + "'s money decreased by " + amount + " to " + money);
        } else if (ownedSquares.size() > 0) {
            gui.AdditionalWindows.List.createAndShowGUI(ownedSquares, this);
            reduceMoney(amount);
        } else {
            location = Properties.HEAVEN_ID;
            System.out.println(name + " is bankrupt."); // create a new
            // additional window
        }
    }
    
    /** @param amount
     * @requires amount is greater than or equal to 0
     * @modifies this, Main
     * @effects this players money is decreased by amount and is added to the pool */
    public void payToPool(int amount) {
        reduceMoney(amount);
        Main.pool += amount;
        System.out.println("pool has " + Main.pool);
        RollingTheDice.logContinue(" and  paid " + amount + " to the pool.");
    }
    
    /** @modifies this, Main
     * @effects this players money is increased by total pool*refund percent and is decreased from the pool */
    public void obtainPool() {
        addMoney(Main.pool * Properties.TAX_REFUND_PERCENT / 100);
        Main.pool = Main.pool * (100 - Properties.TAX_REFUND_PERCENT) / 100;
        System.out.println("pool has " + Main.pool);
        RollingTheDice.logAdd(name + " got " + (Main.pool * Properties.TAX_REFUND_PERCENT / 100) + " from the pool.");
    }
    
    /** @param player
     * @param amount
     * @requires player != null, amount is greater than 0
     * @modifies this, player
     * @effects reduces this players money by amount and adds it to player */
    public void pay(Player player, int amount) {
        System.out.println(name + " paid " + amount + " to " + player.getName());
        this.reduceMoney(amount);
        player.addMoney(amount);
        RollingTheDice.logAdd(name + " paid to " + player.getName());
    }
    
    /** @param cardType
     * @modifies this
     * @effects Adds the given card to the players card inventory */
    public void addToCardInventory(CardType cardType) {
        cardInventory.add(cardType);
        System.out.println("Player " + name + " is given the card " + cardType);
    }
    
    /** @param cardType
     * @return Return true if the player has the card type */
    public boolean haveCard(CardType cardType) {
        return cardInventory.contains(cardType);
    }
    
    public void buyStock(stockType stt) {
        Stocks[stt.getOrder()]++;
        if (!inAuction)
            reduceMoney(stt.getPrice());
        else
            reduceMoney(bid);
        PlayerInfo.refreshData();
        Board.informationTable.validate();
    }
    public void sellStock(stockType stt) {
        Stocks[stt.getOrder()]--;
        addMoney(stt.getPrice());
    }
    public int getStockAmount(stockType stt) {
        return Stocks[stt.getOrder()];
    }
    public void giveStock(int index) {
        Stocks[index]++;
    }
    public void removeStock(int index) {
        if (Stocks[index] > 0)
            Stocks[index]--;
    }
    public int[] getStocks() {
        return Stocks;
    }
    
    public int getMoney() {
        return money;
    }
    
    public String getName() {
        return name;
    }
    
    public int getLocation() {
        return location;
    }
    
    
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public void setJailTime(int jailTime) {
        this.jailTime = jailTime;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setJailed(boolean jailed) {
        this.jailed = jailed;
    }
    
    /** @param land
     * @modifies this
     * @effects this player pays for the land and owns it */
    public void buySquare(GameSquare land) {
        if (land instanceof Ownable) {
            if (!inAuction)
                reduceMoney(((Ownable) land).getPrice());
            else
                reduceMoney(this.bid);
            getOwnership(land);
        }
    }
    
    /** @param land
     * @modifies this
     * @effects this player get half the price of land and relinquishes ownership */
    public void sellSquare(GameSquare land) {
        if (land instanceof Ownable && !(land instanceof Land)) {
            if (land instanceof TransitStation)
                addMoney(((Ownable) land).getPrice() / 2);
            else
                addMoney(((Ownable) land).getPrice());
            removeOwnership(land);
            System.out.println(name + " sold " + land + " for " + ((Ownable) land).getPrice());
        }
        
        if (ownedSquares.contains(land)) {
            addMoney(((Ownable) land).getPrice());
            removeOwnership(land);
            System.out.println(name + " sold " + land + " for " + ((Ownable) land).getPrice());
        }
    }
    
    public void getOwnership(GameSquare square) {
        
        if (square instanceof TransitStation) {
            GameSquare connection = Main.gameSquares[((TransitStation) square).getConnectedTransit()];
            
            ownedSquares.add((Ownable) connection);
            connection.setOwner(this);
            
            ownedSquares.add((Ownable) square);
            square.setOwner(this);
        } else {
            ownedSquares.add((Ownable) square);
            square.setOwner(this);
        }
    }
    
    public void removeOwnership(GameSquare square) {
        if (square instanceof TransitStation) {
            GameSquare connection = Main.gameSquares[((TransitStation) square).getConnectedTransit()];
            
            ownedSquares.remove((Ownable) connection);
            connection.setOwner(null);
            
            ownedSquares.remove((Ownable) square);
            square.setOwner(null);
        } else {
            ownedSquares.remove((Ownable) square);
            
            square.setOwner(null);
        }
    }
    
    public int getID() {
        return id;
    }
    
    /** @param color
     *            The color type is present in the game
     * @return number of lands of this color this player owns */
    public int getNumberOfOwnedByColor(color color) {
        int counter = 0;
        for (Ownable ownable : ownedSquares) {
            if (ownable instanceof Land && ((Land) ownable).getColor() == color)
                counter++;
        }
        return counter;
    }
    public ArrayList<Ownable> getOwnedSquares() {
        return ownedSquares;
    }
    
    // ////////// DO NOT USE THESE METHODS - THESE ARE JUST FOR DEBUGGING
    // ////////// //
    public <T> void setLocation(T gameSquare) {
        this.location = gameSquare instanceof GameSquare ? ((GameSquare) gameSquare).getID() : (int) gameSquare;
    }
    
    public void setMoney(int amount) {
        this.money = amount;
    }
    
    // /////////////////////////////////////////////////////////////////////////////
    // //
    
    public boolean hasGetOutOfJail() {
        return cardInventory.contains(CardType.GetOutOfJail);
    }
    public boolean hasOnlinePricing() {
        return cardInventory.contains(CardType.OnlinePricing);
    }
    public boolean hasBargainBusiness() {
        return cardInventory.contains(CardType.BargainBusiness);
    }
    public boolean hasRenovationSuccess() {
        return cardInventory.contains(CardType.RenovationSuccess);
    }
    
    public void removeGetOutOfJailCard() {
        cardInventory.remove(CardType.GetOutOfJail);
    }
    public void removeOnlinePricingCard() {
        cardInventory.remove(CardType.OnlinePricing);
    }
    public void removeBargainBusinessCard() {
        cardInventory.remove(CardType.BargainBusiness);
    }
    public void removeRenovationSuccessCard() {
        cardInventory.remove(CardType.RenovationSuccess);
    }
    
    public ArrayList<CardType> getCardsInventory() {
        return cardInventory;
    }
    
    public boolean isJailed() {
        return jailed;
    }
    
    /** @modifies this
     * @effects this player goes to jail and is now jailed */
    public void goToJail() {
        this.jailed = true;
        jailTime = 3;
        this.moveTo(jailID);
    }
    
    /** @modifies this
     * @effects this player is not jailed */
    public void getOutOfJail() {
        this.jailed = false;
        jailTime = 0;
    }
    
    public boolean isReleaseTime() {
        if (jailTime == 0)
            return true;
        return false;
    }
    
    public int getJailTime() {
        return jailTime;
    }
    
    public void reduceJailTime() {
        jailTime--;
    }
    
    public void resetDoublesRolled() {
        doublesRolled = 0;
    }
    
    public boolean isThirdDoubles() {
        if (doublesRolled == 3)
            return true;
        return false;
    }
    
    public int getDoublesRolled() {
        return doublesRolled;
    }
    
    public void doublesRolled() {
        doublesRolled++;
    }
    
    /** @return The number of utilities owned by this player */
    public int numOfOwnedUtilities() {
        int output = 0;
        
        for (Ownable nextOwn : ownedSquares) {
            if (nextOwn instanceof Utility)
                output++;
        }
        
        return output;
    }
    
    /** @return The Lands owned by this player */
    public ArrayList<Land> getOwnedLands() {
        ArrayList<Land> output = new ArrayList<Land>();
        for (Ownable ownable : ownedSquares) {
            if (ownable instanceof Land)
                output.add((Land) ownable);
        }
        return output;
    }
    
    /** @return Returns player information */
    public String toString() {
        String Lands = "[";
        for (Ownable land : ownedSquares) {
            Lands += land.getName() + ", ";
        }
        if (Lands.length() > 2)
            Lands = Lands.substring(0, Lands.length() - 2);
        Lands += "]";
        
        return "Player " + name + " has " + money + " is at " + gameSquares[location] + "\n" + "Has Cards:"
            + cardInventory + "\n" + "Has Lands:" + Lands;
    }
    
    /** @return Checks the representation invariants */
    public boolean repOK() {
        boolean sqOk = true;
        
        for (int i = 0; i < ownedSquares.size(); i++) {
            if (!(ownedSquares.get(i) instanceof Ownable))
                sqOk = false;
        }
        
        return (money >= 0) && (120 > location) && (location >= 0) &&
            (cardInventory != null) && (ownedSquares != null) && (ownedSquares != null)
            && sqOk && (doublesRolled >= 0) && (doublesRolled <= 3) && (jailTime >= 0) && (jailTime <= 3);
    }
    
    public boolean isLoseTurn() {
        return loseTurn;
    }
    
    public void LoseTurn(boolean loseTurn) {
        this.loseTurn = loseTurn;
    }
    
    public void setBid(int input) {
        this.bid = input;
    }
    public int getBid() {
        return this.bid;
    }
    
}
