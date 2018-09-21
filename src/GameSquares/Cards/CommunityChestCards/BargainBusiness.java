package GameSquares.Cards.CommunityChestCards;

import gui.AdditionalWindows.MessageDisplayer;
import java.io.Serializable;
import GameSquares.Cards.CommunityChestCard;
import Main.Player;

public class BargainBusiness extends CommunityChestCard implements Serializable {
    private static final long serialVersionUID = 1L;
    
    public BargainBusiness() {
        super(CardType.BargainBusiness, false);
    }
    
    @Override
    public void onDraw(Player pl) {
        new MessageDisplayer("You picked BargainBusiness Card.");
        pl.addToCardInventory(CardType.BargainBusiness);
    }
    
    @Override
    public String toString() {
        return  "Bargain Business";
    }
    
    @Override
    public String getName() {
        return "Bargain Business";
    }
}
