package GameSquares;

import gui.Board.RollingTheDice;
import Main.Player;

public class Subway extends GameSquare {
    private static final long serialVersionUID = 1L;
    
    public Subway(int id) {
        super(id, type.Subway);
    }
    
    @Override
    public void onArrive(Player pl) {
        RollingTheDice.logAdd(pl.getName() + " moved to " + toString());
    }
    
    @Override
    public String toString() {
        return "Subway";
    }
    
}
