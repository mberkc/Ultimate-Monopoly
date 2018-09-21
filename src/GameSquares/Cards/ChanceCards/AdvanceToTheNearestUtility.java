package GameSquares.Cards.ChanceCards;

import gui.AdditionalWindows.MessageDisplayer;
import java.io.Serializable;
import GameSquares.Cards.ChanceCard;
import Main.Player;

public class AdvanceToTheNearestUtility extends ChanceCard implements Serializable {
    private static final long serialVersionUID = 1L;
    
    public AdvanceToTheNearestUtility() {
        super(CardType.AdvanceToTheNearestUtility, false);
    }
    
    @Override
    public void onDraw(Player pl) {
        new MessageDisplayer("You picked AdvanceToTheNearestUtility Card.");
        // moveTo nearest Utility
    }
    
    @Override
    public String toString() {
        return "Advance to the nearest Utility";
    }
    
    @Override
    public String getName() {
        return "Advance to the nearest Utility";
    }
}
