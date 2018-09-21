package GameSquares.Cards.ChanceCards;

import gui.AdditionalWindows.MessageDisplayer;
import java.io.Serializable;
import GameSquares.Cards.ChanceCard;
import Main.Player;

public class ThreeSpacesBack extends ChanceCard implements Serializable {
    private static final long serialVersionUID = 1L;
    
    public ThreeSpacesBack() {
        super(CardType.ThreeSpacesBack, false);
    }
    
    @Override
    public void onDraw(Player pl) {
        new MessageDisplayer("You picked ThreeSpacesBack Card.");
        pl.moveBy(-3);
    }
    
    @Override
    public String toString() {
        return "Three spaces back";
    }
    
    @Override
    public String getName() {
        return "Three spaces back";
    }
    
}
