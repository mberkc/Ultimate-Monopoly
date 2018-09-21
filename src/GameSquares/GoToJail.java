package GameSquares;

import gui.Board.RollingTheDice;
import Main.Player;

public class GoToJail extends GameSquare {
    
    private static final long serialVersionUID = 1L;
    
    public GoToJail(int id) {
        super(id, type.GotoJail);
    }
    
    @Override
    public void onArrive(Player pl) {
        RollingTheDice.logAdd(pl.getName() + " moved to " + toString());
        System.out.println("Player:" + pl.getName() + " is now going to jail.");
        pl.goToJail();
    }
    
    @Override
    public String toString() {
        return "Go To Jail. Location: " + id;
    }
    
}
