package GameSquares.Cards.ChanceCards;

import gui.AdditionalWindows.MessageDisplayer;
import gui.AdditionalWindows.InputReaders.GetTextInput;
import gui.Board.RollingTheDice;
import java.io.Serializable;
import GameSquares.Cards.ChanceCard;
import Main.Player;

public class BusTicket extends ChanceCard implements Serializable {
    private static final long serialVersionUID = 1L;
    private int               sideStart, sideEnd, moveTo;
    private Integer           trackStart;
    
    public BusTicket() {
        super(CardType.BusTicket, false);
    }
    
    @Override
    public void onDraw(Player pl) {
        new MessageDisplayer("You picked BusTicket Card.");
        RollingTheDice.logContinue("Bus Ticket.");
        
        for (int i = 0; i < pl.getCardsInventory().size(); i++) {
            if (pl.getCardsInventory().get(i).equals(CardType.GetOutOfJail)) {
                pl.getCardsInventory().remove(i);
                i--;
            }
        }
        trackStart = null;
        if (pl.getLocation() == 7) {
            sideStart = 0;
            sideEnd = 10;
        } else if (pl.getLocation() == 22) {
            sideStart = 20;
            sideEnd = 30;
        } else if (pl.getLocation() == 36) {
            sideStart = 30;
            sideEnd = 39;
            trackStart = 0;
        } else if (pl.getLocation() == 50) {
            sideStart = 40;
            sideEnd = 54;
        } else if (pl.getLocation() == 61) {
            sideStart = 54;
            sideEnd = 68;
        } else if (pl.getLocation() == 70) {
            sideStart = 68;
            sideEnd = 82;
        } else if (pl.getLocation() == 94) {
            sideStart = 82;
            sideEnd = 95;
            trackStart = 40;
        } else if (pl.getLocation() == 112) {
            sideStart = 108;
            sideEnd = 114;
        }
        if (trackStart == null) {
            moveTo = -1;
            while (!(moveTo >= sideStart && moveTo <= sideEnd))
                moveTo = new GetTextInput("You are at Square " + pl.getLocation()
                    + ". You can go to Square " + sideStart + "-" + sideEnd).getInt();
        } else {
            moveTo = -1;
            while (!(moveTo >= sideStart && moveTo <= sideEnd) && moveTo != trackStart)
                moveTo = new GetTextInput("You are at Square " + pl.getLocation()
                    + ". You can go to Square " + sideStart + "-" + sideEnd + " and " + trackStart).getInt();
        }
        pl.moveTo(moveTo);
    }
    
    public String toString() {
        return "Bus Ticket";
    }
    
    @Override
    public String getName() {
        return "Bus Ticket";
    }
    
}
