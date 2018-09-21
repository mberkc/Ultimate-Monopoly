package GameSquares;

import gui.Board.RollingTheDice;
import Main.Player;

public class StartSquare extends GameSquare {
    private static final long serialVersionUID = 1L;
    
    public StartSquare(int id) {
        super(id, type.StartSquare);
    }
    
    @Override
    public void onArrive(Player pl) {
        // Start Square does nothing!
        RollingTheDice.logAdd(pl.getName() + " moved to " + toString());
    }
    
    @Override
    public String toString() {
        return "Start Square. Location: " + id;
    }
    
}
