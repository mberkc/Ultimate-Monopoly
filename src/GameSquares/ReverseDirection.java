package GameSquares;

import gui.Board.RollingTheDice;
import Main.Player;

public class ReverseDirection extends GameSquare {
    private static final long serialVersionUID = 1L;
    
    public ReverseDirection(int id) {
        super(id, type.ReverseDirection);
    }
    
    @Override
    public void onArrive(Player pl) {
        RollingTheDice.logAdd(pl.getName() + " moved to " + toString());
        
    }
    
    @Override
    public String toString() {
        return "Reverse Direction";
    }
    
}
