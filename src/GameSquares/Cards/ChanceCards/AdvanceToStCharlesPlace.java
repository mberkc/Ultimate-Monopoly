package GameSquares.Cards.ChanceCards;

import gui.AdditionalWindows.MessageDisplayer;
import java.io.Serializable;
import GameSquares.Cards.ChanceCard;
import Main.Player;

public class AdvanceToStCharlesPlace extends ChanceCard implements Serializable {
    private static final long serialVersionUID = 1L;
    
    public AdvanceToStCharlesPlace() {
        super(CardType.AdvanceToStCharlesPlace, false);
    }
    
    @Override
    public void onDraw(Player pl) {
        new MessageDisplayer( "You picked AdvanceToStCharlesPlace Card.");
        pl.moveTo(6);
    }
    
    @Override
    public String toString() {
        return "Advance to StCharlesPlace";
    }
    
    @Override
    public String getName() {
        return "Advance to StCharlesPlace";
    }
}
