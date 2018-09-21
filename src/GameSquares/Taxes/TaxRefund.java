package GameSquares.Taxes;

import gui.Board.RollingTheDice;
import GameSquares.GameSquare;
import Main.Main;
import Main.Player;
import Main.Properties;

public class TaxRefund extends GameSquare {
    private static final long serialVersionUID = 1L;
    
    public TaxRefund(int id) {
        super(id, type.TaxRefund);
    }
    
    @Override
    public void onArrive(Player pl) {
        RollingTheDice.logAdd(pl.getName() + " moved to " + toString());
        System.out.println("Player " + pl.getName() + " recieved "
            + Integer.toString(Main.pool * Properties.TAX_REFUND_PERCENT / 100) + " from the pool as tax refund");
        pl.obtainPool();
    }
    
    @Override
    public String toString() {
        return "Tax Refund";
    }
    
}
