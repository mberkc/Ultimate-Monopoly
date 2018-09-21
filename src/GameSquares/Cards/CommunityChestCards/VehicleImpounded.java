package GameSquares.Cards.CommunityChestCards;

import gui.AdditionalWindows.MessageDisplayer;
import gui.Board.RollingTheDice;
import java.io.Serializable;
import GameSquares.Cards.CommunityChestCard;
import Main.Player;

public class VehicleImpounded extends CommunityChestCard implements Serializable {
    private static final long serialVersionUID = 1L;
    
    public VehicleImpounded() {
        super(CardType.VehicleImpounded, false);
    }
    
    @Override
    public void onDraw(Player pl) {
        new MessageDisplayer("You picked VehicleImpounded Card.");
        RollingTheDice.logContinue("VehicleImpounded Card.");
        pl.payToPool(50);
        pl.moveTo(20);
        pl.LoseTurn(true);
        RollingTheDice.logAdd("Player:" + pl.getName() + " will lose next turn.");
    }
    
    public String toString() {
        return "Vehicle Impounded";
    }
    
    @Override
    public String getName() {
        return "Vehicle Impounded";
    }
    
}
