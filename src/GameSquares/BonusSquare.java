package GameSquares;

import gui.Board.RollingTheDice;
import Main.Player;

public class BonusSquare extends GameSquare {
    private static final long serialVersionUID = 1L;
    private final int         bonusMoney       = 300;
    
    public BonusSquare(int id) {
        super(id, type.Bonus);
    }
    
    @Override
    public void onArrive(Player pl) {
        RollingTheDice.logAdd(pl.getName() + " moved to Bonus Square and collected $300");
        new gui.AdditionalWindows.MessageDisplayer("You are at Bonus Square. You will collect $300.");
        pl.addMoney(bonusMoney);
    }
    
    @Override
    public String toString() {
        return "Bonus Square";
    }
    
}
