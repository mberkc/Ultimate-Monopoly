package GameSquares.Cards.ChanceCards;

import gui.AdditionalWindows.MessageDisplayer;
import java.io.Serializable;
import GameSquares.Cards.ChanceCard;
import Main.Player;

public class AdvanceToTheNearestRailroad extends ChanceCard implements Serializable {
    private static final long serialVersionUID = 1L;
    
    public AdvanceToTheNearestRailroad() {
        super(CardType.AdvanceToTheNearestRailroad, false);
    }
    
    @Override
    public void onDraw(Player pl) {
        new MessageDisplayer("You picked AdvanceToTheNearestRailRoad Card.");
        // moveTo nearest RailRoad
    }
    
    @Override
    public String toString() {
        return "Advance to the nearest Railroad";
    }
    
    @Override
    public String getName() {
        return "Advance to the nearest Railroad";
    }
}
