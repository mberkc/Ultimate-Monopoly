package GameSquares.Cards.ChanceCards;

import gui.AdditionalWindows.MessageDisplayer;
import java.io.Serializable;
import GameSquares.Cards.ChanceCard;
import Main.Player;
import Main.Properties;

public class MargiGras extends ChanceCard implements Serializable {
    private static final long serialVersionUID = 1L;
    
    public MargiGras() {
        super(CardType.MargiGras, false);
    }
    
    @Override
    public void onDraw(Player pl) {
        new MessageDisplayer("You picked Margi Gras card. Every player advance to Canal Street.");
        Player[] players = Main.Main.players;
        for (int i = 0; i < players.length; i++) {
            if ((players[i].getLocation() != Properties.HEAVEN_ID)
                || !players[i].isJailed())
                players[i].moveTo(49);
        }
    }
    
    @Override
    public String toString() {
        return "Margi Gras";
    }
    
    @Override
    public String getName() {
        return "Margi Gras";
    }
}
