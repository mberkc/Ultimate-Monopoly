package GameSquares.Cards.ChanceCards;

import gui.AdditionalWindows.MessageDisplayer;
import java.io.Serializable;
import GameSquares.Cards.ChanceCard;
import Main.Player;

public class AdvanceToStart extends ChanceCard implements Serializable {
    private static final long serialVersionUID = 1L;
    
    public AdvanceToStart() {
        super(CardType.AdvanceToStart, false);
    }
    
    @Override
    public void onDraw(Player pl) {
        new MessageDisplayer("You picked AdvanceToStart Card.");
        pl.moveTo(0);
    }
    
    @Override
    public String toString() {
        return "Advance to Start";
    }
    
    @Override
    public String getName() {
        return "Advance to Start";
    }
}
