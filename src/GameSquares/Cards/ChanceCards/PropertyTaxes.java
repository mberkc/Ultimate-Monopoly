package GameSquares.Cards.ChanceCards;

import gui.AdditionalWindows.MessageDisplayer;
import java.io.Serializable;
import java.util.ArrayList;
import GameSquares.Land;
import GameSquares.Land.state;
import GameSquares.Cards.ChanceCard;
import Main.Player;

public class PropertyTaxes extends ChanceCard implements Serializable {
    
    public PropertyTaxes() {
        super(CardType.PropertyTaxes, false);
    }
    
    private static final long serialVersionUID = 1L;
    private int               tax              = 5;
    
    @Override
    public void onDraw(Player pl) {
        new MessageDisplayer("You picked property taxes. Pay 5$ to the pool for each unmortgaged property you own. ");
        
        ArrayList<Land> lands = pl.getOwnedLands();
        int taxSum = 0;
        for (int i = 0; i < lands.size(); i++) {
            if (lands.get(i).getState() != state.mortgage)
                taxSum += tax;
        }
        
        pl.payToPool(taxSum);
    }
    
    @Override
    public String toString() {
        return "Property Tax";
    }
    
    @Override
    public String getName() {
        return "Property Taxes";
    }
}
