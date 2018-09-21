package GameSquares.Cards.CommunityChestCards;

import gui.AdditionalWindows.MessageDisplayer;
import java.io.Serializable;
import GameSquares.Cards.CommunityChestCard;
import Main.Player;

public class RenovationSuccess extends CommunityChestCard implements Serializable {
    private static final long serialVersionUID = 1L;
    
    public RenovationSuccess() {
        super(CardType.RenovationSuccess, true);
    }
    
    @Override
    public void onDraw(Player pl) {
        new MessageDisplayer("You picked RenovationSuccess Card.");
        pl.addToCardInventory(CardType.RenovationSuccess);
    }
    
    @Override
    public String toString() {
        return "Renovation Success";
    }
    
    @Override
    public String getName() {
        return "Renovation Success";
    }
}
