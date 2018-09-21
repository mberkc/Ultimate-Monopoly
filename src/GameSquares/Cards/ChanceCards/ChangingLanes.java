package GameSquares.Cards.ChanceCards;

import gui.AdditionalWindows.MessageDisplayer;
import gui.Board.RollingTheDice;
import java.io.Serializable;
import GameSquares.Cards.ChanceCard;
import Main.Player;

public class ChangingLanes extends ChanceCard implements Serializable {
    private static final long serialVersionUID = 1L;
    
    public ChangingLanes() {
        super(CardType.ChangingLanes, false);
    }
    
    @Override
    public void onDraw(Player pl) {
        new MessageDisplayer("You picked ChangingLanes Card.");
        RollingTheDice.logContinue("ChangingLanes Card.");
        int loc = pl.getLocation();
        switch (loc) {
            case 7:
                pl.moveTo(101);
                break;
            case 22:
                pl.moveTo(108);
                break;
            case 36:
                pl.moveTo(118);
                break;
            case 50:
                pl.moveTo(8);
                break;
            case 61:
                pl.moveTo(15);
                break;
            case 70:
                pl.moveTo(20);
                break;
            case 94:
                pl.moveTo(0);
                break;
        }
    }
    
    public String toString() {
        return "Changing Lanes";
    }
    
    @Override
    public String getName() {
        return "Changing Lanes";
    }
    
}
