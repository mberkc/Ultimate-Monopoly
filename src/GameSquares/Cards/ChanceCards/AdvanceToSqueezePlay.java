package GameSquares.Cards.ChanceCards;

import gui.AdditionalWindows.MessageDisplayer;
import java.io.Serializable;
import GameSquares.Cards.ChanceCard;
import Main.Player;

public class AdvanceToSqueezePlay extends ChanceCard implements Serializable {
    private static final long serialVersionUID = 1L;
    
    public AdvanceToSqueezePlay() {
        super(CardType.AdvanceToSqueezePlay, false);
    }
    
    @Override
    public void onDraw(Player pl) {
        new MessageDisplayer("You picked AdvanceToSqueezePlay Card.");
        pl.moveTo(96);
    }
    
    @Override
    public String toString() {
        return "Advance to SqueezePlay";
    }
    
    @Override
    public String getName() {
        return "Advance to SqueezePlay";
    }
}
