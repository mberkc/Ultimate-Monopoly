package GameSquares.Cards.ChanceCards;

import gui.AdditionalWindows.MessageDisplayer;
import gui.Board.RollingTheDice;
import java.io.Serializable;
import GameSquares.Cards.ChanceCard;
import Main.Player;

public class GoToJail extends ChanceCard implements Serializable {
    private static final long serialVersionUID = 1L;
    
    public GoToJail() {
        super(CardType.GoToJail, false);
    }
    
    @Override
    public void onDraw(Player pl) {
        new MessageDisplayer("You picked GoToJail Card.");
        RollingTheDice.logContinue("GoToJail Card.");
        pl.goToJail();
    }
    
    @Override
    public String toString() {
        return "Go to Jail";
    }
    
    @Override
    public String getName() {
        return "Go to Jail";
    }
}
