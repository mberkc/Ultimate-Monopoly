package GameSquares.Cards.CommunityChestCards;

import gui.AdditionalWindows.MessageDisplayer;
import gui.Board.RollingTheDice;
import java.io.Serializable;
import GameSquares.Cards.CommunityChestCard;
import Main.Player;

public class OnlinePricing extends CommunityChestCard implements Serializable {
    private static final long serialVersionUID = 1L;
    
    public OnlinePricing() {
        super(CardType.OnlinePricing, true);
    }
    
    @Override
    public void onDraw(Player pl) {
        new MessageDisplayer(
            "You picked Special Online Pricing Card. Next time you land on anyone else's railroad only pay 1/2 the rent.");
        RollingTheDice.logContinue("OnlinePricing Card.");
        pl.addToCardInventory(CardType.OnlinePricing);
    }
    
    @Override
    public String toString() {
        return "Online Pricing";
    }
    
    @Override
    public String getName() {
        return "Online Pricing";
    }
    
}
