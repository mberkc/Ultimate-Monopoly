package GameSquares;

import gui.Board.RollingTheDice;
import Main.Player;

public class BusTicketSquare extends GameSquare {
    private static final long serialVersionUID = 1L;
    
    public BusTicketSquare(int id) {
        super(id, type.BusTicket);
    }
    
    @Override
    public void onArrive(Player pl) {
        RollingTheDice.logAdd(pl.getName() + " moved to " + toString());
        
    }
    
    @Override
    public String toString() {
        return "Bus Ticket";
    }
    
}
