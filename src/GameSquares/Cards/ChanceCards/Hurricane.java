package GameSquares.Cards.ChanceCards;

import gui.AdditionalWindows.MessageDisplayer;
import java.io.Serializable;
import java.util.ArrayList;
import GameSquares.Land;
import GameSquares.Cards.ChanceCard;
import Main.Player;

public class Hurricane extends ChanceCard implements Serializable {
    
    private static final long serialVersionUID = 1L;
    public boolean testing = false;
    public Hurricane() {
        super(CardType.Hurricane, false);
    }
    @Override
    public void onDraw(Player pl) {
    	
    	if(!testing){
    		new MessageDisplayer("You picked hurricane chance card, a players color grouping is downgraded");

    		for (int p = 0; p < Main.Main.players.length; p++) {
    			Player temp = Main.Main.players[p];
    			ArrayList<Land> lands = temp.getOwnedLands();

    			for (Land currentLand : lands) {
    				if (currentLand.majorityOwnership()) {
    					System.out.println(currentLand.getName() + " is now " + currentLand.getRent());
    					currentLand.downgrade();
    					System.out.println(currentLand.getRent());
    				}
    			}
    		}
    	} else{
    		ArrayList<Land> lands = pl.getOwnedLands();
    		
    		for (Land currentLand : lands) {
				if (currentLand.majorityOwnership()) {
					System.out.println(currentLand.getName() + " is now " + currentLand.getRent());
					currentLand.downgrade();
					System.out.println(currentLand.getRent());
				}
			}
    	}
    	
    }
    
    @Override
    public String toString() {
        return "Hurricane";
    }
    
    @Override
    public String getName() {
        return "Hurricane";
    }
}
