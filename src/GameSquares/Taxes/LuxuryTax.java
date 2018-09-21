package GameSquares.Taxes;

import gui.Board.RollingTheDice;
import GameSquares.GameSquare;
import Main.Player;
import Main.Properties;

public class LuxuryTax extends GameSquare {
    private static final long serialVersionUID = 1L;
    
    public LuxuryTax(int id) {
        super(id, type.LuxuryTax);
    }
    
    @Override
    public void onArrive(Player pl) {
        RollingTheDice.logAdd(pl.getName() + " moved to " + toString());
        pl.payToPool(Properties.LUXURYTAX_AMOUNT);
        System.out.println("Player " + pl.getName() + " payed " + Integer.toString(Properties.LUXURYTAX_AMOUNT)
            + " as luxury tax");
    }
    
    @Override
    public String toString() {
        return "Luxury Tax";
    }
    
}
