package GameSquares.Taxes;

import gui.AdditionalWindows.InputReaders.GetYesNoInput;
import gui.Board.RollingTheDice;
import GameSquares.GameSquare;
import GameSquares.Land;
import Main.Player;
import Main.Properties;

public class IncomeTax extends GameSquare {
    private static final long serialVersionUID = 1L;
    
    public IncomeTax(int id) {
        super(id, type.IncomeTax);
    }
    
    @Override
    public void onArrive(Player pl) {
        RollingTheDice.logAdd(pl.getName() + " moved to " + toString());
        int assetTax = pl.getMoney();
        for (Land lnd : pl.getOwnedLands())
            assetTax += lnd.getPrice();
        assetTax = ((int) assetTax * Properties.INCOMETAX_PERCENT) / 100;
        
        boolean payAmount = new GetYesNoInput("Pay 200$ or lose 10% of your money", "Income Tax").getValue();
        
        pl.payToPool(payAmount ? Properties.INCOMETAX_AMOUNT : assetTax);
    }
    
    @Override
    public String toString() {
        return "Income Tax";
    }
    
}
