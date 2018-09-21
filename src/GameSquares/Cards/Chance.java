package GameSquares.Cards;

import gui.Board.RollingTheDice;
import GameSquares.GameSquare;
import Main.Player;

public class Chance extends GameSquare {
    private static final long serialVersionUID = 1L;
    
    private ChanceDeck        ChanceDeck       = null;
    
    public Chance(int id, ChanceDeck ChanceDeck, Player[] players) {
        super(id, type.Chance);
        this.ChanceDeck = ChanceDeck;
    }
    
    @Override
    public void onArrive(Player pl) {
        ChanceCard card = ChanceDeck.draw();
        RollingTheDice.logAdd(pl.getName() + " moved to Chance Square and picked ");
        card.onDraw(pl);
    }
    
    @Override
    public String toString() {
        return "Chance at Location: " + id;
    }
    
}
