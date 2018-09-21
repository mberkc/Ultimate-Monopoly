package GameSquares.Cards;

import gui.Board.RollingTheDice;
import GameSquares.GameSquare;
import Main.Player;

public class CommunityChest extends GameSquare {
    private static final long  serialVersionUID = 1L;
    
    private CommunityChestDeck CommunityDeck    = null;
    
    public CommunityChest(int id, CommunityChestDeck CommunityDeck) {
        super(id, type.CommunityChest);
        this.CommunityDeck = CommunityDeck;
    }
    
    @Override
    public void onArrive(Player pl) {
        CommunityChestCard card = CommunityDeck.draw();
        RollingTheDice.logAdd(pl.getName() + " moved to CommunityChest Square and picked ");
        card.onDraw(pl);
    }
    
    @Override
    public String toString() {
        return "Community Chest at Location: " + id;
    }
}
