package GameSquares;

import gui.AdditionalWindows.InputReaders.GetYesNoInput;
import gui.Board.RollingTheDice;
import Main.Player;

public class Jail extends GameSquare {
    
    private static final long serialVersionUID = 1L;
    boolean                   getOutOfJail     = false;
    int                       defaultJailTime  = 3;
    
    public Jail(int id) {
        super(id, type.Jail);
    }
    
    @Override
    public void onArrive(Player pl) {
        RollingTheDice.logAdd(pl.getName() + " moved to Jail");
        if (pl.isJailed()) {
            if (pl.hasGetOutOfJail()) {
                getOutOfJail = new GetYesNoInput("You can use GetOutOfJail Card", "Do you want to use it ?").getValue();
            }
            if (getOutOfJail) {
                pl.removeGetOutOfJailCard();
            } else {
                if (getOutOfJail = new GetYesNoInput("By paying $50 fee you can get out of jail",
                    "Do you want to pay ?").getValue()) {
                    pl.payToPool(50);
                }
            }
            if (getOutOfJail) {
                pl.getOutOfJail();
            } else {
                System.out.println("Player:" + pl.getName() + " is now in jail for " + defaultJailTime
                    + " rounds(default).");
            }
        } else
            System.out.println("Player:" + pl.getName() + " is now visiting jail.");
        
        getOutOfJail = false;
    }
    
    @Override
    public String toString() {
        return "Jail. Location: " + id;
    }
    
}
